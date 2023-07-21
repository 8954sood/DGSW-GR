#fastapi
from fastapi import APIRouter, Depends, Path, Request

#DB
from sqlalchemy import create_engine, Column, Integer, String, select
from sqlalchemy.orm import sessionmaker, Session, DeclarativeBase, Mapped, mapped_column
from sqlalchemy.ext.asyncio import create_async_engine, async_sessionmaker, AsyncSession

#Usecase, Schema
# from api.models import Settings
from .schema import (
    CreateUserRequest, 
    CreateUserResponse, 
    DeleteUserRequest, 
    UpdateUserRequest,
    LoginUserRequest
)
from .usecase import ReadAllUser, CreateUser, DeleteUser, UpdateUser, ReadByLoginUser

#Load Jwt
# from async_fastapi_jwt_auth import AuthJWT
# from async_fastapi_jwt_auth.exceptions import AuthJWTException
from models import auth as jwt

#ErrorResponse
from starlette.responses import JSONResponse

#db session
import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from db import get_session

router = APIRouter(prefix="/user")

@router.get("/all", tags=["user"])
async def read_all(
    request: Request,
    session: AsyncSession= Depends(get_session)
):
    usecase = ReadAllUser(session)
    users = await usecase.execute()
    # users = [user async for user in usecase.execute()]
    return {"users": users}
    return ""
    ""

@router.post("/create", tags=["user"])
async def create(
    request: Request,
    data: CreateUserRequest,
    session: AsyncSession= Depends(get_session)
):
    usecase = CreateUser(session)
    result = await usecase.execute(data.login_id, data.password, data.name, data.grade, data.class_id, data.number)
    print(result.login_id)
    return {"result": "success"}
    ""
@router.delete("/delete", tags=["user"])
async def delete(
    request: Request,
    data: DeleteUserRequest,
    session: AsyncSession= Depends(get_session)
) -> dict:
    usecase = DeleteUser(session)
    result = await usecase.execute(data.id)
    return {"result": result}
@router.put("/update", tags=["user"])
async def update(
    request: Request,
    data: UpdateUserRequest,
    session: AsyncSession= Depends(get_session)
) -> dict:
    usecase = UpdateUser(session)
    result = await usecase.execute(data)
    return {"result": result}

@router.post("/login", tags=["user"])
async def login(
    request: Request,
    data: LoginUserRequest,
    session: AsyncSession= Depends(get_session)
) -> dict:
    usecase = ReadByLoginUser(session)
    result = await usecase.execute(data.login_id, data.password)
    refresh_token = await jwt.async_create_refresh_token(result)
    access_token = await jwt.async_create_access_token(result)
    return {"refresh_token": refresh_token, "access_token": access_token}
    
    ""
@router.post("/test", tags=["user"])
async def tokenInvaild(
    token: str
):
    
    result = await jwt.async_decode_token(token)# int 값 저장하면 int로 decode, str로 저장하면 str로 decode

    print(type(result))
    return result
# @AuthJWT.load_config
# def get_config():
#     return Settings()


# @router.post("/refresh", tags=["user"])
# async def refresh(
#     request: Request,
#     Authorize: AuthJWT = Depends()
# ):
#     await Authorize.jwt_refresh_token_required()
#     current_user = await Authorize.get_jwt_subject()
#     new_access_token = await Authorize.create_access_token(subject=current_user)
#     return {"access_token": new_access_token}
#     ""
# @router.get('/protected')
# async def protected(Authorize: AuthJWT = Depends()):
#     await Authorize.jwt_required()

#     current_user = await Authorize.get_jwt_subject()
#     return {"user": current_user}
