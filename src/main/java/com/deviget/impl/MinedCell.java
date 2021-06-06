package com.deviget.impl;

import com.deviget.model.CoveredCell;
import com.deviget.model.Grid;
import com.deviget.model.UncoveredCell;

public class MinedCell extends AbstractCell {

    public MinedCell(Grid grid, int row, int column) {
        super(grid, row, column);
        setCellState(new CoveredCell());
    }
    
}
