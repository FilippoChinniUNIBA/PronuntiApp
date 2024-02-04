package it.uniba.dib.sms2324.num15.PronuntiApp.views.navigation_selector;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AppuntamentiLogopedistaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ClassificaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.PazientiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profile.ProfileLogopedistaFragment;

public class NavigationNavBarSelectorLogopedista extends AbstractNavigationSelector implements NavigationNavBarItemSelector {
    private final PazientiFragment pazientiFragment = new PazientiFragment();
    private final ClassificaFragment classificaFragment = new ClassificaFragment();
    private final ProfileLogopedistaFragment profileLogopedistaFragment = new ProfileLogopedistaFragment();
    private final AppuntamentiLogopedistaFragment appuntamentiLogopedistaFragment = new AppuntamentiLogopedistaFragment();

    public NavigationNavBarSelectorLogopedista(FragmentManager fragmentManager, @IdRes int fragmentContainerId, BottomNavigationView bottomNavigationView) {
        super(fragmentManager, fragmentContainerId, bottomNavigationView);
            fragmentManager.addOnBackStackChangedListener(() -> {
                    Fragment currentFragment = fragmentManager.findFragmentById(fragmentContainerId);
                    if (currentFragment instanceof PazientiFragment) {
                        bottomNavigationView.getMenu().getItem(0).setChecked(true);
                    } else if (currentFragment instanceof ClassificaFragment) {
                        bottomNavigationView.getMenu().getItem(1).setChecked(true);
                    } else if (currentFragment instanceof AppuntamentiLogopedistaFragment) {
                        bottomNavigationView.getMenu().getItem(2).setChecked(true);
                    } else if (currentFragment instanceof ProfileLogopedistaFragment) {
                        bottomNavigationView.getMenu().getItem(3).setChecked(true);
                    }
            });
        }

    public boolean selectItem(@IdRes int itemId) {
        Fragment fragment = null;
        if (itemId == R.id.pazienti)
            fragment = pazientiFragment;
        else if (itemId == R.id.classificaGenitore)
            fragment = classificaFragment;
        else if (itemId == R.id.calendarLogopedista)
            fragment = appuntamentiLogopedistaFragment;
        else if (itemId == R.id.profiloLogopedista)
            fragment = profileLogopedistaFragment;

        if (fragment != null) {
            replaceFragment(fragmentManager,fragmentContainerId,fragment);
            return true;
        }
        return false;
    }


}
