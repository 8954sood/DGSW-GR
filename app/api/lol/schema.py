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