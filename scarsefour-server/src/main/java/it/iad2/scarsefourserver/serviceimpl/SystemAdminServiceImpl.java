package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.model.Cassa;
import it.iad2.scarsefourserver.model.Cassiera;
import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.Scontrino;
import it.iad2.scarsefourserver.repository.CassaRepository;
import it.iad2.scarsefourserver.repository.CassieraRepository;
import it.iad2.scarsefourserver.repository.PosizioneScaffaleRepository;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.ScontrinoRepository;
import it.iad2.scarsefourserver.service.SystemAdminService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemAdminServiceImpl implements SystemAdminService {

    @Autowired
    ProdottoRepository prodottoRepository;
    
    @Autowired
    CassaRepository cassaRepository;
    
    @Autowired
    CassieraRepository cassieraRepository;
    
    @Autowired
    ScontrinoRepository scontrinoRepository;
    
    @Autowired
    PosizioneScaffaleRepository posizioneScaffaleRepository;
    
    @Override
    public void generaDatiTest() {
        //##Gestione Cassa##
        
        //Cancellazione dati dei db
        prodottoRepository.deleteAllInBatch();
        cassaRepository.deleteAllInBatch();
        cassieraRepository.deleteAllInBatch();
        
        //Popolamento dati prodotto
        Prodotto prodotto;
        for (int i = 0; i < 100; i++) {
            prodotto = new Prodotto("ean" + i, "codice" + i, "descrizione" + i, i, 5, 10, i);
            prodottoRepository.save(prodotto);
            //Associa Prodotto con PosizioneScaffale
        }
        //Popolamento dati cassa
        Cassa cassa;
        for (int i = 0; i < 5; i++) {
            cassa = new Cassa("codice" + i);
            cassaRepository.save(cassa);
        }
        //Popolamento dati cassiera
        Cassiera cassiera;
        for (int i = 0; i < 10; i++) {
            cassiera = new Cassiera("nome" + i, "cognome" + i, "codiceFiscale" + i);
            cassieraRepository.save(cassiera);
        }
        //Popolamento dati scontrino
        Scontrino scontrino;
        for (int i = 0; i < 50; i++) {
            scontrino = new Scontrino(LocalDateTime.MAX, i, Double.NaN);
            scontrinoRepository.save(scontrino);
            //Associa RigaScontrino con Scontrino
        }
        //Popolamento dati PosizioneScaffale
        PosizioneScaffale posizioneScaffale;
        for (int i = 0; i < 100; i++) {
            posizioneScaffale = new PosizioneScaffale("codice" + i, "descizione" + i);
            posizioneScaffaleRepository.save(posizioneScaffale);
        }
        
        //Associa Cassa con Scontrino
        //Associa Cassiera con Scontrino
        //Associa RigaScontrino con PosizioneScaffale
        //Associa RigaScontrino con Prodotto
        
        
        //##Gestione Scaffale##
        
        //Associare Prodotto con PosizioneScaffale
        
        
        //##Gestione Sconti##
        
        //Popolamento dati Sconto
        
        //Associare Sconto con Prodotto
    }

}
