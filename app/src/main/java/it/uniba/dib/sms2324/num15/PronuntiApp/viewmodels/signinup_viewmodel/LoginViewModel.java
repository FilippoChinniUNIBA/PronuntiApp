package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.signinup_viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.Autenticazione;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.DAOGenerica;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.GenitoreDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.LogopedistaDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.PazienteDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.TipoUtente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.profile_viewmodel.ProfileGenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.profile_viewmodel.ProfileLogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.profile_viewmodel.ProfilePazienteViewModel;

public class LoginViewModel extends ViewModel {

	private ProfileLogopedistaViewModel profileLogopedistaViewModel;
	private ProfileGenitoreViewModel profileGenitoreViewModel;
	private ProfilePazienteViewModel profilePazienteViewModel;

	public void login(String email, String password) {
		FirebaseAuth mAuth = new Autenticazione().getAuth();
		String userId = mAuth.getCurrentUser().getUid();

		DAOGenerica daoGenerica = new DAOGenerica();
		CompletableFuture<TipoUtente> tipoUtenteFuture = daoGenerica.getTipoUtente(userId);

		tipoUtenteFuture.thenAccept(tipoUtente -> {
			switch (tipoUtente) {
				case LOGOPEDISTA:
					profileLogopedistaViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(ProfileLogopedistaViewModel.class);
                    //profileLogopedistaViewModel.setLogopedista();
					break;
				case GENITORE:
					profileGenitoreViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(ProfileGenitoreViewModel.class);
                    //profileGenitoreViewModel.setGenitore();
					break;
				case PAZIENTE:
					profilePazienteViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(ProfilePazienteViewModel.class);
                    //profilePazienteViewModel.setPaziente();
					break;
				default:
					Log.e("LoginViewModel.login()", "TipoUtente non riconosciuto: " + tipoUtente);  //TODO aggiungere agli errori
			}
		});
	}

    private void setLogopedistaViewModel (String userId) {
        LogopedistaDAO logopedistaDAO = new LogopedistaDAO();
        CompletableFuture<Logopedista> logopedistaFuture = logopedistaDAO.getById(userId);

        logopedistaFuture.thenAccept(logopedista -> {
            profileLogopedistaViewModel.setLogopedista(logopedista);
        });
    }

    private void setGenitoreViewModel (String userId) {
        GenitoreDAO genitoreDAO = new GenitoreDAO();
        CompletableFuture<Genitore> pazienteFuture = genitoreDAO.getById(userId);

        pazienteFuture.thenAccept(genitore -> {
            profileGenitoreViewModel.setGenitore(genitore);
        });
    }

    private void setPazienteViewModel (String userId) {
        PazienteDAO pazienteDAO = new PazienteDAO();
        CompletableFuture<Paziente> pazienteFuture = pazienteDAO.getById(userId);

        pazienteFuture.thenAccept(paziente -> {
            profilePazienteViewModel.setPaziente(paziente);
        });
    }

}
