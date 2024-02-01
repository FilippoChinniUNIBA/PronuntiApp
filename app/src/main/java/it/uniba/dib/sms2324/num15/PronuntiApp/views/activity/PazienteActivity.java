package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE.TestMenuTestFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.PazientiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ScenarioFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.navBarApp.NavigationNavBarItemSelector;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.navBarApp.NavigationNavBarSelectorLogopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.navBarApp.NavigationNavBarSelectorPaziente;

public class PazienteActivity extends AbstractAppActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBottomNavBar(R.menu.bottom_navbar_paziente, new NavigationNavBarSelectorPaziente(getSupportFragmentManager(), R.id.appFrameLayout, bottomNavigationView));
        setFirstFragment(R.id.appFrameLayout, new ScenarioFragment());
    }
}
