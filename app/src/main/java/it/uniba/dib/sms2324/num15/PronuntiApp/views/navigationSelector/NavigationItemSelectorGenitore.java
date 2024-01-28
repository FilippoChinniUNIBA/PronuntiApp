package it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AppuntamentiGenitoreFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ClassificaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.MonitoraggioFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ProfileFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ScenariGenitoriFragment;

public class NavigationItemSelectorGenitore implements NavigationItemSelector{
    private final FragmentManager fragmentManager;
    private final int fragmentContainerId;
    private final MonitoraggioFragment monitoraggioFragment = new MonitoraggioFragment();
    private final ScenariGenitoriFragment scenariGenitoriFragment = new ScenariGenitoriFragment();
    private final ClassificaFragment classificaFragment = new ClassificaFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();
    private final AppuntamentiGenitoreFragment appuntamentiGenitoreFragment = new AppuntamentiGenitoreFragment();

    public NavigationItemSelectorGenitore(FragmentManager fragmentManager, @IdRes int fragmentContainerId) {
        this.fragmentManager = fragmentManager;
        this.fragmentContainerId = fragmentContainerId;
    }

    public boolean selectItem(@IdRes int itemId) {
        Fragment fragment = null;
        if (itemId == R.id.monitoraggio_bambino)
            fragment = monitoraggioFragment;
        else if (itemId == R.id.scenari_genitori)
            fragment = scenariGenitoriFragment;
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
