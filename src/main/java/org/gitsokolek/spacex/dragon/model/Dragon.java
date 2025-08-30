package org.gitsokolek.spacex.dragon.model;

import org.gitsokolek.spacex.utilities.developmentutilities.DevUtils;

import java.util.Objects;

public class Dragon
{
	private DragonId     id;
	private String       name;
	private DragonStatus status;

	private static final String NOT_IMPLEMENTED_YET = DevUtils.notImplementedYet();



	public Dragon(DragonId id, String name, DragonStatus status)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public static Dragon createNew(String name)
	{
		return new Dragon(DragonId.random(), requireNonBlank(name), DragonStatus.ON_GROUND);
	}



	public DragonId getId()
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public String getName()
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public DragonStatus getStatus()
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public void setName(String name)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	public void setStatus(DragonStatus status)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}



	private static String requireNonBlank(String v)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}
}
