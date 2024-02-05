package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class ProfileGenitoreFragment extends AbstractProfileWithImageFragment{

    private TextInputEditText textInputEditTextTelefono;
    private ProfilePazienteFragment profilePazienteFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile_genitore, container, false);
        textViewUsernameProfilo = view.findViewById(R.id.textViewUsernameProfiloGenitore);
        textInputEditTextNome = view.findViewById(R.id.textInputEditTextNomeProfiloGenitore);
        textInputEditTextCognome = view.findViewById(R.id.textInputEditTextCognomeProfiloGenitore);
        textInputEditTextEmail = view.findViewById(R.id.textInputEditTextEmailProfiloGenitore);
        imageViewProfile = view.findViewById(R.id.imageViewProfile);
        imageViewEditProfile = view.findViewById(R.id.imageViewEditProfile);
        buttonModificaProfilo= view.findViewById(R.id.buttonModificaProfiloGenitore);
        setPickMedia();

        textInputEditTextTelefono = view.findViewById(R.id.textInputEditTextTelefonoProfiloGenitore);

        setData();

        profilePazienteFragment = new ProfilePazienteFragment();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerBambino, profilePazienteFragment);
        fragmentTransaction.commit();


        return view;
    }

    @Override
    void setData() {
        textInputEditTextNome.setText("Nome");
        textInputEditTextNome.setEnabled(false);
        textInputEditTextCognome.setText("Cognome");
        textInputEditTextCognome.setEnabled(false);
        textInputEditTextEmail.setText("Email");
        textInputEditTextEmail.setEnabled(false);
        textViewUsernameProfilo.setText("@username");
        textInputEditTextTelefono.setText("Telefono");
        textInputEditTextTelefono.setEnabled(false);
    }

    @Override
    void confermaModificaProfilo() {
        super.confermaModificaProfilo();
        profilePazienteFragment.confermaModificaProfilo();
    }

    @Override
    void modificaProfilo() {
        textInputEditTextNome.setEnabled(true);
        textInputEditTextCognome.setEnabled(true);
        textInputEditTextEmail.setEnabled(true);
        textInputEditTextTelefono.setEnabled(true);

        buttonModificaProfilo.setText(getString(R.string.confirm_modify_profile));
        buttonModificaProfilo.setOnClickListener(v->confermaModificaProfilo());

        imageViewProfile.setOnClickListener(v->pickImage());

        imageViewEditProfile.setOnClickListener(v->pickImage());
        imageViewEditProfile.setVisibility(View.VISIBLE);

        //rendi modificabile anche i bambini
        profilePazienteFragment.modificaProfilo();

        //focus automatico per far capire che si può modificare
        textInputEditTextNome.requestFocus();
    }
}
