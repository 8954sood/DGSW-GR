from __future__ import annotations

from sqlalchemy import Column, ForeignKey
from sqlalchemy import Integer, String, select
from pydantic import BaseModel
from ..db import ENGINE, Base
from sqlalchemy.ext.asyncio import AsyncSession
from sqlalchemy.orm import Mapped, mapped_column, relationship, selectinload


class UserTable(Base):
    __tablename__ = "user"
    id = Column(Integer, primary_key=True, autoincrement=True)
    login_id = Column(String(20), nullable=False)
    password = Column(String(30), nullable=False)
    name = Column(String(5), nullable=False)
    grade = Column(Integer, nullable=False)
    class_id = Column(Integer, nullable=False)
    number = Column(Integer, nullable=False)
class Userrr(Base):
    __tablename__ = "userrr"
    id = Column(Integer, primary_key=True, autoincrement=True)
    name = Column(String(30), nullable=False)

    @classmethod
    async def read_by_id(
        cls, session: AsyncSession, notebook_id: int, include_notes: bool = False
    ) -> Userrr | None:
        stmt = select(cls).where(cls.id == notebook_id)
        # if include_notes:
        #     stmt = stmt.options(selectinload(cls.name))
        return await session.scalar(stmt.order_by(cls.id))

class User(BaseModel):
    id: int
    login_id: str
    password: str
    name: str
    grade: int
    class_id: int
    number: int
class InputUser(BaseModel):
    login_id: str
    password: str
    name: str
    grade: int
    class_id: int
    number: int
class LoginUser(BaseModel):
    login_id: str
    password: str



# async with ENGINE.begin() as conn:
#     await conn.run_sync()
    # await conn.run_sync(Base.metadata.create_all)
