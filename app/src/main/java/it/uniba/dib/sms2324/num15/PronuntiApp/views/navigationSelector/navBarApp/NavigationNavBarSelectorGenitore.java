package it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.navBarApp;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AppuntamentiGenitoreFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ClassificaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.MonitoraggioFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ProfileFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ScenariGenitoriFragment;

public class NavigationNavBarSelectorGenitore extends AbstractNavigationSelector implements NavigationNavBarItemSelector {
    private final MonitoraggioFragment monitoraggioFragment = new MonitoraggioFragment();
    private final ScenariGenitoriFragment scenariGenitoriFragment = new ScenariGenitoriFragment();
    private final ClassificaFragment classificaFragment = new ClassificaFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();
    private final AppuntamentiGenitoreFragment appuntamentiGenitoreFragment = new AppuntamentiGenitoreFragment();

    public NavigationNavBarSelectorGenitore(FragmentManager fragmentManager, @IdRes int fragmentContainerId, BottomNavigationView bottomNavigationView) {
        super( fragmentManager, fragmentContainerId, bottomNavigationView);
        fragmentManager.addOnBackStackChangedListener(() -> {
            Fragment currentFragment = fragmentManager.findFragmentById(fragmentContainerId);
            if (currentFragment instanceof MonitoraggioFragment) {
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
            } else if (currentFragment instanceof ScenariGenitoriFragment) {
                bottomNavigationView.getMenu().getItem(1).setChecked(true);
            } else if (currentFragment instanceof ClassificaFragment) {
                bottomNavigationView.getMenu().getItem(2).setChecked(true);
            } else if (currentFragment instanceof AppuntamentiGenitoreFragment) {
                bottomNavigationView.getMenu().getItem(3).setChecked(true);
            } else if (currentFragment instanceof ProfileFragment) {
                bottomNavigationView.getMenu().getItem(4).setChecked(true);
            }
        });
    }

    public boolean selectItem(@IdRes int itemId) {
        Fragment fragment = null;
        if (itemId == R.id.monitoraggio_bambino)
            fragment = monitoraggioFragment;
        else if (itemId == R.id.scenari_genitori)
            fragment = scenariGenitoriFragment;
        else if (itemId == R.id.classificaGenitore)
            fragment = classificaFragment;
        else if (itemId == R.id.calendarPaziente)
            fragment = appuntamentiGenitoreFragment;
        else if (itemId == R.id.profiloLogopedista)
            fragment = profileFragment;

        if (fragment != null) {
            replaceFragment(fragmentManager,fragmentContainerId,fragment);
            return true;
        }
        return false;
    }
}