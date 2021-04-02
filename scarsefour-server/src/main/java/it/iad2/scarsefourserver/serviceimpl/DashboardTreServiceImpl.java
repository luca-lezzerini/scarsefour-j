package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.AggiungiEanRispostaDto;
import it.iad2.scarsefourserver.dto.ListaRigaScontrinoTreDto;
import it.iad2.scarsefourserver.repository.ProdottoRepositoryTre;
import it.iad2.scarsefourserver.repository.RigaScontrinoRepositoryTre;
import it.iad2.scarsefourserver.repository.ScontrinoRepositoryTre;
import it.iad2.scarsefourserver.service.DashboardTreService;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        Prodotto prod = prodottoRepository.findByEan(ean);
        if (prod != null) {
            return prod;
        } else {
            prod = new Prodotto();
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
        //Setto la data
        scontrino.setTimeStamp(LocalDateTime.now());
        //Recupero numero progressivo e lo salvo 
        Integer numero = scontrinoRepository.findFirstByNumeroIsNotNullOrderByNumeroDesc().getNumero();
        scontrino.setNumero(numero + 1);
        System.out.println("il numero più alto degli scontrini è.........." + numero);
        System.out.println("il numero dello scontrino è.........." + scontrino.getNumero());
        //Salvo lo scontrino
        Scontrino salvato;
        salvato = scontrinoRepository.save(scontrino);
        System.out.println("lo scontrino è: " + salvato);
        return creaNuovoScontrinoVuoto();
    }

    @Override
    public Scontrino annullaScontrino(Scontrino scontrino) {
        //Cancella righeScontrino
        var sco = scontrinoRepository.findById(scontrino.getId());
        if (sco.isEmpty()){
            System.out.println("ERRORE: id scontrino non trovata. Creato scontrino vuoto");
            scontrino = new Scontrino();
            scontrino = scontrinoRepository.save(scontrino);
        } else {
            scontrino = sco.get();
        }
        scontrino.getRighe().forEach(r -> {
            rigaScontrinoRepository.deleteById(r.getId());
        });
        scontrino.getRighe().clear();
        //Cancello lo scontrino
        //scontrino.setRighe(new ArrayList<RigaScontrino>());
        scontrino = scontrinoRepository.save(scontrino);
        System.out.println("Annulla scontrino: righe scontrino nuovo: " + scontrino.getRighe());
        return scontrino;
    }

    @Override
    public ListaRigaScontrinoTreDto stornaUltimo(Scontrino scontrino) {
        System.out.println("siamo in storna, lo scontrino è..." + scontrino);
        // Recupero lo scontrino dal DB
        var sco = scontrinoRepository.findById(scontrino.getId());
        if (sco.isEmpty()) {
            System.out.println("ERRORE: id scontrino non trovata. Creato scontrino vuoto");
            scontrino = new Scontrino();
            scontrino = scontrinoRepository.save(scontrino);
        } else {
            scontrino = sco.get();
        }
        // Recuperiamo la lista di righe scontrino
        List<RigaScontrino> listaRighe = scontrino.getRighe();
        // Recuperiamo l'ultima riga
        RigaScontrino ultimaRiga;
        if (!listaRighe.isEmpty()) {
            ultimaRiga = listaRighe.get(listaRighe.size() - 1);
            // Recuperiamo il prodotto associato 
            Prodotto prod = ultimaRiga.getProdotto();
            // rimuovo la riga dallo scontrino...
            scontrino.getRighe().removeIf(r -> r==ultimaRiga);
            scontrino = scontrinoRepository.save(scontrino);
            // ... la rimuovo dal prodotto ...
            prod.getRighe().removeIf(r -> r==ultimaRiga);
            prodottoRepository.save(prod);
            // ... e la rimuovo dalle righeScontrino
            listaRighe.removeIf(r -> r==ultimaRiga);
            // Cancello l'ultima riga inserita
            rigaScontrinoRepository.delete(ultimaRiga);
        } else {
            System.out.println("ERRORE! Scontrino vuoto!");
        }
        // Ricalcolo il totale
        List<RigaScontrino> righe = scontrino.getRighe();
        scontrino.setTotale(calcolaTotale(righe));
        scontrino = scontrinoRepository.save(scontrino);
        System.out.println("scontrino salvato" + scontrino.getRighe().toString());
        return new ListaRigaScontrinoTreDto(listaRighe, scontrino);
    }

    @Override
    public AggiungiEanRispostaDto aggiungiRigaScontrino(Scontrino scontrino, String ean) {
        // definisco la variabile per l'esito
        boolean esito;
        // vedo se il client mi ha mandato uno scontrino vuoto
        if (scontrino == null || scontrino.getId() == null) {
            // scontrino vuoto da client, creo nuovo scontrino
            scontrino = new Scontrino();
            scontrino = scontrinoRepository.save(scontrino);
        } else {
            // recupero scontrino aggiornato da DB
            var sco = scontrinoRepository.findById(scontrino.getId());
            if (sco.isEmpty()) {
                System.out.println("ERRORE: id scontrino non trovata. Creato scontrino vuoto");
                scontrino = new Scontrino();
                scontrino = scontrinoRepository.save(scontrino);
            } else {
                scontrino = sco.get();
            }
        }
        // cerco l'ean
        var prod = prodottoRepository.findByEan(ean);
        // verifico se l'ho trovato
        if (prod == null) {
            // non trovato
            esito = false;
        } else {
            // trovato
            esito = true;
            // aggiungo una riga ...
            var riga = new RigaScontrino();
            riga.setProdotto(prod);
            riga.setQuantita(1);
            riga.setScontrino(scontrino);
            riga = rigaScontrinoRepository.save(riga);
            // ... la collego allo scontrino ...
            scontrino.getRighe().add(riga);
            scontrino = scontrinoRepository.save(scontrino);
            // ... e al prodotto ...
            prod.getRighe().add(riga);
            prod = prodottoRepository.save(prod);
        }
        // Calcolo del totale
        List<RigaScontrino> righe = scontrino.getRighe();
        scontrino.setTotale(calcolaTotale(righe));
        scontrino = scontrinoRepository.save(scontrino);
        System.out.println("scontrino salvato" + scontrino.getRighe().toString());
        AggiungiEanRispostaDto aggiungiEanRispostaDto = new AggiungiEanRispostaDto(scontrino, esito, righe);
        return aggiungiEanRispostaDto;
    }

    @Override
    public Scontrino aggiornaRighe(Scontrino scontrino) {
        System.out.println("Scontrino in aggiornaRighe " + scontrino);
        //return rigaScontrinoRepository.findByScontrino(scontrino);
        Optional<Scontrino> op = scontrinoRepository.findById(scontrino.getId());
        return op.get();
    }

    @Override
    public Scontrino creaNuovoScontrinoVuoto() {
        // Creo un nuovo scontrino lo salvo e lo restituisco vuoto
        Scontrino scontrinoNuovo = new Scontrino(LocalDateTime.now(), 0, 0.0);
        scontrinoNuovo = scontrinoRepository.save(scontrinoNuovo);
        return scontrinoNuovo;
    }
    
    private Double calcolaTotale(List<RigaScontrino> righe){
        double totale = 0;
         totale = righe.stream()
                .mapToDouble(r -> r.getProdotto().getPrezzo())
                .sum();
         return totale;
    }

}
