from fastapi import APIRouter, Depends, Path, Request, HTTPException

from sqlalchemy.ext.asyncio import create_async_engine, async_sessionmaker, AsyncSession

# Schema
from .schema import (
    SearchRequest, 
    TokenRequest,
    CreateUserRequest,
    EditUserRequest,
    UpdateUserRequest
)
from models import LOLSchema

# Core
from core.network.lol.main import LoLRequest

# Jwt
from models import JWTCheck, auth as jwt
from db import get_session

# usecase
from .usecase import CreateUser, RankUserList, ReadByIdUser, UpdateUser, ReadByNicknameLOLANDUserTable

router = APIRouter(prefix="/lol")
network = LoLRequest()
@router.post("/search", tags=["lol"])
async def searchUser(
    request: Request,
    data: SearchRequest
):
    data.name = data.name.replace(" ", "")
    if data.name == "":
        raise HTTPException(status_code=404, detail="not found")
    return await network.searchSummoner(data.name)
@router.post("/update", tags=["lol"])
async def updateUser(
    request: Request,
    data: UpdateUserRequest,
    session: AsyncSession= Depends(get_session)
):
    """
    이미 등록된 유저의 롤 정보를 업데이트 하기 위한 리퀘스트입니다.
    누구나 유저 정보 업데이트는 가능하기에 대상의 id(닉네임)을 넣으면 정보를 업데이트할 수 있습니다.
    """

    #https://renew.deeplol.gg/match/matches 에 post 보내야함, referer 똑바로 안보내면 빠구먹음
    usecase = ReadByIdUser(session)
    # user_id = await JWTCheck.accessToken(jwt, data.token)
    user = await usecase.execute(data.id)
    await LoLRequest().updateSummoner(user.nickname, user.puu_id)
    _user = await network.searchSummoner(user.nickname)
    _user = LOLSchema(id=user.id, user_id=user.user_id ,**_user)
    usecase = UpdateUser(session)
    await usecase.execute(_user)

    # await usecase.execute(data.id, _user["name"], _user["tier_str"], _user["tier_int"], _user["level"], _user["profile_id"], _user["profile_icon"], _user["puu_id"])

    return {"result": True}
    ""
@router.put("/edit", tags=["lol"])
async def editUser(

):
    ""
    
@router.post("/create", tags=["lol"])
async def createUser(
    request: Request,
    data: CreateUserRequest,
    session: AsyncSession= Depends(get_session)
):
    """
    롤 유저의 닉네임과 토큰 필요
    """
    usecase = CreateUser(session)

    id = await JWTCheck.accessToken(jwt, data.token)
    _user = await network.searchSummoner(data.name)
    
    await usecase.execute(id, _user["nickname"], _user["tier_str"], _user["tier_int"], _user["level"], _user["profile_id"], _user["profile_icon"], _user["puu_id"])
    return {"result": "success"}
    

@router.delete("/delete", tags=["lol"])
async def deleteUser(
    request: Request
):
    ""
@router.get("/rank", tags=["lol"])
async def rankUser(
    request: Request,
    category: str,
    session: AsyncSession= Depends(get_session)
):
    """
    내생각에 rank도 레벨, 티어순 정렬 2개 필요할듯
    """
    usecase = RankUserList(session)
    if not category in {"level", "tier"}:
        raise HTTPException(404, "that's not correct category")
    result = await usecase.execute("test")
    items = []
    for i in result:
        data = i.__dict__
        del data['_sa_instance_state']
        del data['user_id'] # 보안상 노출되면 안되는 것
        items.append(data)
    return items[::-1]
    
    
    

@router.get("/info", tags=["lol"])
async def infoUser(
    request: Request,
    user: int,
    session: AsyncSession= Depends(get_session)
):
    
    ""