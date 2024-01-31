package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE.TestMenuTestFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.PazientiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ScenariGenitoriFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.navBarApp.NavigationNavBarSelectorGenitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.navBarApp.NavigationNavBarSelectorPaziente;

public class GenitoreActivity extends AbstractAppActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBottomNavBar(R.menu.bottom_navbar_genitore, new NavigationNavBarSelectorGenitore(getSupportFragmentManager(), R.id.appFrameLayout, bottomNavigationView));
        setFirstFragment(R.id.appFrameLayout, new ScenariGenitoriFragment());

        //BUTTONS FOR TEST
        View buttonToTest = findViewById(R.id.buttonTest);
        buttonToTest.setOnClickListener(v -> {
                    TestMenuTestFragment testMenuTestFragment = new TestMenuTestFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.appFrameLayout, testMenuTestFragment)
                            .addToBackStack(null)
                            .commit();
                }
        );

    }
}
