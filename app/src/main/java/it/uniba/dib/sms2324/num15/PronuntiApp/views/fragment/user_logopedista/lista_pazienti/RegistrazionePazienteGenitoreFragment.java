package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti;

import static it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.autenticazione_viewmodel.RegistrazioneViewModel.verificaRegistrazione;

import android.app.DatePickerDialog;
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
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.lista_pazienti.RegistrazionePazienteGenitoreController;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.InfoDialog;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.utils_fragments.DatePickerCustom;

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

    private LogopedistaViewModel mLogopedistaViewModel;
    private RegistrazionePazienteGenitoreController mController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registrazione_paziente_genitore, container, false);
        setToolBar(view, getString(R.string.registrazionePaziente));

        this.mLogopedistaViewModel = new ViewModelProvider(requireActivity()).get(LogopedistaViewModel.class);
        this.mController = mLogopedistaViewModel.getRegistrazionePazienteGenitoreController();


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

        editTextDataNascitaPaziente.setOnClickListener(v -> DatePickerCustom.showDatePickerDialog(getContext(), editTextDataNascitaPaziente));

        buttonRegistraPazienteEGenitore.setOnClickListener(v -> {
            Logopedista mLogopedista = mLogopedistaViewModel.getLogopedistaLiveData().getValue();
            String idLogopedista = mLogopedista.getIdProfilo();

            eseguiRegistrazionePaziente(idLogopedista).thenAccept(paziente -> {
                eseguiRegistrazioneGenitore(idLogopedista, paziente.getIdProfilo()).thenAccept(genitore -> {
                    paziente.setGenitore(genitore);
                    mController.reLogLogopedista(mLogopedista.getEmail(), mLogopedista.getPassword()).thenAccept(userId -> {
                        mLogopedista.addPaziente(paziente);
                        mLogopedista.aggiornaClassificaPazienti();
                        mLogopedistaViewModel.aggiornaLogopedistaRemoto();
                        getActivity().runOnUiThread(() -> navigateTo(R.id.action_registrazionePazienteGenitoreFragment_to_pazientiFragment));
                    });
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

        CompletableFuture<Paziente> futurePaziente = new CompletableFuture<>();

        int statusCampiValidi = mController.verificaCorrettezzaCampiPaziente(nomePaziente, cognomePaziente, emailPaziente, usernamePaziente, passwordPaziente, confermaPasswordPaziente, editTextEtaPaziente.getText().toString(), editTextDataNascitaPaziente.getText().toString(), editTextSessoPaziente.getText().toString());
        if (statusCampiValidi != 0) {
            creaDialogErroreCampi(statusCampiValidi);
        }
        else {
            char sessoPaziente = editTextSessoPaziente.getText().toString().charAt(0);
            LocalDate dataNascitaPaziente = LocalDate.parse(editTextDataNascitaPaziente.getText().toString());
            int etaPaziente = Integer.parseInt(editTextEtaPaziente.getText().toString());

            CompletableFuture<String> futureIsRegistrationCorrect = verificaRegistrazione(emailPaziente, passwordPaziente);
            futureIsRegistrationCorrect.thenAccept(userId -> {
                if (userId == null) {
                    creaDialogErroreCampi(8);   //Errore del Database; probabilmente email del bambino già in uso
                } else {
                    Paziente paziente = mController.registrazionePaziente(userId, nomePaziente, cognomePaziente, usernamePaziente, emailPaziente, passwordPaziente, etaPaziente, dataNascitaPaziente, sessoPaziente, idLogopedista);
                    Log.d("RegistrazionePazienteGenitoreFragment.eseguiRegistrazionePaziente()", paziente.toString());

                    futurePaziente.complete(paziente);
                }
            });
        }

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

        int statusCampiValidi = mController.verificaCorrettezzaCampiGenitore(nomeGenitore, cognomeGenitore, emailGenitore, usernameGenitore, passwordGenitore, confermaPasswordGenitore, telefonoGenitore);
        if (statusCampiValidi != 0) {
            creaDialogErroreCampi(statusCampiValidi);
        }
        else {
            CompletableFuture<String> futureIsRegistrationCorrect = verificaRegistrazione(emailGenitore, passwordGenitore);
            futureIsRegistrationCorrect.thenAccept(userId -> {
                if (userId == null) {
                    creaDialogErroreCampi(9);   //Errore del Database; probabilmente email del genitore già in uso
                } else {
                    Genitore genitore = mController.registrazioneGenitore(userId, nomeGenitore, cognomeGenitore, usernameGenitore, emailGenitore, passwordGenitore, telefonoGenitore, idLogopedista, idPaziente);
                    Log.d("RegistrazionePazienteGenitoreFragment.eseguiRegistrazioneGenitore()", genitore.toString());

                    futureGenitore.complete(genitore);
                }
            });
        }

        return futureGenitore;
    }

    public void creaDialogErroreCampi(int tipoErrore) {
        String messaggioErrore = "";
        switch (tipoErrore) {
            case 1:
                messaggioErrore = getString(R.string.erroreRegistrazionePazienteCampiMancanti);
                break;
            case 2:
                messaggioErrore = getString(R.string.erroreRegistrazionePazientePasswordDifformi);
                break;
            case 3:
                messaggioErrore = getString(R.string.erroreRegistrazionePazienteEtaNonValida);
                break;
            case 4:
                messaggioErrore = getString(R.string.erroreRegistrazionePazienteDataNonValida);
                break;
            case 5:
                messaggioErrore = getString(R.string.erroreRegistrazionePazienteSessoNonValido);
                break;
            case 6:
                messaggioErrore = getString(R.string.erroreRegistrazioneGenitoreCampiMancanti);
                break;
            case 7:
                messaggioErrore = getString(R.string.erroreRegistrazioneGenitorePasswordDifformi);
                break;
            case 8:
                messaggioErrore = getString(R.string.erroreRegistrazionePazienteAutenticazione);
                break;
            case 9:
                messaggioErrore = getString(R.string.erroreRegistrazioneGenitoreAutenticazione);
                break;
        }
        InfoDialog infoDialog = new InfoDialog(getContext(), messaggioErrore, getString(R.string.tastoRiprova));
        infoDialog.show();
        infoDialog.setOnConfermaButtonClickListener(null);
    }

}
