package com.deviget.impl;

import com.deviget.model.Game;
import com.deviget.model.GameState;
import com.deviget.model.Grid;

public class DefaultGame implements Game {
    private long id;
    private String playerId;
    private GameState state;
    private Grid grid;

    public DefaultGame() {
    }

    public DefaultGame(String playerId, Grid grid) {
        setId(System.currentTimeMillis());
        this.playerId = playerId;
        this.grid = grid;
    }

    @Override
    public long getId() {
        return id;
    }


    @Override
    public GameState getState() {
        return state;
    }

    @Override
    public void end() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
