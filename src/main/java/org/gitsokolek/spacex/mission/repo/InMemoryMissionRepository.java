package org.gitsokolek.spacex.mission.repo;

import org.gitsokolek.spacex.mission.model.*;
import org.gitsokolek.spacex.utilities.developmentutilities.DevUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryMissionRepository implements MissionRepository
{
	private final Map<java.util.UUID, Mission> store     = new ConcurrentHashMap<>();
	private final Map<String, java.util.UUID>  nameIndex = new ConcurrentHashMap<>();

	private static final String NOT_IMPLEMENTED_YET = DevUtils.notImplementedYet();



	@Override
	public Mission save(Mission mission)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	@Override
	public Optional<Mission> findById(MissionId id)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	@Override
	public Optional<Mission> findByName(String name)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	@Override
	public List<Mission> findByStatus(MissionStatus status)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	@Override
	public List<Mission> findAll()
	{
		return List.copyOf(store.values());
	}



	@Override
	public boolean deleteById(MissionId id)
	{
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
