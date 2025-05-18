package org.esprit.menumicroservice.Repo;

import org.esprit.menumicroservice.Entities.Plat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlatRepository extends JpaRepository<Plat, Long>, JpaSpecificationExecutor<Plat> {
}
