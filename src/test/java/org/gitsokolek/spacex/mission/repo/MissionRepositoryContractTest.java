package org.gitsokolek.spacex.mission.repo;

import org.gitsokolek.spacex.mission.model.Mission;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MissionRepositoryContractTest
{
	@Test
	void nameIndexTrimmed()
	{
		MissionRepository repo = new InMemoryMissionRepository();
		Mission           m    = repo.save(Mission.createNew("Alpha"));
		assertTrue(repo.existsByName(" Alpha "));
		assertTrue(repo.findByName(" Alpha ").isPresent());
		assertEquals(m.getId(), repo.findByName("Alpha").get().getId());
	}
}
