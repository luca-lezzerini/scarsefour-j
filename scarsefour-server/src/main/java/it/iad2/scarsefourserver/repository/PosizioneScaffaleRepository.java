package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author matte
 */
public interface PosizioneScaffaleRepository extends JpaRepository<PosizioneScaffale, Long>{
    
    List<PosizioneScaffale> findByCodiceContains(String c);
    
}
