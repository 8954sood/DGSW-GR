from typing import Tuple
import aiohttp

class BaseNetwork:
    def __init__(self) -> None:
        pass
    
    async def httpGetRequests(self, url: str) -> Tuple[dict, int]:
        async with aiohttp.ClientSession() as session:
            async with session.get(url) as response:
                data = await response.json()
                status = response.status
        print(status)
        return (data, status)