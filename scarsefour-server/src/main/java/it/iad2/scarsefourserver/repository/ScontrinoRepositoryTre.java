package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.Scontrino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScontrinoRepositoryTre extends JpaRepository<Scontrino, Long>{

    Scontrino findFirstByNumeroIsNotNullOrderByNumeroDesc();
}
