package com.deviget.impl;

import com.deviget.exception.BuildGridException;
import com.deviget.model.Cell;
import com.deviget.model.CellPosition;
import com.deviget.model.Game;
import com.deviget.model.Grid;
import com.deviget.persistence.CellEntity;
import com.deviget.persistence.GameEntity;
import com.deviget.statepattern.CoveredCell;
import com.deviget.statepattern.QuestionMarkedCell;
import com.deviget.statepattern.RedFlaggedCell;
import com.deviget.statepattern.UncoveredCell;
import com.service.GridService;
import io.micronaut.http.annotation.Controller;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

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

    /**
     * <p>
     * Given an already existen (previously) persisted GameEntity, load the grid in order to be able to continue playing.
     * </p>
     */
    @Override
    public Grid loadGrid(GameEntity entity) {
        Grid grid = new DefaultGrid(entity.getRows(), entity.getColumns());
        Cell[][] cells  = grid.getCells();
        Set<CellEntity> cellEntities = entity.getCellEntities();

        for (CellEntity cellEntity : cellEntities) {
            int row = cellEntity.getX();
            int column = cellEntity.getY();
            Cell cell = cells[row][column];

            switch (cellEntity.getType()) {
                case HARMLESS: {
                    cell = new HarmlessCell(grid, row, column);
                }
                case MINED: {
                    cell = new MinedCell(grid, row, column);
                }
            }
          
            //
            switch (cellEntity.getStatus()) {
                case COVERED: {
                    cell.setCellState(new CoveredCell(cell));
                    break;
                }
                case QUESTIONMARKED: {
                    cell.setCellState(new QuestionMarkedCell(cell));
                    break;
                }
                case REDFLAGGED: {
                    cell.setCellState(new RedFlaggedCell(cell));
                    break;
                }
                case UNCOVERED: {
                    cell.setCellState(new UncoveredCell(cell));
                    break;
                }
            }
        }

        return grid;
    }

}
