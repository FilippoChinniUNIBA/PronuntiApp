package it.uniba.dib.sms2324.num15.PronuntiApp.views;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AppuntamentiGenitoreFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ClassificaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ScenarioFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.PersonaggiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ProfileFragment;

public class NavigationItemSelectorPaziente implements NavigationItemSelector{
    private final FragmentManager fragmentManager;
    private final int fragmentContainerId;
    private final ScenarioFragment scenarioFragment = new ScenarioFragment();
    private final PersonaggiFragment personaggiFragment = new PersonaggiFragment();
    private final ClassificaFragment classificaFragment = new ClassificaFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();
    private final AppuntamentiGenitoreFragment appuntamentiGenitoreFragment = new AppuntamentiGenitoreFragment();

    public NavigationItemSelectorPaziente(FragmentManager fragmentManager, @IdRes int fragmentContainerId) {
        this.fragmentManager = fragmentManager;
        this.fragmentContainerId = fragmentContainerId;
    }

    public boolean selectItem(@IdRes int itemId) {
        Fragment fragment = null;
        if (itemId == R.id.scenario)
            fragment = scenarioFragment;
        else if (itemId == R.id.personaggi)
            fragment = personaggiFragment;
        else if (itemId == R.id.results)
            fragment = classificaFragment;
        else if (itemId == R.id.calendarPaziente)
            fragment = appuntamentiGenitoreFragment;
        else if (itemId == R.id.profile)
            fragment = profileFragment;

        if (fragment != null) {
            fragmentManager.beginTransaction().replace(fragmentContainerId, fragment).commit();
            return true;
        }
        return false;
    }
}

