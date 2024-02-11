package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.autenticazione;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.AuthSharedPreferences;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Profilo;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.autenticazione_viewmodel.LoginViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.AbstractAppActivity;
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

        this.mLoginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

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
        buttonAccessoRapido.setOnClickListener(v -> navigateTo(R.id.action_loginFragment_to_avvioRapidoFragment));
        buttonToRegister.setOnClickListener(v -> navigateTo(R.id.action_loginFragment_to_registrazioneFragment));
    }

    private void eseguiLogin() {
        String email = textInputEditTextEmail.getText().toString();
        String password = textInputEditTextPassword.getText().toString();

        loginActivityProfilo(email, password);
    }

    private void loginActivityProfilo(String email, String password) {
        CompletableFuture<Boolean> futureIsLoginCorrect = mLoginViewModel.verificaLogin(email, password);
        futureIsLoginCorrect.thenAccept(isLoginCorrect -> {
            if (!isLoginCorrect) {
                InfoDialog infoDialog = new InfoDialog(getContext(), getString(R.string.erroreLoginCredenziali), getString(R.string.tastoRiprova));
                infoDialog.show();
                infoDialog.setOnConfermaButtonClickListener(null);
            }
            else {
                AuthSharedPreferences authSharedPreferences = new AuthSharedPreferences(requireActivity());
                authSharedPreferences.salvaCredenziali(email,password);

                CompletableFuture<Profilo> futureProfilo = mLoginViewModel.login();
                futureProfilo.thenAccept(profilo -> {
                    Log.d("LoginFragment.loginActivityProfilo()", "Profilo: " + profilo.toString());

                    getActivity().runOnUiThread(() -> ((AbstractAppActivity) getActivity()).navigaConProfilo(profilo, getActivity()));
                });
            }
        });
    }



}
