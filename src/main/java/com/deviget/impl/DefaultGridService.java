package com.deviget.impl;

import com.deviget.exception.BuildGridException;
import com.deviget.model.Cell;
import com.deviget.model.CellPosition;
import com.deviget.model.Game;
import com.deviget.model.Grid;
import com.service.GridService;
import io.micronaut.http.annotation.Controller;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

@Controller
public class DefaultGridService implements GridService {
    @Override
    public void uncoverCellAt(Grid grid, int x, int y) {
        Cell cell = grid.getCellAt(x, y);
        cell.getCellState().uncoverCell();
    }

    @Override
    public void coverCellAt(Grid grid, int x, int y) {
        grid.coverCell(x, y);
    }

    @Override
    public void questionMarkCellAt(Grid grid, int x, int y) {
        grid.questionMarkCell(x, y);
    }

    @Override
    public void redFlagCellAt(Grid grid, int x, int y) {
        grid.redFlagCell(x, y);
    }

    @Override
    public Grid buildGrid(Game game, int rowCount, int columnCount, int minedCells) throws BuildGridException {
        if (minedCells >= rowCount * columnCount) {
            // hey, you should not be asking for this!
            // A grid with only minedCells is not (yet) supported!
            throw new BuildGridException("A grid with mined cells only is not supported!", rowCount, columnCount,
                    minedCells);
        }
        if (minedCells <= 0) {
            // Number of mined cells should be greater than zero
            throw new BuildGridException("Number of mined cells should be greater than zero!", rowCount, columnCount,
                    minedCells);
        }

        Cell[][] cells = new Cell[rowCount][columnCount];

        Grid grid = new DefaultGrid(cells, new ArrayList<>(), game);

        int remaining = minedCells;

        while (remaining > 0) {

            int[] minedRandomRows = new Random().ints(remaining, 0, rowCount).toArray();
            int[] minedRandomCols = new Random().ints(remaining, 0, columnCount).toArray();

            // populate grid with mined cells using random generated positions
            for (int i = 0; i < remaining; i++) {
                int r = minedRandomRows[i];
                int c = minedRandomCols[i];
                if (Objects.isNull(cells[r][c])) {
                    CellPosition cellPosition = new CellPosition(r, c);
                    cells[r][c] = new MinedCell(grid, cellPosition);

                    grid.coverCell(r, c);

                    grid.getMinedPositions().add(cellPosition);
                    remaining--;
                }
            }
        }

        // populate remaining empty spaces witg Harmless cells
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (Objects.isNull(cells[i][j])) {
                    cells[i][j] = new HarmlessCell(grid, i, j);

                    grid.coverCell(i, j);
                }
            }
        }

        return grid;
    }

}
