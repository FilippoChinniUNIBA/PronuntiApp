package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel;

import static it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.autenticazione_viewmodel.RegistrazioneViewModel.helperRegistrazione;

import androidx.lifecycle.ViewModel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.GenitoreDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.PazienteDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.TipoUtente;

public class RegistrazionePazienteGenitoreViewModel extends ViewModel {
    private static final Map<String, Integer> PERSONAGGI_INIZIALI = new HashMap<String, Integer>() {{
        put("personaggio1", 0);
        put("personaggio2", 0);
        put("personaggio3", 0);
        put("personaggio4", 0);
        put("personaggio5", 0);
        put("personaggio6", 0);
        put("personaggio7", 0);
        put("personaggio8", 0);
        put("personaggio9", 0);
        put("personaggio10", 0);
    }};
    private static final int VALUTA_INIZIALE_PAZIENTE = 50;

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

}
