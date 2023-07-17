from fastapi import APIRouter
from sqlalchemy.ext.asyncio import async_sessionmaker, AsyncSession
from data.db import session, async_session_maker
from data.model.user import User, UserTable, InputUser
from sqlalchemy import select
from data.db import ENGINE
from data.connector import Connector
user = APIRouter(prefix="/user")

@user.get("/list", status_code=200, tags=["user"])
async def getUsers():
    query = select(UserTable)#.where(UserTable.id < 10).limit(30)
    # # users = session.query(UserTable).all()
    # result = await session.execute(query)
    # return result.scalars.all()
    # async with ENGINE.begin() as conn:
    #     result = await conn.execute(query)
    # print((await Connector.execute(query)).fetchall())
    result = await Connector.execute(query)
    return str(result.fetchall())#.scalars().all()

@user.post("/create", status_code=200, tags=["user"])
async def postCreateUser(data: InputUser):
    _user = UserTable()
    _user.login_id = data.login_id
    _user.password = data.password
    _user.name = data.name
    _user.grade = data.grade
    _user.class_id = data.class_id
    _user.number = data.number
    await Connector.insert_object(async_sessionmaker(ENGINE), _user)
        # _session.commit()
    # async with ENGINE.begin() as conn:
    #     session.add(_user)
    #     result = await conn.commit()


    
    # await session.commit()
    return
