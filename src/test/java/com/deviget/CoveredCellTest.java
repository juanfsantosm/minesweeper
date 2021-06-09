package com.deviget;

import com.deviget.impl.DefaultGrid;
import com.deviget.impl.HarmlessCell;
import com.deviget.model.Cell;
import com.deviget.model.Grid;
import com.deviget.statepattern.CoveredCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoveredCellTest {
    Grid grid;

    Cell[][] cells = new Cell[5][5];

    @BeforeEach
    public void before() {
        grid = new DefaultGrid(cells, new ArrayList<>(), null);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new HarmlessCell(grid, i, j);
            }
        }
    }

    @Test
    public void testGetAdjacent() {
        Cell cell = cells[1][1];
        List<Cell> adjacents = new CoveredCell(cell).getAdjacent(cell);
        assertTrue(contains(adjacents, 0, 0));
        assertTrue(contains(adjacents, 0, 1));
        assertTrue(contains(adjacents, 0, 2));
        assertTrue(contains(adjacents, 1, 0));
        assertTrue(contains(adjacents, 1, 2));
        assertTrue(contains(adjacents, 2, 0));
        assertTrue(contains(adjacents, 2, 1));
        assertTrue(contains(adjacents, 2, 2));
    }

    private boolean contains(List<Cell> cells, int x, int y) {
        for (Cell cell : cells) {
            if (cell.getCellPosition().getX() == x && cell.getCellPosition().getY() == y) {
                return true;
            }
        }
        return false;
    }
}
