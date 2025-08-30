package org.gitsokolek.spacex.dragon.repo;

import org.gitsokolek.spacex.dragon.model.*;
import org.gitsokolek.spacex.utilities.developmentutilities.DevUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryDragonRepository implements DragonRepository {
    private final Map<java.util.UUID, Dragon> store = new ConcurrentHashMap<>();
    private final Map<String, java.util.UUID> nameIndex = new ConcurrentHashMap<>();

	private static final String NOT_IMPLEMENTED_YET = DevUtils.notImplementedYet();


    @Override
    public Dragon save(Dragon dragon) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
    }

    @Override
    public Optional<Dragon> findById(DragonId id) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
    }

    @Override
    public Optional<Dragon> findByName(String name) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
    }

    @Override
    public List<Dragon> findByStatus(DragonStatus status) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
    }

    @Override
    public List<Dragon> findAll()
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
    }

    @Override
    public boolean deleteById(DragonId id) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
    }

    @Override
    public boolean existsByName(String name)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
    }

    private static String normalize(String s)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}
}
