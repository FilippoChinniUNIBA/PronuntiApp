package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.signInUp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.signinupviewmodel.LoginViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class LoginFragment extends AbstractFragmentWithNavigation {

    private TextInputEditText textInputEditTextUsername;
    private TextInputEditText textInputEditTextPassword;
    private Button buttonLogin;
    private Button buttonToRegister;
    private TextView textViewTitle;

    private LoginViewModel loginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        this.loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        textViewTitle = view.findViewById(R.id.textViewTitoloLogin);
        textInputEditTextUsername = view.findViewById(R.id.textInputEditTextUsernameLogin);
        textInputEditTextPassword = view.findViewById(R.id.textInputEditTextPasswordLogin);
        buttonLogin = view.findViewById(R.id.buttonLogin);
        buttonToRegister = view.findViewById(R.id.buttonToRegister);

        loginViewModel.login(textInputEditTextPassword.getText().toString(),textInputEditTextUsername.getText().toString());

        buttonLogin.setOnClickListener(v -> navigateTo(R.id.loginRegistrazioneFrameLayout, new AvvioRapidoFragment()));
        buttonToRegister.setOnClickListener(v -> navigateTo(R.id.loginRegistrazioneFrameLayout, new RegistrazioneFragment()));

        return view;
    }

    /*
    private void startAppActivity() {

        //controllo che tipo di utente è


        //se è un logopedista
        //startActivity(new Intent(getActivity(), LogopedistaActivity.class));
        //se è un genitore
        //se è un paziente
        //startActivity(new Intent(getActivity(), PazienteActivity.class));

        if (getActivity() != null) {
            getActivity().finish(); // Chiude l'Activity corrente
        }
    }*/

}
