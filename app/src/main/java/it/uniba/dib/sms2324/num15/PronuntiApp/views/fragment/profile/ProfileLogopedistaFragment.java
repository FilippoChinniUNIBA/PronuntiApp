package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;


public class ProfileLogopedistaFragment extends AsbtractProfileFragment {

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextTelefono;
    private TextInputEditText textInputEditTextIndirizzo;
    public ProfileLogopedistaFragment(){
        // require a empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile_logopedista, container, false);
        textViewUsernameProfilo = view.findViewById(R.id.textViewUsernameProfiloLogopedista);
        textInputEditTextNome = view.findViewById(R.id.textInputEditTextNomeProfiloLogopedista);
        textInputEditTextCognome = view.findViewById(R.id.textInputEditTextCognomeProfiloLogopedista);
        imageViewProfile = view.findViewById(R.id.imageViewProfile);
        imageViewEditProfile = view.findViewById(R.id.imageViewEditProfile);
        buttonModificaProfilo= view.findViewById(R.id.buttonModificaProfiloLogopedista);
        setPickMedia();

        textInputEditTextEmail = view.findViewById(R.id.textInputEditTextEmailProfiloLogopedista);
        textInputEditTextTelefono = view.findViewById(R.id.textInputEditTextTelefonoProfiloLogopedista);
        textInputEditTextIndirizzo = view.findViewById(R.id.textInputEditTextIndirizzoProfiloLogopedista);

        setData();

        return view;
    }

    public void setData(){
        textInputEditTextNome.setText("Nome");
        textInputEditTextNome.setEnabled(false);
        textInputEditTextCognome.setText("Cognome");
        textInputEditTextCognome.setEnabled(false);
        textInputEditTextEmail.setText("Email");
        textInputEditTextEmail.setEnabled(false);
        textViewUsernameProfilo.setText("@username");
        textInputEditTextTelefono.setText("Telefono");
        textInputEditTextTelefono.setEnabled(false);
        textInputEditTextIndirizzo.setText("Indirizzo");
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

    //richiamre dao per salvare in db
    //@Ovvveride
    //public void save(){}


}