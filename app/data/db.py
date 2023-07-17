from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker, scoped_session
from sqlalchemy.ext.asyncio import AsyncEngine, AsyncSession, create_async_engine

# user_name = "root"
# user_pwd = "DB 비밀번호" #본인 db 비밀번호로 변경
# db_host = "127.0.0.1"
# db_name = "crudpy" #임의로 설정했음

# DATABASE =  'mysql://%s:%s@%s/%s?charset=utf8' % (
#     user_name,
#     user_pwd,
#     db_host,
#     db_name
# )

# ENGINE = create_engine(
#     DATABASE,
#     encoding="utf-8",
#     echo=True
# ) # 위의 정보들을 담은 상태에서 utf-8의 인코딩 방식으을 가진 DB 접속 엔진을 생성합니다.

SQLALCHEMY_DB_URL = "sqlite:///./sql_app.db"

ENGINE = create_engine(
    SQLALCHEMY_DB_URL,
    connect_args={"check_same_thread": False},
    echo=True
)

session = scoped_session(
    sessionmaker(
        autocommit = False,
        autoflush = False,
        bind = ENGINE
    )
)
# SessionLocal = sessionmaker(
#     autocommit=False,
#     autoflush=False,
#     bind=engine
# )

Base = declarative_base()
Base.query = session.query_property()

def init_db():
    Base.metadata.create_all(bind=ENGINE)