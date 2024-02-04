package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.PazientiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigation_selector.NavigationNavBarSelectorLogopedista;

public class LogopedistaActivity extends AbstractAppActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logopedista);
        setBottomNavBar(R.menu.bottom_navbar_logopedista, new NavigationNavBarSelectorLogopedista(getSupportFragmentManager(), R.id.frameLayoutLogopedista, bottomNavigationView));
        setFirstFragment(R.id.frameLayoutLogopedista, new PazientiFragment());

    }

}

