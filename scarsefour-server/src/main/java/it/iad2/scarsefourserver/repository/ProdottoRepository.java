package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

<<<<<<< Updated upstream
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    Prodotto findByCodiceEquals(String c);

    Prodotto findByEanEquals(String c);

=======
public interface ProdottoRepository extends JpaRepository<Prodotto, Long>{
    Prodotto findByCodice(String c);
    
>>>>>>> Stashed changes
}
