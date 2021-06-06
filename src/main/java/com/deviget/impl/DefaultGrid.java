package com.deviget.impl;

import com.deviget.model.Cell;
import com.deviget.model.CoveredCell;
import com.deviget.model.Grid;
import com.deviget.model.UncoveredCell;

public class DefaultGrid implements Grid {
    Cell [][] cells;

    public DefaultGrid(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public int getRowcount() {
        return cells.length;
    }

    @Override
    public int getColumncount() {
        return cells[0].length;
    }

    @Override
    public int getTotalCells() {
        return cells.length * cells[0].length;
    }

    @Override
    public int getTotalMinedCells() {
        int minedCells = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (MinedCell.class.isAssignableFrom( cells[i][j].getClass())) {
                    minedCells++;
                }
            }
        }
        return minedCells;
    }

    @Override
    public int getTotalHarmlessCells() {
        int harmlessCells = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (HarmlessCell.class.isAssignableFrom( cells[i][j].getClass())) {
                    harmlessCells++;
                }
            }
        }
        return harmlessCells;
    }

    @Override
    public int getTotalCoveredCells() {
        int coveredCells = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (CoveredCell.class.isAssignableFrom( cells[i][j].getCellState().getClass())) {
                    coveredCells++;
                }
            }
        }
        return coveredCells;
    }

    @Override
    public int getTotalUncoveredCells() {
        int uncoveredCells = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (UncoveredCell.class.isAssignableFrom( cells[i][j].getCellState().getClass())) {
                    uncoveredCells++;
                }
            }
        }
        return uncoveredCells;
    }
    
}
