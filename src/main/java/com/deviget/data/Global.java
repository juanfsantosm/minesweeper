package com.deviget.data;

import com.deviget.model.Game;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class Global {
    public List<Game> runningGames = new ArrayList<>();
}
