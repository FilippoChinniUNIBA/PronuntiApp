package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.PazientiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationselector.NavigationNavBarSelectorLogopedista;

public class LogopedistaActivity extends AbstractAppActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBottomNavBar(R.menu.bottom_navbar_logopedista, new NavigationNavBarSelectorLogopedista(getSupportFragmentManager(), R.id.appFrameLayout, bottomNavigationView));
        setFirstFragment(R.id.appFrameLayout, new PazientiFragment());

    }

}

