#fastapi
from fastapi import APIRouter, Depends, Path, Request, HTTPException

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
    LoginUserRequest,
    RefreshTokenRequest,
    CheckDuplication,
    TokenRequest
)
from .usecase import ReadAllUser, CreateUser, DeleteUser, UpdateUser, ReadByLoginUser, ReadByIdUser, ReadByLoginIdUser

#Load Jwt
# from async_fastapi_jwt_auth import AuthJWT
# from async_fastapi_jwt_auth.exceptions import AuthJWTException
from models import auth as jwt
from models import JWTCheck

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
@router.post("/check", tags=["user"])
async def check(
    request: Request,
    data: CheckDuplication,
    session: AsyncSession= Depends(get_session)
):
    usecase = ReadByLoginIdUser(session)
    await usecase.execute(data.login_id)
    return {"result": False}
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

    '''
    프레쉬 토큰을 db에 저장하고, 그에따른 aceess token을 재발급 한다? = 
    현재 리프레쉬 토큰은 id.refresh로 만들어지기에 refresh token을 디코드 -> id값만 다시 token화 기'''
    return {"refresh_token": refresh_token, "access_token": access_token}
    
    ""
@router.post("/token", tags=["user"])
async def token(
    request: Request,
    data: RefreshTokenRequest,
    session: AsyncSession= Depends(get_session)
):
    result = await JWTCheck.refreshToken(jwt, data.refresh_token)
    result = int(result.split(".")[0]) # int 값

    usecase = ReadByIdUser(session)
    user = await usecase.execute(result) # 한번더 찾는 이유: 검증을 위해서
    
    access_token = await jwt.async_create_access_token(user)
    return access_token

@router.post("/info", tags=["user"])
async def info(
    request: Request,
    data: TokenRequest,
    session: AsyncSession= Depends(get_session)
):
    result = await JWTCheck.accessToken(jwt, data.token)
    usecase = ReadByIdUser(session)
    user = await usecase.execute(result) # 한번더 찾는 이유: 검증을 위해서
    return {"user": user}
# @router.get("/info", tags=["user"])
# # async def 
# @router.post("/test", tags=["user"])
# async def tokenInvaild(
#     token: str
# ):
    
#     result = await jwt.async_decode_token(token)# int 값 저장하면 int로 decode, str로 저장하면 str로 decode

#     print(type(result))
#     return result