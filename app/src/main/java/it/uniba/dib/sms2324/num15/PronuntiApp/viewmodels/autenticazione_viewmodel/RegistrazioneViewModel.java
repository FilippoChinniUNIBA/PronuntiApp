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
	public static CompletableFuture<String> verificaRegistrazione(String email, String password, String confermaPassword) {
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

	public static void helperRegistrazione(String userId, TipoUtente tipoUtente) {
		DAOGenerica daoGenerica = new DAOGenerica();
		daoGenerica.saveTipoUtente(userId, tipoUtente);
	}

	public Logopedista registrazioneLogopedista(String userId, String nome, String cognome, String username, String email, String password, String telefono, String indirizzo) {
		TipoUtente tipoUtente = TipoUtente.LOGOPEDISTA;

		Logopedista logopedista = new Logopedista(userId, nome, cognome, username, email, password, telefono, indirizzo);
		LogopedistaDAO logopedistaDAO = new LogopedistaDAO();
		logopedistaDAO.save(logopedista);

		helperRegistrazione(userId, tipoUtente);

		return logopedista;
	}

}
