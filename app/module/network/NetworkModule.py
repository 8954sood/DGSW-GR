import aiohttp
from typing import Tuple
async def httpRequests(url: str) -> Tuple[dict, int]:
    async with aiohttp.ClientSession() as session:
        async with session.get(url) as response:
            data = await response.json()
            status = response.status
    print(status)
    return (data, status)