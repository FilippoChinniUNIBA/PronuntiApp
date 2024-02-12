package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationBarView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;

public class LogopedistaActivity extends AbstractAppActivity {
    private LogopedistaViewModel mLogopedistaViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logopedista);

        //Setup Navigazione
        navcontroller = Navigation.findNavController(this, R.id.fragmentContainerLogopedista);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);

        NavigationUI.setupWithNavController(bottomNavigationView, navcontroller);
        setOnBackPressedCallback(R.id.pazientiFragment);

        //Setup Dati
        this.mLogopedistaViewModel = new ViewModelProvider(this).get(LogopedistaViewModel.class);
        this.mLogopedistaViewModel.setLogopedista((Logopedista) getIntent().getSerializableExtra("mLogopedista"));
        this.mLogopedistaViewModel.initMListaAppuntamenti();
        this.mLogopedistaViewModel.initMListaTemplateScenariGioco();
        this.mLogopedistaViewModel.initMListaTemplateEsercizi();
    }

}

