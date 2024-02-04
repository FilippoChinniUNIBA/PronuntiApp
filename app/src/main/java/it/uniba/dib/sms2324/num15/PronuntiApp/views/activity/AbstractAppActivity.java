package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;


import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigation_selector.NavigationNavBarItemSelector;

public abstract class AbstractAppActivity extends AppCompatActivity {

    protected BottomNavigationView bottomNavigationView;
    protected NavigationNavBarItemSelector navigationNavBarItemSelector;



    protected void setBottomNavBar(int menuId, NavigationNavBarItemSelector navigationNavBarItemSelector) {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
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
    protected void setFirstFragment(int fragmentContainerId, androidx.fragment.app.Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(fragmentContainerId, fragment)
                .commit();
    }

    public BottomNavigationView getBottomNavBar() {
        return bottomNavigationView;
    }

}
