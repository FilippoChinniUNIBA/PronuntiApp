package it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AppuntamentiLogopedistaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ClassificaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.PazientiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ProfileFragment;

public class NavigatioItemSelectorLogopedista implements NavigationItemSelector{
    private final FragmentManager fragmentManager;
    private final int fragmentContainerId;
    private final PazientiFragment pazientiFragment = new PazientiFragment();
    private final ClassificaFragment classificaFragment = new ClassificaFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();
    private final AppuntamentiLogopedistaFragment appuntamentiLogopedistaFragment = new AppuntamentiLogopedistaFragment();

    public NavigatioItemSelectorLogopedista(FragmentManager fragmentManager, @IdRes int fragmentContainerId) {
        this.fragmentManager = fragmentManager;
        this.fragmentContainerId = fragmentContainerId;
    }

    public boolean selectItem(@IdRes int itemId) {
        Fragment fragment = null;
        if (itemId == R.id.pazienti)
            fragment = pazientiFragment;
        else if (itemId == R.id.results)
            fragment = classificaFragment;
        else if (itemId == R.id.calendarLogopedista)
            fragment = appuntamentiLogopedistaFragment;
        else if (itemId == R.id.profile)
            fragment = profileFragment;

        if (fragment != null) {
            fragmentManager.beginTransaction().replace(fragmentContainerId, fragment).commit();
            return true;
        }
        return false;
    }
}
