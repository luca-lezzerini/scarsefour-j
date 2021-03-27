package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.model.Cassa;
import it.iad2.scarsefourserver.model.Cassiere;
import it.iad2.scarsefourserver.model.MovimentiScaffale;
import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Sconto;
import it.iad2.scarsefourserver.model.Scontrino;
import it.iad2.scarsefourserver.repository.CassaRepository;
import it.iad2.scarsefourserver.repository.CassiereRepository;
import it.iad2.scarsefourserver.repository.MovimentiScaffaleRepository;
import it.iad2.scarsefourserver.repository.PosizioneScaffaleRepository;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.RigaScontrinoRepository;
import it.iad2.scarsefourserver.repository.ScontoRepository;
import it.iad2.scarsefourserver.repository.ScontrinoRepository;
import it.iad2.scarsefourserver.service.SystemAdminService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemAdminServiceImpl implements SystemAdminService {

    @Autowired
    ProdottoRepository prodottoRepository;

    @Autowired
    CassaRepository cassaRepository;

    @Autowired
    CassiereRepository cassieraRepository;

    @Autowired
    ScontrinoRepository scontrinoRepository;

    @Autowired
    PosizioneScaffaleRepository posizioneScaffaleRepository;

    @Autowired
    ScontoRepository scontoRepository;

    @Autowired
    MovimentiScaffaleRepository movScaffaleRepository;

    @Autowired
    RigaScontrinoRepository rigaScontrinoRepository;

    @Override
    public void generaDatiTest() {
        //##Gestione Cassa##

        //Cancellazione dati dei db
        scontoRepository.deleteAllInBatch();
        posizioneScaffaleRepository.deleteAllInBatch();
        rigaScontrinoRepository.deleteAllInBatch();
        cassaRepository.deleteAllInBatch();
        cassieraRepository.deleteAllInBatch();
        scontrinoRepository.deleteAllInBatch();
        prodottoRepository.deleteAllInBatch();

        //Popolamento dati prodotto
        Prodotto prodotto;
        for (int i = 0; i < 100; i++) {
            prodotto = new Prodotto("ean" + i, "codice" + i, "descrizione" + i, i, 5, 10, i);
            prodottoRepository.save(prodotto);
        }
        //Popolamento dati cassa
        Cassa cassa;
        for (int i = 0; i < 20; i++) {
            cassa = new Cassa("codice" + i);
            cassaRepository.save(cassa);
        }
        //Popolamento dati cassiera
        Cassiere cassiera;
        for (int i = 0; i < 10; i++) {
            cassiera = new Cassiere("nome" + i, "cognome" + i, "codiceFiscale" + i);
            cassieraRepository.save(cassiera);
        }
        //Popolamento dati scontrino
        Scontrino scontrino;
        for (int i = 0; i < 20; i++) {
            scontrino = new Scontrino(LocalDateTime.now(), i, 1.0 * i);
            scontrinoRepository.save(scontrino);
        }
        //Popolamento dati PosizioneScaffale
        PosizioneScaffale posizioneScaffale;
        for (int i = 0; i < 100; i++) {
            posizioneScaffale = new PosizioneScaffale("codice" + i, "descizione" + i);
            posizioneScaffaleRepository.save(posizioneScaffale);
        }

        //Popolamento dati Sconto
        Sconto sconto;
        for (int i = 0; i < 10; i++) {
            sconto = new Sconto(LocalDateTime.now().plusDays(i), LocalDateTime.now().plusDays(i + 5), i, "descrizione" + i, "codice" + i);
            scontoRepository.save(sconto);
        }

        // Popolamento RigaScontrino
        RigaScontrino rigaScontrino;
        for (int i = 0; i < 60; i++) {
            rigaScontrino = new RigaScontrino();
            rigaScontrino.setQuantita(i);
            rigaScontrinoRepository.save(rigaScontrino);
        }
        
        //Associa Prodotto con PosizioneScaffale
        AssociaProdottoPosizioneScaffale();
        
        //Associa Prodotto con Sconto
        AssociaProdottoSconto();
        
        //Associa Prodotto con RigaScontrino
        AssociaProdottoRigaScontrino();
        
        //Associa Cassa con Scontrino
        //AssociaCassaScontrino();
        
        //Associa Cassiera con Scontrino
        
        //Associa RigaScontrino con Scontrino 
        AssociaRigaScontrinoScontrino();
    }

    void AssociaProdottoPosizioneScaffale() {
        List<Prodotto> prodotti = prodottoRepository.findAll();
        List<PosizioneScaffale> posScaff = posizioneScaffaleRepository.findAll();

        for (int i = 0; i < prodotti.size(); i++) {
            prodotti.get(i).setPosizioneScaffale(posScaff.get(i));
            prodottoRepository.save(prodotti.get(i));

            posScaff.get(i).setProdotto(prodotti.get(i));
            posizioneScaffaleRepository.save(posScaff.get(i));
        }
    }
    
    void AssociaProdottoSconto(){
        List<Prodotto> prodotti = prodottoRepository.findAll();
        List<Sconto> sconti = scontoRepository.findAll();
        
        for (int i = 0; i < prodotti.size(); i++) {
            prodotti.get(i).setSconti(sconti);
            prodottoRepository.save(prodotti.get(i));
        }
        for (int i = 0; i < sconti.size(); i++) {
            sconti.get(i).setProdotti(prodotti);
            scontoRepository.save(sconti.get(i));
        }
    }
    
    void AssociaProdottoRigaScontrino(){
        List<Prodotto> prodotti = prodottoRepository.findAll();
        List<RigaScontrino> rigaScontrini = rigaScontrinoRepository.findAll();
        List<RigaScontrino> tempRighe;
        int n = 10;
        for (int i = 0; i < 10; i++) {
            tempRighe = new ArrayList();
            for (int j = i * 10; j < n; j++) {
                rigaScontrini.get(j).setProdotto(prodotti.get(i));
                rigaScontrinoRepository.save(rigaScontrini.get(j));
                tempRighe.add(rigaScontrini.get(j));
            }
            prodotti.get(i).setRighe(tempRighe);
            prodottoRepository.save(prodotti.get(i));
            n += 10;
            if (n > rigaScontrini.size()){
                return;
            }
        }
    }
    
    void AssociaCassaScontrino(){
        List<Cassa> casse = cassaRepository.findAll();
        List<Scontrino> scontrini = scontrinoRepository.findAll();
        
        for (int i = 0; i < casse.size(); i++) {
            casse.get(i).setScontrino(scontrini.get(i));
            cassaRepository.save(casse.get(i));

            scontrini.get(i).setCassa(casse.get(i));
            scontrinoRepository.save(scontrini.get(i));
        }
    }
    
    void AssociaRigaScontrinoScontrino(){
        List<Scontrino> scontrini = scontrinoRepository.findAll();
        List<RigaScontrino> rigaScontrini = rigaScontrinoRepository.findAll();
        List<RigaScontrino> tempRighe;
        int n = 10;
        for (int i = 0; i < 10; i++) {
            tempRighe = new ArrayList();
            for (int j = i * 10; j < n; j++) {
                rigaScontrini.get(j).setScontrino(scontrini.get(i));
                rigaScontrinoRepository.save(rigaScontrini.get(j));
                tempRighe.add(rigaScontrini.get(j));
            }
            scontrini.get(i).setRighe(tempRighe);
            scontrinoRepository.save(scontrini.get(i));
            n += 10;
            if (n > rigaScontrini.size()){
                return;
            }
        }
    }
}