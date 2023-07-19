import logging
from typing import Annotated, AsyncIterator

from fastapi import Depends
from sqlalchemy.exc import SQLAlchemyError
from sqlalchemy.ext.asyncio import async_sessionmaker, create_async_engine

logger = logging.getLogger(__name__)

DB_URL = "sqlite+aiosqlite:///./db.sqlite3"
engine = create_async_engine(
    DB_URL,
    echo=True
)

AsyncSessionLocal = async_sessionmaker(
    bind=engine,
    autoflush=False,
    future=True,
)


async def get_session() -> AsyncIterator[async_sessionmaker]:
    try:
        yield AsyncSessionLocal
    except SQLAlchemyError as e:
        logger.exception(e)


AsyncSession = Annotated[async_sessionmaker, Depends(get_session)]
