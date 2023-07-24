from db import AsyncSession
from sqlalchemy import select
from models import LOLTable, LOLSchema

from fastapi import HTTPException
class CreateUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self, user_id: int, nickname: str, tier_str: str, tier_int: int, level: int, profile_id: int, profile_icon: str, puu_id: str):
        async with self.async_session() as session:
            _user = LOLTable(user_id= user_id, nickname=nickname, tier_str=tier_str, tier_int=tier_int, level=level, profile_id=profile_id, profile_icon= profile_icon, puu_id=puu_id)
            session.add(_user)
            try:
                await session.commit()
            except:
                raise HTTPException(404, detail="user that already exists")
            return True
class RankUserList:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self, category: str):
        async with self.async_session() as session:
            _query = select(LOLTable).order_by(LOLTable.tier_int)
            _user = (await session.execute(_query)).scalars().all()
            return _user