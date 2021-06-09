package com.deviget.impl;

import com.deviget.model.Cell;
import com.deviget.model.CellPosition;
import com.deviget.model.CellState;
import com.deviget.model.Grid;
import com.deviget.statepattern.CoveredCell;

public abstract class AbstractCell implements Cell {
    CellPosition cellPosition;
    int adjacentMinedCells;
    Grid grid;
    CellState cellState;

    public AbstractCell(Grid grid, int row, int column) {
        this(grid, new CellPosition(row, column));
    }

    public AbstractCell(Grid grid, CellPosition cellPosition) {
        this.cellPosition = cellPosition;
        this.grid = grid;
        setCellState(new CoveredCell(this));
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

    public CellPosition getCellPosition() {
        return cellPosition;
    }

    public void setCellPosition(CellPosition cellPosition) {
        this.cellPosition = cellPosition;
    }
}
