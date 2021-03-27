package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.Cassiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CassiereRepository extends JpaRepository<Cassiere, Long> {
    
}
