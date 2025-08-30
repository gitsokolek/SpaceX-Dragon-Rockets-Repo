package org.gitsokolek.spacex.mission.model;

import org.gitsokolek.spacex.utilities.developmentutilities.DevUtils;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public final class MissionId implements Serializable
{
	private final UUID value;

	private static final String NOT_IMPLEMENTED_YET = DevUtils.notImplementedYet();



	public MissionId(UUID value)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public static MissionId random()
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public UUID value()
	{
		return value;
	}



	@Override
	public boolean equals(Object o)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	@Override
	public int hashCode()
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	@Override
	public String toString()
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}
}
