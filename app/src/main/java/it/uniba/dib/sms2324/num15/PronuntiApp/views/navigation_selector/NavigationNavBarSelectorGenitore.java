package it.uniba.dib.sms2324.num15.PronuntiApp.views.navigation_selector;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.AppuntamentiGenitoreFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.MonitoraggioFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profilo.ProfiloGenitoreFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.giochi.ScenariGenitoriFragment;

public class NavigationNavBarSelectorGenitore extends AbstractNavigationSelector implements NavigationNavBarItemSelector {
    private final MonitoraggioFragment monitoraggioFragment = new MonitoraggioFragment();
    private final ScenariGenitoriFragment scenariGenitoriFragment = new ScenariGenitoriFragment();
    //private final ClassificaFragment classificaFragment = new ClassificaFragment();
    private final ProfiloGenitoreFragment profiloGenitoreFragment = new ProfiloGenitoreFragment();
    private final AppuntamentiGenitoreFragment appuntamentiGenitoreFragment = new AppuntamentiGenitoreFragment();

    public NavigationNavBarSelectorGenitore(FragmentManager fragmentManager, @IdRes int fragmentContainerId, BottomNavigationView bottomNavigationView) {
        super( fragmentManager, fragmentContainerId, bottomNavigationView);

        fragmentManager.addOnBackStackChangedListener(() -> {
            Fragment currentFragment = fragmentManager.findFragmentById(fragmentContainerId);
            if (currentFragment instanceof MonitoraggioFragment) {
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
            } else if (currentFragment instanceof ScenariGenitoriFragment) {
                bottomNavigationView.getMenu().getItem(1).setChecked(true);
            } else if (currentFragment instanceof AppuntamentiGenitoreFragment) {
                bottomNavigationView.getMenu().getItem(2).setChecked(true);
            } else if (currentFragment instanceof ProfiloGenitoreFragment) {
                bottomNavigationView.getMenu().getItem(3).setChecked(true);
            } /*else if (currentFragment instanceof ClassificaFragment) {
                bottomNavigationView.getMenu().getItem(4).setChecked(true);
            }*/
        });
    }
}
