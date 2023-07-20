from fastapi import APIRouter, Depends, Path, Request
from api.models import UserSchema

from sqlalchemy import create_engine, Column, Integer, String, select
from sqlalchemy.orm import sessionmaker, Session, DeclarativeBase, Mapped, mapped_column
from sqlalchemy.ext.asyncio import create_async_engine, async_sessionmaker, AsyncSession

from .schema import CreateUserRequest, CreateUserResponse, DeleteUserRequest, UpdateUserRequest
from .usecase import ReadAllUser, CreateUser, DeleteUser, UpdateUser
# from core.main import r

import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from db import get_session

router = APIRouter(prefix="/user")

@router.get("/all", tags=["user"])
async def read_all(
    session: AsyncSession= Depends(get_session)
):
    usecase = ReadAllUser(session)
    users = await usecase.execute()
    # users = [user async for user in usecase.execute()]
    return {"users": users}
    return ""
    ""

@router.post("/create", tags=["user"])
async def create(
    data: CreateUserRequest,
    session: AsyncSession= Depends(get_session)
):
    usecase = CreateUser(session)
    await usecase.execute(data.login_id, data.password, data.name, data.grade, data.class_id, data.number)
    return {"result": "success"}
    ""
@router.delete("/delete", tags=["user"])
async def delete(
    data: DeleteUserRequest,
    session: AsyncSession= Depends(get_session)
) -> dict:
    usecase = DeleteUser(session)
    result = await usecase.execute(data.id)
    return {"result": result}
@router.put("/update", tags=["user"])
async def update(
    data: UpdateUserRequest,
    session: AsyncSession= Depends(get_session)
) -> dict:
    usecase = UpdateUser(session)
    result = await usecase.execute(data)
    return {"result": result}