from fastapi import FastAPI, Request
from fastapi.responses import JSONResponse

from api.main import router as api_router
from db import init_db
from async_fastapi_jwt_auth.exceptions import AuthJWTException
from dotenv import load_dotenv
import asyncio
load_dotenv()
app = FastAPI(title="async-fastapi-sqlalchemy")

app.include_router(api_router, prefix="/api")
loop = asyncio.get_event_loop()
@app.on_event("startup")
async def on_start():
    await init_db()

@app.get("/", include_in_schema=False)
async def health() -> JSONResponse:
    return JSONResponse({"message": "It worked!!"})

@app.exception_handler(AuthJWTException)
def authjwt_exception_handler(request: Request, exc: AuthJWTException):
    return JSONResponse(
        status_code=exc.status_code,
        content={"detail": exc.message}
    )

if __name__ == "__main__":
    import uvicorn

    uvicorn.run("main:app", host="0.0.0.0", port=8000, reload=True, log_level="info")
