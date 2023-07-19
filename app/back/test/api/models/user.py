from __future__ import annotations

from sqlalchemy.orm import Mapped, mapped_column, relationship, selectinload
from sqlalchemy import String, select
from typing import TYPE_CHECKING, AsyncIterator
from sqlalchemy.ext.asyncio import AsyncSession

from .base import Base

class UserTable(Base):
    __tablename__ = "users"
    id: Mapped[int] = mapped_column(primary_key=True, autoincrement=True)
    username: Mapped[str] = mapped_column(unique=True)

    @classmethod
    async def read_all(cls, session: AsyncSession) -> AsyncIterator[UserTable]:
        query = select(cls)
        return await session.scalars(query)
    
    @classmethod
    async def create(cls, session: AsyncSession, username: str) -> UserTable:
        _user = UserTable(username=username)
        session.add(_user)
        await session.flush()
        return
