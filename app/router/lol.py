from fastapi import APIRouter
from module import Response, Summoner, LolNetwork
lol = APIRouter(prefix="/lol")

@lol.on_event("startup")
async def startLol():
    print("d")
    

@lol.post(f"/summoner", status_code=201, tags=['Lol'])
async def postLolSummoner(data: Summoner):
    
    # if data.name == "error":
    #     raise HTTPException(status_code=405, detail="이름이 에러입니다.")
    return Response.errorResponse(403, data.name)
@lol.get(f"/rank", status_code=200, tags=['Lol'])
async def getLolRank():
    data = await LolNetwork.searchSummoner("바비호바")
    return data