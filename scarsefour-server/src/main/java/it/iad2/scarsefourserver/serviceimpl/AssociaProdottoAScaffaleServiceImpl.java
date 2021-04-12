package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.SkuScaffaleRepository;
import it.iad2.scarsefourserver.service.AssociaProdottoAScaffaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociaProdottoAScaffaleServiceImpl implements AssociaProdottoAScaffaleService {

    @Autowired
    ProdottoRepository prodottoRepository;
    
    @Autowired
    SkuScaffaleRepository skuScaffaleRepository;
    
   
    
    
    @Override
    public ListaProdottiDto cercaProdottiNonAssociati() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
