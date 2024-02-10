package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.autenticazione.LoginFragment;

public class FirstActivity extends AbstractAppActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_activity);
        navcontroller = Navigation.findNavController(this, R.id.fragmentContainerAutenticazione);

        setOnBackPressedCallback(R.id.loginFragment);

    }
}
