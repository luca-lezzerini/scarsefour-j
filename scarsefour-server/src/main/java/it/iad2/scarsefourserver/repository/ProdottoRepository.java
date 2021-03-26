package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long>{
    
}
