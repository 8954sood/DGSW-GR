from fastapi import APIRouter, Depends, Path, Request, HTTPException
from fastapi.responses import FileResponse



router = APIRouter(prefix="/file")


@router.get("/static/image/{filename}", tags=["file"])
async def unrankfile(filename: str):
    return FileResponse(f"./image/static/{filename}")