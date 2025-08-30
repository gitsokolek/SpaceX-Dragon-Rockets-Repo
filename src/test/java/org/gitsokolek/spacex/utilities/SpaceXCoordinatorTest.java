package org.gitsokolek.spacex.utilities;

import org.gitsokolek.spacex.dragon.model.Dragon;
import org.gitsokolek.spacex.dragon.model.DragonId;
import org.gitsokolek.spacex.dragon.model.DragonStatus;
import org.gitsokolek.spacex.dragon.repo.InMemoryDragonRepository;
import org.gitsokolek.spacex.mission.model.Mission;
import org.gitsokolek.spacex.mission.model.MissionId;
import org.gitsokolek.spacex.mission.model.MissionStatus;
import org.gitsokolek.spacex.mission.repo.InMemoryMissionRepository;
import org.gitsokolek.spacex.mission.repo.MissionRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpaceXCoordinatorTest
{
	@Test
	void statusTransitionsOnAssignAndRepair()
	{
		MissionRepository        mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator        coord = new SpaceXCoordinator(mRepo, dRepo);

		Mission m  = coord.createMission("M");
		Dragon  d1 = coord.createDragon("D1");
		Dragon  d2 = coord.createDragon("D2");

		coord.assignDragon(m.getId(), d1.getId());
		assertEquals(MissionStatus.PENDING, mRepo.findById(m.getId()).get().getStatus());

		coord.assignDragon(m.getId(), d2.getId());
		assertEquals(MissionStatus.PENDING, mRepo.findById(m.getId()).get().getStatus());

		coord.changeDragonStatus(d2.getId(), DragonStatus.IN_REPAIR);
		assertEquals(MissionStatus.PENDING, mRepo.findById(m.getId()).get().getStatus());

		coord.changeDragonStatus(d2.getId(), DragonStatus.ON_GROUND);
		assertEquals(MissionStatus.PENDING, mRepo.findById(m.getId()).get().getStatus());

		coord.changeDragonStatus(d1.getId(), DragonStatus.IN_SPACE);
		assertEquals(MissionStatus.IN_PROGRESS, mRepo.findById(m.getId()).get().getStatus());
	}



	@Test
	void allAssignedButDeletedLeadsToScheduled()
	{
		InMemoryMissionRepository mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository  dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator         coord = new SpaceXCoordinator(mRepo, dRepo);

		Mission m = coord.createMission("K");
		Dragon  d = coord.createDragon("DX");
		coord.assignDragon(m.getId(), d.getId());
		assertEquals(MissionStatus.PENDING, mRepo.findById(m.getId()).get().getStatus());

		assertTrue(coord.deleteDragon(d.getId()));
		assertEquals(MissionStatus.SCHEDULED, mRepo.findById(m.getId()).get().getStatus());
	}



	@Test
	void cannotAssignToEndedMission()
	{
		InMemoryMissionRepository mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository  dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator         coord = new SpaceXCoordinator(mRepo, dRepo);

		Mission m = coord.createMission("E");
		coord.endMission(m.getId());
		Dragon d = coord.createDragon("Dx");
		var mId = m.getId();
		var dId = d.getId();
		assertThrows(IllegalStateException.class, () -> coord.assignDragon(mId, dId));
	}



	@Test
	void assignToMissingMissionThrows()
	{
		InMemoryMissionRepository mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository  dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator         coord = new SpaceXCoordinator(mRepo, dRepo);

		Dragon    d       = coord.createDragon("D");
		MissionId missing = MissionId.random();
		var dId = d.getId();
		assertThrows(IllegalArgumentException.class, () -> coord.assignDragon(missing, dId));
	}



	@Test
	void assignMissingDragonThrows()
	{
		InMemoryMissionRepository mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository  dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator         coord = new SpaceXCoordinator(mRepo, dRepo);

		Mission  m       = coord.createMission("M");
		DragonId missing = DragonId.random();
		var mId = m.getId();
		assertThrows(IllegalArgumentException.class, () -> coord.assignDragon(mId, missing));
	}



	@Test
	void unassignOnNotAssignedIsNoOp()
	{
		InMemoryMissionRepository mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository  dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator         coord = new SpaceXCoordinator(mRepo, dRepo);

		Mission m = coord.createMission("M");
		Dragon  d = coord.createDragon("D");
		coord.unassignDragon(m.getId(), d.getId());
		assertEquals(MissionStatus.SCHEDULED, mRepo.findById(m.getId()).get().getStatus());
	}



	@Test
	void endMissionIsIdempotent()
	{
		InMemoryMissionRepository mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository  dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator         coord = new SpaceXCoordinator(mRepo, dRepo);

		Mission m = coord.createMission("M");
		coord.endMission(m.getId());
		coord.endMission(m.getId());
		assertEquals(MissionStatus.ENDED, mRepo.findById(m.getId()).get().getStatus());
	}



	@Test
	void allOnGroundYieldsPending()
	{
		InMemoryMissionRepository mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository  dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator         coord = new SpaceXCoordinator(mRepo, dRepo);

		Mission m  = coord.createMission("M");
		Dragon  d1 = coord.createDragon("D1");
		Dragon  d2 = coord.createDragon("D2");
		coord.assignDragon(m.getId(), d1.getId());
		coord.assignDragon(m.getId(), d2.getId());
		assertEquals(MissionStatus.PENDING, mRepo.findById(m.getId()).get().getStatus());
	}



	@Test
	void mixedInSpaceAndInRepairYieldsPending()
	{
		InMemoryMissionRepository mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository  dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator         coord = new SpaceXCoordinator(mRepo, dRepo);

		Mission m  = coord.createMission("M");
		Dragon  d1 = coord.createDragon("D1");
		Dragon  d2 = coord.createDragon("D2");
		coord.assignDragon(m.getId(), d1.getId());
		coord.assignDragon(m.getId(), d2.getId());
		coord.setDragonStatus(d1.getId(), DragonStatus.IN_SPACE);
		coord.setDragonStatus(d2.getId(), DragonStatus.IN_REPAIR);
		assertEquals(MissionStatus.PENDING, mRepo.findById(m.getId()).get().getStatus());
	}



	@Test
	void allInSpaceYieldsInProgress()
	{
		InMemoryMissionRepository mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository  dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator         coord = new SpaceXCoordinator(mRepo, dRepo);

		Mission m  = coord.createMission("M");
		Dragon  d1 = coord.createDragon("D1");
		Dragon  d2 = coord.createDragon("D2");
		coord.assignDragon(m.getId(), d1.getId());
		coord.assignDragon(m.getId(), d2.getId());
		coord.setDragonStatus(d1.getId(), DragonStatus.IN_SPACE);
		coord.setDragonStatus(d2.getId(), DragonStatus.IN_SPACE);
		assertEquals(MissionStatus.IN_PROGRESS, mRepo.findById(m.getId()).get().getStatus());
	}



	@Test
	void setDragonStatusWithoutMissionIsSafe()
	{
		InMemoryMissionRepository mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository  dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator         coord = new SpaceXCoordinator(mRepo, dRepo);

		Dragon d = coord.createDragon("D");
		coord.setDragonStatus(d.getId(), DragonStatus.IN_REPAIR);
		assertEquals(DragonStatus.IN_REPAIR, dRepo.findById(d.getId()).get().getStatus());
	}



	@Test
	void deleteDragonInRepairRecomputesMission()
	{
		InMemoryMissionRepository mRepo = new InMemoryMissionRepository();
		InMemoryDragonRepository  dRepo = new InMemoryDragonRepository();
		SpaceXCoordinator         coord = new SpaceXCoordinator(mRepo, dRepo);

		Mission m  = coord.createMission("M");
		Dragon  d1 = coord.createDragon("D1");
		Dragon  d2 = coord.createDragon("D2");
		coord.assignDragon(m.getId(), d1.getId());
		coord.assignDragon(m.getId(), d2.getId());
		coord.setDragonStatus(d1.getId(), DragonStatus.IN_REPAIR);
		assertEquals(MissionStatus.PENDING, mRepo.findById(m.getId()).get().getStatus());
		assertTrue(coord.deleteDragon(d1.getId()));
		assertEquals(MissionStatus.PENDING, mRepo.findById(m.getId()).get().getStatus());
		assertTrue(coord.deleteDragon(d2.getId()));
		assertEquals(MissionStatus.SCHEDULED, mRepo.findById(m.getId()).get().getStatus());
	}


}
