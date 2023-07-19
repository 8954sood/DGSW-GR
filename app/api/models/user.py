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

