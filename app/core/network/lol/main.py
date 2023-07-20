from ..base import BaseNetwork
from .response import searchUserResponse
from core.utiles.error import errorResponse
from fastapi import HTTPException

class LoLRequest(BaseNetwork):
    def __init__(self) -> None:
        super().__init__()
    
    async def searchSummoner(self, name: str) -> dict:
        url = f"https://b2c-api-cdn.deeplol.gg/summoner/summoner?summoner_name={name}&platform_id=KR"
        data, status = await self.httpGetRequests(url)
        if status == 404:
            return errorResponse(404, "not found user")

        return searchUserResponse(data)