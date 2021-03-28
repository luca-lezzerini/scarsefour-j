package it.iad2.scarsefour.dashboardtre;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RigaScontrino> stornaUltimo(Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Scontrino aggiungiScontrino(Scontrino scontrino) {
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
