from pydantic import BaseModel
from models import TokenRequestSchema as TokenRequest

class SearchRequest(BaseModel):
    name: str

class CreateUserRequest(TokenRequest):
    name: str

class EditUserRequest(TokenRequest):
    name: str

class UpdateUserRequest(BaseModel):
    id: int

class InfoUserResponse(BaseModel):
    name: str
    grade: int
    class_id: int
    number: int
    nickname: str
    tier_str: str
    tier_int: int
    level: int
    profile_id: int
    profile_icon: str
    puu_id: str
    