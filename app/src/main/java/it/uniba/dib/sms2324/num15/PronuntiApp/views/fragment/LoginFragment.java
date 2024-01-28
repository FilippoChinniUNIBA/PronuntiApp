package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.AppActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.LoginActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.NavigationLogin;

public class LoginFragment extends Fragment {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonToRegister;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        editTextEmail = view.findViewById(R.id.editTextEmailLogin);
        editTextPassword = view.findViewById(R.id.editTextPasswordLogin);
        buttonLogin = view.findViewById(R.id.buttonLogin);
        buttonToRegister = view.findViewById(R.id.buttonToRegister);
        setUpListeners();

        return view;
    }

    private void setUpListeners() {
        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AppActivity.class);
            startActivity(intent);
            if (getActivity() != null) {
                getActivity().finish(); // Chiude l'Activity corrente
            }
        });

        buttonToRegister.setOnClickListener(v -> {
            if (getActivity() != null) {
                NavigationLogin.replaceFragment(getActivity().getSupportFragmentManager(),
                        R.id.loginFrameLayout,
                        new RegistrazioneFragment());
            }
        });

    }
}
