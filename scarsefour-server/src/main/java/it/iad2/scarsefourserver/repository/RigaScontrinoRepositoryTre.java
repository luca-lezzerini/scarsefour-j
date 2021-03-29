package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RigaScontrinoRepositoryTre extends JpaRepository<RigaScontrino, Long>{
    
    List<RigaScontrino> findByScontrino(Scontrino scontrino);

}
