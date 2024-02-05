package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.signinup_viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.Autenticazione;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAOGenerica;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.TipoUtente;

public class RegistrazioneViewModel extends ViewModel {

	public void registrazione(String email, String password, TipoUtente tipoUtente) {
		Autenticazione autenticazione = new Autenticazione();

		CompletableFuture<String> future = autenticazione.registrazione(email, password);
		future.thenAccept(userId -> {
			DAOGenerica daoGenerica = new DAOGenerica();
			daoGenerica.saveTipoUtente(userId, tipoUtente);

			LoginViewModel loginViewModel = new LoginViewModel();
			loginViewModel.login(email, password);
		});
	}

}
