package org.gitsokolek.spacex.dragon.model;

import org.gitsokolek.spacex.utilities.developmentutilities.DevUtils;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public final class DragonId implements Serializable {
    private final UUID value;

	private static final String NOT_IMPLEMENTED_YET = DevUtils.notImplementedYet();



    public DragonId(UUID value)
	{
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
    }

    public static DragonId random() {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
    }

    public UUID value() { return value; }

    @Override
    public boolean equals(Object o) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
    }

    @Override
    public int hashCode() {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}

    @Override
    public String toString() {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}
}
