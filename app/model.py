from sqlalchemy import Column, ForeignKey
from sqlalchemy import Integer, String
from pydantic import BaseModel
from .data.db import *
class UserTable(Base):
    __tablename__ = "user"
    id = Column(Integer, primary_key=True, autoincrement=True)
    name = Column(String(50), nullable=False)
    age = Column(Integer, nullable=False)
class User(BaseModel):
    id: int
    name: str
    age: int

def setup():
    init_db()
    print("실행")
# if __name__ == "__main__":
#     print("테이블 생성")
#     for table in Base.metadata.tables.values():
#         _statement = CreateTable(table).compile(dialect=sqlite.dialect())
# class Lol(Base):
#     __tablename__ = "lols"
