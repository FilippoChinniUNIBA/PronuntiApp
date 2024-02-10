package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity; // For activities
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationBarView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.giochi.ScenariGenitoriFragment;

public class GenitoreActivity extends AbstractAppActivity {
    private GenitoreViewModel mGenitoreViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genitore);
        navcontroller = Navigation.findNavController(this, R.id.fragmentContainerGenitore);

        this.mGenitoreViewModel = new ViewModelProvider(this).get(GenitoreViewModel.class);
        mGenitoreViewModel.setGenitore((Genitore) getIntent().getSerializableExtra("profilo"));

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                navcontroller.getGraph()).setFallbackOnNavigateUpListener(() -> navcontroller.navigateUp()).build();

        NavigationUI.setupWithNavController(bottomNavigationView, navcontroller);
        NavigationUI.setupActionBarWithNavController(this, navcontroller, appBarConfiguration);

        setOnBackPressedCallback(R.id.monitoraggioFragment2);

        //setBottomNavBar(R.menu.bottom_navbar_genitore, new NavigationNavBarSelectorGenitore(getSupportFragmentManager(), R.id.fragmentContainerGenitore, bottomNavigationView));
        //setFirstFragment(R.id.fragmentContainerGenitore, new ScenariGenitoriFragment());
    }

}
