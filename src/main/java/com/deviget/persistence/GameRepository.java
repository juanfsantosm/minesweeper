package com.deviget.persistence;

import io.micronaut.context.annotation.Executable;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Repository
public interface GameRepository extends CrudRepository<GameEntity, Long> {
    @Executable
    Iterable<GameEntity> findAll();

    @Executable
    GameEntity save(@Valid @NotNull @NonNull GameEntity entity);
    
}