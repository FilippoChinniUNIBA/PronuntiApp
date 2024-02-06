package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.pazienti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.registrazione_viewmodel.RegistrazionePazienteEGenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class RegistrazionePazienteGenitoreFragment extends AbstractFragmentWithNavigation {
    private TextInputEditText editTextNomePaziente;
    private TextInputEditText editTextCognomePaziente;
    private TextInputEditText editTextEmailPaziente;
    private TextInputEditText editTextUsernamePaziente;
    private TextInputEditText editTextPasswordPaziente;
    private TextInputEditText editTextConfermaPasswordPaziente;
    private TextInputEditText editTextEtaPaziente;
    private TextInputEditText editTextDataNascitaPaziente;
    private TextInputEditText editTextSessoPaziente;
    private TextInputEditText editTextNomeGenitore;
    private TextInputEditText editTextCognomeGenitore;
    private TextInputEditText editTextEmailGenitore;
    private TextInputEditText editTextUsernameGenitore;
    private TextInputEditText editTextPasswordGenitore;
    private TextInputEditText editTextConfermaPasswordGenitore;
    private TextInputEditText editTextTelefonoGenitore;
    private TextInputEditText editTextIndirizzoGenitore;
    private Button buttonRegistraPazienteEGenitore;
    private RegistrazionePazienteEGenitoreViewModel registrazionePazienteEGenitoreViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registrazione_paziente_genitore, container, false);

        editTextNomePaziente = (TextInputEditText) view.findViewById(R.id.textInputEditTextNomePaziente);
        editTextCognomePaziente = view.findViewById(R.id.textInputEditTextCognomePaziente);
        editTextEmailPaziente = view.findViewById(R.id.textInputEditTextEmailPaziente);
        editTextUsernamePaziente = view.findViewById(R.id.textInputEditTextUsernamePaziente);
        editTextPasswordPaziente = view.findViewById(R.id.textInputEditTextPasswordPaziente);
        editTextConfermaPasswordPaziente = view.findViewById(R.id.textInputEditTextConfermaPasswordPaziente);
        editTextEtaPaziente = view.findViewById(R.id.textInputEditTextEtaPaziente);
        editTextSessoPaziente = view.findViewById(R.id.textInputEditTextSessoPaziente);
        editTextDataNascitaPaziente = view.findViewById(R.id.textInputEditTextDataNascitaPaziente);

        editTextNomeGenitore = (TextInputEditText) view.findViewById(R.id.textInputEditTextNomeGenitore);
        editTextCognomeGenitore = view.findViewById(R.id.textInputEditTextCognomeGenitore);
        editTextEmailGenitore = view.findViewById(R.id.textInputEditTextEmailGenitore);
        editTextUsernameGenitore = view.findViewById(R.id.textInputEditTextUsernameGenitore);
        editTextPasswordGenitore = view.findViewById(R.id.textInputEditTextPasswordGenitore);
        editTextConfermaPasswordGenitore = view.findViewById(R.id.textInputEditTextConfermaPasswordGenitore);
        editTextTelefonoGenitore = view.findViewById(R.id.textInputEditTextTelefonoGenitore);
        editTextIndirizzoGenitore = view.findViewById(R.id.textInputEditTextIndirizzoGenitore);

        buttonRegistraPazienteEGenitore = view.findViewById(R.id.buttonRegistraPazienteEGenitore);

        registrazionePazienteEGenitoreViewModel = new ViewModelProvider(this).get(RegistrazionePazienteEGenitoreViewModel.class);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonRegistraPazienteEGenitore.setOnClickListener(v -> registraPazienteEGenitore());
    }

    private void registraPazienteEGenitore(){

        String nomepaziente = editTextNomePaziente.getText().toString();
        String cognomepaziente = editTextCognomePaziente.getText().toString();
        String emailpaziente = editTextEmailPaziente.getText().toString();
        String usernamepaziente = editTextUsernamePaziente.getText().toString();
        String passwordpaziente = editTextPasswordPaziente.getText().toString();
        String confermapasswordpaziente = editTextConfermaPasswordPaziente.getText().toString();
        String sessopaziente = editTextSessoPaziente.getText().toString();
        String datanascitapaziente = editTextDataNascitaPaziente.getText().toString();
        String etapaziente = editTextEtaPaziente.getText().toString();

        String nomegenitore = editTextNomeGenitore.getText().toString();
        String cognomegenitore = editTextCognomeGenitore.getText().toString();
        String emailgenitore = editTextEmailGenitore.getText().toString();
        String usernamegenitore = editTextUsernameGenitore.getText().toString();
        String passwordgenitore = editTextPasswordGenitore.getText().toString();
        String confermapasswordgenitore = editTextConfermaPasswordGenitore.getText().toString();
        String telefonogenitore = editTextTelefonoGenitore.getText().toString();
        String indirizzogenitore = editTextIndirizzoGenitore.getText().toString();

    }

}
