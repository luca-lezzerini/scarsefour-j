package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.ProdottoGiacenza;
import it.iad2.scarsefourserver.model.SkuScaffale;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CaricaMerceRepository extends JpaRepository<Prodotto, Long> {

    @Query("select ps from PosizioneScaffale ps where ps.descrizione like %:criterio%")
    List<PosizioneScaffale> cercaLikeDescrizione(@Param("criterio") String criterio);

    @Query("SELECT new it.iad2.scarsefourserver.model.ProdottoGiacenza"
            + " (p.id, p.codice, p.descrizione, k.giacenza, k.id) FROM Prodotto p"
            + " LEFT JOIN p.listaSku k"
            + " LEFT JOIN k.posizioneScaffale ps"
            + " WHERE ps.id=:idPos")
    List<ProdottoGiacenza> selezionaProdottiGiacenzaByIdPos(@Param("idPos") Long id);

    @Query("select ss from SkuScaffale ss where ss.id = :idSS")
    SkuScaffale selezionaSkuScaffaleById(@Param("idSS") Long id_sku);
    
    @Query("select p from PosizioneScaffale p")
    Page<PosizioneScaffale> trovaTuttiPaginati(Pageable p);
    
    }
