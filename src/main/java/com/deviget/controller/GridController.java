package com.deviget.controller;

import com.deviget.data.Global;
import com.deviget.model.Game;
import com.deviget.model.Grid;
import com.deviget.model.Player;
import com.service.GridService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Controller("/grid")
public class GridController {

    List<Game> runningGames = new ArrayList<>();
    List<Player> connectedPlayers;

    @Inject
    Global data;

    @Inject
    GridService gridService;

    @Get(produces = MediaType.APPLICATION_JSON, value = "/{gridId}")
    public Grid getGrid(@PathVariable("gridId") long gridId) {
        return data.runningGames.stream().filter(g -> g.getGrid().getId() == gridId).findFirst().orElse(null).getGrid();
    }

    @Get(produces = MediaType.APPLICATION_JSON, value = "/list")
    public List<Game> listGames() {
        return runningGames;
    }

    @Get(produces = MediaType.APPLICATION_JSON, value = "/{gridId}/uncover/{x}/{y}")
    public Grid uncoverCell(@PathVariable("gridId") long gridId, @PathVariable("x") int x, @PathVariable("y") int y) {
        Grid grid = data.runningGames.stream().filter(g -> g.getGrid().getId() == gridId).findFirst().orElse(null).getGrid();
        gridService.uncoverCellAt(grid, x, y);
        return grid;
    }

    @Get(produces = MediaType.APPLICATION_JSON, value = "/{gridId}/questionmark/{x}/{y}")
    public Grid questionmarkCell(@PathVariable("gridId") long gridId, @PathVariable("x") int x, @PathVariable("y") int y) {
        Grid grid = data.runningGames.stream().filter(g -> g.getGrid().getId() == gridId).findFirst().orElse(null).getGrid();
        gridService.questionMarkCellAt(grid, x, y);
        return grid;
    }

    @Get(produces = MediaType.APPLICATION_JSON, value = "/{gridId}/redflag/{x}/{y}")
    public Grid redflagCell(@PathVariable("gridId") long gridId, @PathVariable("x") int x, @PathVariable("y") int y) {
        Grid grid = data.runningGames.stream().filter(g -> g.getGrid().getId() == gridId).findFirst().orElse(null).getGrid();
        gridService.redFlagCellAt(grid, x, y);
        return grid;
    }

}
