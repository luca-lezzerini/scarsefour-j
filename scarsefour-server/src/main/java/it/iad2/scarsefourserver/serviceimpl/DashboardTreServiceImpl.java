package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.repository.ProdottoRepositoryTre;
import it.iad2.scarsefourserver.repository.RigaScontrinoRepositoryTre;
import it.iad2.scarsefourserver.repository.ScontrinoRepositoryTre;
import it.iad2.scarsefourserver.service.DashboardTreService;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardTreServiceImpl implements DashboardTreService {

    @Autowired
    ProdottoRepositoryTre prodottoRepository;
    @Autowired
    RigaScontrinoRepositoryTre rigaScontrinoRepository;
    @Autowired
    ScontrinoRepositoryTre scontrinoRepository;

    @Override
    public Prodotto vediPrezzo(String ean) {
        List<Prodotto> lista = prodottoRepository.findByEan(ean);
        if (!lista.isEmpty()) {
            return lista.get(0);
        } else {
            Prodotto prod = new Prodotto();
            return prod;
        }
    }

    @Override
    public Scontrino chiudiScontrino(Scontrino scontrino, List<RigaScontrino> righe) {
        //Associa e salva Scontrino e RigheScontrino
        List<RigaScontrino> lriga = righe;
        righe.forEach(r -> {
            lriga.add(r);
            r.setScontrino(scontrino);
            rigaScontrinoRepository.save(r);
        });
        //Salvo lo scontrino
        scontrinoRepository.save(scontrino);

        return creaNuovoScontrinoVuoto();
    }

    @Override
    public Scontrino annullaScontrino(Scontrino scontrino) {
        //Cancella righeScontrino
        scontrino.getRighe().forEach(r -> {
            rigaScontrinoRepository.deleteById(r.getId());
        });
        //Cancello lo scontrino
        scontrinoRepository.deleteById(scontrino.getId());
        return creaNuovoScontrinoVuoto();
    }

    @Override
    public Scontrino stornaUltimo(Scontrino scontrino) {
        // Cancello l'ultima riga inserita
        if (!scontrino.getRighe().isEmpty()) {
            RigaScontrino ultimaRiga = scontrino.getRighe().get(scontrino.getRighe().size() - 1);
            rigaScontrinoRepository.deleteById(ultimaRiga.getId());
        }
        return aggiornaRighe(scontrino);
    }

    @Override
    public Scontrino aggiungiRigaScontrino(Scontrino scontrino) {
        //Salva o aggiorna scontrino e le righeScontrino
        List<RigaScontrino> righe = scontrino.getRighe();
        System.out.println("righe" + righe.toString());
        double totale = 0;
        System.out.println(scontrino.getRighe().toString());
        for (RigaScontrino rigaScontrino : righe) {
            totale += rigaScontrino.getProdotto().getPrezzo();
            rigaScontrinoRepository.save(rigaScontrino);
        }
        System.out.println("totale = " + totale);
        scontrino.setTotale(totale);
        Scontrino scontrinoSalvato = scontrinoRepository.save(scontrino);
        System.out.println("scontrino salvato" + scontrinoSalvato.getRighe().toString());
        return aggiornaRighe(scontrinoSalvato);
    }

    @Override
    public Scontrino aggiornaRighe(Scontrino scontrino) {
        //return rigaScontrinoRepository.findByScontrino(scontrino);
        return rigaScontrinoRepository.findById(scontrino.getId()).get().getScontrino();
    }

    private Scontrino creaNuovoScontrinoVuoto() {
        // Creo un nuovo scontrino lo salvo e lo restituisco vuoto
        Scontrino scontrinoNuovo = new Scontrino(LocalDateTime.now(), 0, 0.0);
        scontrinoNuovo = scontrinoRepository.save(scontrinoNuovo);
        return scontrinoNuovo;
    }

}
