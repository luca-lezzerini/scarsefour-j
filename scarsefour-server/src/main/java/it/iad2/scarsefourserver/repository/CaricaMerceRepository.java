package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CaricaMerceRepository extends JpaRepository<PosizioneScaffale, Long> {

//    @Query(
//            value = "select p from prodotto p"
//            + " left join p.listaSku k"
//            + " left join k.posizioneScaffale ps"
//            + " where ps.id=:idPos"
//    )
//    List<Prodotto> selezionaProdottiInPosizioneScaffale(@Param("idPos") Long id);

}
