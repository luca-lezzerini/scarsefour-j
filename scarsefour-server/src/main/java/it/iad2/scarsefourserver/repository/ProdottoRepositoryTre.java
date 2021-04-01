package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.Prodotto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepositoryTre extends JpaRepository<Prodotto, Long>{
    
    Prodotto findByEan(String ean);

}
