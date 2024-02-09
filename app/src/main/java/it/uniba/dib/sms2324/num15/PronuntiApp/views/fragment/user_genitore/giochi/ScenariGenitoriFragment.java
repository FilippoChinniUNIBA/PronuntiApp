package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.giochi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class ScenariGenitoriFragment extends Fragment{

    public ScenariGenitoriFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenari_genitore, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("Giochi del giorno");
        }
    }

}
