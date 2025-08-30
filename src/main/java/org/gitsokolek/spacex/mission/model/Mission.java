package org.gitsokolek.spacex.mission.model;

import org.gitsokolek.spacex.dragon.model.DragonId;

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



	public Mission(MissionId id, String name, MissionStatus status)
	{
		this.id     = Objects.requireNonNull(id);
		this.name   = requireNonBlank(name);
		this.status = Objects.requireNonNull(status);
	}



	public static Mission createNew(String name)
	{
		return new Mission(MissionId.random(), requireNonBlank(name), MissionStatus.SCHEDULED);
	}



	public MissionId getId()
	{
		return id;
	}



	public String getName()
	{
		return name;
	}



	public MissionStatus getStatus()
	{
		return status;
	}



	public Set<DragonId> getAssignedDragons()
	{
		return Collections.unmodifiableSet(assignedDragons);
	}



	public void setName(String name)
	{
		this.name = requireNonBlank(name);
	}



	public void setStatus(MissionStatus status)
	{
		this.status = Objects.requireNonNull(status);
	}



	public boolean addDragon(DragonId id)
	{
		if (status == MissionStatus.ENDED)
		{throw new IllegalStateException("Cannot assign to ENDED mission");}
		return assignedDragons.add(Objects.requireNonNull(id));
	}



	public boolean removeDragon(DragonId id)
	{
		return assignedDragons.remove(Objects.requireNonNull(id));
	}



	public void clearAssignedDragons()
	{
		assignedDragons.clear();
	}



	private static String requireNonBlank(String v)
	{
		Objects.requireNonNull(v);
		String t = v.trim();
		if (t.isEmpty())
		{throw new IllegalArgumentException("name must not be blank");}
		return t;
	}
}
