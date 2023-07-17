from fastapi import APIRouter
from data.db import session
from data.model.user import User, UserTable, InputUser
user = APIRouter(prefix="/user")

@user.get("/list", status_code=200, tags=["user"])
async def getUsers():
    users = session.query(UserTable).all()
    return users

@user.post("/create", status_code=200, tags=["user"])
async def postCreateUser(data: InputUser):
    _user = UserTable()
    _user.login_id = data.login_id
    _user.password = data.password
    _user.name = data.name
    _user.grade = data.grade
    _user.class_id = data.class_id
    _user.number = data.number
    session.add(_user)
    session.commit()
    return