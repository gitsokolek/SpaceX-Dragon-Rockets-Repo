
package org.gitsokolek.spacex.mission.model;

import org.gitsokolek.spacex.dragon.model.DragonId;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MissionTest
{
	@Test
	void createNewDefaults()
	{
		Mission m = Mission.createNew("M1");
		assertNotNull(m.getId());
		assertEquals("M1", m.getName());
		assertEquals(MissionStatus.SCHEDULED, m.getStatus());
		assertTrue(m.getAssignedDragons().isEmpty());
	}



	@Test
	void addRemoveAndClearAssignedDragons()
	{
		Mission  m  = Mission.createNew("M1");
		DragonId d1 = DragonId.random();
		DragonId d2 = DragonId.random();
		assertTrue(m.addDragon(d1));
		assertFalse(m.addDragon(d1));
		assertTrue(m.getAssignedDragons().contains(d1));
		assertTrue(m.addDragon(d2));
		assertTrue(m.removeDragon(d1));
		assertFalse(m.getAssignedDragons().contains(d1));
		m.clearAssignedDragons();
		assertTrue(m.getAssignedDragons().isEmpty());
	}



	@Test
	void assignedDragonsIsUnmodifiable()
	{
		Mission       m    = Mission.createNew("M1");
		Set<DragonId> view = m.getAssignedDragons();
		var random = DragonId.random();
		assertThrows(UnsupportedOperationException.class, () -> view.add(random));
	}



	@Test
	void constructorNullsThrow()
	{
		var random1 = MissionId.random();
		var random2 = MissionId.random();
		assertThrows(NullPointerException.class, () -> new Mission(null, "M", MissionStatus.SCHEDULED));
		assertThrows(NullPointerException.class, () -> new Mission(random1, null, MissionStatus.SCHEDULED));
		assertThrows(NullPointerException.class, () -> new Mission(random2, "M", null));
	}



	@Test
	void settersWork()
	{
		Mission m = Mission.createNew("M1");
		m.setName("M2");
		m.setStatus(MissionStatus.IN_PROGRESS);
		assertEquals("M2", m.getName());
		assertEquals(MissionStatus.IN_PROGRESS, m.getStatus());
	}
}
