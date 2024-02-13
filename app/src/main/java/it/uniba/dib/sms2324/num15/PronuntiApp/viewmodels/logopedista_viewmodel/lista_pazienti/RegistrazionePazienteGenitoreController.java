package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.lista_pazienti;

import static it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.autenticazione_viewmodel.RegistrazioneViewModel.helperRegistrazione;

import androidx.lifecycle.ViewModel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.Autenticazione;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.GenitoreDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.PazienteDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.TipoUtente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class RegistrazionePazienteGenitoreController {

    private static final Map<String, Integer> PERSONAGGI_INIZIALI = new HashMap<String, Integer>() {{
        put("-NqIFkOcaDJKFU_BhuH2", 0);     //Batman
        put("-NqIFkOqJ7eznb2BIubm", 2);     //Cane
        put("-NqIFkOuix-wiNRaxtlo", 0);     //Capitan America
        put("-NqIFkP0yNyXQFp5VveX", 0);     //Cavaliera
        put("-NqIFkP7u9RENb6CfQlG", 0);     //Cavaliere
        put("-NqIFkPCmPM-2sOY6O0e", 1);     //Coniglio
        put("-NqIFkPIJoJlQZ1iwgfj", 0);     //Draghetta
        put("-NqIFkPTTupPgYkYc74E", 0);     //Drago
        put("-NqIFkPYD_JMBxlV-dIx", 0);     //Elefante
        put("-NqIFkPchAFVCOm0Uffi", 0);     //Gattina
        put("-NqIFkPgCrXO8lqN56jo", 0);     //Gatto
        put("-NqIFkPlPjvZDY2TskvO", 0);     //Mucca
        put("-NqIFkPpkXllw9SXtFY2", 0);     //Pecora
        put("-NqIFkPtcD7NHKpKOL0X", 0);     //Spiderman
        put("-NqIFkPxWAuHV9o40Idc", 0);     //Wonderwoman
    }};
    private static final int VALUTA_INIZIALE_PAZIENTE = 100;

    public Genitore registrazioneGenitore(String userId, String nome, String cognome, String username, String email, String password, String telefono, String idLogopedista, String idPaziente) {
        TipoUtente tipoUtente = TipoUtente.GENITORE;

        Genitore genitore = new Genitore(userId, nome, cognome, username, email, password, telefono);
        GenitoreDAO genitoreDAO = new GenitoreDAO();
        genitoreDAO.save(genitore, idLogopedista, idPaziente);

        helperRegistrazione(userId, tipoUtente);

        return genitore;
    }

    public Paziente registrazionePaziente(String userId, String nome, String cognome, String username, String email, String password, int eta, LocalDate dataNascita, char sesso, String idLogopedista) {
        TipoUtente tipoUtente = TipoUtente.PAZIENTE;

        Paziente paziente = new Paziente(userId, nome, cognome, username, email, password, eta, dataNascita, sesso, VALUTA_INIZIALE_PAZIENTE, VALUTA_INIZIALE_PAZIENTE, PERSONAGGI_INIZIALI);
        PazienteDAO pazienteDAO = new PazienteDAO();
        pazienteDAO.save(paziente, idLogopedista);

        helperRegistrazione(userId, tipoUtente);

        return paziente;
    }

    public CompletableFuture<String> reLogLogopedista(String email, String password) {
        CompletableFuture<String> future = new CompletableFuture<>();

        Autenticazione auth = new Autenticazione();
        auth.login(email, password).thenAccept(future::complete);

        return future;
    }

    public int verificaCorrettezzaCampiPaziente(String nomePaziente, String cognomePaziente, String emailPaziente, String usernamePaziente, String passwordPaziente, String confermaPasswordPaziente, String etaPaziente, String dataNascitaPaziente, String sessoPaziente) {
        if (nomePaziente == null || cognomePaziente == null || emailPaziente == null || usernamePaziente == null || passwordPaziente == null || confermaPasswordPaziente == null || etaPaziente == null || dataNascitaPaziente == null || sessoPaziente == null || nomePaziente.isEmpty() || cognomePaziente.isEmpty() || emailPaziente.isEmpty() || usernamePaziente.isEmpty() || passwordPaziente.isEmpty() || confermaPasswordPaziente.isEmpty() || etaPaziente.isEmpty() || dataNascitaPaziente.isEmpty() || sessoPaziente.isEmpty()) {
            return 1;   //Campi incompleti (Paziente)
        }
        if (!passwordPaziente.equals(confermaPasswordPaziente)) {
            return 2;   //Password difformi (Paziente)
        }
        try {
            Integer.parseInt(etaPaziente);
            if(Integer.parseInt(etaPaziente) < 0)   return 3;   //Età non valida
        } catch (Exception e) {
            return 3;   //Età non valida
        }
        try {
            LocalDate.parse(dataNascitaPaziente);
            if(LocalDate.parse(dataNascitaPaziente).isAfter(LocalDate.now()))   return 4;   //Data di nascita non valida
        } catch (Exception e) {
            return 4;   //Data di nascita non valida
        }
        if (sessoPaziente.length() != 1) {
            return 5;   //Sesso non valido
        }

        return 0;   //Campi corretti
    }

    public int verificaCorrettezzaCampiGenitore(String nomeGenitore, String cognomeGenitore, String emailGenitore, String usernameGenitore, String passwordGenitore, String confermaPasswordGenitore, String telefonoGenitore) {
        if (nomeGenitore == null || cognomeGenitore == null || emailGenitore == null || usernameGenitore == null || passwordGenitore == null || confermaPasswordGenitore == null || telefonoGenitore == null || nomeGenitore.isEmpty() || cognomeGenitore.isEmpty() || emailGenitore.isEmpty() || usernameGenitore.isEmpty() || passwordGenitore.isEmpty() || confermaPasswordGenitore.isEmpty() || telefonoGenitore.isEmpty()) {
            return 6;   //Campi incompleti (Genitore)
        }
        if (!passwordGenitore.equals(confermaPasswordGenitore)) {
            return 7;   //Password difformi (Genitore)
        }

        return 0;   //Campi corretti
    }

}
