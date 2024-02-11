package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profilo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;

public class ProfiloGenitoreFragment extends AbstractProfileWithImageFragment{
    private TextInputEditText textInputEditTextTelefono;
    private ProfiloPazienteFragment profiloPazienteFragment;
    private GenitoreViewModel mGenitoreViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile_genitore, container, false);

        mGenitoreViewModel = new ViewModelProvider(requireActivity()).get(GenitoreViewModel.class);

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

        profiloPazienteFragment = new ProfiloPazienteFragment();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerBambino, profiloPazienteFragment);
        fragmentTransaction.commit(); //TODO perché qua non usi la navigazione normale?


        return view;
    }

    @Override
    void setData() {

        Genitore genitore = mGenitoreViewModel.getGenitoreLiveData().getValue();

        textInputEditTextNome.setText(genitore.getNome());
        textInputEditTextNome.setEnabled(false);
        textInputEditTextCognome.setText(genitore.getCognome());
        textInputEditTextCognome.setEnabled(false);
        textInputEditTextEmail.setText(genitore.getEmail());
        textInputEditTextEmail.setEnabled(false);
        textViewUsernameProfilo.setText(genitore.getUsername());
        textInputEditTextTelefono.setText(genitore.getTelefono());
        textInputEditTextTelefono.setEnabled(false);
    }

    @Override
    void confermaModificaProfilo() {
        super.confermaModificaProfilo();
        profiloPazienteFragment.confermaModificaProfilo();
        mGenitoreViewModel.getGenitoreLiveData().getValue().setTelefono(textInputEditTextTelefono.getText().toString());
        mGenitoreViewModel.aggiornaGenitoreRemoto();
    }

    @Override
    void modificaProfilo() {
        textInputEditTextTelefono.setEnabled(true);

        buttonModificaProfilo.setText(getString(R.string.confirm_modify_profile));
        buttonModificaProfilo.setOnClickListener(v->confermaModificaProfilo());

        imageViewProfile.setOnClickListener(v->pickImage());

        imageViewEditProfile.setOnClickListener(v->pickImage());
        imageViewEditProfile.setVisibility(View.VISIBLE);

        //rendi modificabile anche i bambini
        profiloPazienteFragment.modificaProfilo();
        setData();
        //focus automatico per far capire che si può modificare
        textInputEditTextNome.requestFocus();
    }

}
