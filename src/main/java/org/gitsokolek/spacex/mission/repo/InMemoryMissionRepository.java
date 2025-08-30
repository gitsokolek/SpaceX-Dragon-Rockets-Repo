package org.gitsokolek.spacex.mission.repo;

import org.gitsokolek.spacex.mission.model.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryMissionRepository implements MissionRepository {
	private final Map<java.util.UUID, Mission> store = new ConcurrentHashMap<>();
	private final Map<String, java.util.UUID> nameIndex = new ConcurrentHashMap<>();

	@Override
	public Mission save(Mission mission) {
		Objects.requireNonNull(mission);
		java.util.UUID id = mission.getId().value();
		store.put(id, mission);
		nameIndex.put(normalize(mission.getName()), id);
		return mission;
	}

	@Override
	public Optional<Mission> findById(MissionId id) {
		Mission m = store.get(Objects.requireNonNull(id).value());
		return Optional.ofNullable(m);
	}

	@Override
	public Optional<Mission> findByName(String name) {
		java.util.UUID id = nameIndex.get(normalize(Objects.requireNonNull(name)));
		return id == null ? Optional.empty() : Optional.ofNullable(store.get(id));
	}

	@Override
	public List<Mission> findByStatus(MissionStatus status) {
		Objects.requireNonNull(status);
		return store.values().stream()
					.filter(m -> m.getStatus() == status)
					.collect(Collectors.toUnmodifiableList());
	}

	@Override
	public List<Mission> findAll() {
		return List.copyOf(store.values());
	}

	@Override
	public boolean deleteById(MissionId id) {
		Mission removed = store.remove(Objects.requireNonNull(id).value());
		if (removed != null) nameIndex.remove(normalize(removed.getName()));
		return removed != null;
	}

	@Override
	public boolean existsByName(String name) {
		return nameIndex.containsKey(normalize(Objects.requireNonNull(name)));
	}

	private static String normalize(String s) { return s.trim(); }
}
