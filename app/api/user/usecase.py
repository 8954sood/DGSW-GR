

from typing import Any, AsyncIterator
import sys
import os
# sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from db import AsyncSession
from sqlalchemy import String, select
from api.models import UserSchema, UserTable
from sqlalchemy.ext.asyncio import create_async_engine, async_sessionmaker, AsyncSession

class CreateUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self, login_id: str, password: str, name: str, grade: int, class_id: int, number: int) -> UserSchema:
        async with self.async_session() as session:
            # _user = await UserTable.create(session, login_id=login_id, password=password, name=name, grade=grade, class_id=class_id, number=number)
            # return UserSchema.model_validate(_user)
            _user = UserTable(login_id=login_id, password=password, name=name, grade=grade, class_id=class_id, number=number)
            session.add(_user)
            s = await session.flush()
            await session.commit()
            print(s)
            return

class ReadAllUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self) -> dict: #AsyncIterator[]:
        async with self.async_session() as session:
            _query = select(UserTable)
            results = await session.execute(_query)

            # async for _user in session.scalars(_user):
            #     yield 
            return results.scalars().all()

            # async for _user in UserTable.read_all(session, include_notes=True):
            #     yield UserSchema.model_validate(_user)


