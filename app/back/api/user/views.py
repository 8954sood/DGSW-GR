from fastapi import APIRouter, Depends, Path, Request
from .schema import (
    CreateUserRequest,
    CreateUserResponse,
    ReadAllUserResponse
)
from api.models import UserSchema
from .usecase import CreateUser, ReadAllUser
router = APIRouter(prefix="/user")

@router.get("/all", response_class=None)
async def read_all(
    usecase: ReadAllUser = Depends(ReadAllUser)
):
    users = [user async for user in usecase.execute()]
    return {"users": users}

@router.post("/signup", response_class=CreateUserResponse)
async def create(
    data: CreateUserRequest,
    usecase: CreateUser= Depends(CreateUser)
) -> UserSchema:
    return await usecase.execute(data.login_id, data.password, data.name, data.grade, data.class_id, data.number)