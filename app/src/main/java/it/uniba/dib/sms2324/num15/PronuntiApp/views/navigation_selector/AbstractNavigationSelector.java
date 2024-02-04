package it.uniba.dib.sms2324.num15.PronuntiApp.views.navigation_selector;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class AbstractNavigationSelector {

    protected final FragmentManager fragmentManager;
    protected final int fragmentContainerId;

    protected final BottomNavigationView bottomNavigationView;

    public AbstractNavigationSelector(FragmentManager fragmentManager, @IdRes int fragmentContainerId, BottomNavigationView bottomNavigationView) {
        this.fragmentManager = fragmentManager;
        this.fragmentContainerId = fragmentContainerId;
        this.bottomNavigationView = bottomNavigationView;
    }

    public static void replaceFragment(FragmentManager fragmentManager, int containerId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
