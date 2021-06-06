package com.service;

import com.deviget.exception.BuildGridException;
import com.deviget.model.Grid;

public interface GridService {
   
    void uncoverCellAt(Grid grid, int x , int y);

    void coverCellAt(Grid grid, int x , int y);

    void questionMarkCellAt(Grid grid, int x, int y);

    void redFlagCellAt(Grid grid, int x, int y);

    Grid buildGrid(int rowCount , int columnCount, int minedCells) throws BuildGridException;
}
