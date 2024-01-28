package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.NavigationLogin;

public class RegistrazioneFragment extends Fragment {

    private EditText editTextNome;
    private EditText editTextCognome;
    private EditText editTextEmail;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConfermaPassword;
    private EditText editTextTelefono;
    private EditText editTextIndirizzo;
    private Button buttonRegistrati;
    private Button buttonVaiAlLogin;

    public RegistrazioneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registrazione, container, false);

        editTextNome = view.findViewById(R.id.editTextNome);
        editTextCognome = view.findViewById(R.id.editTextCognome);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextConfermaPassword = view.findViewById(R.id.editTextConfermaPassword);
        editTextTelefono = view.findViewById(R.id.editTextTelefono);
        editTextIndirizzo = view.findViewById(R.id.editTextIndirizzo);
        buttonRegistrati = view.findViewById(R.id.buttonRegistrati);
        buttonVaiAlLogin = view.findViewById(R.id.buttonVaiAlLogin);

        buttonRegistrati.setOnClickListener(v -> {
            // Implement registration logic
        });

        buttonVaiAlLogin.setOnClickListener(v -> {
            if (getActivity() != null) {
                NavigationLogin.replaceFragment(getActivity().getSupportFragmentManager(),
                        R.id.loginFrameLayout,
                        new LoginFragment());
            }
        });

        return view;
    }
}
