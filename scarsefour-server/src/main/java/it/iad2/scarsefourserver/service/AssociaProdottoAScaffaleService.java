package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.dto.ListaPosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.dto.PosizioneScaffaleDto;
import it.iad2.scarsefourserver.model.PosizioneScaffale;

public interface AssociaProdottoAScaffaleService {

     
    ListaProdottiDto cercaProdottiNonAssociati(PosizioneScaffale posizioneScaffale);
    ListaPosizioneScaffaleDto selezionaPosizioni();
     
    }

