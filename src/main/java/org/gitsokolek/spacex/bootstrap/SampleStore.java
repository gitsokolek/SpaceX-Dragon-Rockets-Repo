package org.gitsokolek.spacex.bootstrap;

import org.gitsokolek.spacex.dragon.model.Dragon;
import org.gitsokolek.spacex.dragon.model.DragonStatus;
import org.gitsokolek.spacex.dragon.repo.DragonRepository;
import org.gitsokolek.spacex.dragon.repo.InMemoryDragonRepository;
import org.gitsokolek.spacex.mission.model.Mission;
import org.gitsokolek.spacex.mission.repo.InMemoryMissionRepository;
import org.gitsokolek.spacex.mission.repo.MissionRepository;
import org.gitsokolek.spacex.utilities.SpaceXCoordinator;

public final class SampleStore {
    private final MissionRepository missionRepo;
    private final DragonRepository dragonRepo;
    private final SpaceXCoordinator coordinator;

    private SampleStore(MissionRepository missionRepo, DragonRepository dragonRepo, SpaceXCoordinator coordinator) {
        this.missionRepo = missionRepo;
        this.dragonRepo = dragonRepo;
        this.coordinator = coordinator;
    }

	public static SampleStore create() {
		MissionRepository missionRepo = new InMemoryMissionRepository();
		DragonRepository dragonRepo = new InMemoryDragonRepository();
		SpaceXCoordinator coordinator = new SpaceXCoordinator(missionRepo, dragonRepo);

		Mission transit = Mission.createNew("Transit");
		Mission luna1 = Mission.createNew("Luna1");
		Mission verticalLanding = Mission.createNew("Vertical Landing");
		Mission mars = Mission.createNew("Mars");
		Mission luna2 = Mission.createNew("Luna2");
		Mission doubleLanding = Mission.createNew("Double Landing");

		missionRepo.save(transit);
		missionRepo.save(luna1);
		missionRepo.save(verticalLanding);
		missionRepo.save(mars);
		missionRepo.save(luna2);
		missionRepo.save(doubleLanding);

		Dragon redDragon = Dragon.createNew("Red Dragon");
		Dragon dragonXL = Dragon.createNew("Dragon XL");
		Dragon falconHeavy = Dragon.createNew("Falcon Heavy");
		Dragon dragon1 = Dragon.createNew("Dragon 1");
		Dragon dragon2 = Dragon.createNew("Dragon 2");

		dragonRepo.save(redDragon);
		dragonRepo.save(dragonXL);
		dragonRepo.save(falconHeavy);
		dragonRepo.save(dragon1);
		dragonRepo.save(dragon2);

		coordinator.assignDragon(transit.getId(), redDragon.getId());
		coordinator.assignDragon(transit.getId(), dragonXL.getId());
		coordinator.assignDragon(transit.getId(), falconHeavy.getId());
		coordinator.setDragonStatus(dragonXL.getId(), DragonStatus.IN_SPACE);
		coordinator.setDragonStatus(falconHeavy.getId(), DragonStatus.IN_SPACE);

		coordinator.assignDragon(luna1.getId(), dragon1.getId());
		coordinator.assignDragon(luna1.getId(), dragon2.getId());

		coordinator.endMission(verticalLanding.getId());
		coordinator.endMission(doubleLanding.getId());

		return new SampleStore(missionRepo, dragonRepo, coordinator);
	}


	public MissionRepository missionRepo() { return missionRepo; }
    public DragonRepository dragonRepo() { return dragonRepo; }
    public SpaceXCoordinator coordinator() { return coordinator; }
}
