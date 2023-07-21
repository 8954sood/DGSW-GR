from __future__ import annotations

from fastapi import FastAPI, Depends
from pydantic import BaseModel
from sqlalchemy import create_engine, Column, Integer, String, select
from sqlalchemy.orm import sessionmaker, Session, DeclarativeBase, Mapped, mapped_column
from sqlalchemy.ext.asyncio import create_async_engine, async_sessionmaker, AsyncSession


from typing import TYPE_CHECKING, AsyncIterator
# SQLALCHEMY 
engine = create_async_engine("sqlite+aiosqlite:///db.sqlite3", connect_args={"check_same_thread": False})
SessionLocal = async_sessionmaker(engine)

class Base(DeclarativeBase):
    pass

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

async def get_db():
    async with engine.begin() as conn:
        await conn.run_sync(Base.metadata.create_all)

    db = SessionLocal()
    try:
        yield db
    finally:
        await db.close()


# PYDANTIC
class UserBase(BaseModel):
    username: str


# FASTAPI
app = FastAPI(debug=True)

@app.get("/")
def tst():
    return ""

@app.post("/user")
async def index(user: UserBase, db: AsyncSession = Depends(get_db)):
    db_user = User(username=user.username)
    db.add(db_user)
    await db.commit()
    await db.refresh(db_user)
    return db_user

@app.get("/user")
async def get_users(db: AsyncSession = Depends(get_db)):
    results = await db.execute(select(User))
    
    users = results.scalars().all()
    return {"users": users}
if __name__ == "__main__":
    import uvicorn
    uvicorn.run("test_main:app", host="0.0.0.0", port=8001, reload=True)
