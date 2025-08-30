package org.gitsokolek.spacex.dragon.repo;

import org.gitsokolek.spacex.dragon.model.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryDragonRepository implements DragonRepository {
	private final Map<java.util.UUID, Dragon> store = new ConcurrentHashMap<>();
	private final Map<String, java.util.UUID> nameIndex = new ConcurrentHashMap<>();

	@Override
	public Dragon save(Dragon dragon) {
		Objects.requireNonNull(dragon);
		java.util.UUID id = dragon.getId().value();
		store.put(id, dragon);
		nameIndex.put(normalize(dragon.getName()), id);
		return dragon;
	}

	@Override
	public Optional<Dragon> findById(DragonId id) {
		Dragon d = store.get(Objects.requireNonNull(id).value());
		return Optional.ofNullable(d);
	}

	@Override
	public Optional<Dragon> findByName(String name) {
		java.util.UUID id = nameIndex.get(normalize(Objects.requireNonNull(name)));
		return id == null ? Optional.empty() : Optional.ofNullable(store.get(id));
	}

	@Override
	public List<Dragon> findByStatus(DragonStatus status) {
		Objects.requireNonNull(status);
		return store.values().stream()
					.filter(d -> d.getStatus() == status)
					.collect(Collectors.toUnmodifiableList());
	}

	@Override
	public List<Dragon> findAll() {
		return List.copyOf(store.values());
	}

	@Override
	public boolean deleteById(DragonId id) {
		Dragon removed = store.remove(Objects.requireNonNull(id).value());
		if (removed != null) nameIndex.remove(normalize(removed.getName()));
		return removed != null;
	}

	@Override
	public boolean existsByName(String name) {
		return nameIndex.containsKey(normalize(Objects.requireNonNull(name)));
	}

	private static String normalize(String s) { return s.trim(); }
}
