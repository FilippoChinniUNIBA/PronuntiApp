package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;

public class GenitoreActivity extends AbstractAppActivity {
    private GenitoreViewModel mGenitoreViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.backgroundWhite));

        setContentView(R.layout.activity_genitore);

        //Setup Navigazione
        navcontroller = Navigation.findNavController(this, R.id.fragmentContainerGenitore);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);

        NavigationUI.setupWithNavController(bottomNavigationView, navcontroller);
        setOnBackPressedCallback(R.id.terapieFragment);

        //Setup Dati
        this.mGenitoreViewModel = new ViewModelProvider(this).get(GenitoreViewModel.class);
        this.mGenitoreViewModel.setGenitore((Genitore) getIntent().getSerializableExtra("mGenitore"));
        this.mGenitoreViewModel.setPaziente((Paziente) getIntent().getSerializableExtra("mPaziente"));
        this.mGenitoreViewModel.setAppuntamenti((List<Appuntamento>) getIntent().getSerializableExtra("mAppuntamenti"));
    }

}
