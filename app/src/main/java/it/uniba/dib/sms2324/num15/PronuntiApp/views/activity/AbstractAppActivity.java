package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;


import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigation_selector.NavigationNavBarItemSelector;

public abstract class AbstractAppActivity extends AppCompatActivity {

    protected BottomNavigationView bottomNavigationView;


    protected void setBottomNavBar(int menuId, NavigationNavBarItemSelector navigationNavBarItemSelector) {
        bottomNavigationView.inflateMenu(menuId);
        bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setOnItemSelectedListener(item -> navigationNavBarItemSelector.selectItem(item.getItemId()));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Controlla se il tasto up è stato premuto
        if (item.getItemId() == android.R.id.home) {
            // Sostituisci il fragment corrente con il fragment precedente
            getSupportFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setFirstFragment(int fragmentContainerId, Fragment fragment) {
        //TODO nicola non so perché qui non setti il fragment in backstack come negli altri,
        // comunque oltre questo, non so se ha senso usare il getSupportFragmentManager() qui
        // forse sarebbe meglio passare il fragmentManager come attributo di classe.
        // poi in ogni caso serve anche qui una funzione tipo replaceFragment o navigateToFragment
        // per astrarre la transazione del fragment
        getSupportFragmentManager().beginTransaction()
                .replace(fragmentContainerId, fragment)
                .commit();
    }

    public BottomNavigationView getBottomNavBar() {
        return bottomNavigationView;
    }

}
