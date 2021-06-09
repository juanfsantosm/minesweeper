package com.deviget;

import com.deviget.exception.BuildGridException;
import com.deviget.model.CellPosition;
import com.deviget.model.Game;
import com.deviget.model.Grid;
import com.deviget.statepattern.UncoveredCell;
import com.service.GridService;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class GridServiceTest {

    @Inject
    GridService gridService;

    @Mock
    Game game;

    @Test
    public void testBuildGrid() throws BuildGridException {
        Grid grid = gridService.buildGrid(game, 6, 5, 5);
        System.out.println(grid);
        assertEquals(30, grid.getTotalCells());
        assertEquals(5, grid.getTotalMinedCells());
        assertEquals(25, grid.getTotalHarmlessCells());
        assertEquals(30, grid.getTotalCoveredCells());
        assertEquals(0, grid.getTotalUncoveredCells());
        assertEquals(6, grid.getRowcount());
        assertEquals(5, grid.getColumncount());
    }

    @Test
    public void testInvalidNumberMinedCells() {
        assertThrows(BuildGridException.class, () -> {
            Grid grid = gridService.buildGrid(game, 3, 3, 20);
        });
    }

    @Test
    public void testCoverCell() throws BuildGridException {
        Grid grid = gridService.buildGrid(game, 6, 5, 5);
        CellPosition p = grid.getHarmelessPositions().get(0);
        gridService.uncoverCellAt(grid, p.getX(), p.getY());
        assertTrue(grid.getCellAt(p.getX(), p.getY()).getCellState().getClass().equals(UncoveredCell.class));
    }
}
