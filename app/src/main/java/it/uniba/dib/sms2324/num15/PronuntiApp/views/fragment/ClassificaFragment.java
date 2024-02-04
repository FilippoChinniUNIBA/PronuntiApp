package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialogpopup.ConnessioneErroreDialog;

public class ClassificaFragment extends Fragment {

    public ClassificaFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classifica, container, false);
        ConnessioneErroreDialog connessioneErroreDialog = new ConnessioneErroreDialog(getContext());
        view.findViewById(R.id.buttonClassifica).setOnClickListener(v-> connessioneErroreDialog.show());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("Classifica");
        }
    }
}