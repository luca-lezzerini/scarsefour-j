package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.dto.ProdottoDto;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.service.ProdottoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoServiceImp implements ProdottoService {

    @Autowired
    ProdottoRepository prodottoRepository;

    @Override
    public ListaProdottiDto modificaProdotto(Prodotto prodotto) {
        prodottoRepository.save(prodotto);
        return aggiornaProdotto();

    }

    @Override
    public ListaProdottiDto confermaProdotto(Prodotto prodotto) {
        prodottoRepository.save(prodotto);
        return aggiornaProdotto();
    }

    @Override
    public ListaProdottiDto rimuoviProdotto(Prodotto prodotto) {
        prodottoRepository.deleteById(prodotto.getId());
        return aggiornaProdotto();
    }

    @Override
    public ListaProdottiDto aggiornaProdotto() {
        List<Prodotto> listaProdotti = prodottoRepository.findAll();
        return new ListaProdottiDto(listaProdotti);

    }

    @Override
    public ProdottoDto cercaProdotto(String cerca) {

        Prodotto p = prodottoRepository.findByCodiceEquals(cerca);
        if (p == null) {
            p = new Prodotto();
        }
        return new ProdottoDto(p);
    }
}
