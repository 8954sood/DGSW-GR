from db import AsyncSession
from sqlalchemy import select
from models import LOLTable, LOLSchema, UserTable, UserSchema
from typing import Tuple
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
class ReadByIdUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self, id: int) -> LOLSchema:
        async with self.async_session() as session:
            _query = select(LOLTable).filter(LOLTable.id == id)
            _user = (await session.execute(_query)).scalars().first()
            if not _user:
                raise HTTPException(404, "not found User")
            # print(_user.__dict__)
            return LOLSchema(**_user.__dict__)

class ReadByNicknameLOLANDUserTable:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self, nickname: str) -> Tuple[UserSchema, LOLSchema]:
        async with self.async_session() as session:
            ""
            _query = select(LOLTable).filter(LOLTable.nickname == nickname)
            _lol = (await session.execute(_query)).scalars().first()
            if not _lol:
                raise HTTPException(404, "not found User")
            _lol= LOLSchema(**_lol.__dict__)
            _query = select(UserTable).filter(UserTable.id == _lol.user_id)
            _user = (await session.execute(_query)).scalars().first()
            if not _user:
                raise HTTPException(404, "not found User")
            _user = UserSchema(**_user.__dict__)
            return _user, _lol
            
class UpdateUser:
    def __init__(self, session: AsyncSession) -> None:
        self.async_session = session
    async def execute(self, user: LOLSchema) -> bool:
        async with self.async_session() as session:
            _user: LOLTable = (await session.execute(select(LOLTable).filter(LOLTable.id == user.id))).scalars().first()
            if _user:
                # _user.id = user.id
                _user.nickname = user.nickname
                _user.level = user.level
                _user.tier_int = user.tier_int
                _user.tier_str = user.tier_str
                _user.profile_icon = user.profile_icon
                _user.profile_id = user.profile_id
                session.add(_user)
                await session.commit()
                return True
            else:
                return False