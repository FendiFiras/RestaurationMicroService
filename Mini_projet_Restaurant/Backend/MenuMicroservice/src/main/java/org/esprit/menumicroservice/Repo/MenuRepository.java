package org.esprit.menumicroservice.Repo;

import org.esprit.menumicroservice.Entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}

