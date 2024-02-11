package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.autenticazione_viewmodel.LoginViewModel;

public class AutenticazioneActivity extends AbstractAppActivity {
    private LoginViewModel mLoginViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticazione);

        this.mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);


        navcontroller = Navigation.findNavController(this, R.id.fragmentContainerAutenticazione);
        setOnBackPressedCallback(R.id.loginFragment);

    }
}
