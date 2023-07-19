from __future__ import annotations

from sqlalchemy.orm import Mapped, mapped_column, relationship, selectinload
from sqlalchemy import String, select
from typing import TYPE_CHECKING, AsyncIterator
from sqlalchemy.ext.asyncio import AsyncSession

from .base import Base

class UserTable(Base):
    __tablename__ = "users"
    id: Mapped[int] = mapped_column(primary_key=True, autoincrement=True)
    login_id: Mapped[str] = mapped_column(nullable=False)
    password: Mapped[str] = mapped_column(nullable=False)
    name: Mapped[str] = mapped_column( nullable=False)
    grade: Mapped[int] = mapped_column(nullable=False)
    class_id: Mapped[int] = mapped_column(nullable=False)
    number: Mapped[int] = mapped_column(nullable=False)

    @classmethod
    async def read_all(cls, session: AsyncSession) -> AsyncIterator[UserTable]:
        query = select(cls)
        return await session.scalars(query)
    @classmethod
    async def create(cls, session: AsyncSession, login_id: str, password: str, name: str, grade: int, class_id: int, number: int) -> UserTable:
        _user = UserTable(login_id=login_id, password=password, name=name, grade=grade, class_id=class_id, number=number)
        session.add(_user)
        await session.flush()
        return
