package org.gitsokolek.spacex.utilities;

import org.gitsokolek.spacex.dragon.model.Dragon;
import org.gitsokolek.spacex.dragon.model.DragonId;
import org.gitsokolek.spacex.dragon.model.DragonStatus;
import org.gitsokolek.spacex.dragon.repo.DragonRepository;
import org.gitsokolek.spacex.mission.model.Mission;
import org.gitsokolek.spacex.mission.model.MissionId;
import org.gitsokolek.spacex.mission.model.MissionStatus;
import org.gitsokolek.spacex.mission.repo.MissionRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SpaceXCoordinator {
	private final MissionRepository missionRepo;
	private final DragonRepository dragonRepo;

	public SpaceXCoordinator(MissionRepository missionRepo, DragonRepository dragonRepo) {
		this.missionRepo = Objects.requireNonNull(missionRepo);
		this.dragonRepo = Objects.requireNonNull(dragonRepo);
	}

	public Mission createMission(String name) {
		Mission m = new Mission(MissionId.random(), name, MissionStatus.SCHEDULED);
		return missionRepo.save(m);
	}

	public Dragon createDragon(String name) {
		Dragon d = new Dragon(DragonId.random(), name, DragonStatus.ON_GROUND);
		return dragonRepo.save(d);
	}

	public Mission assignDragon(MissionId missionId, DragonId dragonId) {
		Mission m = requireMission(missionId);
		if (m.getStatus() == MissionStatus.ENDED) throw new IllegalStateException("Mission already ended");
		Dragon d = requireDragon(dragonId);
		Optional<Mission> other = findMissionByDragon(dragonId);
		if (other.isPresent() && !other.get().getId().equals(missionId)) throw new IllegalStateException("Dragon already assigned to another mission");
		m.addDragon(dragonId);
		missionRepo.save(m);
		updateMissionStatus(m);
		return m;
	}


	public Mission unassignDragon(MissionId missionId, DragonId dragonId) {
		Mission m = requireMission(missionId);
		Dragon d = dragonRepo.findById(dragonId).orElse(null);
		boolean changed = m.removeDragon(dragonId);
		if (changed) {
			missionRepo.save(m);
			if (d != null && d.getStatus() == DragonStatus.IN_SPACE) {
				d.setStatus(DragonStatus.ON_GROUND);
				dragonRepo.save(d);
			}
			updateMissionStatus(m);
		}
		return m;
	}

	public Mission endMission(MissionId missionId) {
		Mission m = requireMission(missionId);
		if (m.getStatus() == MissionStatus.ENDED) return m;
		for (DragonId did : m.getAssignedDragons()) {
			dragonRepo.findById(did).ifPresent(d -> {
				if (d.getStatus() == DragonStatus.IN_SPACE) {
					d.setStatus(DragonStatus.ON_GROUND);
					dragonRepo.save(d);
				}
			});
		}
		m.clearAssignedDragons();
		m.setStatus(MissionStatus.ENDED);
		return missionRepo.save(m);
	}

	public Dragon setDragonStatus(DragonId dragonId, DragonStatus status) {
		Dragon d = requireDragon(dragonId);
		d.setStatus(Objects.requireNonNull(status));
		dragonRepo.save(d);
		for (Mission m : missionRepo.findAll()) {
			if (m.getAssignedDragons().contains(dragonId) && m.getStatus() != MissionStatus.ENDED) {
				updateMissionStatus(m);
			}
		}
		return d;
	}
	@Deprecated
	public Dragon changeDragonStatus(DragonId dragonId, DragonStatus status) {
		return setDragonStatus(dragonId, status);
	}
	@Deprecated
	public Mission assignDragonToMission(MissionId missionId, DragonId dragonId) {
		return assignDragon(missionId, dragonId);
	}
	@Deprecated
	public Mission unassignDragonFromMission(MissionId missionId, DragonId dragonId) {
		return unassignDragon(missionId, dragonId);
	}

	public boolean deleteDragon(DragonId dragonId) {
		Objects.requireNonNull(dragonId);
		for (Mission m : missionRepo.findAll()) {
			if (m.getAssignedDragons().contains(dragonId)) {
				m.removeDragon(dragonId);
				missionRepo.save(m);
				updateMissionStatus(m);
			}
		}
		return dragonRepo.deleteById(dragonId);
	}

	private void updateMissionStatus(Mission m) {
		if (m.getStatus() == MissionStatus.ENDED) return;
		MissionStatus computed = computeStatusForMission(m);
		if (computed != m.getStatus()) {
			m.setStatus(computed);
			missionRepo.save(m);
		}
	}

	private MissionStatus computeStatusForMission(Mission m) {
		boolean anyExisting = false;
		boolean anyInRepair = false;
		boolean anyInSpace = false;

		for (DragonId did : m.getAssignedDragons()) {
			Optional<Dragon> od = dragonRepo.findById(did);
			if (od.isPresent()) {
				anyExisting = true;
				DragonStatus s = od.get().getStatus();
				if (s == DragonStatus.IN_REPAIR) anyInRepair = true;
				if (s == DragonStatus.IN_SPACE) anyInSpace = true;
			}
		}

		if (!anyExisting) return MissionStatus.SCHEDULED;
		if (anyInRepair) return MissionStatus.PENDING;
		if (anyInSpace) return MissionStatus.IN_PROGRESS;
		return MissionStatus.PENDING;
	}


	private Mission requireMission(MissionId id) {
		return missionRepo.findById(Objects.requireNonNull(id)).orElseThrow(() -> new IllegalArgumentException("Mission not found"));
	}

	private Dragon requireDragon(DragonId id) {
		return dragonRepo.findById(Objects.requireNonNull(id)).orElseThrow(() -> new IllegalArgumentException("Dragon not found"));
	}

	private Optional<Mission> findMissionByDragon(DragonId id) {
		List<Mission> all = missionRepo.findAll();
		for (Mission m : all) if (m.getAssignedDragons().contains(id)) return Optional.of(m);
		return Optional.empty();
	}
}
