package org.gitsokolek.spacex.utilities;

import org.gitsokolek.spacex.dragon.model.*;
import org.gitsokolek.spacex.dragon.repo.InMemoryDragonRepository;
import org.gitsokolek.spacex.mission.model.*;
import org.gitsokolek.spacex.mission.repo.InMemoryMissionRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpaceXCoordinatorExclusivityTest {
	@Test void dragonCannotBeAssignedToTwoMissions() {
		InMemoryMissionRepository mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator coord = new SpaceXCoordinator(mRepo, dRepo);

		Mission a = coord.createMission("A");
		Mission b = coord.createMission("B");
		Dragon d = coord.createDragon("D");

		coord.assignDragon(a.getId(), d.getId());
		assertEquals(MissionStatus.PENDING, mRepo.findById(a.getId()).get().getStatus());
		assertEquals(MissionStatus.SCHEDULED, mRepo.findById(b.getId()).get().getStatus());

		assertThrows(IllegalStateException.class, () -> coord.assignDragon(b.getId(), d.getId()));
		assertEquals(MissionStatus.PENDING, mRepo.findById(a.getId()).get().getStatus());
		assertEquals(MissionStatus.SCHEDULED, mRepo.findById(b.getId()).get().getStatus());
	}


	@Test void dragonCanBeReassignedAfterUnassign() {
		InMemoryMissionRepository mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator coord = new SpaceXCoordinator(mRepo, dRepo);

		Mission a = coord.createMission("A1");
		Mission b = coord.createMission("B1");
		Dragon d = coord.createDragon("DX");

		coord.assignDragon(a.getId(), d.getId());
		coord.unassignDragon(a.getId(), d.getId());
		assertEquals(MissionStatus.SCHEDULED, mRepo.findById(a.getId()).get().getStatus());

		coord.assignDragon(b.getId(), d.getId());
		assertEquals(MissionStatus.PENDING, mRepo.findById(b.getId()).get().getStatus());
	}

	@Test void doubleAssignSameDragonIsIdempotentForStatus() {
		InMemoryMissionRepository mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator coord = new SpaceXCoordinator(mRepo, dRepo);

		Mission m = coord.createMission("M");
		Dragon d = coord.createDragon("D");
		coord.assignDragon(m.getId(), d.getId());
		assertEquals(MissionStatus.PENDING, mRepo.findById(m.getId()).get().getStatus());
		coord.assignDragon(m.getId(), d.getId());
		assertEquals(MissionStatus.PENDING, mRepo.findById(m.getId()).get().getStatus());
	}

}
