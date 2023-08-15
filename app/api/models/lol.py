from __future__ import annotations

from sqlalchemy.orm import Mapped, mapped_column, relationship, selectinload
from sqlalchemy import String, select
from typing import TYPE_CHECKING, AsyncIterator
from sqlalchemy.ext.asyncio import AsyncSession

from .base import Base

class LOLTable(Base):
    __tablename__ = "lol"
    id: Mapped[int] = mapped_column(primary_key=True, autoincrement=True)
    user_id: Mapped[int] = mapped_column(nullable=False, unique=True)
    student: Mapped[int] = mapped_column(nullable=False)
    icon: Mapped[str] = mapped_column(nullable=False)
    level: Mapped[int] = mapped_column(nullable=False)
    name: Mapped[str] = mapped_column(nullable=False)
    most: Mapped[str] = mapped_column(nullable=False)
    kda: Mapped[str] = mapped_column(nullable=False)
    tier_str: Mapped[str] = mapped_column(nullable=False)
    tier_point: Mapped[str] = mapped_column(nullable=False)
    tier_int: Mapped[int] = mapped_column(nullable=False)
    tier_icon: Mapped[str] = mapped_column(nullable=False)
    win_lose: Mapped[str] = mapped_column(nullable=False)
    win_rate: Mapped[str] = mapped_column(nullable=False)