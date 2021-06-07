package com.deviget.model;

public interface Game {
    long getId();
    Player getPlayer();
    GameState getState();
    void end();
}
