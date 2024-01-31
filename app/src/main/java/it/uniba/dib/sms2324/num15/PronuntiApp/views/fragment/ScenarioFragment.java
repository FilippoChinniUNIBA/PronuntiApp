package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class ScenarioFragment extends Fragment {

    public ScenarioFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scenario, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("Giochi del bambino");
        }
    }
}
