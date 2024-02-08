package it.uniba.dib.sms2324.num15.PronuntiApp.views.navigation_selector;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ClassificaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.ScenarioFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.PersonaggiFragment;

public class NavigationNavBarSelectorPaziente extends AbstractNavigationSelector implements NavigationNavBarItemSelector {
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
}

