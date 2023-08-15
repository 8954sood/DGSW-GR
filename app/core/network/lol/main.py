from ..base import BaseNetwork
from .response import searchUserResponse, lol_parser
from core.utiles.error import errorResponse
from fastapi import HTTPException
from bs4 import BeautifulSoup

class LoLRequest(BaseNetwork):
    def __init__(self) -> None:
        super().__init__()
    
    async def searchSummoner(self, name: str) -> dict:
        # url = f"https://b2c-api-cdn.deeplol.gg/summoner/summoner?summoner_name={name}&platform_id=KR"
        # data, status = await self.httpGetRequests(url)
        # if status == 404:
        #     return errorResponse(404, "not found user")

        # return searchUserResponse(data)
        url = f"https://www.op.gg/summoner/userName={name}"
        data, status = await self.httpGetRequests(url)
        if status == 404:
            return errorResponse(404, "not found user")
        result = lol_parser(BeautifulSoup(data, 'html.parser'))
        if result == False:
            raise HTTPException(404, "not found user")
        return result
        
    
    async def updateSummoner(self, name: str, puu_id: str) -> bool:
        url = "https://renew.deeplol.gg/match/matches"
        body = {
            "platform_id": "KR",
            "puu_id": puu_id,
            "season": 17,
            "queue_type": "ALL",
            "start_idx": 0,
            "count": 20
        }
        headers = {"Referer": f"https://www.deeplol.gg/summoner/KR/{name}"}
        
        data, status = await self.httpPostRequests(url, body, headers)
        if status == 404:
            return errorResponse(404, "not found user")

        return True
