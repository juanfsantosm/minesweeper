package com.deviget.model;

import com.deviget.persistence.GameStatus;

public interface Game {
    long getId();
    String getPlayerId();
    GameStatus getState();
    void setState(GameStatus gameStatus);
    void end(GameStatus gameStatus);
    Grid getGrid();
    void setGrid(Grid grid);
}
