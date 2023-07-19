from fastapi import FastAPI
from fastapi.responses import JSONResponse

from api.main import router as api_router
from db import init_db
app = FastAPI(title="async-fastapi-sqlalchemy")

app.include_router(api_router, prefix="/api")

@app.on_event("startup")
async def on_start():
    await init_db()

@app.get("/", include_in_schema=False)
async def health() -> JSONResponse:
    return JSONResponse({"message": "It worked!!"})


if __name__ == "__main__":
    import uvicorn

    uvicorn.run("main:app", host="0.0.0.0", port=8000, reload=True)
