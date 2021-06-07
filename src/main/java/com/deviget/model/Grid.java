package com.deviget.model;

public interface Grid {
    Cell [][] getCells();
    int getRowcount();
    int getColumncount();
    int getTotalCells();
    int getTotalMinedCells();
    int getTotalHarmlessCells();
    int getTotalCoveredCells();
    int getTotalUncoveredCells();
    Cell getCellAt(int x, int y);
    Game getGame();
}
