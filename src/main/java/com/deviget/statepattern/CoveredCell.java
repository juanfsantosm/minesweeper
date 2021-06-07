package com.deviget.statepattern;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.deviget.impl.MinedCell;
import com.deviget.model.Cell;
import com.deviget.model.CellState;

@Singleton
public class CoveredCell implements CellState {

    @Inject GameService GameService;

    private Cell cell;

    public CoveredCell(Cell cell) {
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
        // do nothing
    }

    @Override
    public void uncoverCell() {
        if (MinedCell.class.isAssignableFrom(getCell().getClass())){

            // end of game
            getCell().getGrid().getGame().end();
        } else {

        }
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
