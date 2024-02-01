package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.signInUp.LoginFragment;

public class FirstActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registrazione);

        if (savedInstanceState == null) {
            // Crea un'istanza del Fragment
            LoginFragment loginFragment = new LoginFragment();

            // Ottieni il FragmentManager e inizia una transazione
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Aggiungi il Fragment al FrameLayout
            fragmentTransaction.add(R.id.loginRegistrazioneFrameLayout, loginFragment);
            fragmentTransaction.commit();
        }
    }
}
