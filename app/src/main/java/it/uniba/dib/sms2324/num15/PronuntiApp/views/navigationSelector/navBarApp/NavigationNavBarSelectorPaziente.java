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
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ProfileFragment;

public class NavigationNavBarSelectorPaziente extends AbstractNavigationSelector implements NavigationNavBarItemSelector {
    private final ScenarioFragment scenarioFragment = new ScenarioFragment();
    private final PersonaggiFragment personaggiFragment = new PersonaggiFragment();
    private final ClassificaFragment classificaFragment = new ClassificaFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();
    private final AppuntamentiGenitoreFragment appuntamentiGenitoreFragment = new AppuntamentiGenitoreFragment();

    public NavigationNavBarSelectorPaziente(FragmentManager fragmentManager, @IdRes int fragmentContainerId, BottomNavigationView bottomNavigationView) {
        super( fragmentManager, fragmentContainerId, bottomNavigationView);
    }

    public boolean selectItem(@IdRes int itemId) {
        Fragment fragment = null;
        if (itemId == R.id.scenario)
            fragment = scenarioFragment;
        else if (itemId == R.id.personaggi)
            fragment = personaggiFragment;
        else if (itemId == R.id.classificaLogopedista)
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

