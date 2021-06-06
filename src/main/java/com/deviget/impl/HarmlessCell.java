package com.deviget.impl;

import com.deviget.model.CoveredCell;
import com.deviget.model.Grid;

public class HarmlessCell extends AbstractCell {
    
    public HarmlessCell(Grid grid, int row, int column) {
        super(grid, row, column);
        setCellState(new CoveredCell());
    }
}
