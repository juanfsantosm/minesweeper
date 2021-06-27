package com.deviget;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import com.deviget.persistence.GameRepository;

import org.junit.jupiter.api.Test;

import io.micronaut.context.BeanContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

@MicronautTest
public class GameRepositoryTest {

    @Inject
    GameRepository gameRepository;

    @Inject
    BeanContext beanContext;

    @Test
    public void testFindAll() {
        gameRepository.findAll();
    }
}
