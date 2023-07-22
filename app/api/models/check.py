from .jwt import AuthHandler
from fastapi import HTTPException

class JWTCheck:
    @staticmethod
    async def refreshToken(jwt: AuthHandler, token: str) -> str:
        result = await jwt.async_decode_token(token)
        if type(result) == int:
            raise HTTPException(403, detail="that's not access token") 
        return result
    @staticmethod
    async def accessToken(jwt: AuthHandler, token: str) -> int:
        result = await jwt.async_decode_token(token)
        if type(result) == str:
            raise HTTPException(403, detail="that's not access token") 
        return result