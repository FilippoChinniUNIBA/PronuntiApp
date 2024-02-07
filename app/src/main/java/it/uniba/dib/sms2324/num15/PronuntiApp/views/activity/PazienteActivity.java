package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.ScenarioFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigation_selector.NavigationNavBarSelectorPaziente;

public class PazienteActivity extends AbstractAppActivity {
    private PazienteViewModel mPazienteViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paziente);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        this.mPazienteViewModel = new ViewModelProvider(this).get(PazienteViewModel.class);
        mPazienteViewModel.setPaziente((Paziente) getIntent().getSerializableExtra("profilo"));

        setBottomNavBar(R.menu.bottom_navbar_paziente, new NavigationNavBarSelectorPaziente(getSupportFragmentManager(), R.id.frameLayoutPaziente, bottomNavigationView));
        setFirstFragment(R.id.frameLayoutPaziente, new ScenarioFragment());
    }
}
