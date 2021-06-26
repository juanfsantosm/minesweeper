package com.deviget.persistence;

import javax.persistence.*;

@Entity
public class CellEntity {
   private GameEntity gameEntity;
   private int x;
   private int y;
   private CellType type;
   private CellStatus status;

    @Id
    @ManyToOne
    public GameEntity getGameEntity() {
        return gameEntity;
    }

    @Id
    public int getX() {
        return x;
    }

    @Id
    public int getY() {
        return y;
    }

    @Enumerated(EnumType.ORDINAL)
    public CellType getType() {
        return type;
    }

    @Enumerated(EnumType.ORDINAL)
    public CellStatus getStatus() {
        return status;
    }

    public void setGameEntity(GameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }
}
