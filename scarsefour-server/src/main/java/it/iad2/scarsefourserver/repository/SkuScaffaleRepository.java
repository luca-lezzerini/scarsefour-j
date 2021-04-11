package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.SkuScaffale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author utente
 */
@Repository
public interface SkuScaffaleRepository extends JpaRepository<SkuScaffale, Long> {
    
}
