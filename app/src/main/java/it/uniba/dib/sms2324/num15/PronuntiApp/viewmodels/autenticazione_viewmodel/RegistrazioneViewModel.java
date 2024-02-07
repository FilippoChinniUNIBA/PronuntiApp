package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.autenticazione_viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.Autenticazione;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAOGenerica;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.GenitoreDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.LogopedistaDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.PazienteDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.TipoUtente;

public class RegistrazioneViewModel extends ViewModel {
	public CompletableFuture<String> verificaRegistrazione(String email, String password, String confermaPassword) {
		CompletableFuture<String> future = new CompletableFuture<>();

		if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
			future.complete(null);
		}
		else if (!password.equals(confermaPassword)) {
			future.complete(null);
		}
		else {
			Autenticazione auth = new Autenticazione();
			auth.registrazione(email, password).handle((userId, exception) -> {
				if (exception != null) {
					Log.d("RegistrazioneViewModel.verificaRegistrazione()", "Errore durante la registrazione: " + exception.getMessage()); //TODO aggiungere agli errori
					future.complete(null);
				}
				else {
					future.complete(userId);
				}
				return null;
			});
		}

		return future;
	}

	public Logopedista registrazioneLogopedista(String userId, String nome, String cognome, String username, String email, String password, String telefono, String indirizzo) {
		TipoUtente tipoUtente = TipoUtente.LOGOPEDISTA;

		Logopedista logopedista = new Logopedista(userId, nome, cognome, username, email, password, telefono, indirizzo);
		LogopedistaDAO logopedistaDAO = new LogopedistaDAO();
		logopedistaDAO.save(logopedista);

		helperRegistrazione(userId, tipoUtente);

		return logopedista;
	}

	public Genitore registrazioneGenitore(String userId, String nome, String cognome, String username, String email, String password, String telefono, String idPaziente) {
		TipoUtente tipoUtente = TipoUtente.GENITORE;

		Genitore genitore = new Genitore(userId, nome, cognome, username, email, password, telefono);
		GenitoreDAO genitoreDAO = new GenitoreDAO();
		genitoreDAO.save(genitore, idPaziente); //TODO qui serve che il Logopedista si rilogghi prima di fare questa operazione

		helperRegistrazione(userId, tipoUtente);

		return genitore;
	}

	public Paziente registrazionePaziente(String userId, String nome, String cognome, String username, String email, String password, int eta, LocalDate dataNascita, char sesso, int valuta, int punteggioTot) {
		TipoUtente tipoUtente = TipoUtente.PAZIENTE;

		Paziente paziente = new Paziente(userId, nome, cognome, username, email, password, eta, dataNascita, sesso, valuta, punteggioTot, PERSONAGGI_INIZIALI);
		PazienteDAO pazienteDAO = new PazienteDAO();
		pazienteDAO.save(paziente); //TODO qui serve che il Logopedista si rilogghi prima di fare questa operazione

		helperRegistrazione(userId, tipoUtente);

		return paziente;
	}

	public void helperRegistrazione(String userId, TipoUtente tipoUtente) {
		DAOGenerica daoGenerica = new DAOGenerica();
		daoGenerica.saveTipoUtente(userId, tipoUtente);
	}

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

}
