package com.deviget.statepattern;

import com.deviget.model.Cell;
import com.deviget.model.CellState;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UncoveredCell implements CellState {
    
    private Cell cell;

    public UncoveredCell(Cell cell) {
        this.cell = cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @JsonIgnore
    public Cell getCell() {
        return cell;
    }

    @Override
    public void coverCell() {
        // do nothin!
    }

    @Override
    public void uncoverCell() {
        // do nothing! already uncovered
    }

    @Override
    public void redFlagCell() {
        // do nothing
    }

    @Override
    public void questionMarkCell() {
        // do nothing
    }
    
}
