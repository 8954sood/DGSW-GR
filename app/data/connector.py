from sqlalchemy.ext.asyncio import async_sessionmaker, AsyncSession
from data.db import session, async_session_maker
from data.model.user import User, UserTable, InputUser
from sqlalchemy import select
from data.db import ENGINE

class Connector:
    def __init__(self) -> None:
        pass
    
    @staticmethod
    async def insert_object(async_session: async_sessionmaker[AsyncSession], data: object) -> bool:
        try:
            async with async_session() as session:
                async with session.begin():
                    session.add(data)
            return True
        except:
            return False
    @staticmethod
    async def delete_object(async_session: async_sessionmaker[AsyncSession], data: object) -> bool:
        try:
            async with async_session() as session:
                async with session.begin():
                    await session.delete(data)
            return True
        except:
            return False
    
    @staticmethod
    async def execute(query: object) -> any:
        try:
            async with ENGINE.begin() as conn:
                result = await conn.execute(query)
            return result
        except:
            return False
    @staticmethod
    async def executes(query: object) -> any:
        try:
            async with ENGINE.begin() as conn:
                result = await conn.execute(query)
                ㅊ
            return result
        except:
            return False
    
     