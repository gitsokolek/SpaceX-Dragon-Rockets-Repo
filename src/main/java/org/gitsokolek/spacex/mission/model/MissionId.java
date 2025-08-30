package org.gitsokolek.spacex.mission.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public final class MissionId implements Serializable {
	private final UUID value;

	public MissionId(UUID value) {
		this.value = Objects.requireNonNull(value);
	}

	public static MissionId random() {
		return new MissionId(UUID.randomUUID());
	}

	public UUID value() { return value; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MissionId)) return false;
		MissionId missionId = (MissionId) o;
		return value.equals(missionId.value);
	}

	@Override
	public int hashCode() { return value.hashCode(); }

	@Override
	public String toString() { return value.toString(); }
}
