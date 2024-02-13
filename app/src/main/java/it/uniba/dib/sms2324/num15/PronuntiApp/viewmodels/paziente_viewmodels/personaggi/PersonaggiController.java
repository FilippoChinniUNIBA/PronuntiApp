package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.personaggi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;

public class PersonaggiController {
	private PazienteViewModel mPazienteViewModel;

	public PersonaggiController(PazienteViewModel mPazienteViewModel) {
		this.mPazienteViewModel = mPazienteViewModel;
	}

	public static String getTexturePersonaggioSelezionato(List<Personaggio> listaPersonaggi, Map<String, Integer> mappaPersonaggiSbloccati) {
		Map<String, Integer> mappaConvertita = mappaPersonaggiSbloccati.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> Integer.parseInt(String.valueOf(e.getValue()))));
		for (Map.Entry<String, Integer> entry : mappaConvertita.entrySet()) {

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

	public List<Personaggio> getSortedListPersonaggi(List<Personaggio> listaPersonaggi, List<String> listaId) {
		Map<String, Personaggio> mappaIdPersonaggio = new HashMap<>();
		for (Personaggio personaggio : listaPersonaggi) {
			mappaIdPersonaggio.put(personaggio.getIdPersonaggio(), personaggio);
		}
		List<Personaggio> listaOrdinata = new ArrayList<>();
		for (String id : listaId) {
			if (mappaIdPersonaggio.containsKey(id)) {
				listaOrdinata.add(mappaIdPersonaggio.get(id));
			}
		}
		return listaOrdinata;
	}

	public boolean isValutaSufficiente(int costoSbloccoPersonaggio) {
		int valutaPaziente = mPazienteViewModel.getPazienteLiveData().getValue().getValuta();
		return (valutaPaziente >= costoSbloccoPersonaggio);
	}

	public void updateSelezionePersonaggio(String idPersonaggio) {
		Map<String, Integer> personaggi = mPazienteViewModel.getPazienteLiveData().getValue().getPersonaggiSbloccati();

		Map<String, Integer> personaggiModificati = deselezionaPersonaggio(personaggi);
		personaggiModificati.put(idPersonaggio, 2);

		updateListaPersonaggiSbloccatiRemota(personaggiModificati);
	}

	public void updateListaPersonaggiSbloccatiRemota(Map<String, Integer> personaggiModificati) {
		mPazienteViewModel.getPazienteLiveData().getValue().setPersonaggiSbloccati(personaggiModificati);
		mPazienteViewModel.aggiornaTexturePersonaggioSelezionatoLiveData();
		mPazienteViewModel.aggiornaPazienteRemoto();
	}

	public void updateValutaPaziente(int costoSbloccoPersonaggio) {
		mPazienteViewModel.getPazienteLiveData().getValue().decrementaValuta(costoSbloccoPersonaggio);
		mPazienteViewModel.aggiornaPazienteRemoto();
	}

	private Map<String, Integer> deselezionaPersonaggio(Map<String, Integer> mappa) {
		Map<String, Integer> nuovaMappa = new HashMap<>();

		for (Map.Entry<String, Integer> entry : mappa.entrySet()) {
			String chiave = entry.getKey();
			int valore = Integer.parseInt(String.valueOf(entry.getValue()));
			int nuovoValore = (valore == 2) ? 1 : valore;
			nuovaMappa.put(chiave, nuovoValore);
		}
		return nuovaMappa;
	}

}
