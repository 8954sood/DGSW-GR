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
