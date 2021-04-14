package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CaricaMerceRepository extends JpaRepository<Prodotto, Long> {
    
    @Query("select ps from PosizioneScaffale ps where ps.descrizione like :criterio%")
    List<PosizioneScaffale> cercaLikeDescrizione(@Param("criterio") String criterio);

    @Query(
            value = "select p from Prodotto p"
            + " left join p.listaSku k"
            + " left join k.posizioneScaffale ps"
            + " where ps.id=:idPos")
    List<Prodotto> selezionaProdottiInPosizioneScaffale(@Param("idPos") Long id);

//    @Query(
//            value = "select k from Sku_Scaffale k"            
//            + " where k.posizione_scaffale_id = :idPosizioneScaffale"
//            + " and k.prodotto_id = :idProdotto")
//    SkuScaffale selezionaSkuScaffale(@Param("idPosizioneScaffale") Long id1, @Param("idProdotto") Long id2);

}
