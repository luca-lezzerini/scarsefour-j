package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.Giacenza;
import it.iad2.scarsefourserver.model.PosizioneScaffale;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author matte
 */
public interface PosizioneScaffaleRepository extends JpaRepository<PosizioneScaffale, Long> {

    List<PosizioneScaffale> findByCodiceContains(String c);
    
    @Query(
            "SELECT new it.iad2.scarsefourserver.model.Giacenza"
            + " (p.codice, p.descrizione, s.giacenza, s.scortaMinima) FROM SkuScaffale s"
            + " JOIN s.prodotto p"
            + " JOIN s.posizioneScaffale ps"
            + " WHERE ps.id = :id")
    List<Giacenza> visualizzaGiacenzaProdotti(@Param("id") Long id);

    PosizioneScaffale findByCodiceEquals(String codice);
    
    @Query("SELECT p FROM PosizioneScaffale p WHERE p.codice Like %:codice% ")
    List<PosizioneScaffale> contieneCode(@Param("codice")String criterio);
    
   
}
