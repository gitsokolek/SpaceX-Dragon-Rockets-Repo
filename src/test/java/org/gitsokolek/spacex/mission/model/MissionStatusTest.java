
package org.gitsokolek.spacex.mission.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MissionStatusTest
{
	@Test
	void hasExpectedValues()
	{
		assertEquals(4, MissionStatus.values().length);
		assertNotNull(MissionStatus.valueOf("SCHEDULED"));
		assertNotNull(MissionStatus.valueOf("PENDING"));
		assertNotNull(MissionStatus.valueOf("IN_PROGRESS"));
		assertNotNull(MissionStatus.valueOf("ENDED"));
	}
}
