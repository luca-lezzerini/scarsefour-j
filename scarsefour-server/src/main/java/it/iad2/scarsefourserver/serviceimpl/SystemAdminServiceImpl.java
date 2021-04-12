package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.model.Cassa;
import it.iad2.scarsefourserver.model.Cassiere;
import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Sconto;
import it.iad2.scarsefourserver.model.Scontrino;
import it.iad2.scarsefourserver.model.SkuScaffale;
import it.iad2.scarsefourserver.repository.CassaRepository;
import it.iad2.scarsefourserver.repository.CassiereRepository;
import it.iad2.scarsefourserver.repository.MovimentiScaffaleRepository;
import it.iad2.scarsefourserver.repository.PosizioneScaffaleRepository;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.RigaScontrinoRepository;
import it.iad2.scarsefourserver.repository.ScontoRepository;
import it.iad2.scarsefourserver.repository.ScontrinoRepository;
import it.iad2.scarsefourserver.repository.SkuScaffaleRepository;
import it.iad2.scarsefourserver.service.SystemAdminService;
import java.time.LocalDateTime;
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
    
    @Autowired
    SkuScaffaleRepository skuScaffaleRepository;

    @Override
    public void generaDatiTest() {
        //Cancellazione dati dei db
        movScaffaleRepository.deleteAllInBatch();
        scontoRepository.deleteAllInBatch();
        rigaScontrinoRepository.deleteAllInBatch();
        cassaRepository.deleteAllInBatch();
        cassieraRepository.deleteAllInBatch();
        scontrinoRepository.deleteAllInBatch();
        posizioneScaffaleRepository.deleteAllInBatch();
        prodottoRepository.deleteAllInBatch();
        skuScaffaleRepository.deleteAllInBatch();

        //Popolamento dati prodotto e posizioneScaffale
        Prodotto prodotto;
        PosizioneScaffale posizioneScaffale;
        for (int i = 0; i < 100; i++) {
            prodotto = new Prodotto("ean" + i, "codice" + i, "descrizione" + i, i, 5, 10, i);
            prodottoRepository.save(prodotto);

            posizioneScaffale = new PosizioneScaffale("codice" + i, "descrizione" + i);
            posizioneScaffaleRepository.save(posizioneScaffale);

            //Associo Prodotto a posizioneScaffale
            //associaProdottoPosizioneScaffale(prodotto, posizioneScaffale);
        }
        
        //Popolo dati Sku
        SkuScaffale sku;
        for (int i = 0; i < 100; i++) {
            sku = new SkuScaffale(i + 5, 5);
            skuScaffaleRepository.save(sku);
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
            rigaScontrino.setQuantita(1);
            rigaScontrinoRepository.save(rigaScontrino);
        }

        //Associa Prodotto con RigaScontrino e Scontrino con RigaScontrino
        //Seleziono i primi 10 prodotti
        List<Prodotto> prodotti = prodottoRepository.findAll().subList(0, 10);
        //seleziono i primi 10 scontrini
        List<Scontrino> scontrini = scontrinoRepository.findAll().subList(0, 10);
        System.out.println("\n\nLISTA PRODOTTI: " + prodotti.toString());
        //seleziono tutte le righe scontrino
        List<RigaScontrino> rigaScontrini = rigaScontrinoRepository.findAll();

        //eseguo le associazioni Prodotto-rigaScontrino e Scontrino-rigaScontrino
        int n = 10;
        for (int i = 0; i < prodotti.size(); i++) {
            Prodotto p = prodotti.get(i);
            for (int j = i * 10; j < n; j++) {
                List<RigaScontrino> subListRiga = rigaScontrini.subList(j, n);
                associaProdottoRigaScontrino(p, subListRiga);
                Scontrino s = scontrini.get(i);
                associaRigaScontrinoScontrino(s, subListRiga);
            }
            n += 10;
            if (n > rigaScontrini.size()) {
                break;
            }
        }
        
        //Seleziono i primi 50 prodotti
        List<Prodotto> prodSku = prodottoRepository.findAll().subList(0, 50);
        //seleziono gli sku
        List<SkuScaffale> listaSku = skuScaffaleRepository.findAll();
        //associo sku a prodotti 
        int x = 10;
        for (int i = 0; i < listaSku.size(); i++) {
            SkuScaffale skuScaffale = listaSku.get(i);
            System.out.println("SCAFFALE SKU: " + skuScaffale.getId());
            for (int j = i * 10; j < x; j++) {
                Prodotto p = prodSku.get(j);
                System.out.println("PRODOTTO SKU: " + p.getCodice());
                associaSkuProdotto(skuScaffale, prodSku);
            }
            x += 10;
            if (x > prodSku.size()) {
                break;
            }
        }
        
        //seleziono le prime 50 posizioni scaffale
        List<PosizioneScaffale> posizioni = posizioneScaffaleRepository.findAll().subList(0, 50);
        //associo sku a posizioni 
        int y = 10;
        for (int i = 0; i < listaSku.size(); i++) {
            SkuScaffale skuScaffale = listaSku.get(i);
            for (int j = i * 10; j < y; j++) {
                PosizioneScaffale p = posizioni.get(j);
                associaSkuPosizioneScaffale(skuScaffale, posizioni);
            }
            y += 10;
            if (y > posizioni.size()) {
                break;
            }
        }
        //Associa Cassa con Scontrino
        associaCassaScontrino();

        //Associa Cassiera con Scontrino
        associaCassieraScontrino();

        //Associa Prodotto con Sconto
        associaProdottoSconto();
        
        //Creazione di record prodoti senza vincoli di foreign key
//        for (int i = 0; i < 50; i++) {
//            prodotto = new Prodotto("xean" + i, "xcodice" + i, "xcancellabile" + i, i, 5, 10, i);
//            prodottoRepository.save(prodotto);
//        }
//        //Creazione di record sconti senza vincoli di foreign key
//        for (int i = 0; i < 10; i++) {
//            sconto = new Sconto(LocalDateTime.now().plusDays(i), LocalDateTime.now().plusDays(i + 5), i, "cancellabile" + i, "codice" + i);
//            scontoRepository.save(sconto);
//        }
    }

//    void associaProdottoPosizioneScaffale(Prodotto p, PosizioneScaffale ps) {
//        //OneToOne
//        p.setPosizioneScaffale(ps);
//        prodottoRepository.save(p);
//
//        ps.setProdotto(p);
//        posizioneScaffaleRepository.save(ps);
//    }

    void associaProdottoSconto() {
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
        sconti.forEach(s -> {
            List<Prodotto> pr = s.getProdotti();
            prodotti.forEach(p -> {
                pr.add(p);
            });
            scontoRepository.save(s);
        });
    }

    void associaProdottoRigaScontrino(Prodotto p, List<RigaScontrino> rigaScontrini) {
        //OneToMany
        //prodotti.forEach(p -> {
        System.out.println("PRODOTTO: " + p.getCodice());
        List<RigaScontrino> lriga = p.getRighe();
        rigaScontrini.forEach(r -> {
            lriga.add(r);
            r.setProdotto(p);
            System.out.println("RIGA SCONTRINO: " + r.getId());
            rigaScontrinoRepository.save(r);
        });
        prodottoRepository.save(p);
        //});
    }

    void associaRigaScontrinoScontrino(Scontrino s, List<RigaScontrino> rigaScontrini) {
        //OneToMany
        //scontrino.forEach(s -> {
        List<RigaScontrino> lriga = s.getRighe();
        rigaScontrini.forEach(r -> {
            lriga.add(r);
            r.setScontrino(s);
            rigaScontrinoRepository.save(r);
        });
        scontrinoRepository.save(s);
        //});
    }
    
    void associaSkuProdotto(SkuScaffale sku, List<Prodotto> listaProdotti) {
        //OneToMany
        List<Prodotto> prodotti = sku.getProdotti();
        listaProdotti.forEach(r -> {
            prodotti.add(r);
            r.setSkuScaffale(sku);
            prodottoRepository.save(r);
        });
        skuScaffaleRepository.save(sku);
    }
   
    void associaSkuPosizioneScaffale(SkuScaffale sku, List<PosizioneScaffale> listaPosizioni) {
        //OneToMany
        List<PosizioneScaffale> posizioni = sku.getPosizioneScaffale();
        listaPosizioni.forEach(r -> {
            posizioni.add(r);
            r.setSkuScaffale(sku);
            posizioneScaffaleRepository.save(r);
        });
        skuScaffaleRepository.save(sku);
    }

    void associaCassaScontrino() {
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

    void associaCassieraScontrino() {
        //TODO
    }
}
