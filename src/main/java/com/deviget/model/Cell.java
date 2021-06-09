package com.deviget.model;

public interface Cell {
    CellPosition getCellPosition();
    Grid getGrid();
    int getAdjacentMinedCells();
    CellState getCellState();
    void setCellState(CellState cellState);
}
