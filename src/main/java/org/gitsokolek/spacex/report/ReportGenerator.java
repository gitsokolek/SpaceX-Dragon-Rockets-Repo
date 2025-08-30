package org.gitsokolek.spacex.report;

import org.gitsokolek.spacex.dragon.model.DragonId;
import org.gitsokolek.spacex.dragon.model.DragonStatus;
import org.gitsokolek.spacex.dragon.repo.DragonRepository;
import org.gitsokolek.spacex.mission.model.Mission;
import org.gitsokolek.spacex.mission.model.MissionStatus;
import org.gitsokolek.spacex.mission.repo.MissionRepository;

public class ReportGenerator
{


	public String generate(MissionRepository missionRepo, DragonRepository dragonRepo)
	{
		StringBuilder sb = new StringBuilder();
		for (Mission m : missionRepo.findAll())
		{
			sb.append("• ")
			  .append(m.getName())
			  .append(" – ")
			  .append(formatMissionStatus(m.getStatus()))
			  .append(" – Dragons: ")
			  .append(m.getAssignedDragons().size())
			  .append("\n");

			for (DragonId id : m.getAssignedDragons())
			{
				dragonRepo.findById(id).ifPresent(d ->
														  sb.append("\to ")
															.append(d.getName())
															.append(" – ")
															.append(formatDragonStatus(d.getStatus()))
															.append("\n")
												 );
			}
		}
		return sb.toString();
	}



	private static String formatMissionStatus(MissionStatus s)
	{
		return switch (s)
		{
			case SCHEDULED -> "Scheduled";
			case PENDING -> "Pending";
			case IN_PROGRESS -> "In progress";
			case ENDED -> "Ended";
		};
	}



	private static String formatDragonStatus(DragonStatus s)
	{
		return switch (s)
		{
			case ON_GROUND -> "On ground";
			case IN_SPACE -> "In space";
			case IN_REPAIR -> "In repair";
		};
	}
}
