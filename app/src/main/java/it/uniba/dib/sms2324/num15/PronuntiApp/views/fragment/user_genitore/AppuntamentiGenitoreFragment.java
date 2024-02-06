package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class AppuntamentiGenitoreFragment extends Fragment {

    public AppuntamentiGenitoreFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appuntamenti_genitore, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("Apppuntamenti");
        }
    }
}