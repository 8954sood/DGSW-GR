from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker, scoped_session
from sqlalchemy.ext.asyncio import AsyncEngine, AsyncSession, create_async_engine, async_scoped_session, async_sessionmaker
from asyncio import current_task
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

# SQLALCHEMY_DB_URL = "sqlite:///./sql_app.db"
SQLALCHEMY_DB_URL = "sqlite+aiosqlite:///./sql_app.db"

ENGINE = create_async_engine(
    SQLALCHEMY_DB_URL,
    connect_args={"check_same_thread": False},
    echo=True
)#future=True <- 게속 초기화 시킴
#echo=True,
async_session_maker = sessionmaker(bind=ENGINE, class_=AsyncEngine, expire_on_commit=False)
session = async_scoped_session(
    session_factory=async_session_maker,
    scopefunc=current_task
)
Base = declarative_base()

async def init_db():
    async with ENGINE.begin() as conn:
        # await conn.run_sync(Base.metadata.drop_all)
        await conn.run_sync(Base.metadata.create_all)
# async def close_db():
#     async with async_sessionmaker(ENGINE)() as _session:
#         await _session.close_all()
# async def get_db_session() -> AsyncSession:
#     sess = AsyncSession(bind=ENGINE)
#     try:
#         yield sess
#     finally:
#         await sess.close()
# session = scoped_session(
#     sessionmaker(
#         autocommit = False,
#         autoflush = False,
#         bind = ENGINE
#     )
# )
# # SessionLocal = sessionmaker(
# #     autocommit=False,
# #     autoflush=False,
# #     bind=engine
# # )

# Base.query = session.query_property()

# def init_db():
#     Base.metadata.create_all(bind=ENGINE)