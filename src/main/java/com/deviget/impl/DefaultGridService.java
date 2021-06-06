package com.deviget.impl;

import java.util.Objects;
import java.util.Random;

import com.deviget.exception.BuildGridException;
import com.deviget.model.Cell;
import com.deviget.model.Grid;
import com.service.GridService;

import io.micronaut.http.annotation.Controller;

@Controller
public class DefaultGridService implements GridService {

    @Override
    public void uncoverCellAt(Grid grid, int x, int y) {
        // TODO Auto-generated method stub

    }

    @Override
    public void coverCellAt(Grid grid, int x, int y) {
        // TODO Auto-generated method stub

    }

    @Override
    public void questionMarkCellAt(Grid grid, int x, int y) {
        // TODO Auto-generated method stub

    }

    @Override
    public void redFlagCellAt(Grid grid, int x, int y) {
        // TODO Auto-generated method stub

    }

    @Override
    public Grid buildGrid(int rowCount, int columnCount, int minedCells) throws BuildGridException {
        if (minedCells >= rowCount * columnCount) {
            // hey, you should not be asking for this!
            // A grid with only minedCells is not (yet) supported!
            throw new BuildGridException("A grid with only mined cells is not supported!", rowCount, columnCount,
                    minedCells);
        }

        Cell[][] cells = new Cell[rowCount][columnCount];

        Grid grid = new DefaultGrid(cells);

        int remaining = minedCells;

        while (remaining > 0) {

            int[] minedRandomRows = new Random().ints(remaining, 0, rowCount).toArray();
            int[] minedRandomCols = new Random().ints(remaining, 0, columnCount).toArray();

            // populate grid with mined cells using random generated positions
            for (int i = 0; i < remaining; i++) {
                int r = minedRandomRows[i];
                int c = minedRandomCols[i];
                if (Objects.isNull(cells[r][c])) {
                    cells[r][c] = new MinedCell(grid, r, c);
                    remaining--;
                }
            }
        }

        // populate remaining empty spaces witg Harmless cells
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (Objects.isNull(cells[i][j])) {
                    cells[i][j] = new HarmlessCell(grid, i, j);
                }
            }
        }

        return grid;
    }

}
