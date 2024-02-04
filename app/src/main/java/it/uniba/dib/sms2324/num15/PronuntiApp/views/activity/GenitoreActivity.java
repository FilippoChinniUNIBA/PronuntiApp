package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ScenariGenitoriFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigation_selector.NavigationNavBarSelectorGenitore;

public class GenitoreActivity extends AbstractAppActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBottomNavBar(R.menu.bottom_navbar_genitore, new NavigationNavBarSelectorGenitore(getSupportFragmentManager(), R.id.appFrameLayout, bottomNavigationView));
        setFirstFragment(R.id.appFrameLayout, new ScenariGenitoriFragment());

    }
}
