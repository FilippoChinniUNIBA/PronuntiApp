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
        navcontroller = Navigation.findNavController(this, R.id.fragmentContainerLogopedista);

        this.mLogopedistaViewModel = new ViewModelProvider(this).get(LogopedistaViewModel.class);
        mLogopedistaViewModel.setLogopedista((Logopedista) getIntent().getSerializableExtra("profilo"));

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);

        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                //navcontroller.getGraph()).setFallbackOnNavigateUpListener(() -> navcontroller.navigateUp()).build();

        NavigationUI.setupWithNavController(bottomNavigationView, navcontroller);
        //NavigationUI.setupActionBarWithNavController(this, navcontroller, appBarConfiguration);

        setOnBackPressedCallback(R.id.pazientiFragment);

        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //setBottomNavBar(R.menu.bottom_navbar_logopedista, new NavigationNavBarSelectorLogopedista(getSupportFragmentManager(), R.id.frameLayoutLogopedista, bottomNavigationView));
        //setFirstFragment(R.id.frameLayoutLogopedista, new PazientiFragment());
    }

}

