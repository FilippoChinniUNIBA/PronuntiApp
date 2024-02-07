package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.pazienti;

import static it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.autenticazione_viewmodel.RegistrazioneViewModel.verificaRegistrazione;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.Autenticazione;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.RegistrazionePazienteGenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.InfoDialog;
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

    private Button buttonRegistraPazienteEGenitore;

    private RegistrazionePazienteGenitoreViewModel mRegistrazionePazienteGenitoreViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registrazione_paziente_genitore, container, false);

        this.mRegistrazionePazienteGenitoreViewModel = new ViewModelProvider(this).get(RegistrazionePazienteGenitoreViewModel.class);


        this.editTextNomePaziente = view.findViewById(R.id.textInputEditTextNomePaziente);
        this.editTextCognomePaziente = view.findViewById(R.id.textInputEditTextCognomePaziente);
        this.editTextEmailPaziente = view.findViewById(R.id.textInputEditTextEmailPaziente);
        this.editTextUsernamePaziente = view.findViewById(R.id.textInputEditTextUsernamePaziente);
        this.editTextPasswordPaziente = view.findViewById(R.id.textInputEditTextPasswordPaziente);
        this.editTextConfermaPasswordPaziente = view.findViewById(R.id.textInputEditTextConfermaPasswordPaziente);
        this.editTextEtaPaziente = view.findViewById(R.id.textInputEditTextEtaPaziente);
        this.editTextSessoPaziente = view.findViewById(R.id.textInputEditTextSessoPaziente);
        this.editTextDataNascitaPaziente = view.findViewById(R.id.textInputEditTextDataNascitaPaziente);

        this.editTextNomeGenitore = view.findViewById(R.id.textInputEditTextNomeGenitore);
        this.editTextCognomeGenitore = view.findViewById(R.id.textInputEditTextCognomeGenitore);
        this.editTextEmailGenitore = view.findViewById(R.id.textInputEditTextEmailGenitore);
        this.editTextUsernameGenitore = view.findViewById(R.id.textInputEditTextUsernameGenitore);
        this.editTextPasswordGenitore = view.findViewById(R.id.textInputEditTextPasswordGenitore);
        this.editTextConfermaPasswordGenitore = view.findViewById(R.id.textInputEditTextConfermaPasswordGenitore);
        this.editTextTelefonoGenitore = view.findViewById(R.id.textInputEditTextTelefonoGenitore);

        this.buttonRegistraPazienteEGenitore = view.findViewById(R.id.buttonRegistraPazienteEGenitore);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonRegistraPazienteEGenitore.setOnClickListener(v -> {
            Autenticazione auth = new Autenticazione();
            String idLogopedista = auth.getCurrentUserId(); //TODO dovrebbe prenderlo dal logopedista del main viewModel dell'activity

            eseguiRegistrazionePaziente(idLogopedista).thenAccept(paziente -> {
                eseguiRegistrazioneGenitore(idLogopedista, paziente.getIdProfilo()).thenAccept(genitore -> {
                    //TODO qui logopedista deve riloggarsi, e solo DOPO si deve eseguire il resto
                    //TODO qui l'oggetto logopedista del main viewModel dell'activity deve essere aggiornato
                    replaceFragment(R.id.frameLayoutLogopedista, new PazientiFragment(), null);
                });
            });
        });
    }

    private CompletableFuture<Paziente> eseguiRegistrazionePaziente(String idLogopedista) {
        String nomePaziente = editTextNomePaziente.getText().toString();
        String cognomePaziente = editTextCognomePaziente.getText().toString();
        String emailPaziente = editTextEmailPaziente.getText().toString();
        String usernamePaziente = editTextUsernamePaziente.getText().toString();
        String passwordPaziente = editTextPasswordPaziente.getText().toString();
        String confermaPasswordPaziente = editTextConfermaPasswordPaziente.getText().toString();
        char sessoPaziente = editTextSessoPaziente.getText().toString().charAt(0);
        LocalDate dataNascitaPaziente = LocalDate.parse(editTextDataNascitaPaziente.getText().toString());
        int etaPaziente = Integer.parseInt(editTextEtaPaziente.getText().toString());

        CompletableFuture<Paziente> futurePaziente = new CompletableFuture<>();

        CompletableFuture<String> futureIsRegistrationCorrect = verificaRegistrazione(emailPaziente, passwordPaziente, confermaPasswordPaziente);
        futureIsRegistrationCorrect.thenAccept(userId -> {
            if (userId == null) {
                InfoDialog infoDialog = new InfoDialog(getContext(), "Campi del Genitore incompleti o incorretti. Oppure password difformi.", "Riprova");
                infoDialog.show();
                infoDialog.setOnConfermaButtonClickListener(null);
            } else {
                Paziente paziente = mRegistrazionePazienteGenitoreViewModel.registrazionePaziente(userId, nomePaziente, cognomePaziente, usernamePaziente, emailPaziente, passwordPaziente, etaPaziente, dataNascitaPaziente, sessoPaziente, idLogopedista);
                Log.d("RegistrazionePazienteGenitoreFragment.eseguiRegistrazionePaziente()", paziente.toString());

                futurePaziente.complete(paziente);
            }
        });

        return futurePaziente;
    }

    private CompletableFuture<Genitore> eseguiRegistrazioneGenitore(String idLogopedista, String idPaziente) {
        String nomeGenitore = editTextNomeGenitore.getText().toString();
        String cognomeGenitore = editTextCognomeGenitore.getText().toString();
        String emailGenitore = editTextEmailGenitore.getText().toString();
        String usernameGenitore = editTextUsernameGenitore.getText().toString();
        String passwordGenitore = editTextPasswordGenitore.getText().toString();
        String confermaPasswordGenitore = editTextConfermaPasswordGenitore.getText().toString();
        String telefonoGenitore = editTextTelefonoGenitore.getText().toString();

        CompletableFuture<Genitore> futureGenitore = new CompletableFuture<>();

        CompletableFuture<String> futureIsRegistrationCorrect = verificaRegistrazione(emailGenitore, passwordGenitore, confermaPasswordGenitore);
        futureIsRegistrationCorrect.thenAccept(userId -> {
            if (userId == null) {
                InfoDialog infoDialog = new InfoDialog(getContext(), "Campi del Bambino incompleti o incorretti. Oppure password difformi.", "Riprova");
                infoDialog.show();
                infoDialog.setOnConfermaButtonClickListener(null);
            } else {
                Genitore genitore = mRegistrazionePazienteGenitoreViewModel.registrazioneGenitore(userId, nomeGenitore, cognomeGenitore, usernameGenitore, emailGenitore, passwordGenitore, telefonoGenitore, idLogopedista, idPaziente);
                Log.d("RegistrazionePazienteGenitoreFragment.eseguiRegistrazioneGenitore()", genitore.toString());

                futureGenitore.complete(genitore);
            }
        });

        return futureGenitore;
    }

}
