
package org.gitsokolek.spacex.mission.model;

import org.gitsokolek.spacex.dragon.model.DragonId;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class MissionTest {
    @Test
    void createNewDefaults() {
        Mission m = Mission.createNew("M1");
        assertNotNull(m.getId());
        assertEquals("M1", m.getName());
        assertEquals(MissionStatus.SCHEDULED, m.getStatus());
        assertTrue(m.getAssignedDragons().isEmpty());
    }
    @Test
    void addRemoveAndClearAssignedDragons() {
        Mission m = Mission.createNew("M1");
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
    void assignedDragonsIsUnmodifiable() {
        Mission m = Mission.createNew("M1");
        Set<DragonId> view = m.getAssignedDragons();
        assertThrows(UnsupportedOperationException.class, () -> view.add(DragonId.random()));
    }
    @Test
    void constructorNullsThrow() {
        assertThrows(NullPointerException.class, () -> new Mission(null, "M", MissionStatus.SCHEDULED));
        assertThrows(NullPointerException.class, () -> new Mission(MissionId.random(), null, MissionStatus.SCHEDULED));
        assertThrows(NullPointerException.class, () -> new Mission(MissionId.random(), "M", null));
    }
    @Test
    void settersWork() {
        Mission m = Mission.createNew("M1");
        m.setName("M2");
        m.setStatus(MissionStatus.IN_PROGRESS);
        assertEquals("M2", m.getName());
        assertEquals(MissionStatus.IN_PROGRESS, m.getStatus());
    }
}
