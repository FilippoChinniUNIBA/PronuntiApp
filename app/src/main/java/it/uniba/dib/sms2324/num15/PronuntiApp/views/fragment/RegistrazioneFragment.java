package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.NavigationLogin;

public class RegistrazioneFragment extends Fragment {

    private TextInputEditText editTextNome;
    private TextInputEditText editTextCognome;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextUsername;
    private TextInputEditText editTextPassword;
    private TextInputEditText editTextConfermaPassword;
    private TextInputEditText editTextTelefono;
    private TextInputEditText editTextIndirizzo;
    private Button buttonRegistrati;
    private Button buttonVaiAlLogin;

    public RegistrazioneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registrazione, container, false);

        editTextNome = (TextInputEditText) view.findViewById(R.id.textInputEditTextNome);
        editTextCognome = view.findViewById(R.id.textInputEditTextCognome);
        editTextEmail = view.findViewById(R.id.textInputEditTextEmail);
        editTextUsername = view.findViewById(R.id.textInputEditTextUsername);
        editTextPassword = view.findViewById(R.id.textInputEditTextPassword);
        editTextConfermaPassword = view.findViewById(R.id.textInputEditTextConfermaPassword);
        editTextTelefono = view.findViewById(R.id.textInputEditTextTelefono);
        editTextIndirizzo = view.findViewById(R.id.textInputEditTextIndirizzo);
        buttonRegistrati = view.findViewById(R.id.buttonRegistrati);
        buttonVaiAlLogin = view.findViewById(R.id.buttonVaiAlLogin);

        buttonRegistrati.setOnClickListener(v -> registration());

        buttonVaiAlLogin.setOnClickListener(v -> navigationToLogin());

        return view;
    }

    private void navigationToLogin() {
        if (getActivity() != null) {
            NavigationLogin.replaceFragment(getActivity().getSupportFragmentManager(),
                    R.id.loginFrameLayout,
                    new LoginFragment());
        }
    }

    private void registration(){

    }
}
