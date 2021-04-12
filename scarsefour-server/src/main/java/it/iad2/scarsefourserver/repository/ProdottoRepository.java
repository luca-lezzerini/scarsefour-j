package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.Prodotto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    @Query("select p from Prodotto p"
            + " left join p.skuScaffale s"
            + " left join s.posizioneScaffale z"
            + " where z.id =:idPosizione"
    )
            List<Prodotto> trovaProdottiSuScaffale(@Param("idPosizione") Long idPos);
            

    
    Prodotto findByEanEquals(String c);

    Prodotto findByCodice(String c);
}


