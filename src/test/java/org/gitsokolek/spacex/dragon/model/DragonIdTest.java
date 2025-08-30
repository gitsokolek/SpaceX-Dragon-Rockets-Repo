
package org.gitsokolek.spacex.dragon.model;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class DragonIdTest {
    @Test
    void randomGeneratesUnique() {
        DragonId a = DragonId.random();
        DragonId b = DragonId.random();
        assertNotNull(a);
        assertNotNull(b);
        assertNotEquals(a, b);
    }
    @Test
    void equalsAndHashCodeForSameUuid() {
        UUID u = UUID.randomUUID();
        DragonId a = new DragonId(u);
        DragonId b = new DragonId(u);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
    @Test
    void toStringIsUuidString() {
        UUID u = UUID.randomUUID();
        DragonId a = new DragonId(u);
        assertEquals(u.toString(), a.toString());
    }
    @Test
    void nullConstructorArgumentThrows() {
        assertThrows(NullPointerException.class, () -> new DragonId(null));
    }
}
