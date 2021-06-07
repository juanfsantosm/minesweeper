package com.deviget.statepattern;

import com.deviget.model.Cell;
import com.deviget.model.CellState;

public class QuestionMarkedCell implements CellState {

    private Cell cell;

    public QuestionMarkedCell(Cell cell) {
        this.cell = cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    @Override
    public void coverCell() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void uncoverCell() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void redFlagCell() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void questionMarkCell() {
        // TODO Auto-generated method stub
        
    }
    
}
