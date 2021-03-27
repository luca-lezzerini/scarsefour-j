package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.Cassiere;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CassiereRepository extends JpaRepository<Cassiere, Long> {

    @Query("select c from Cassiere c where c.cognome like :criterio%")
    List<Cassiere> cercaLikeCognome(@Param("criterio") String criterio);
}
