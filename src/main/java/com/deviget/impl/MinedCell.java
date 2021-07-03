package com.deviget.impl;

import com.deviget.model.CellPosition;
import com.deviget.model.Grid;

public class MinedCell extends AbstractCell {

    public MinedCell(Grid grid, CellPosition cellPosition) {
        super(grid, cellPosition);
    }

    public MinedCell(Grid grid, int row, int column) {
        super(grid, row, column);
    }
}
