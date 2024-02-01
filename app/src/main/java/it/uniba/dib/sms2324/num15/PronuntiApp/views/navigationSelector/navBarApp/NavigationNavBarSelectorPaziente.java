package it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.navBarApp;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AppuntamentiGenitoreFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ClassificaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ScenarioFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.PersonaggiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profile.ProfileFragmentLogopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profile.ProfileFragmentPaziente;

public class NavigationNavBarSelectorPaziente extends AbstractNavigationSelector implements NavigationNavBarItemSelector {
    private final ScenarioFragment scenarioFragment = new ScenarioFragment();
    private final PersonaggiFragment personaggiFragment = new PersonaggiFragment();
    private final ClassificaFragment classificaFragment = new ClassificaFragment();
    private final ProfileFragmentPaziente profileFragmentPaziente = new ProfileFragmentPaziente();
    private final AppuntamentiGenitoreFragment appuntamentiGenitoreFragment = new AppuntamentiGenitoreFragment();

    public NavigationNavBarSelectorPaziente(FragmentManager fragmentManager, @IdRes int fragmentContainerId, BottomNavigationView bottomNavigationView) {
        super( fragmentManager, fragmentContainerId, bottomNavigationView);
        fragmentManager.addOnBackStackChangedListener(() -> {
            Fragment currentFragment = fragmentManager.findFragmentById(fragmentContainerId);
            if (currentFragment instanceof ScenarioFragment) {
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
            } else if (currentFragment instanceof PersonaggiFragment) {
                bottomNavigationView.getMenu().getItem(1).setChecked(true);
            } else if (currentFragment instanceof ClassificaFragment) {
                bottomNavigationView.getMenu().getItem(2).setChecked(true);
            } else if (currentFragment instanceof AppuntamentiGenitoreFragment) {
                bottomNavigationView.getMenu().getItem(3).setChecked(true);
            } else if (currentFragment instanceof ProfileFragmentLogopedista) {
                bottomNavigationView.getMenu().getItem(4).setChecked(true);
            }
        });
    }

    public boolean selectItem(@IdRes int itemId) {
        Fragment fragment = null;
        if (itemId == R.id.giochiPaziente)
            fragment = scenarioFragment;
        else if (itemId == R.id.personaggi)
            fragment = personaggiFragment;
        else if (itemId == R.id.classificaGenitore)
            fragment = classificaFragment;
        else if (itemId == R.id.calendarPaziente)
            fragment = appuntamentiGenitoreFragment;
        else if (itemId == R.id.profiloPaziente)
            fragment = profileFragmentPaziente;

        if (fragment != null) {
            replaceFragment(fragmentManager,fragmentContainerId,fragment);
            return true;
        }
        return false;
    }
}

