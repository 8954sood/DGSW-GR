from pydantic import BaseModel, ConfigDict


class UserSchema(BaseModel):
    id: int
    login_id: str
    password: str
    name: str
    grade: int
    class_id: int
    number: int

    # model_config = ConfigDict(from_attributes=True)
class LOLSchema(BaseModel):
    id: int
    user_id: int
    nickname: str
    tier_str: str
    tier_int: int
    level: int
    profile_id: int
    profile_icon: str
    puu_id: str

class TokenRequestSchema(BaseModel):
    token: str