from fastapi import APIRouter, Depends, Path, Request
from api.models import UserSchema

from sqlalchemy import create_engine, Column, Integer, String, select
from sqlalchemy.orm import sessionmaker, Session, DeclarativeBase, Mapped, mapped_column
from sqlalchemy.ext.asyncio import create_async_engine, async_sessionmaker, AsyncSession
from starlette.responses import JSONResponse

from .schema import SearchRequest
# from .usecase import ReadAllUser, CreateUser
from core.network.lol.main import LoLRequest
import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from db import get_session

router = APIRouter(prefix="/lol")

@router.post("/search")
async def searchUser(data: SearchRequest):
    data.name = data.name.replace(" ", "")
    if data.name == "":
        return JSONResponse(status_code=404, content="not found")
    requests = LoLRequest()
    return await requests.searchSummoner(data.name)
    