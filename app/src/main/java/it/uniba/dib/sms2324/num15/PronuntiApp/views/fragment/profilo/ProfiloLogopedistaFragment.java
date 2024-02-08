package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profilo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;


public class ProfiloLogopedistaFragment extends AbstractProfileWithImageFragment {
    private TextInputEditText textInputEditTextTelefono;
    private TextInputEditText textInputEditTextIndirizzo;

    public ProfiloLogopedistaFragment() {}

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

        textInputEditTextTelefono = view.findViewById(R.id.textInputEditTextTelefonoProfiloLogopedista);
        textInputEditTextIndirizzo = view.findViewById(R.id.textInputEditTextIndirizzoProfiloLogopedista);

        setData();

        return view;
    }

    public void setData(){

        Logopedista logopedista = null; /*profileLogopedistaViewModel.getLogopedista();*/ //TODO deve prendere il logopedista dal viewmodel

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
    public void modificaProfilo(){
        textInputEditTextNome.setEnabled(true);
        textInputEditTextCognome.setEnabled(true);
        textInputEditTextEmail.setEnabled(true);
        textInputEditTextTelefono.setEnabled(true);
        textInputEditTextIndirizzo.setEnabled(true);

        buttonModificaProfilo.setText(getString(R.string.confirm_modify_profile));
        buttonModificaProfilo.setOnClickListener(v->confermaModificaProfilo());

        imageViewProfile.setOnClickListener(v->pickImage());

        imageViewEditProfile.setOnClickListener(v->pickImage());
        imageViewEditProfile.setVisibility(View.VISIBLE);

        //focus automatico per far capire che si può modificare
        textInputEditTextNome.requestFocus();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("Profilo");
        }
    }



}