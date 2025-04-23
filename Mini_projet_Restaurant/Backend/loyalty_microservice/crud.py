from sqlalchemy.orm import Session
from models import UserLoyalty
import requests

def check_user_exists(user_id):
    try:
        response = requests.get(f"http://localhost:8087/users/users/{user_id}")
        return response.status_code == 200
    except requests.exceptions.RequestException as e:
        print(f"🚨 Error checking user: {e}")
        return False
def get_loyalty(db: Session, user_id: int):
    return db.query(UserLoyalty).filter(UserLoyalty.user_id == user_id).first()

def add_order(db: Session, user_id: int, amount: float):
    # ✅ Check user existence
    if not check_user_exists(user_id):
        raise HTTPException(status_code=404, detail="User not found in User Service")

    points_earned = int(amount)  # Example: 1 DT = 1 point
    loyalty = get_loyalty(db, user_id)

    if not loyalty:
        loyalty = UserLoyalty(user_id=user_id, points=points_earned)
        db.add(loyalty)
    else:
        loyalty.points += points_earned

    db.commit()
    db.refresh(loyalty)
    return loyalty