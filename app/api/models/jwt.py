from datetime import datetime, timedelta
import jwt
from fastapi import HTTPException
# from .schema import Settings
from typing import Union
import asyncio
from pydantic import BaseModel
import os
from dotenv import load_dotenv


load_dotenv()
# class Settings(BaseModel):
#     SECRET_KEY: str = os.environ.get("SECRET_KEY")
#     ALGORITHM: str = os.environ.get("ALGORITHM")

class AuthHandler:
    secret = os.environ.get("SECRET_KEY")

    algorithm = os.environ.get("ALGORITHM")
    access_expires = timedelta(hours=2)
    refresh_expires = timedelta(days=14)
    async def async_encode_token(self, sub: Union[int, str], expires: timedelta) -> str:
        loop = asyncio.get_event_loop()
        return await loop.run_in_executor(None, self.encode_token, sub, expires)
    async def async_decode_token(self, token: str):
        loop = asyncio.get_event_loop()
        return await loop.run_in_executor(None, self.decode_token, token)
    async def async_create_access_token(self, user_obj: object) -> str:
        loop = asyncio.get_event_loop()
        return await loop.run_in_executor(None, self.create_access_token, user_obj)
    async def async_create_refresh_token(self, user_obj: object) -> str:
        loop = asyncio.get_event_loop()
        return await loop.run_in_executor(None, self.create_refresh_token, user_obj)
    def encode_token(self, sub: Union[int, str], expires: timedelta) -> str:
        payload = {
            "exp": datetime.utcnow() + expires,
            "iat": datetime.utcnow(),
            "sub": sub,
        }

        return jwt.encode(payload, self.secret, self.algorithm)

    def decode_token(self, token: str):
        try:
            payload = jwt.decode(token, self.secret, algorithms=[self.algorithm])
            return payload["sub"]
        except jwt.ExpiredSignatureError:
            raise HTTPException(status_code=401, detail="Token expired")
        except jwt.InvalidTokenError:
            raise HTTPException(status_code=401, detail="Invalid token")

    def create_access_token(self, user_obj: object) -> str:
        """
        액세스 토큰에는 유저 id(pk)만을 sub에 담습니다.
        이후 sub는 decode를 통해 인증인가에 사용됩니다.
        """
        sub = user_obj.id
        return self.encode_token(sub, self.access_expires)

    def create_refresh_token(self, user_obj: object) -> str:
        """
        리프레시 토큰으로 인증인가를 할 수 없도록 액세스 토큰과 다른 sub내용을 만듭니다.
        sub : 유저 id(pk) + 'refresh'
        """
        sub = f"{user_obj.id}.refresh"
        return self.encode_token(sub, self.refresh_expires)

auth = AuthHandler()