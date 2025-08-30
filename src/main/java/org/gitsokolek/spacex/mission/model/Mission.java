package org.gitsokolek.spacex.mission.model;

import org.gitsokolek.spacex.dragon.model.DragonId;
import org.gitsokolek.spacex.utilities.developmentutilities.DevUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Mission
{
	private       MissionId     id;
	private       String        name;
	private       MissionStatus status;
	private final Set<DragonId> assignedDragons = new HashSet<>();

	private static final String NOT_IMPLEMENTED_YET = DevUtils.notImplementedYet();



	public Mission(MissionId id, String name, MissionStatus status)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public static Mission createNew(String name)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public MissionId getId()
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public String getName()
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public MissionStatus getStatus()
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public Set<DragonId> getAssignedDragons()
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public void setName(String name)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public void setStatus(MissionStatus status)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public boolean addDragon(DragonId id)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public boolean removeDragon(DragonId id)

	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public void clearAssignedDragons()
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	private static String requireNonBlank(String v)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}
}
