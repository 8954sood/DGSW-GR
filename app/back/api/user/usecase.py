from typing import AsyncIterator
import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from db import AsyncSession
from api.models import UserTable, UserSchema

class CreateUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self, login_id: str, password: str, name: str, grade: int, class_id: int, number: int) -> UserSchema:
        async with self.async_session() as session:
            _user = await UserTable.create(session, login_id=login_id, password=password, name=name, grade=grade, class_id=class_id, number=number)
            return UserSchema.model_validate(_user)

class ReadAllUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self) -> AsyncIterator[UserSchema]:
        async with self.async_session() as session:
            async for _user in UserTable.read_all(session, include_notes=True):
                yield UserSchema.model_validate(_user)

