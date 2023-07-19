from fastapi import APIRouter, Depends, Path, Request
from api.models import UserSchema

from sqlalchemy import create_engine, Column, Integer, String, select
from sqlalchemy.orm import sessionmaker, Session, DeclarativeBase, Mapped, mapped_column
from sqlalchemy.ext.asyncio import create_async_engine, async_sessionmaker, AsyncSession

from .schema import CreateUserRequest, CreateUserResponse
from .usecase import ReadAllUser, CreateUser
import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from db import get_session

router = APIRouter(prefix="/user")

@router.get("/all")
async def read_all(
    session: AsyncSession= Depends(get_session)
):
    usecase = ReadAllUser(session)
    users = await usecase.execute()
    # users = [user async for user in usecase.execute()]
    return {"users": users}
    return ""
    ""

@router.post("/create")
async def create(
    data: CreateUserRequest,
    session: AsyncSession= Depends(get_session)
):
    usecase = CreateUser(session)
    await usecase.execute(data.login_id, data.password, data.name, data.grade, data.class_id, data.number)
    return {"result": "success"}
    ""
