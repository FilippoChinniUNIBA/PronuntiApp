package it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NavigationLogin extends AbstractNavigationSelector {

    public NavigationLogin(FragmentManager fragmentManager, int fragmentContainerId) {
        super(fragmentManager, fragmentContainerId);
    }

    public static void replaceFragment(FragmentManager fragmentManager, int containerId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
