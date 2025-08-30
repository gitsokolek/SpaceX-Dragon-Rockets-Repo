package org.gitsokolek.spacex.dragon.repo;

import org.gitsokolek.spacex.dragon.model.Dragon;
import org.gitsokolek.spacex.dragon.model.DragonId;
import org.gitsokolek.spacex.dragon.model.DragonStatus;

import java.util.List;
import java.util.Optional;

public interface DragonRepository
{
	Dragon save(Dragon dragon);

	Optional<Dragon> findById(DragonId id);

	Optional<Dragon> findByName(String name);

	List<Dragon> findByStatus(DragonStatus status);

	List<Dragon> findAll();

	boolean deleteById(DragonId id);

	boolean existsByName(String name);
}
