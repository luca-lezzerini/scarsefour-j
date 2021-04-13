package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.Giacenza;
import it.iad2.scarsefourserver.model.PosizioneScaffale;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author matte
 */
public interface PosizioneScaffaleRepository extends JpaRepository<PosizioneScaffale, Long> {

    List<PosizioneScaffale> findByCodiceContains(String c);

    @Query(
//            "SELECT p.codice, p.descrizione, s.giacenza, s.scortaMinima FROM SkuScaffale s"
//            + " JOIN s.prodotto p"
//            + " JOIN s.posizioneScaffale ps"
//            + " WHERE ps.id = ?1"
            "SELECT p,s FROM SkuScaffale s"
            + " JOIN s.prodotto p"
            + " JOIN s.posizioneScaffale ps"
            + " WHERE ps.id = ?1")
    List<Object[]> visualizzaGiacenzaProdotti(Long id);

}
