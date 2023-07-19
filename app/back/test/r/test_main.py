from __future__ import annotations

from fastapi import FastAPI, Depends
from pydantic import BaseModel
from sqlalchemy import create_engine, Column, Integer, String, select
from sqlalchemy.orm import sessionmaker, Session, DeclarativeBase, Mapped, mapped_column
from sqlalchemy.ext.asyncio import create_async_engine, async_sessionmaker, AsyncSession

from usecase import *
from schema import CreateUserRequest, CreateUserResponse
from typing import TYPE_CHECKING, AsyncIterator
# SQLALCHEMY 
# engine = create_async_engine("sqlite+aiosqlite:///db.sqlite3", connect_args={"check_same_thread": False})
# SessionLocal = async_sessionmaker(engine)

# class Base(DeclarativeBase):
#     pass



# async def get_db():
#     async with engine.begin() as conn:
#         await conn.run_sync(Base.metadata.create_all)

#     db = SessionLocal()
#     try:
#         yield db
#     finally:
#         await db.close()


# PYDANTIC


# FASTAPI
app = FastAPI(debug=True)

@app.get("/")
def tst():
    return ""
# @app.get("")
# async def read_all(
#     usecase: ReadAllUser = Depends(ReadAllUser)
# ):
#     users = [user async for user in usecase.execute()]
#     return {"users": users}

# @app.post("/signup", response_class=CreateUserResponse)
# async def create(
#     data: CreateUserRequest,
#     usecase: CreateUser= Depends(CreateUser)
# ) -> UserSchema:
#     return await usecase.execute(data.username)

@app.post("/user")
async def index(user: UserBase, db: CreateUser = Depends(CreateUser)) -> str:
    # db_user = User(username=user.username)
    # db.add(db_user)
    # await db.commit()
    # await db.refresh(db_user)
    return ""

@app.get("/user")
async def get_users(db: AsyncSession = Depends()):
    results = await db.execute(select(User))
    users = results.scalars().all()
    return {"users": users}
if __name__ == "__main__":
    import uvicorn
    uvicorn.run("test_main:app", host="0.0.0.0", port=8001, reload=True)
