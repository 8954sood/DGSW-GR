from pydantic import BaseModel
from models import TokenRequestSchema as TokenRequest

class SearchRequest(BaseModel):
    name: str

class CreateUserRequest(BaseModel):
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
    level: int
    icon: str
    most: list
    kda: str
    tier_str: str
    tier_point: str
    tier_icon: str
    win_lose: str
    win_rate: str
    