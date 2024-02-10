package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.AbstractAppActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.ConnessioneErroreDialog;

public class ClassificaFragment extends AbstractFragmentWithNavigation {

    public ClassificaFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classifica, container, false);

        ConnessioneErroreDialog connessioneErroreDialog = new ConnessioneErroreDialog(getContext()); //TODO perché sta sta cosa qua??
        view.findViewById(R.id.buttonClassifica).setOnClickListener(v-> connessioneErroreDialog.show());

        return view;
    }

}