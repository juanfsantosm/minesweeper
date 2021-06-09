package com.deviget;

public class NewGameRequest {
    int rows;
    int columns;
    int minedCells;
    String playerId;

    public NewGameRequest() {
    }

    public NewGameRequest(int rows, int columns, int minedCells, String playerId) {
        this.rows = rows;
        this.columns = columns;
        this.minedCells = minedCells;
        this.playerId = playerId;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getMinedCells() {
        return minedCells;
    }

    public void setMinedCells(int minedCells) {
        this.minedCells = minedCells;
    }
}
