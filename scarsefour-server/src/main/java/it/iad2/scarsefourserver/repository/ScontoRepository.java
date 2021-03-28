package it.iad2.scarsefourserver.repository;


import it.iad2.scarsefourserver.model.Sconto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScontoRepository extends JpaRepository<Sconto, Long> {
    List<Sconto> findByCodiceContains(String c);
    
}
