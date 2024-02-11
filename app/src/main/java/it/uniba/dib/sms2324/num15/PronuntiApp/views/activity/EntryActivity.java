package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.AuthSharedPreferences;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Profilo;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.autenticazione_viewmodel.LoginViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.autenticazione.LoginFragment;

public class EntryActivity extends AbstractAppActivity {
    private LoginViewModel mLoginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        this.mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        verifyLoginPrecedente();
    }

    private void verifyLoginPrecedente() {
        AuthSharedPreferences authSharedPreferences = new AuthSharedPreferences(this);
        String email = authSharedPreferences.getEmail();
        String password = authSharedPreferences.getPassword();

        if (email != null && password != null) {
            loginActivityProfilo(email, password);
        } else {
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

                this.runOnUiThread(() -> navigaConProfilo(profilo, this));
            });
        });
    }

}
