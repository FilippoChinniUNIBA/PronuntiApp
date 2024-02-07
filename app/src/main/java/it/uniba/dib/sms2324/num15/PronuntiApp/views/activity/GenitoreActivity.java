package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.giochi.ScenariGenitoriFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigation_selector.NavigationNavBarSelectorGenitore;

public class GenitoreActivity extends AbstractAppActivity {
    private GenitoreViewModel mGenitoreViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genitore);

        this.mGenitoreViewModel = new ViewModelProvider(this).get(GenitoreViewModel.class);
        mGenitoreViewModel.setGenitore((Genitore) getIntent().getSerializableExtra("profilo"));

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        setBottomNavBar(R.menu.bottom_navbar_genitore, new NavigationNavBarSelectorGenitore(getSupportFragmentManager(), R.id.frameLayoutGenitore, bottomNavigationView));
        setFirstFragment(R.id.frameLayoutGenitore, new ScenariGenitoriFragment());
    }
}
