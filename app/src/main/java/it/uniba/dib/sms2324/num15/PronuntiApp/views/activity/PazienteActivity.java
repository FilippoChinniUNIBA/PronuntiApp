package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.ScenarioFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigation_selector.NavigationNavBarSelectorPaziente;

public class PazienteActivity extends AbstractAppActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paziente);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        setBottomNavBar(R.menu.bottom_navbar_paziente, new NavigationNavBarSelectorPaziente(getSupportFragmentManager(), R.id.frameLayoutPaziente, bottomNavigationView));
        setFirstFragment(R.id.frameLayoutPaziente, new ScenarioFragment());
    }
}
