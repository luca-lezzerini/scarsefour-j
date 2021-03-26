package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.Cassa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CassaRepository extends JpaRepository<Cassa, Long> {
	
	List<Cassa> findByCodiceContains(String codice);
}
