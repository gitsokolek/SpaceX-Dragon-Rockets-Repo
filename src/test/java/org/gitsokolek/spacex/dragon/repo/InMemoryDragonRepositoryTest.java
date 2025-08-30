package org.gitsokolek.spacex.dragon.repo;

import org.gitsokolek.spacex.dragon.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryDragonRepositoryTest {
    @Test void saveAndFind() {
        InMemoryDragonRepository repo = new InMemoryDragonRepository();
        Dragon d = Dragon.createNew("RepoD");
        repo.save(d);
        assertTrue(repo.existsByName("RepoD"));
        assertTrue(repo.existsByName(" RepoD "));
        assertTrue(repo.findByName(" RepoD ").isPresent());
        assertEquals(d.getId(), repo.findByName("RepoD").get().getId());
        assertTrue(repo.findById(d.getId()).isPresent());
    }

    @Test void findByStatusRejectsNull() {
        InMemoryDragonRepository repo = new InMemoryDragonRepository();
        assertThrows(NullPointerException.class, () -> repo.findByStatus(null));
    }

    @Test void deleteByIdRemovesFromIndex() {
        InMemoryDragonRepository repo = new InMemoryDragonRepository();
        Dragon d = repo.save(Dragon.createNew("X"));
        assertTrue(repo.existsByName("X"));
        assertTrue(repo.deleteById(d.getId()));
        assertFalse(repo.existsByName("X"));
        assertTrue(repo.findAll().isEmpty());
    }

    @Test void findByStatusFilters() {
        InMemoryDragonRepository repo = new InMemoryDragonRepository();
        Dragon a = repo.save(Dragon.createNew("A"));
        Dragon b = repo.save(Dragon.createNew("B"));
        repo.save(Dragon.createNew("C"));
        a.setStatus(DragonStatus.IN_REPAIR);
        b.setStatus(DragonStatus.IN_SPACE);
        List<Dragon> repair = repo.findByStatus(DragonStatus.IN_REPAIR);
        assertEquals(1, repair.size());
        assertEquals("A", repair.get(0).getName());
    }
}
