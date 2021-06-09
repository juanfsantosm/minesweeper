package com.deviget;

import com.deviget.exception.BuildGridException;
import com.deviget.impl.DefaultGame;
import com.deviget.model.Game;
import com.deviget.model.Grid;
import com.deviget.model.Player;
import com.service.GridService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Controller("/game")
public class RunningGames {

    List<Game> runningGames = new ArrayList<>();
    List<Player> connectedPlayers;

    @Inject
    GridService gridService;

    @Post(produces = MediaType.APPLICATION_JSON, value = "/new", consumes = MediaType.APPLICATION_JSON)
    public Grid startGame(NewGameRequest newGameRequest) {
        Grid grid = null;
        try {
            grid = gridService.buildGrid(newGameRequest.getRows(), newGameRequest.getColumns(), newGameRequest.getMinedCells());
            Game game = new DefaultGame(null, grid);
            runningGames.add(game);
        } catch (BuildGridException e) {
            e.printStackTrace();
        }
        return grid;
    }

    @Get(produces = MediaType.APPLICATION_JSON, value = "/list/{playerId}")
    public List<Game> listGames(@PathVariable("playerId") String playerId) {
        return runningGames.stream().filter(r -> r.getPlayerId().equalsIgnoreCase(playerId)).collect(Collectors.toList());
    }

    @Get(produces = MediaType.APPLICATION_JSON, value = "/list")
    public List<Game> listGames() {
        return runningGames;
    }

    public Game loadGame(long id) {
        return null;
    }

}
