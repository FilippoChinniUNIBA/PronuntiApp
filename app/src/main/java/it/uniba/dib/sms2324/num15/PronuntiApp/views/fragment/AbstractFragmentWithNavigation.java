package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public abstract class AbstractFragmentWithNavigation extends Fragment {

    protected void replaceFragment(int idFragmentContainer, Fragment fragment, String tag) {
        if (getActivity() != null) {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(idFragmentContainer, fragment);
            fragmentTransaction.addToBackStack(tag);
            Log.d("replaceFragment", "in backstack: " + tag);
            fragmentTransaction.commit();
        }
        Log.d("navigateTo", "navigateTo: " + idFragmentContainer);
    }

    protected void takeBackFragmentFromBackStack(int idFragmentContainer, String tagName) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tagName);

        if (fragment != null) {
            if (fragmentManager.getBackStackEntryCount() > 0) {
                FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
                String foundFragmentTag = backStackEntry.getName();
                if (tagName.equals(foundFragmentTag)) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(idFragmentContainer, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    Log.e("Fragment", "Found fragment doesn't match expected tag");
                }
            } else {
                Log.e("Fragment", "BackStack is empty");
            }
        } else {
            Log.e("Fragment", "Fragment not found in backstack");
        }

    }


}
