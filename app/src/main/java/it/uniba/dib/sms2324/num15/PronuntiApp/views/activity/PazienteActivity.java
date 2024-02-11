package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationBarView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.ScenarioFragment;

public class PazienteActivity extends AbstractAppActivity {
    private PazienteViewModel mPazienteViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_paziente);

        navcontroller = Navigation.findNavController(this, R.id.fragmentContainerPaziente);

        this.mPazienteViewModel = new ViewModelProvider(this).get(PazienteViewModel.class);
        mPazienteViewModel.setPaziente((Paziente) getIntent().getSerializableExtra("profilo"));

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                navcontroller.getGraph()).setFallbackOnNavigateUpListener(() -> navcontroller.navigateUp()).build();

        NavigationUI.setupWithNavController(bottomNavigationView, navcontroller);
        NavigationUI.setupActionBarWithNavController(this, navcontroller, appBarConfiguration);

        setOnBackPressedCallback(R.id.scenarioFragment);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        //setBottomNavBar(R.menu.bottom_navbar_paziente, new NavigationNavBarSelectorPaziente(getSupportFragmentManager(), R.id.fragmentContainerPaziente, bottomNavigationView));
        //setFirstFragment(R.id.fragmentContainerPaziente, new ScenarioFragment());
    }


}
