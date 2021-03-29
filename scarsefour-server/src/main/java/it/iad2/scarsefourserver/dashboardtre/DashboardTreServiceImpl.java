package it.iad2.scarsefourserver.dashboardtre;

import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
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
        return lista.get(0);
    }
    
    @Override
    public Scontrino chiudiScontrino(Scontrino scontrino, List<RigaScontrino> righe) {
        //Associa e salva Scontrino e RigheScontrino
        List<RigaScontrino> lriga = scontrino.getRighe();
        righe.forEach(r -> {
            lriga.add(r);
            r.setScontrino(scontrino);
            rigaScontrinoRepository.save(r);
        });
        return scontrinoRepository.save(scontrino);
    }
    
    @Override
    public void annullaScontrino(Scontrino scontrino) {
        //Cancella righeScontrino
        scontrino.getRighe().forEach(r -> {
            rigaScontrinoRepository.deleteById(r.getId());
        });
        //Cancello lo scontrino
        scontrinoRepository.deleteById(scontrino.getId());
    }
    
    @Override
    public List<RigaScontrino> stornaUltimo(Scontrino scontrino) {
        // Cancello l'ultima riga inserita
        if (scontrino.getRighe().size() == 0) {
            RigaScontrino ultimaRiga = scontrino.getRighe().get(scontrino.getRighe().size() - 1);
            rigaScontrinoRepository.deleteById(ultimaRiga.getId());
        }
        return aggiornaRighe(scontrino);
    }
    
    @Override
    public Scontrino aggiungiScontrino(Scontrino scontrino) {
        //Salva o aggiorna scontrino e le righeScontrino
        List<RigaScontrino> righe = scontrino.getRighe();
        double totale = 0;
        for (RigaScontrino rigaScontrino : righe) {
            totale += rigaScontrino.getProdotto().getPrezzo();
            rigaScontrinoRepository.save(rigaScontrino);
        }
        scontrino.setTotale(totale);
        Scontrino scontrinoSalvato = scontrinoRepository.save(scontrino);
        return scontrinoSalvato;
    }
    
    @Override
    public List<RigaScontrino> aggiornaRighe(Scontrino scontrino) {
        return rigaScontrinoRepository.findByScontrino(scontrino);
    }
    
}
