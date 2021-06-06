package com.deviget.impl;

import com.deviget.model.Cell;
import com.deviget.model.CellState;
import com.deviget.model.Grid;

public abstract class AbstractCell implements Cell {
    int row;
    int column;
    int adjacentMinedCells;
    Grid grid;
    CellState cellState;

    public AbstractCell(Grid grid, int row, int column) {
        this.row = row;
        this.column = column;
        this.grid = grid;
    }

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public Grid getGrid() {
        return grid;
    }
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    public int getAdjacentMinedCells() {
        return adjacentMinedCells;
    }
    public void setAdjacentMinedCells(int adjacentMinedCells) {
        this.adjacentMinedCells = adjacentMinedCells;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }
}
