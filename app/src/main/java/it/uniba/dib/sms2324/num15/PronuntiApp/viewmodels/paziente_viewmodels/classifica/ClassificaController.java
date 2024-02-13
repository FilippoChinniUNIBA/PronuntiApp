package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.classifica;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.classifica.EntryClassifica;

public class ClassificaController {

    public static List<EntryClassifica> costruisciClassifica(List<Paziente> pazienti, List<Personaggio> personaggi) {
        List<EntryClassifica> classifica = new ArrayList<>();

        for (Paziente paziente : pazienti) {

            Map<String, Integer> personaggiSbloccati = paziente.getPersonaggiSbloccati().entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> Integer.parseInt(String.valueOf(e.getValue()))));
            for (Map.Entry<String, Integer> entry : personaggiSbloccati.entrySet()) {

                if (entry.getValue() == 2) {
                    String idPersonaggio = entry.getKey();

                    for (Personaggio personaggio : personaggi) {
                        if (personaggio.getIdPersonaggio().equals(idPersonaggio)) {
                            EntryClassifica entryClassifica = new EntryClassifica(paziente.getUsername(), paziente.getPunteggioTot(), personaggio.getTexturePersonaggio());
                            classifica.add(entryClassifica);
                            break;
                        }
                    }
                    break;
                }
            }
        }
        classifica.sort((p1, p2) -> p2.getPunteggio() - p1.getPunteggio());
        return classifica;
    }

}
