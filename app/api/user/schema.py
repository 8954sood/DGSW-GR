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


class ReadAllUserResponse(BaseModel):
    users: List[UserSchema]

class DeleteUserRequest(BaseModel):
    id: int
class UpdateUserRequest(UserSchema):
    pass