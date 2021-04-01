package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.RispostaEanDto;
import it.iad2.scarsefourserver.dto.ScontrinoRigheDto;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.RigaScontrinoRepository;
import it.iad2.scarsefourserver.repository.ScontrinoRepository;
import it.iad2.scarsefourserver.service.DashboardGruppoUnoService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardGruppoUnoServiceImpl implements DashboardGruppoUnoService {

    @Autowired
    ScontrinoRepository scontrinoRepository;
    @Autowired
    RigaScontrinoRepository rigaScontrinoRepository;
    @Autowired
    ProdottoRepository prodottoRepository;

    @Override
    public RispostaEanDto cercaEan1(String barcode, Scontrino scontrino) {
        RispostaEanDto rispostaEanDto = new RispostaEanDto();
        System.out.println("barcode: " + barcode);
        //cerco se esiste un prodotto nel repository con il barcode passato in input
        Prodotto prodotto = prodottoRepository.findByEanEquals(barcode);
        if (prodotto == null) {
            //prodotto non trovato
            System.out.println("Errore EAN sconosciuto");
            rispostaEanDto.setScontrino(scontrino);
            if (scontrino != null) {
                rispostaEanDto.setRigheScontrino(scontrinoRepository.findById(scontrino.getId()).get().getRighe());
            }

        } else {
            //prodotto trovato
            if (scontrino == null) {
                //se lo scontrino che arriva dal client è null, creo nuovo scontrino e lo salvo nel repository 
                scontrino = new Scontrino(LocalDateTime.now(), 0, 0.);
                scontrinoRepository.save(scontrino);
                // salvo nel repository una nuova rigaScontrino
                RigaScontrino riga = new RigaScontrino();
                riga.setScontrino(scontrino);
                riga.setProdotto(prodotto);
                rigaScontrinoRepository.save(riga);
                // Aggiorno il totale dello scontrino e la lista righe scotrino con il prodotto in esame
                scontrino.setTotale(scontrino.getTotale() + prodotto.getPrezzo());
                scontrino.getRighe().add(riga);
                scontrinoRepository.save(scontrino);
                // preparo la risposta
                rispostaEanDto.setBarcode(barcode);
                rispostaEanDto.setScontrino(scontrino);
                rispostaEanDto.setRigheScontrino(scontrinoRepository.findById(scontrino.getId()).get().getRighe());
            } else {
                List<RigaScontrino> listaRighe = scontrinoRepository.findById(scontrino.getId()).get().getRighe();
                boolean trovato = false;
                for (RigaScontrino riga : listaRighe) {
                    if (riga.getProdotto().getEan().equals(barcode)) {
                        riga.setQuantita(riga.getQuantita() + 1);
                        scontrino.setTotale(scontrino.getTotale() + prodotto.getPrezzo());
                        scontrino.setRighe(listaRighe);
                        rigaScontrinoRepository.save(riga);
                        scontrinoRepository.save(scontrino);
                        trovato = true;
                        rispostaEanDto.setBarcode(barcode);
                        rispostaEanDto.setScontrino(scontrino);
                        rispostaEanDto.setRigheScontrino(listaRighe);
                        break;
                    }
                }
                if (!trovato) {
                    listaRighe = scontrinoRepository.findById(scontrino.getId()).get().getRighe();
                    // salvo nel repository una nuova rigaScontrino
                    RigaScontrino riga = new RigaScontrino();
                    riga.setScontrino(scontrino);
                    riga.setProdotto(prodotto);
                    rigaScontrinoRepository.save(riga);
                    listaRighe.add(riga);
                    // Aggiorno il totale dello scontrino e la lista righe scotrino con il prodotto in esame
                    scontrino.setTotale(scontrino.getTotale() + prodotto.getPrezzo());
                    scontrino.setRighe(listaRighe);
                    scontrinoRepository.save(scontrino);
                    // preparo la risposta
                    rispostaEanDto.setBarcode(barcode);
                    rispostaEanDto.setScontrino(scontrino);
                    rispostaEanDto.setRigheScontrino(listaRighe);
                }
            }

        }
        return rispostaEanDto;
    }

    @Override
    public Scontrino chiudiScontrino1(Scontrino scontrino) {
        scontrino.setNumero(scontrinoRepository.findAll().size() + 1);
        scontrinoRepository.save(scontrino);
        return null;
    }

    @Override
    public double vediPrezzo1(String barcode) {
        Prodotto prodotto = prodottoRepository.findByEanEquals(barcode);
        if (prodotto != null) {
            return prodotto.getPrezzo();
        }
        return 0;
    }

    @Override
    public ScontrinoRigheDto stornaUltimo1(Scontrino scontrino, String lastBarcode) {
        List<RigaScontrino> listaRighe = scontrinoRepository.findById(scontrino.getId()).get().getRighe();

        for (RigaScontrino riga : listaRighe) {
            if (riga.getProdotto().getEan().equals(lastBarcode)) {
                scontrino.setTotale(scontrino.getTotale() - riga.getProdotto().getPrezzo());
                if (riga.getQuantita() > 1) {
                    riga.setQuantita(riga.getQuantita() - 1);
                    rigaScontrinoRepository.save(riga);
                } else {
                    rigaScontrinoRepository.delete(riga);
                    listaRighe.remove(riga);
                    scontrino.setRighe(listaRighe);
                }
                scontrinoRepository.save(scontrino);
                break;
            }
        }
        return new ScontrinoRigheDto(scontrino, listaRighe);
//        List<RigaScontrino> listaRighe = scontrinoRepository.findById(scontrino.getId()).get().getRighe();
//        RigaScontrino ultimaRiga = listaRighe.get(listaRighe.size() - 1);
//        rigaScontrinoRepository.delete(ultimaRiga);
//        scontrino.setTotale(scontrino.getTotale() - ultimaRiga.getProdotto().getPrezzo());
//        scontrinoRepository.findById(scontrino.getId()).get().getRighe().remove(ultimaRiga);
//        listaRighe = scontrinoRepository.findById(scontrino.getId()).get().getRighe();
//        scontrinoRepository.save(scontrino);
//        return new ScontrinoRigheDto(scontrino, listaRighe);
    }

    @Override
    public Scontrino annullaScontrino1(Scontrino scontrino) {
        List<RigaScontrino> listaRighe = scontrinoRepository.findById(scontrino.getId()).get().getRighe();
        listaRighe.forEach(x -> {
            rigaScontrinoRepository.delete(x);
        });
        scontrinoRepository.delete(scontrino);
        return null;
    }

}
