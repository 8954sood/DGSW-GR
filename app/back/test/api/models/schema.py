from pydantic import BaseModel, ConfigDict

class UserSchema(BaseModel):
    id: int
    username: str
