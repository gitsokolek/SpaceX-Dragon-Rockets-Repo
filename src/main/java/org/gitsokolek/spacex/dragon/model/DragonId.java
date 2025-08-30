package org.gitsokolek.spacex.dragon.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public final class DragonId implements Serializable {
	private final UUID value;

	public DragonId(UUID value) {
		this.value = Objects.requireNonNull(value);
	}

	public static DragonId random() {
		return new DragonId(UUID.randomUUID());
	}

	public UUID value() { return value; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof DragonId)) return false;
		DragonId dragonId = (DragonId) o;
		return value.equals(dragonId.value);
	}

	@Override
	public int hashCode() { return value.hashCode(); }

	@Override
	public String toString() { return value.toString(); }
}
