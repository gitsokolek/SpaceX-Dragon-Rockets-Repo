package org.gitsokolek.spacex.dragon.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record DragonId(UUID value) implements Serializable {
	public DragonId {
		Objects.requireNonNull(value);
	}

	public static DragonId random() {
		return new DragonId(UUID.randomUUID());
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
