package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.ListaPosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.dto.PosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.ProdottoPosizioneDto;
import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
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
    public ListaPosizioneScaffaleDto selezionaPosizioni() {
        List<PosizioneScaffale> lista = new ArrayList<>();
        lista = posizioneScaffaleRepository.findAll();
        return new ListaPosizioneScaffaleDto(lista);
    }

    @Override
    public PosizioneScaffaleDto associaProdottoScaffale(ProdottoPosizioneDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
