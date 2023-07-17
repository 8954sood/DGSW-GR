from sqlalchemy import Column, ForeignKey
from sqlalchemy import Integer, String
from pydantic import BaseModel
from ..db import *

class UserTable(Base):
    __tablename__ = "user"
    id = Column(Integer, primary_key=True, autoincrement=True)
    login_id = Column(String(20), nullable=False)
    password = Column(String(30), nullable=False)
    name = Column(String(5), nullable=False)
    grade = Column(Integer, nullable=False)
    class_id = Column(Integer, nullable=False)
    number = Column(Integer, nullable=False)
class User(BaseModel):
    id: int
    login_id: str
    password: str
    name: str
    grade: int
    class_id: int
    number: int
class InputUser(BaseModel):
    login_id: str
    password: str
    name: str
    grade: int
    class_id: int
    number: int