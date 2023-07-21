from typing import Any, AsyncIterator
import sys
import os
# sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from db import AsyncSession
# from api.models import UserTable, UserSchema
from base import Base, User, UserBase
from sqlalchemy.ext.asyncio import create_async_engine, async_sessionmaker, AsyncSession

class CreateUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def __call__(self, username: str) -> UserBase:
        async with self.async_session() as session:
            _user = await User.create(session, username=username)
            return UserBase.model_validate(_user)

class ReadAllUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self) -> AsyncIterator[UserBase]:
        async with self.async_session() as session:
            async for _user in User.read_all(session, include_notes=True):
                yield UserBase.model_validate(_user)


