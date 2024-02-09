package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.autenticazione_viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.Autenticazione;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.database_utils.ComandiDatabaseGenerici;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.LogopedistaDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.TipoUtente;

public class RegistrazioneViewModel extends ViewModel {

	public static CompletableFuture<String> verificaRegistrazione(String email, String password) {
		CompletableFuture<String> future = new CompletableFuture<>();

		Autenticazione auth = new Autenticazione();
		auth.registrazione(email, password).handle((userId, exception) -> {
			if (exception != null) {
				Log.e("RegistrazioneViewModel.verificaRegistrazione()", "Errore durante la registrazione: " + exception.getMessage()); //TODO aggiungere agli errori
				future.complete(null);
			} else {
				future.complete(userId);
			}
			return null;
		});
		return future;
	}

	public static void helperRegistrazione(String userId, TipoUtente tipoUtente) {
		ComandiDatabaseGenerici comandiDatabaseGenerici = new ComandiDatabaseGenerici();
		comandiDatabaseGenerici.saveTipoUtente(userId, tipoUtente);
	}

	public Logopedista registrazioneLogopedista(String userId, String nome, String cognome, String username, String email, String password, String telefono, String indirizzo) {
		TipoUtente tipoUtente = TipoUtente.LOGOPEDISTA;

		Logopedista logopedista = new Logopedista(userId, nome, cognome, username, email, password, telefono, indirizzo);
		LogopedistaDAO logopedistaDAO = new LogopedistaDAO();
		logopedistaDAO.save(logopedista);

		helperRegistrazione(userId, tipoUtente);

		return logopedista;
	}

	public int verificaCorrettezzaCampiLogopedista(String nomeLogopedista, String cognomeLogopedista, String usernameLogopedista, String emailLogopedista, String passwordLogopedista, String confermaPasswordLogopedista, String telefono, String indirizzo) {
		if (nomeLogopedista.isEmpty() || cognomeLogopedista.isEmpty() || usernameLogopedista.isEmpty() || emailLogopedista.isEmpty() || passwordLogopedista.isEmpty() || confermaPasswordLogopedista.isEmpty() || telefono.isEmpty() || indirizzo.isEmpty() || nomeLogopedista == null || cognomeLogopedista == null || usernameLogopedista == null || emailLogopedista == null || passwordLogopedista == null || confermaPasswordLogopedista == null || telefono == null || indirizzo == null) {
			return 1;	//Campi incompleti
		}
		if (!passwordLogopedista.equals(confermaPasswordLogopedista)) {
			return 2;	//Password Difformi
		}
		return 0;
	}

}
