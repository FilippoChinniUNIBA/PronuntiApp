package it.uniba.dib.sms2324.num15.PronuntiApp.views.navigation_selector;

import android.util.Log;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AppuntamentiGenitoreFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ClassificaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ScenarioFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.PersonaggiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profile.ProfilePazienteFragment;

public class NavigationNavBarSelectorPaziente extends AbstractNavigationSelector implements NavigationNavBarItemSelector {
    private final ScenarioFragment scenarioFragment = new ScenarioFragment();
    private final PersonaggiFragment personaggiFragment = new PersonaggiFragment();
    private final ClassificaFragment classificaFragment = new ClassificaFragment();

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
            }
        });
    }

    public boolean selectItem(@IdRes int itemId) {
        Fragment fragment = null;
        if (itemId == R.id.giochiPaziente)
            fragment = scenarioFragment;
        else if (itemId == R.id.personaggi)
            fragment = personaggiFragment;
        else if (itemId == R.id.classificaPaziente)
            fragment = classificaFragment;
        if (fragment != null) {
            replaceFragment(fragmentManager,fragmentContainerId,fragment);
            return true;
        }
        return false;
    }
}

