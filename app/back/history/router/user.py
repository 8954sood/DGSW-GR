from fastapi import APIRouter
from sqlalchemy.ext.asyncio import async_sessionmaker, AsyncSession
from data.db import session, async_session_maker
from data.model.user import User, UserTable, InputUser, LoginUser, Userrr
from sqlalchemy import select
from data.db import ENGINE
from data.connector import Connector
user = APIRouter(prefix="/user")

async def print_all_names():
    async with ENGINE.begin() as session:

        
        result = await Userrr.read_by_id(session, 1, True)
    print(result.name)
    # query = select(Userrr)
    #     result = await session.execute(query)
    # result = await Connector.execute(query)
    # users = result.scalars().all()
    # print(users)
    # for user in users:
    #     print(user)
    #     print(user.name)
async def insert_names(name:str):
    # async with async_session_maker() as session:
    query = Userrr()
    query.name = name
    await Connector.insert_object(async_sessionmaker(ENGINE), query)

@user.post("/testmake", status_code=200, tags=["user"])
async def insert_name(name:str):
    await insert_names(name)
    return None
@user.get("/testlist", status_code=200, tags=["user"])
async def getList():
    await print_all_names()
    return
@user.get("/list", status_code=200, tags=["user"])
async def getUsers():
    query = select(UserTable)#.where(UserTable.id < 10).limit(30)
    # # users = session.query(UserTable).all()
    # result = await session.execute(query)
    # return result.scalars.all()
    # print(await Connector.get_object(async_sessionmaker(ENGINE), query))
    async with ENGINE.begin() as conn:
        result = await conn.execute(query)
        # print(result.scalar_one_or_none().name)
        
        for i in result.scalars().all():
            print("발언")
            if hasattr(i, "name"):
                print(i.name)
        print("132132121312")
        print(result.all())
        print(list(result.all()))
        # print(result.fe)
        
    # print((await Connector.execute(query)).fetchall())
    result = await Connector.execute(query)
    
    # print(type(result.fetchall()[0]))
    # dicts = dict(result)
    # print(result.scalars())
    # print(dicts.keys())
    # print(dicts['_row_getter'])
    # for i, j in dicts['_row_getter'].items():
    #     print(i, j)
    # print(dicts['_row_getter'])   
    
    print(result.scalars().all())
    # print(result.mappings().all())

    return str(result.fetchall())#.scalars().all()

@user.post("/singup", status_code=200, tags=["user"])
async def postSingUpUser(data: InputUser):
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
@user.post("/login", status_code=200, tags=["user"])
async def postLoginUser(data: LoginUser):
    return
