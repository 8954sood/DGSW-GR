from typing import Tuple
import aiohttp
from fastapi import HTTPException

class BaseNetwork:
    def __init__(self) -> None:
        pass
    
    async def httpGetRequests(self, url: str, headers: dict= {"Content-Type": "application/json"}) -> Tuple[dict, int]:
        async with aiohttp.ClientSession(headers=headers) as session:
            async with session.get(url) as response:
                data = await response.json()
                status = response.status
        print(status)
        return (data, status)
    
    async def httpPostRequests(self, url: str, body: dict, headers: dict= {"Content-Type": "application/json"}) -> Tuple[dict | str, int]:
        try:
            async with aiohttp.ClientSession(headers=headers) as session:
                async with session.post(url, json=body) as response:
                    try:
                        data = await response.json()
                    except aiohttp.ContentTypeError:
                        data = await response.text()
                    status = response.status
        except Exception as E: 
            print(E)
            raise HTTPException(404, detail=E)
        print(status)
        print(data)
        return (data, status)