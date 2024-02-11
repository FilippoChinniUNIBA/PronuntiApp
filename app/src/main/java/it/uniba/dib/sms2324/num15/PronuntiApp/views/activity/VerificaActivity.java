package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.Autenticazione;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.CredentialSaver;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Profilo;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.autenticazione_viewmodel.LoginViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.InfoDialog;

public class VerificaActivity extends AbstractAppActivity{
    private LoginViewModel mLoginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifica);
        this.mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        verifyLoginPrecedente();
    }

    private void verifyLoginPrecedente(){
        CredentialSaver credentialSaver = new CredentialSaver(this);
        String email = credentialSaver.getEmail();
        String password = credentialSaver.getPassword();
        if(email !=null && password !=null){
            loginActivityProfilo(email,password);
        }else {
            Intent intent = new Intent(this, AutenticazioneActivity.class);
            startActivity(intent);
        }
    }

    private void loginActivityProfilo(String email, String password) {
        CompletableFuture<Boolean> futureIsLoginCorrect = mLoginViewModel.verificaLogin(email, password);
        futureIsLoginCorrect.thenAccept(isLoginCorrect -> {
            CompletableFuture<Profilo> futureProfilo = mLoginViewModel.login();
            futureProfilo.thenAccept(profilo -> {
                Log.d("LoginFragment.loginActivityProfilo()", "Profilo: " + profilo.toString());

                this.runOnUiThread(() -> {
                    //todo questa sezione anche in LoginFragment puo essere resa statica
                    if (profilo instanceof Logopedista) {
                        Intent intent = new Intent(this, LogopedistaActivity.class);
                        intent.putExtra("profilo", profilo);
                        startActivity(intent);
                    } else if (profilo instanceof Genitore) {
                        Intent intent = new Intent(this, GenitoreActivity.class);
                        intent.putExtra("profilo", profilo);
                        startActivity(intent);
                    } else if (profilo instanceof Paziente) {
                        Intent intent = new Intent(this, PazienteActivity.class);
                        intent.putExtra("profilo", profilo);
                        startActivity(intent);
                    }
                });
            });
        });
    }
}
