import asyncio
import aiohttp
from typing import Tuple
from .NetworkModule import httpRequests
from ..error.Response import errorResponse
from ..response.LolResponse import searchUserResponse
LOL_PATH = "https://b2c-api-cdn.deeplol.gg/summoner/"

class LolNetwork:
    def __init__(self) -> None:
        return
    #https://b2c-api-cdn.deeplol.gg/summoner/summoner?summoner_name=%EB%B0%94%EB%B9%84%EB%9D%BC%EB%9D%BC&platform_id=KR
    #https://lol.dakgg.io/api/v1/search-summoners/kr/%EB%B0%94%EB%B9%84%ED%98%B8%EB%B0%94
    #https://b2c-api-cdn.deeplol.gg/summoner/summoner?summoner_name=%EB%B0%94%EB%B9%84%ED%98%B8%EB%B0%94&platform_id=KR
    @staticmethod
    async def searchSummoner(name: str) -> dict:
        url = f"https://b2c-api-cdn.deeplol.gg/summoner/summoner?summoner_name={name}&platform_id=KR"
        data, status = await httpRequests(url)
        if status == 404:
            return errorResponse(404, "not found user")

        return searchUserResponse(data)
    