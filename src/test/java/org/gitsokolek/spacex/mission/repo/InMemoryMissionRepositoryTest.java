package org.gitsokolek.spacex.mission.repo;

import org.gitsokolek.spacex.mission.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryMissionRepositoryTest {
    @Test void saveAndFind() {
        InMemoryMissionRepository repo = new InMemoryMissionRepository();
        Mission m = Mission.createNew("RepoM");
        repo.save(m);
        assertTrue(repo.existsByName("RepoM"));
        assertTrue(repo.existsByName(" RepoM "));
        assertTrue(repo.findByName(" RepoM ").isPresent());
        assertEquals(m.getId(), repo.findByName("RepoM").get().getId());
        assertTrue(repo.findById(m.getId()).isPresent());
    }

    @Test void findByStatusRejectsNull() {
        InMemoryMissionRepository repo = new InMemoryMissionRepository();
        assertThrows(NullPointerException.class, () -> repo.findByStatus(null));
    }

    @Test void deleteByIdRemovesFromIndex() {
        InMemoryMissionRepository repo = new InMemoryMissionRepository();
        Mission m = repo.save(Mission.createNew("X"));
        assertTrue(repo.existsByName("X"));
        assertTrue(repo.deleteById(m.getId()));
        assertFalse(repo.existsByName("X"));
        assertTrue(repo.findAll().isEmpty());
    }

    @Test void findByStatusFilters() {
        InMemoryMissionRepository repo = new InMemoryMissionRepository();
        Mission a = repo.save(Mission.createNew("A"));
        Mission b = repo.save(Mission.createNew("B"));
        repo.save(Mission.createNew("C"));
        a.setStatus(MissionStatus.PENDING);
        b.setStatus(MissionStatus.IN_PROGRESS);
        List<Mission> pending = repo.findByStatus(MissionStatus.PENDING);
        assertEquals(1, pending.size());
        assertEquals("A", pending.get(0).getName());
    }
}
