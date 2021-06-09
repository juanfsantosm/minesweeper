package com.deviget.controller;

import com.deviget.NewGameRequest;
import com.deviget.data.Global;
import com.deviget.exception.BuildGridException;
import com.deviget.impl.DefaultGame;
import com.deviget.model.Game;
import com.deviget.model.Grid;
import com.service.GridService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Controller("/game")
public class GameController {
    @Inject
    Global data;

    @Inject
    GridService gridService;

    @Post(produces = MediaType.APPLICATION_JSON, value = "/new", consumes = MediaType.APPLICATION_JSON)
    public Grid startGame(NewGameRequest newGameRequest) {
        Grid grid = null;
        try {
            Game game = new DefaultGame();
            grid = gridService.buildGrid(game, newGameRequest.getRows(), newGameRequest.getColumns(), newGameRequest.getMinedCells());
            game.setGrid(grid);
            data.runningGames.add(game);
        } catch (BuildGridException e) {
            e.printStackTrace();
        }
        return grid;
    }

    @Get(produces = MediaType.APPLICATION_JSON, value = "/list/{playerId}")
    public List<Game> listGames(@PathVariable("playerId") String playerId) {
        return data.runningGames.stream().filter(r -> r.getPlayerId().equalsIgnoreCase(playerId)).collect(Collectors.toList());
    }

    @Get(produces = MediaType.APPLICATION_JSON, value = "/list")
    public List<Game> listGames() {
        return data.runningGames;
    }

    public Game loadGame(long id) {
        return null;
    }

}
