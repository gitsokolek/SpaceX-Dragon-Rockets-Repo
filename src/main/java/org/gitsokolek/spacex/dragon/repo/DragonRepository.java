package org.gitsokolek.spacex.dragon.repo;

import org.gitsokolek.spacex.dragon.model.*;

import java.util.*;

public interface DragonRepository {
    Dragon save(Dragon dragon);
    Optional<Dragon> findById(DragonId id);
    Optional<Dragon> findByName(String name);
    List<Dragon> findByStatus(DragonStatus status);
    List<Dragon> findAll();
    boolean deleteById(DragonId id);
    boolean existsByName(String name);
}
