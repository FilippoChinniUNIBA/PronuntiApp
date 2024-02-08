package it.uniba.dib.sms2324.num15.PronuntiApp.views.navigation_selector;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.AppuntamentiLogopedistaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ClassificaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.pazienti.PazientiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profilo.ProfiloLogopedistaFragment;

public class NavigationNavBarSelectorLogopedista extends AbstractNavigationSelector implements NavigationNavBarItemSelector {
    private final PazientiFragment pazientiFragment = new PazientiFragment();
    private final ClassificaFragment classificaFragment = new ClassificaFragment();
    private final ProfiloLogopedistaFragment profiloLogopedistaFragment = new ProfiloLogopedistaFragment();
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
                    } else if (currentFragment instanceof ProfiloLogopedistaFragment) {
                        bottomNavigationView.getMenu().getItem(3).setChecked(true);
                    }
            });
        }

}
