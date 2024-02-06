package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public abstract class AbstractFragmentWithNavigation extends Fragment {

    protected void navigateTo(int idFragment, Fragment fragment) {
        if (getActivity() != null) {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(idFragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        Log.d("navigateTo", "navigateTo: " + idFragment);

    }


}
