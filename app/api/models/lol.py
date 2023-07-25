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
    nickname: Mapped[str] = mapped_column(nullable=False, unique=True)
    tier_str: Mapped[str] = mapped_column(nullable=False)
    tier_int: Mapped[int] = mapped_column(nullable=False)
    level: Mapped[int] = mapped_column(nullable=False)
    profile_id: Mapped[int] = mapped_column(nullable=False)
    profile_icon: Mapped[str] = mapped_column(nullable=False)
    puu_id: Mapped[str] = mapped_column(nullable=False)

