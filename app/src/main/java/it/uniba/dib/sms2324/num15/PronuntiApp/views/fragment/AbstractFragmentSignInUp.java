package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public abstract class AbstractFragmentSignInUp extends Fragment {

    protected void navigateTo(int idFragment, Fragment fragment) {
        if (getActivity() != null) {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(idFragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            }
    }


}
