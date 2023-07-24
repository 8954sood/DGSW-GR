

from typing import Any, AsyncIterator
import sys
import os
# sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from db import AsyncSession
from sqlalchemy import String, select
from api.models import UserSchema, UserTable
from .schema import UpdateUserRequest
# from sqlalchemy.ext.asyncio import create_async_engine, async_sessionmaker, AsyncSession
from fastapi import HTTPException

class CreateUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self, login_id: str, password: str, name: str, grade: int, class_id: int, number: int) -> UserSchema:
        async with self.async_session() as session:
            # _user = await UserTable.create(session, login_id=login_id, password=password, name=name, grade=grade, class_id=class_id, number=number)
            # return UserSchema.model_validate(_user)
            _user = UserTable(login_id=login_id, password=password, name=name, grade=grade, class_id=class_id, number=number)
            session.add(_user)
            try:
            # s = await session.flush()
                await session.commit()
            except:
                raise HTTPException(404, detail="user that already exists")
            results = (await session.execute(select(UserTable).filter(UserTable.login_id == login_id and UserTable.password == password and UserTable == name))).scalars().first()

            return results

    
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
class ReadByLoginUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self, login_id: str, password: str) -> dict: #AsyncIterator[]:
        async with self.async_session() as session:
            _user = (await session.execute(select(UserTable).filter(UserTable.login_id == login_id and UserTable.password == password))).scalars().first()
            if not _user:
                raise HTTPException(status_code=404, detail="not found user")
            return _user
class ReadByIdUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self, id: int) -> dict: #AsyncIterator[]:
        async with self.async_session() as session:
            _user = (await session.execute(select(UserTable).filter(UserTable.id == id))).scalars().first()
            if not _user:
                raise HTTPException(status_code=404, detail="not found user")
            return _user
class ReadByLoginIdUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self, login_id: int) -> dict: #AsyncIterator[]:
        async with self.async_session() as session:
            _user = (await session.execute(select(UserTable).filter(UserTable.login_id == login_id))).scalars().first()
            if _user:
                raise HTTPException(status_code=404, detail="that's duplication id")
            return

class DeleteUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self, id: int) -> bool:
        async with self.async_session() as session:
            _user = (await session.execute(select(UserTable).filter(UserTable.id == id))).scalars().first()
            if _user:
                await session.delete(_user)
                await session.commit()
                return True
            else:
                return False
class UpdateUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self, user: UserSchema) -> bool:
        async with self.async_session() as session:
            _user = (await session.execute(select(UserTable).filter(UserTable.id == user.id))).scalars().first()
            if _user:
                # _user.id = user.id
                _user.class_id = user.class_id
                _user.grade = user.grade
                _user.nubmer = user.number
                _user.password = user.password
                _user.login_id = user.login_id
                session.add(_user)
                await session.commit()
                return True
            else:
                return False