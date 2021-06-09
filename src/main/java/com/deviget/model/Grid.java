package com.deviget.model;

import java.util.List;

public interface Grid {
    long getId();
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
    List<CellPosition> getMinedPositions();
    List<CellPosition> getQuestionMarkedPositions();
    List<CellPosition> getRedFlaggedPositions();
    List<CellPosition> getUncoveredPositions();
    List<CellPosition> getCoveredPositions();

    void coverCell(int x, int y);

    void uncoverCell(int x, int y);

    void redFlagCell(int x, int y);

    void questionMarkCell(int x, int y);
}
