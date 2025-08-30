package org.gitsokolek.spacex.mission.repo;

import org.gitsokolek.spacex.mission.model.*;

import java.util.*;

public interface MissionRepository {
    Mission save(Mission mission);
    Optional<Mission> findById(MissionId id);
    Optional<Mission> findByName(String name);
    List<Mission> findByStatus(MissionStatus status);
    List<Mission> findAll();
    boolean deleteById(MissionId id);
    boolean existsByName(String name);
}
