package org.gitsokolek.spacex.mission.repo;

import org.gitsokolek.spacex.mission.model.Mission;
import org.gitsokolek.spacex.mission.model.MissionId;
import org.gitsokolek.spacex.mission.model.MissionStatus;

import java.util.List;
import java.util.Optional;

public interface MissionRepository
{
	Mission save(Mission mission);

	Optional<Mission> findById(MissionId id);

	Optional<Mission> findByName(String name);

	List<Mission> findByStatus(MissionStatus status);

	List<Mission> findAll();

	boolean deleteById(MissionId id);

	boolean existsByName(String name);
}
