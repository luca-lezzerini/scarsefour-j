package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
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
            value = "select p.descrizione, p.codice, sku.giacenza, sku.scorta_minima"
            + " from prodotto p"
            + " join sku_scaffale sku on p.sku_scaffale_id =sku.id"
            + " join posizione_scaffale ps on p.sku_scaffale_id = sku.id"
            + " where ps.id=?1",
            nativeQuery = true)
    List<Prodotto> visualizzaGiacenzaProdotti(Long id);

}
