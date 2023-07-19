from starlette.responses import JSONResponse

def errorResponse(code: int, message: str):
    return  JSONResponse(status_code=code, content={"error": True, "code": code, "message": message})
