package it.iad2.scarsefour.dashboardtre;

import it.iad2.scarsefourserver.model.Prodotto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepositoryTre extends JpaRepository<Prodotto, Long>{
    
    List<Prodotto> findByEan(String ean);

}
