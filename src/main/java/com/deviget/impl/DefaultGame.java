package com.deviget.impl;

import java.util.Objects;

import com.deviget.model.Game;
import com.deviget.model.Grid;
import com.deviget.persistence.GameStatus;

public class DefaultGame implements Game {
    private long id;
    private String playerId;
    private GameStatus state;
    private Grid grid;

    public DefaultGame() {
    }

    public DefaultGame(long id) {
        this.id=id;
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
    public GameStatus getState() {
        return state;
    }

    @Override
    public void end(GameStatus gameStatus) {
        setState(gameStatus);
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

    public void setState(GameStatus state) {
        this.state = state;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return !Objects.isNull(obj) && getClass().isAssignableFrom(obj.getClass()) && ((DefaultGame)obj).id==id;
    }
}
