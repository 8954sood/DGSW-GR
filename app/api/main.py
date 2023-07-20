from fastapi import APIRouter
from .user.views import router as user_router
from .lol.views import router as lol_router
router = APIRouter()
router.include_router(user_router)
router.include_router(lol_router)