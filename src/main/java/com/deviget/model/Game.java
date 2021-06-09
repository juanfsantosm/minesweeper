package com.deviget.model;

public interface Game {
    long getId();
    String getPlayerId();
    GameState getState();
    void end();
    Grid getGrid();
    void setGrid(Grid grid);
}
