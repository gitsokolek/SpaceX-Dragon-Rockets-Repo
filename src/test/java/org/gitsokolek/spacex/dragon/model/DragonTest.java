
package org.gitsokolek.spacex.dragon.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DragonTest {
    @Test
    void createNewDefaults() {
        Dragon d = Dragon.createNew("D1");
        assertNotNull(d.getId());
        assertEquals("D1", d.getName());
        assertEquals(DragonStatus.ON_GROUND, d.getStatus());
    }
    @Test
    void settersWork() {
        Dragon d = new Dragon(DragonId.random(), "A", DragonStatus.ON_GROUND);
        d.setName("B");
        d.setStatus(DragonStatus.IN_SPACE);
        assertEquals("B", d.getName());
        assertEquals(DragonStatus.IN_SPACE, d.getStatus());
    }
    @Test
    void constructorNullsThrow() {
        assertThrows(NullPointerException.class, () -> new Dragon(null, "x", DragonStatus.ON_GROUND));
        assertThrows(NullPointerException.class, () -> new Dragon(DragonId.random(), null, DragonStatus.ON_GROUND));
        assertThrows(NullPointerException.class, () -> new Dragon(DragonId.random(), "x", null));
    }
    @Test
    void setterNullsThrow() {
        Dragon d = Dragon.createNew("D");
        assertThrows(NullPointerException.class, () -> d.setName(null));
        assertThrows(NullPointerException.class, () -> d.setStatus(null));
    }
}
