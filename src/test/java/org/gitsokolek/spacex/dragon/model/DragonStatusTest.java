
package org.gitsokolek.spacex.dragon.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DragonStatusTest
{
	@Test
	void hasExpectedValues()
	{
		assertEquals(3, DragonStatus.values().length);
		assertNotNull(DragonStatus.valueOf("ON_GROUND"));
		assertNotNull(DragonStatus.valueOf("IN_SPACE"));
		assertNotNull(DragonStatus.valueOf("IN_REPAIR"));
	}
}
