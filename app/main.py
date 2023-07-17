from fastapi import FastAPI, HTTPException
from module import Response, Summoner, LolNetwork
import uvicorn

from router import lol, user

from data.db import session, init_db
from data.model.user import User, UserTable
from fastapi.middleware.cors import CORSMiddleware
from sqlalchemy.orm import declarative_base
# from sqlalchemy.schema import CreateTable
Base = declarative_base()

app = FastAPI(debug=True)
LOL_PATH = "/lol"

#https://wikid  ocs.net/176499
#https://juna-dev.tistory.com/16
app.include_router(lol)
app.include_router(user)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)



@app.on_event("startup") 
async def startup_event(): 
    print("Startup event") 
    init_db()
    # print( Base.metadata.tables)
    # for table in Base.metadata.tables.values():
    #     print(table)
    
    

@app.on_event("shutdown") 
def shutdown_event():
    print("Shutdown event") 
    session.commit()
    session.close_all()
    # with open("log.txt", mode= 'a') as log : 
    #     log.write("application shutdown") 

@app.get("/", status_code=200)
async def main():
    return "ㅇ" 
    

# @app.get("/users", status_code=200)
# async def read_users(): # 유저 리스트 가져오기
#     users = session.query(UserTable).all()
#     return users
# @app.get("/users/{user_id}")
# async def read_user(user_id: int): # 특정 유저 가져오기
#     user = session.query(UserTable).filter(UserTable.id == user_id).first()
#     return user
# @app.post("/users")
# async def create_user(name: str, age: int) : # 유저 생성
#     user = UserTable()
#     user.name = name
#     user.age = age

#     session.add(user)
#     session.commit()

#     return f"{name} created..."
    



# LOL
# @app.post(f"{LOL_PATH}/summoner", status_code=201)
# async def postLolSummoner(data: Summoner):
    
#     # if data.name == "error":
#     #     raise HTTPException(status_code=405, detail="이름이 에러입니다.")
#     return Response.errorResponse(403, data.name)
# @app.get(f"{LOL_PATH}/rank", status_code=200)
# async def getLolRank():
#     data = await LolNetwork.searchSummoner("바비호바")
#     return data
if __name__ == "__main__":
    uvicorn.run("main:app", host="0.0.0.0", port=8000, reload=True)