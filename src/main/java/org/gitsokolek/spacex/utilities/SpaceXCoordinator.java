package org.gitsokolek.spacex.utilities;

import org.gitsokolek.spacex.dragon.model.Dragon;
import org.gitsokolek.spacex.dragon.model.DragonId;
import org.gitsokolek.spacex.dragon.model.DragonStatus;
import org.gitsokolek.spacex.dragon.repo.DragonRepository;
import org.gitsokolek.spacex.mission.model.Mission;
import org.gitsokolek.spacex.mission.model.MissionId;
import org.gitsokolek.spacex.mission.repo.MissionRepository;
import org.gitsokolek.spacex.utilities.developmentutilities.DevUtils;

public class SpaceXCoordinator {
	private final MissionRepository missionRepo;
	private final DragonRepository dragonRepo;

	private static final String NOT_IMPLEMENTED_YET = DevUtils.notImplementedYet();

	public SpaceXCoordinator(MissionRepository missionRepo, DragonRepository dragonRepo) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}

	public Mission createMission(String name) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}

	public Dragon createDragon(String name) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}

	public Mission assignDragon(MissionId missionId, DragonId dragonId) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}

	public Mission unassignDragon(MissionId missionId, DragonId dragonId) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}

	public Mission endMission(MissionId missionId) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}

	public Dragon setDragonStatus(DragonId dragonId, DragonStatus status) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}

	public Dragon changeDragonStatus(DragonId dragonId, DragonStatus status) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}

	public Mission assignDragonToMission(MissionId missionId, DragonId dragonId) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}

	public Mission unassignDragonFromMission(MissionId missionId, DragonId dragonId) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}

	public boolean deleteDragon(DragonId dragonId) {
		throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
	}

}
