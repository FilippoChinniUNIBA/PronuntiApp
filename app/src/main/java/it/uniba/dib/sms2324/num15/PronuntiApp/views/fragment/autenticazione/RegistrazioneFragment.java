package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.autenticazione;

import android.content.Intent;
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
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.autenticazione_viewmodel.RegistrazioneViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.LogopedistaActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.InfoDialog;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class RegistrazioneFragment extends AbstractFragmentWithNavigation {
    private TextInputEditText editTextNome;
    private TextInputEditText editTextCognome;
    private TextInputEditText editTextUsername;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPassword;
    private TextInputEditText editTextConfermaPassword;
    private TextInputEditText editTextTelefono;
    private TextInputEditText editTextIndirizzo;
    private Button buttonRegistrati;
    private Button buttonVaiAlLogin;

    private RegistrazioneViewModel mRegistrazioneViewModel;

    public RegistrazioneFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registrazione, container, false);

        this.mRegistrazioneViewModel = new ViewModelProvider(this).get(RegistrazioneViewModel.class);

        this.editTextNome = view.findViewById(R.id.textInputEditTextNome);
        this.editTextCognome = view.findViewById(R.id.textInputEditTextCognome);
        this.editTextEmail = view.findViewById(R.id.textInputEditTextEmail);
        this.editTextUsername = view.findViewById(R.id.textInputEditTextUsername);
        this.editTextPassword = view.findViewById(R.id.textInputEditTextPassword);
        this.editTextConfermaPassword = view.findViewById(R.id.textInputEditTextConfermaPassword);
        this.editTextTelefono = view.findViewById(R.id.textInputEditTextTelefono);
        this.editTextIndirizzo = view.findViewById(R.id.textInputEditTextIndirizzo);

        this.buttonRegistrati = view.findViewById(R.id.buttonRegistrati);
        this.buttonVaiAlLogin = view.findViewById(R.id.buttonVaiAlLogin);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonRegistrati.setOnClickListener(v -> eseguiRegistrazione());
        buttonVaiAlLogin.setOnClickListener(v -> replaceFragment(R.id.autenticazioneFrameLayout, new LoginFragment(), null));

    }

    private void eseguiRegistrazione() {
        String nome = editTextNome.getText().toString();
        String cognome = editTextCognome.getText().toString();
        String email = editTextEmail.getText().toString();
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String confermaPassword = editTextConfermaPassword.getText().toString();
        String telefono = editTextTelefono.getText().toString();
        String indirizzo = editTextIndirizzo.getText().toString();

        int statusCampiValidi = mRegistrazioneViewModel.verificaCorrettezzaCampiLogopedista(nome, cognome, username, email, password, confermaPassword, telefono, indirizzo);
        if (statusCampiValidi != 0) {
            creaDialogErroreCampi(statusCampiValidi);
        }
        else {
            CompletableFuture<String> futureIsRegistrationCorrect = RegistrazioneViewModel.verificaRegistrazione(email, password);
            futureIsRegistrationCorrect.thenAccept(userId -> {
                if (userId == null) {
                    creaDialogErroreCampi(3);   //Errore Database probabilmente email già in uso
                }
                else {
                    Logopedista logopedista = mRegistrazioneViewModel.registrazioneLogopedista(userId, nome, cognome, username, email, password, telefono, indirizzo);
                    Log.d("RegistrazioneFragment.eseguiRegistrazione()", "Logopedista: " + logopedista.toString());

                    getActivity().runOnUiThread(() -> {
                        Intent intent = new Intent(getActivity(), LogopedistaActivity.class);
                        intent.putExtra("profilo", logopedista);
                        startActivity(intent);
                    });
                }
            });
        }
    }

    public void creaDialogErroreCampi(int tipoErrore) {
        String messaggioErrore = "";
        switch (tipoErrore) {
            case 1:
                messaggioErrore = getString(R.string.erroreRegistrazioneLogopedistaCampiIncompleti);
                break;
            case 2:
                messaggioErrore = getString(R.string.erroreRegistrazioneLogopedistaPasswordDifformi);
                break;
            case 3:
                messaggioErrore = getString(R.string.erroreRegistrazioneLogopedistaAutenticazione);
                break;
        }
        InfoDialog infoDialog = new InfoDialog(getContext(), messaggioErrore, getString(R.string.tastoRiprova));
        infoDialog.show();
        infoDialog.setOnConfermaButtonClickListener(null);
    }

}
