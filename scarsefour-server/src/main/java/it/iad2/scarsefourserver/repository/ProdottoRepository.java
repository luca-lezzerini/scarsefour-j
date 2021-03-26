/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.iad2.scarsefourserver.repository;

import it.iad2.scarsefourserver.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Samuele
 */
public interface ProdottoRepository extends JpaRepository<Prodotto, Long>{
    
}
