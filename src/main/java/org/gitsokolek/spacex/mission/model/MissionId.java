package org.gitsokolek.spacex.mission.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record MissionId(UUID value) implements Serializable {
	public MissionId {
		Objects.requireNonNull(value);
	}

	public static MissionId random() {
		return new MissionId(UUID.randomUUID());
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
