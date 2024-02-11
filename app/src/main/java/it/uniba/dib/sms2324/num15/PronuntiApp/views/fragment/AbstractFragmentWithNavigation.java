package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public abstract class AbstractFragmentWithNavigation extends Fragment {
    protected NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    public void navigateTo(int idAction) {
        if (getActivity() != null) {
            navController.navigate(idAction);
        }
    }

}
