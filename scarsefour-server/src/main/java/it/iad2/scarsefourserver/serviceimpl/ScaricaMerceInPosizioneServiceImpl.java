package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.SkuScaffale;
import it.iad2.scarsefourserver.repository.PosizioneScaffaleRepository;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.SkuScaffaleRepository;
import it.iad2.scarsefourserver.service.ScaricaMerceInPosizioneService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScaricaMerceInPosizioneServiceImpl implements ScaricaMerceInPosizioneService {

    @Autowired
    ProdottoRepository prodottoRepository;

    @Autowired
    PosizioneScaffaleRepository posizioneScaffaleRepository;

    @Autowired
    SkuScaffaleRepository skuScaffaleRepository;

    @Override
    public List<Prodotto> mostraProdottiScaffale(Long id) {
        List<Prodotto> lista = prodottoRepository.mostraProdottiScaffale(id);
        lista.forEach(p -> System.out.println(p));
        return lista;
    }

    @Override
    public PosizioneScaffale cercaScaffale(String codicePosizioneScaffale) {
        return posizioneScaffaleRepository.findByCodiceEquals(codicePosizioneScaffale);
    }

    @Override
    public void scaricaMerce(SkuScaffale sku, int qtaDaRimuovere) {
        int qta = sku.getGiacenza();

        if (qta > qtaDaRimuovere) {
            sku.setGiacenza(qta - qtaDaRimuovere);
            skuScaffaleRepository.save(sku);
        } else {
            skuScaffaleRepository.delete(sku);
        }
    }
}
