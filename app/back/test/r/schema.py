# from api.models.schema import UserSchema
from base import UserBase
from pydantic import BaseModel
from typing import List
class CreateUserRequest(BaseModel):
    username: str
class CreateUserResponse(UserBase):
    pass


class ReadAllUserResponse(BaseModel):
    users: List[UserBase]