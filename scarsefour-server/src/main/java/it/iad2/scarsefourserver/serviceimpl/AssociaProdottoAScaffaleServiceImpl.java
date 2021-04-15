package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.ListaPosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.dto.PosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.ProdottoPosizioneDto;
import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.SkuScaffale;
import it.iad2.scarsefourserver.repository.PosizioneScaffaleRepository;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.SkuScaffaleRepository;
import it.iad2.scarsefourserver.service.AssociaProdottoAScaffaleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociaProdottoAScaffaleServiceImpl implements AssociaProdottoAScaffaleService {

    @Autowired
    ProdottoRepository prodottoRepository;

    @Autowired
    SkuScaffaleRepository skuScaffaleRepository;

    @Autowired
    PosizioneScaffaleRepository posizioneScaffaleRepository;

    public ListaPosizioneScaffaleDto cercaPosizione(Long id) {

        List<PosizioneScaffale> lista = new ArrayList<PosizioneScaffale>();
        return new ListaPosizioneScaffaleDto(lista);
    }

    @Override
    public ListaProdottiDto cercaProdottiNonAssociati(PosizioneScaffale posizioneScaffale) {
        List<Prodotto> lista = new ArrayList<Prodotto>();
        Long id = posizioneScaffale.getId();
        lista = prodottoRepository.trovaProdottiSuScaffale(id);
        return new ListaProdottiDto(lista);

    }

    @Override
    public ListaPosizioneScaffaleDto selezionaPosizioni(String criterio) {
        List<PosizioneScaffale> lista = new ArrayList<>();
        if (criterio == "") {
            lista = posizioneScaffaleRepository.findAll();
        }
        if (criterio.contains(criterio)) {
            lista = posizioneScaffaleRepository.contieneCode(criterio);
        }
        return new ListaPosizioneScaffaleDto(lista);
    }

    @Override
    public PosizioneScaffaleDto associaProdottoScaffale(ProdottoPosizioneDto dto) {
        Prodotto prodotto = dto.getProdotto();
        PosizioneScaffale posizione = dto.getPosizioneScaffale();
        SkuScaffale sks = new SkuScaffale();
        sks.setProdotto(prodotto);
        sks.setPosizioneScaffale(posizione);
        sks = skuScaffaleRepository.save(sks);
        List<SkuScaffale> skLista = prodotto.getListaSku();
        skLista.add(sks);
        prodotto = prodottoRepository.save(prodotto);
        List<SkuScaffale> skLista2 = posizione.getListaSku();
        skLista2.add(sks);
        posizione = posizioneScaffaleRepository.save(posizione);

        return new PosizioneScaffaleDto(posizione);
    }

}
