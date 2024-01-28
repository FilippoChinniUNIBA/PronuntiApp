package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class BottoniSchermateDiTestFragment extends Fragment {

    private Button buttonToTestApi;
    private Button buttonToTestLogin;

    public BottoniSchermateDiTestFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottoni_schermate_test, container, false);
        buttonToTestApi= view.findViewById(R.id.buttonTestApi);
        buttonToTestLogin= view.findViewById(R.id.buttonTestLoginRegistrazione);

        this.buttonToTestApi.setOnClickListener(v -> {
            TestApiFragment testApiFragment = new TestApiFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.appFrameLayout, testApiFragment)
                    .addToBackStack(null)
                    .commit();
        });

        this.buttonToTestLogin.setOnClickListener(v -> {
            LoginFragment testLoginFragment = new LoginFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.appFrameLayout, testLoginFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;

    }



}

