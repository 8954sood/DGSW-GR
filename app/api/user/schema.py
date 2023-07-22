from api.models.schema import UserSchema
from pydantic import BaseModel
from typing import List
class CreateUserRequest(BaseModel):
    login_id: str
    password: str
    name: str
    grade: int
    class_id: int
    number: int

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

class TokenRequest(BaseModel):
    token: str

class RefreshTokenRequest(BaseModel):
    refresh_token: str