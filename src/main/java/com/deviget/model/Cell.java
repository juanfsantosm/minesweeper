package com.deviget.model;

public interface Cell {
    int getRow();
    int getColumn();
    Grid getGrid();
    int getAdjacentMinedCells();
    CellState getCellState();
}
