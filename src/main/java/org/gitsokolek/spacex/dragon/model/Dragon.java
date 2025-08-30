package org.gitsokolek.spacex.dragon.model;

import java.util.Objects;

public class Dragon {
	private DragonId id;
	private String name;
	private DragonStatus status;

	public Dragon(DragonId id, String name, DragonStatus status) {
		this.id = Objects.requireNonNull(id);
		this.name = requireNonBlank(name);
		this.status = Objects.requireNonNull(status);
	}

	public static Dragon createNew(String name) {
		return new Dragon(DragonId.random(), requireNonBlank(name), DragonStatus.ON_GROUND);
	}

	public DragonId getId() { return id; }
	public String getName() { return name; }
	public DragonStatus getStatus() { return status; }

	public void setName(String name) { this.name = requireNonBlank(name); }
	public void setStatus(DragonStatus status) { this.status = Objects.requireNonNull(status); }

	private static String requireNonBlank(String v) {
		Objects.requireNonNull(v);
		String t = v.trim();
		if (t.isEmpty()) throw new IllegalArgumentException("name must not be blank");
		return t;
	}
}
