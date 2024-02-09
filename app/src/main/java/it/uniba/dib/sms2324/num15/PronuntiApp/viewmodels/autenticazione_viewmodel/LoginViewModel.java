package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.autenticazione_viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.Autenticazione;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.database_utils.ComandiDatabaseGenerici;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.GenitoreDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.LogopedistaDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.PazienteDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Profilo;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.TipoUtente;

public class LoginViewModel extends ViewModel {

	public CompletableFuture<Boolean> verificaLogin(String email, String password) {
		CompletableFuture<Boolean> future = new CompletableFuture<>();

		if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
			future.complete(false);
		}
		else {
			Autenticazione auth = new Autenticazione();
			auth.login(email, password).handle((userId, exception) -> {
				if (exception != null) {
					Log.e("LoginViewModel.verificaLogin()", "Errore durante il login: " + exception.getMessage()); //TODO aggiungere agli errori
					future.complete(false);
				}
				else {
					future.complete(true);
				}
				return null;
			});
		}

		return future;
	}

	public CompletableFuture<Profilo> login(String email, String password) {
		CompletableFuture<Profilo> future = new CompletableFuture<>();

		Autenticazione auth = new Autenticazione();
		String userId = auth.getCurrentUserId();

		ComandiDatabaseGenerici comandiDatabaseGenerici = new ComandiDatabaseGenerici();
		CompletableFuture<TipoUtente> tipoUtenteFuture = comandiDatabaseGenerici.getTipoUtente(userId);

		tipoUtenteFuture.thenAccept(tipoUtente -> {
			switch (tipoUtente) {
				case LOGOPEDISTA:
					LogopedistaDAO logopedistaDAO = new LogopedistaDAO();
					CompletableFuture<Logopedista> logopedistaFuture = logopedistaDAO.getById(userId);
					logopedistaFuture.thenAccept(future::complete);
					break;
				case GENITORE:
					GenitoreDAO genitoreDAO = new GenitoreDAO();
					CompletableFuture<Genitore> genitoreFuture = genitoreDAO.getById(userId);
					genitoreFuture.thenAccept(future::complete);
					break;
				case PAZIENTE:
					PazienteDAO pazienteDAO = new PazienteDAO();
					CompletableFuture<Paziente> pazienteFuture = pazienteDAO.getById(userId);
					pazienteFuture.thenAccept(future::complete);
					break;
				default:
					Log.e("LoginViewModel.login()", "TipoUtente non riconosciuto: " + tipoUtente);  //TODO aggiungere agli errori
			}
		});

		return future;
	}

}
