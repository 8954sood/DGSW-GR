from api.models.schema import UserSchema, TokenRequestSchema as TokenRequest
from pydantic import BaseModel
from typing import List
class CreateUserRequest(BaseModel):
    login_id: str
    password: str
    name: str
    grade: str
    class_id: str
    number: str

class CreateUserResponse(UserSchema):
    pass

class CheckDuplication(BaseModel):
    login_id: str

class ReadAllUserResponse(BaseModel):
    users: List[UserSchema]

class DeleteUserRequest(BaseModel):
    id: int

class UpdateUserRequest(UserSchema):
    pass

class LoginUserRequest(BaseModel):
    login_id: str
    password: str

class RefreshTokenRequest(BaseModel):
    refresh_token: str