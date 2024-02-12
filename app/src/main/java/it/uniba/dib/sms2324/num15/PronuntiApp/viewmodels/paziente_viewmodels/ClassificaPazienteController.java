package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.PazienteDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.personaggio.PersonaggioDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.classifica.Classifica;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.classifica.PazienteClassifica;

public class ClassificaPazienteController {
    public CompletableFuture<List<PazienteClassifica>> retrieveClassificaPazienti(String idPaziente) {

        PazienteDAO pazienteDAO = new PazienteDAO();
        PersonaggioDAO personaggioDAO = new PersonaggioDAO();

        List<PazienteClassifica> pazientiClassifica = new ArrayList<>();
        CompletableFuture<List<PazienteClassifica>> futureClassifica = new CompletableFuture<>();
        CompletableFuture<Logopedista> futureLogopedista = pazienteDAO.getDatiLogopedistaByIdPaziente(idPaziente);

        futureLogopedista.thenAccept(logopedista -> {
            for (Paziente paziente : logopedista.getPazienti()) {
                for (Map.Entry<String, Integer> entry : paziente.getPersonaggiSbloccati().entrySet()) {
                    Integer valore = entry.getValue();
                    if (valore == 2) {
                        String chiave = entry.getKey();
                        System.out.println("Chiave: " + chiave + ", Valore: " + valore);
                        personaggioDAO.getById(chiave).thenAccept(personaggio -> {
                            PazienteClassifica pazienteClassifica = new PazienteClassifica(paziente.getUsername(), paziente.getPunteggioTot(), personaggio.getTexturePersonaggio());
                            pazientiClassifica.add(pazienteClassifica);
                        });
                        break;
                    }
                }
            }

            // Ordina la lista di pazienti classificati
            pazientiClassifica.sort((p1, p2) -> p2.getPunteggio() - p1.getPunteggio());

            // Completa il future con la lista di pazienti classificati
            futureClassifica.complete(pazientiClassifica);
        });

        return futureClassifica;
    }

}
