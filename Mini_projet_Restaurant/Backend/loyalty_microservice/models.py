from sqlalchemy import Column, Integer, String, Float
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()

class UserLoyalty(Base):
    __tablename__ = "user_loyalty"

    id = Column(Integer, primary_key=True, index=True)
    user_id = Column(Integer, unique=True, index=True)
    points = Column(Integer, default=0)
    tier = Column(String(50), default="Silver")
