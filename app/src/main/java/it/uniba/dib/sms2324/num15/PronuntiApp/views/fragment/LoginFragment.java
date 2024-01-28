package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.AppActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.NavigationLogin;

public class LoginFragment extends Fragment {

    private TextInputEditText textInputEditTextUsername;
    private TextInputEditText textInputEditTextPassword;
    private Button buttonLogin;
    private Button buttonToRegister;
    private TextView textViewTitle;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        textViewTitle = view.findViewById(R.id.textViewTitoloLogin);
        textInputEditTextUsername = view.findViewById(R.id.textInputEditTextUsernameLogin);
        textInputEditTextPassword = view.findViewById(R.id.textInputEditTextPasswordLogin);
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
