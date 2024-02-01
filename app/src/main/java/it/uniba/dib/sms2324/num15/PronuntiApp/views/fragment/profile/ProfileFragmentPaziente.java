package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class ProfileFragmentPaziente extends AsbtractProfileFragment{
    private TextInputEditText textInputEditTextDataNascita;
    private Spinner spinnerSesso;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile_paziente, container, false);
        textViewUsernameProfilo = view.findViewById(R.id.textViewUsernameProfiloPaziente);
        textInputEditTextNome = view.findViewById(R.id.textInputEditTextNomeProfiloPaziente);
        textInputEditTextCognome = view.findViewById(R.id.textInputEditTextCognomeProfiloPaziente);
        imageViewProfile = view.findViewById(R.id.imageViewProfile);
        imageViewEditProfile = view.findViewById(R.id.imageViewEditProfile);
        buttonModificaProfilo= view.findViewById(R.id.buttonModificaProfiloPaziente);
        setPickMedia();

        textInputEditTextDataNascita = view.findViewById(R.id.textInputEditTextDataNascitaProfiloPaziente);
        spinnerSesso = view.findViewById(R.id.spinnerSessoProfiloPaziente);

        setData();
        return view;
    }

    public void setData(){
        textInputEditTextNome.setText("Nome");
        textInputEditTextNome.setEnabled(false);
        textInputEditTextCognome.setText("Cognome");
        textInputEditTextCognome.setEnabled(false);
        textViewUsernameProfilo.setText("@username");
        textInputEditTextDataNascita.setText("12/03/1999");
        textInputEditTextDataNascita.setEnabled(false);
        spinnerSesso.setSelection(0);
        spinnerSesso.setEnabled(false);
    }

    @Override
    public void modificaProfilo(){
        textInputEditTextNome.setEnabled(true);
        textInputEditTextCognome.setEnabled(true);
        textInputEditTextDataNascita.setEnabled(true);
        spinnerSesso.setEnabled(true);

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
