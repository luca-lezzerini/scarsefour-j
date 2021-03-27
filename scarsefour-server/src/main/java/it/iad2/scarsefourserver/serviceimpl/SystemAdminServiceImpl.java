package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.model.Cassa;
import it.iad2.scarsefourserver.model.Cassiera;
import it.iad2.scarsefourserver.model.MovimentiScaffale;
import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Sconto;
import it.iad2.scarsefourserver.model.Scontrino;
import it.iad2.scarsefourserver.repository.CassaRepository;
import it.iad2.scarsefourserver.repository.CassieraRepository;
import it.iad2.scarsefourserver.repository.MovimentiScaffaleRepository;
import it.iad2.scarsefourserver.repository.PosizioneScaffaleRepository;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.RigaScontrinoRepository;
import it.iad2.scarsefourserver.repository.ScontoRepository;
import it.iad2.scarsefourserver.repository.ScontrinoRepository;
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
    CassieraRepository cassieraRepository;

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
        prodottoRepository.deleteAllInBatch();
        cassaRepository.deleteAllInBatch();
        cassieraRepository.deleteAllInBatch();

        //Popolamento dati prodotto
        Prodotto prodotto;
        for (int i = 0; i < 100; i++) {
            prodotto = new Prodotto("ean" + i, "codice" + i, "descrizione" + i, i, 5, 10, i);
            prodottoRepository.save(prodotto);
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
        for (int i = 0; i < 20; i++) {
            scontrino = new Scontrino(LocalDateTime.MAX, i, Double.NaN);
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
        for (int i = 0; i < 40; i++) {
            rigaScontrino = new RigaScontrino();
            rigaScontrino.setQuantita(i);
            rigaScontrinoRepository.save(rigaScontrino);
        }

        //Associa Prodotto con PosizioneScaffale
        AssociaProdottoPosizioneScaffale();
        
        //Associa Prodotto con Sconto
        //Associa Prodotto con RigaScontrino
        //Associa Cassa con Scontrino
        //Associa Cassiera con Scontrino
        //Associa RigaScontrino con PosizioneScaffale
        //Associa RigaScontrino con Scontrino      
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

}
