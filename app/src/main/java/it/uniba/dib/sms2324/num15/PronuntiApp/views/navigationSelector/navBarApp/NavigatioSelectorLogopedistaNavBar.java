package it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.navBarApp;

import android.util.Log;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AppuntamentiLogopedistaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ClassificaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.PazientiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ProfileFragment;

public class NavigatioSelectorLogopedistaNavBar extends AbstractNavigationSelector implements NavigationNavBarItemSelector {
    private final PazientiFragment pazientiFragment = new PazientiFragment();
    private final ClassificaFragment classificaFragment = new ClassificaFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();
    private final AppuntamentiLogopedistaFragment appuntamentiLogopedistaFragment = new AppuntamentiLogopedistaFragment();

    public NavigatioSelectorLogopedistaNavBar(FragmentManager fragmentManager, @IdRes int fragmentContainerId, BottomNavigationView bottomNavigationView) {
        super(fragmentManager, fragmentContainerId, bottomNavigationView);

            fragmentManager.addOnBackStackChangedListener(() -> {
                    Fragment currentFragment = fragmentManager.findFragmentById(fragmentContainerId);
                    if (currentFragment instanceof PazientiFragment) {
                        bottomNavigationView.setSelectedItemId(R.id.pazienti);
                        Log.d("NAVBAR", "PazientiFragment");
                    } else if (currentFragment instanceof ClassificaFragment) {
                        bottomNavigationView.setSelectedItemId(R.id.classificaLogopedista);
                    } else if (currentFragment instanceof AppuntamentiLogopedistaFragment) {
                        bottomNavigationView.setSelectedItemId(R.id.calendarLogopedista);
                    } else if (currentFragment instanceof ProfileFragment) {
                        bottomNavigationView.setSelectedItemId(R.id.profiloLogopedista);
                    }
            });
        }

    public boolean selectItem(@IdRes int itemId) {
        Fragment fragment = null;
        if (itemId == R.id.pazienti)
            fragment = pazientiFragment;
        else if (itemId == R.id.classificaLogopedista)
            fragment = classificaFragment;
        else if (itemId == R.id.calendarLogopedista)
            fragment = appuntamentiLogopedistaFragment;
        else if (itemId == R.id.profiloLogopedista)
            fragment = profileFragment;

        if (fragment != null) {
            replaceFragment(fragmentManager,fragmentContainerId,fragment);
            return true;
        }
        return false;
    }


}
