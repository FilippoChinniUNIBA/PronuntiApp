package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationBarView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.pazienti.PazientiFragment;

public class LogopedistaActivity extends AbstractAppActivity {
    private LogopedistaViewModel mLogopedistaViewModel;
    private NavController navcontroller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logopedista);
        navcontroller = Navigation.findNavController(this, R.id.fragmentContainerLogopedista);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                navcontroller.getGraph()).setFallbackOnNavigateUpListener(() -> navcontroller.navigateUp()).build();

        NavigationUI.setupWithNavController(bottomNavigationView, navcontroller);
        NavigationUI.setupActionBarWithNavController(this, navcontroller, appBarConfiguration);

        this.mLogopedistaViewModel = new ViewModelProvider(this).get(LogopedistaViewModel.class);
        mLogopedistaViewModel.setLogopedista((Logopedista) getIntent().getSerializableExtra("profilo"));
        navcontroller = Navigation.findNavController(this, R.id.fragmentContainerLogopedista);

        setOnBackPressedCallback(R.id.pazientiFragment);


        //setBottomNavBar(R.menu.bottom_navbar_logopedista, new NavigationNavBarSelectorLogopedista(getSupportFragmentManager(), R.id.frameLayoutLogopedista, bottomNavigationView));
        //setFirstFragment(R.id.frameLayoutLogopedista, new PazientiFragment());
    }

}

