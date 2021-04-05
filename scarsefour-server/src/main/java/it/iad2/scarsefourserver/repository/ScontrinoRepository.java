package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.Scontrino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScontrinoRepository extends JpaRepository<Scontrino, Long> {
    Scontrino findByIdEquals(Long id);
    
    @Query("select max (numero) from Scontrino")
    int cercaUltimoScontrino();
}
