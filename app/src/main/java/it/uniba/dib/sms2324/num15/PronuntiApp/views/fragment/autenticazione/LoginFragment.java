package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.autenticazione;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.AbstractProfilo;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Profilo;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.signinup_viewmodel.LoginViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.GenitoreActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.LogopedistaActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.PazienteActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.InfoDialog;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class LoginFragment extends AbstractFragmentWithNavigation {

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private Button buttonLogin;
    private Button buttonToRegister;
    private Button buttonAccessoRapido;

    private LoginViewModel mLoginViewModel;

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        this.mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        this.textInputEditTextEmail = view.findViewById(R.id.textInputEditTextEmailLogin);
        this.textInputEditTextPassword = view.findViewById(R.id.textInputEditTextPasswordLogin);

        this.buttonLogin = view.findViewById(R.id.buttonLogin);
        this.buttonToRegister = view.findViewById(R.id.buttonToRegister);
        this.buttonAccessoRapido = view.findViewById(R.id.buttonAccessoRapido);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonLogin.setOnClickListener(v -> eseguiLogin());
        buttonAccessoRapido.setOnClickListener(v -> navigateTo(R.id.autenticazioneFrameLayout, new AvvioRapidoFragment()));
        buttonToRegister.setOnClickListener(v -> navigateTo(R.id.autenticazioneFrameLayout, new RegistrazioneFragment()));
    }

    private void eseguiLogin() {
        String email = textInputEditTextEmail.getText().toString();
        String password = textInputEditTextPassword.getText().toString();

        CompletableFuture<Boolean> futureIsLoginCorrect = mLoginViewModel.verificaLogin(email, password);
        futureIsLoginCorrect.thenAccept(isLoginCorrect -> {
            if (!isLoginCorrect) {
                InfoDialog infoDialog = new InfoDialog(getContext(), "Credenziali incorrette o mancanti", "Riprova");
                infoDialog.show();
                infoDialog.setOnConfermaButtonClickListener(null);
            }
            else {
                CompletableFuture<Profilo> futureProfilo = mLoginViewModel.login(email, password);
                futureProfilo.thenAccept(profilo -> {
                    Log.d("LoginFragment.eseguiLogin()", "Profilo: " + profilo.toString());

                    getActivity().runOnUiThread(() -> {
                        if (profilo instanceof Logopedista) {
                            Intent intent = new Intent(getActivity(), LogopedistaActivity.class);
                            intent.putExtra("profilo", profilo);
                            startActivity(intent);
                        } else if (profilo instanceof Genitore) {
                            Intent intent = new Intent(getActivity(), GenitoreActivity.class);
                            intent.putExtra("profilo", profilo);
                            startActivity(intent);
                        } else if (profilo instanceof Paziente) {
                            Intent intent = new Intent(getActivity(), PazienteActivity.class);
                            intent.putExtra("profilo", profilo);
                            startActivity(intent);
                        }
                    });
                });
            }
        });
    }

}
