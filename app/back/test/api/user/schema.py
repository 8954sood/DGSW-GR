from api.models.schema import UserSchema
from pydantic import BaseModel
from typing import List
class CreateUserRequest(BaseModel):
    username: str
class CreateUserResponse(UserSchema):
    pass


class ReadAllUserResponse(BaseModel):
    users: List[UserSchema]