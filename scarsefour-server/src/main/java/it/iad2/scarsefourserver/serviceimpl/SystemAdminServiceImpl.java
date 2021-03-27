package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.model.Cassa;
import it.iad2.scarsefourserver.model.Cassiere;
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
        //Cancellazione dati dei db
        scontoRepository.deleteAllInBatch();
        rigaScontrinoRepository.deleteAllInBatch();
        cassaRepository.deleteAllInBatch();
        cassieraRepository.deleteAllInBatch();
        scontrinoRepository.deleteAllInBatch();
        prodottoRepository.deleteAllInBatch();
        posizioneScaffaleRepository.deleteAllInBatch();

        //Popolamento dati prodotto e posizioneScaffale
        Prodotto prodotto;
        PosizioneScaffale posizioneScaffale;
        for (int i = 0; i < 100; i++) {
            prodotto = new Prodotto("ean" + i, "codice" + i, "descrizione" + i, i, 5, 10, i);
            prodottoRepository.save(prodotto);
            
            posizioneScaffale = new PosizioneScaffale("codice" + i, "descrizione" + i);
            posizioneScaffaleRepository.save(posizioneScaffale);
            
            //Associo Prodotto a posizioneScaffale
            AssociaProdottoPosizioneScaffale(prodotto, posizioneScaffale);
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
        
        //Associa Prodotto con RigaScontrino e Scontrino con RigaScontrino
         //Seleziono i primi 10 prodotti
        List<Prodotto> prodotti = prodottoRepository.findAll().subList(0, 10);
        //seleziono i primi 10 scontrini
        List<Scontrino> scontrini = scontrinoRepository.findAll().subList(0, 10);
        System.out.println("\n\nLISTA PRODOTTI: " + prodotti.toString());
        List<RigaScontrino> rigaScontrini = rigaScontrinoRepository.findAll();
        int n = 10;
        for (int i = 0; i < n; i+= 10) {
            AssociaProdottoRigaScontrino(prodotti, rigaScontrini.subList(i, n));
            AssociaRigaScontrinoScontrino(scontrini, rigaScontrini.subList(i, n));
            n += 10;
            if (n > rigaScontrini.size()){
                return;
            }
        }
        
        //Associa Cassa con Scontrino
        AssociaCassaScontrino();
        
        //Associa Cassiera con Scontrino
        AssociaCassieraScontrino();
        
        //Associa RigaScontrino con Scontrino 
        //AssociaRigaScontrinoScontrino();
        
        //Associa Prodotto con Sconto
        AssociaProdottoSconto();
    }

    void AssociaProdottoPosizioneScaffale(Prodotto p, PosizioneScaffale ps) {
        //OneToOne
        p.setPosizioneScaffale(ps);
        prodottoRepository.save(p);
        
        ps.setProdotto(p);
        posizioneScaffaleRepository.save(ps);   
    }
    
    void AssociaProdottoSconto(){
        //ManyToMany
        List<Prodotto> prodotti = prodottoRepository.findAll();
        List<Sconto> sconti = scontoRepository.findAll();
        
        //Associo ad ogni prodotto la lista sconti
        prodotti.forEach(p -> {
            List<Sconto> sc = p.getSconti();
            
            sconti.forEach(s -> {
                sc.add(s);
            });
            prodottoRepository.save(p);
        });
        
        //Associo ad sconto la lista prodotti
        sconti.forEach(s ->{
            List<Prodotto> pr = s.getProdotti();
            prodotti.forEach(p -> {
                pr.add(p);
            });
            scontoRepository.save(s);
        });
    }
    
    void AssociaProdottoRigaScontrino(List<Prodotto> prodotti, List<RigaScontrino> rigaScontrini){
        //OneToMany
        //List<RigaScontrino> rigaScontrini = rigaScontrinoRepository.findAll();
        //List<RigaScontrino> tempRighe;
        //int n = 10;
//        for (int i = 0; i < 10; i++) {
//            tempRighe = new ArrayList();
//            for (int j = i * 10; j < n; j++) {
//                rigaScontrini.get(j).setProdotto(prodotti.get(i));
//                rigaScontrinoRepository.save(rigaScontrini.get(j));
//                tempRighe.add(rigaScontrini.get(j));
//            }
//            prodotti.get(i).setRighe(tempRighe);
//            prodottoRepository.save(prodotti.get(i));
//            n += 10;
//            if (n > rigaScontrini.size()){
//                return;
//            }
//        }
        
        prodotti.forEach(p -> {
            List<RigaScontrino> lriga = p.getRighe();
            rigaScontrini.forEach(r -> {
                lriga.add(r);
                r.setProdotto(p);
                rigaScontrinoRepository.save(r);
            });
            prodottoRepository.save(p);
        });
    }
    
    void AssociaCassaScontrino(){
//        List<Cassa> casse = cassaRepository.findAll();
//        List<Scontrino> scontrini = scontrinoRepository.findAll();
//        
//        for (int i = 0; i < casse.size(); i++) {
//            casse.get(i).setScontrino(scontrini.get(i));
//            cassaRepository.save(casse.get(i));
//
//            scontrini.get(i).setCassa(casse.get(i));
//            scontrinoRepository.save(scontrini.get(i));
//        }
    }
    
    void AssociaCassieraScontrino(){}
    
    void AssociaRigaScontrinoScontrino(List<Scontrino> scontrino, List<RigaScontrino> rigaScontrini){
        //OneToMany
//        List<Scontrino> scontrini = scontrinoRepository.findAll();
//        List<RigaScontrino> rigaScontrini = rigaScontrinoRepository.findAll();
//        List<RigaScontrino> tempRighe;
//        int n = 10;
//        for (int i = 0; i < 10; i++) {
//            tempRighe = new ArrayList();
//            for (int j = i * 10; j < n; j++) {
//                rigaScontrini.get(j).setScontrino(scontrini.get(i));
//                rigaScontrinoRepository.save(rigaScontrini.get(j));
//                tempRighe.add(rigaScontrini.get(j));
//            }
//            scontrini.get(i).setRighe(tempRighe);
//            scontrinoRepository.save(scontrini.get(i));
//            n += 10;
//            if (n > rigaScontrini.size()){
//                return;
//            }
//        }

        scontrino.forEach(s -> {
            List<RigaScontrino> lriga = s.getRighe();
            rigaScontrini.forEach(r -> {
                lriga.add(r);
                r.setScontrino(s);
                rigaScontrinoRepository.save(r);
            });
            scontrinoRepository.save(s);
        });
    }
}