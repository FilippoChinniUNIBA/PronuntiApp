package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profilo;

import android.media.tv.TvContract;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;


public class ProfiloLogopedistaFragment extends AbstractProfileWithImageFragment {
    private TextInputEditText textInputEditTextTelefono;
    private TextInputEditText textInputEditTextIndirizzo;
    private LogopedistaViewModel logopedistaViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile_logopedista, container, false);

        textViewUsernameProfilo = view.findViewById(R.id.textViewUsernameProfiloLogopedista);
        textInputEditTextNome = view.findViewById(R.id.textInputEditTextNomeProfiloLogopedista);
        textInputEditTextCognome = view.findViewById(R.id.textInputEditTextCognomeProfiloLogopedista);
        textInputEditTextEmail = view.findViewById(R.id.textInputEditTextEmailProfiloLogopedista);
        imageViewProfile = view.findViewById(R.id.imageViewProfile);
        imageViewEditProfile = view.findViewById(R.id.imageViewEditProfile);
        buttonModificaProfilo= view.findViewById(R.id.buttonModificaProfiloLogopedista);
        setPickMedia();

        logopedistaViewModel = new ViewModelProvider(requireActivity()).get(LogopedistaViewModel.class);

        textInputEditTextTelefono = view.findViewById(R.id.textInputEditTextTelefonoProfiloLogopedista);
        textInputEditTextIndirizzo = view.findViewById(R.id.textInputEditTextIndirizzoProfiloLogopedista);

        setData();

        return view;
    }

    @Override
    public void setData(){

        Logopedista logopedista = logopedistaViewModel.getLogopedistaLiveData().getValue();

        textInputEditTextNome.setText(logopedista.getNome());
        textInputEditTextNome.setEnabled(false);
        textInputEditTextCognome.setText(logopedista.getCognome());
        textInputEditTextCognome.setEnabled(false);
        textInputEditTextEmail.setText(logopedista.getEmail());
        textInputEditTextEmail.setEnabled(false);
        textViewUsernameProfilo.setText(logopedista.getUsername());
        textInputEditTextTelefono.setText(logopedista.getTelefono());
        textInputEditTextTelefono.setEnabled(false);
        textInputEditTextIndirizzo.setText(logopedista.getIndirizzo());
        textInputEditTextIndirizzo.setEnabled(false);

    }

    @Override
    public void modificaProfilo() {
        textInputEditTextTelefono.setEnabled(true);
        textInputEditTextIndirizzo.setEnabled(true);

        imageViewProfile.setOnClickListener(v -> pickImage());

        buttonModificaProfilo.setText(getString(R.string.confirm_modify_profile));
        buttonModificaProfilo.setOnClickListener(v -> confermaModificaProfilo());

        imageViewEditProfile.setOnClickListener(v -> pickImage());
        imageViewEditProfile.setVisibility(View.VISIBLE);

        textInputEditTextNome.requestFocus();
    }

    @Override
    void confermaModificaProfilo() {
        super.confermaModificaProfilo();
        //fai le cose che devi fare per salvare i dati aggiornati del logopedista
        //save(textInputEditTextIndirizzo.getText().toString(), textInputEditTextTelefono.getText().toString());
        String indirizzo = textInputEditTextIndirizzo.getText().toString();
        String telefono = textInputEditTextTelefono.getText().toString();
        logopedistaViewModel.getLogopedistaLiveData().getValue().setIndirizzo(indirizzo);
        logopedistaViewModel.getLogopedistaLiveData().getValue().setTelefono(telefono);
        logopedistaViewModel.aggiornaLogopedistaRemoto();
        setData();
    }

}