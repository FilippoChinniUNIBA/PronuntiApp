package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.pazienti.PazientiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigation_selector.NavigationNavBarSelectorLogopedista;

public class LogopedistaActivity extends AbstractAppActivity {
    private LogopedistaViewModel mLogopedistaViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logopedista);

        this.mLogopedistaViewModel = new ViewModelProvider(this).get(LogopedistaViewModel.class);
        mLogopedistaViewModel.setLogopedista((Logopedista) getIntent().getSerializableExtra("profilo"));

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        setBottomNavBar(R.menu.bottom_navbar_logopedista, new NavigationNavBarSelectorLogopedista(getSupportFragmentManager(), R.id.frameLayoutLogopedista, bottomNavigationView));
        setFirstFragment(R.id.frameLayoutLogopedista, new PazientiFragment());
    }

    @Override
    public void onStart() {
        super.onStart();
        //Log.d("LogopedistaActivity", mLogopedistaViewModel.getLogopedista().toString());
    }



}

