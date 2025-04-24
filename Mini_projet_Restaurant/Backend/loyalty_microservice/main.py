from fastapi import FastAPI, Depends
from sqlalchemy.orm import Session
from database import SessionLocal, engine
import models, crud
from database import engine
from models import Base

Base.metadata.create_all(bind=engine)
models.Base.metadata.create_all(bind=engine)

app = FastAPI()

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

@app.post("/add-order/{user_id}")
def add_order(user_id: int, amount: float, db: Session = Depends(get_db)):
    try:
        return crud.add_order(db, user_id, amount)
    except HTTPException as e:
        raise e  # Re-raise HTTP exceptions (like 404)
    except Exception as e:
        # Any other error
        raise HTTPException(status_code=500, detail=str(e))
@app.get("/loyalty/{user_id}")
def get_loyalty(user_id: int, db: Session = Depends(get_db)):
    loyalty = crud.get_loyalty(db, user_id)
    if not loyalty:
        return {"user_id": user_id, "points": 0, "tier": "Bronze"}

    points = loyalty.points
    if points >= 100:
        tier = "Platinum"
    elif points >= 50:
        tier = "Gold"
    elif points >= 25:
        tier = "Silver"
    else:
        tier = "Bronze"
    return {"user_id": user_id, "points": points, "tier": tier}
