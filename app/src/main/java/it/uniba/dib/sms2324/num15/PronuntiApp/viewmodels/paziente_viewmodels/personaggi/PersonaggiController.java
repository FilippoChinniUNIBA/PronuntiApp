package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.personaggi;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class PersonaggiController {

	//TODO qua dovrà andare roba come la azione di sblocco del personaggio

	public static String getTexturePersonaggioSelezionato(List<Personaggio> listaPersonaggi, Map<String, Integer> mappaPersonaggiSbloccati) {
		mappaPersonaggiSbloccati.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> Integer.parseInt(String.valueOf(e.getValue()))));
		for (Map.Entry<String, Integer> entry : mappaPersonaggiSbloccati.entrySet()) {

			if (entry.getValue() == 2) {
				for (Personaggio personaggio : listaPersonaggi) {
					if (personaggio.getIdPersonaggio().equals(entry.getKey())) {
						return personaggio.getTexturePersonaggio();
					}
				}
			}
		}
		return null;
	}

}
