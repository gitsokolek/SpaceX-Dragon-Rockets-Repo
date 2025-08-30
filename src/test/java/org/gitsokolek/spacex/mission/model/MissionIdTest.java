
package org.gitsokolek.spacex.mission.model;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class MissionIdTest {
    @Test
    void randomGeneratesUnique() {
        MissionId a = MissionId.random();
        MissionId b = MissionId.random();
        assertNotEquals(a, b);
    }
    @Test
    void equalsAndHashCodeForSameUuid() {
        UUID u = UUID.randomUUID();
        MissionId a = new MissionId(u);
        MissionId b = new MissionId(u);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
    @Test
    void toStringIsUuidString() {
        UUID u = UUID.randomUUID();
        MissionId a = new MissionId(u);
        assertEquals(u.toString(), a.toString());
    }
    @Test
    void nullConstructorArgumentThrows() {
        assertThrows(NullPointerException.class, () -> new MissionId(null));
    }
}
