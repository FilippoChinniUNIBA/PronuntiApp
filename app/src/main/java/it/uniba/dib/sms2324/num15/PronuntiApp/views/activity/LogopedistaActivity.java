package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE.TestMenuTestFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.PazientiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.navBarApp.NavigationNavBarSelectorGenitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.navBarApp.NavigationNavBarSelectorLogopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.navBarApp.NavigationNavBarItemSelector;

public class LogopedistaActivity extends AbstractAppActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBottomNavBar(R.menu.bottom_navbar_genitore, new NavigationNavBarSelectorLogopedista(getSupportFragmentManager(), R.id.appFrameLayout, bottomNavigationView));
        setFirstFragment(R.id.appFrameLayout, new PazientiFragment());


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

