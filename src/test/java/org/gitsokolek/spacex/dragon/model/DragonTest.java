
package org.gitsokolek.spacex.dragon.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DragonTest
{
	@Test
	void createNewDefaults()
	{
		Dragon d = Dragon.createNew("D1");
		assertNotNull(d.getId());
		assertEquals("D1", d.getName());
		assertEquals(DragonStatus.ON_GROUND, d.getStatus());
	}



	@Test
	void settersWork()
	{
		Dragon d = new Dragon(DragonId.random(), "A", DragonStatus.ON_GROUND);
		d.setName("B");
		d.setStatus(DragonStatus.IN_SPACE);
		assertEquals("B", d.getName());
		assertEquals(DragonStatus.IN_SPACE, d.getStatus());
	}



	@Test
	void constructorNullsThrow()
	{
		var random1 = DragonId.random();
		var random2 = DragonId.random();
		assertThrows(NullPointerException.class, () -> new Dragon(null, "x", DragonStatus.ON_GROUND));
		assertThrows(NullPointerException.class, () -> new Dragon(random1, null, DragonStatus.ON_GROUND));
		assertThrows(NullPointerException.class, () -> new Dragon(random2, "x", null));
	}



	@Test
	void setterNullsThrow()
	{
		Dragon d = Dragon.createNew("D");
		assertThrows(NullPointerException.class, () -> d.setName(null));
		assertThrows(NullPointerException.class, () -> d.setStatus(null));
	}
}
