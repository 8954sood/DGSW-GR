from __future__ import annotations
from sqlalchemy import MetaData

from pydantic import BaseModel, ConfigDict
from sqlalchemy import create_engine, Column, Integer, String, select
from sqlalchemy.orm import sessionmaker, Session, DeclarativeBase, Mapped, mapped_column
from sqlalchemy.ext.asyncio import create_async_engine, async_sessionmaker, AsyncSession

from typing import TYPE_CHECKING, AsyncIterator

convention = {
    "ix": "ix_%(column_0_label)s",
    "uq": "uq_%(table_name)s_%(column_0_name)s",
    "ck": "ck_%(table_name)s_%(constraint_name)s",
    "fk": "fk_%(table_name)s_%(column_0_name)s_%(referred_table_name)s",
    "pk": "pk_%(table_name)s",
}
class Base(DeclarativeBase):
    __abstract__ = True
    metadata = MetaData(naming_convention=convention)

    def __repr__(self) -> str:
        columns = ", ".join(
            [f"{k}={repr(v)}" for k, v in self.__dict__.items() if not k.startswith("_")]
        )
        return f"<{self.__class__.__name__}({columns})>"

class User(Base):
    __tablename__ = "users"
    id: Mapped[int] = mapped_column(primary_key=True)
    username: Mapped[str] = mapped_column(unique=True)
    @classmethod
    async def read_all(cls, session: AsyncSession) -> AsyncIterator[User]:
        query = select(cls)
        return await session.scalars(query)
    
    @classmethod
    async def create(cls, session: AsyncSession, username: str) -> User:
        _user = User(username=username)
        session.add(_user)
        await session.flush()
        return
    

class UserBase(BaseModel):
    username: str

    model_config = ConfigDict(from_attributes=True)