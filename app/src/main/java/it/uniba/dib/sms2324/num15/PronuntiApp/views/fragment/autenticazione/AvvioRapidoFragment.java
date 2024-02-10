package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.autenticazione;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE.TEST_Activity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.GenitoreActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.LogopedistaActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.PazienteActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class AvvioRapidoFragment extends AbstractFragmentWithNavigation {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avvio_rapido, container, false);

        view.findViewById(R.id.button_genitore).setOnClickListener(v ->startActivity(new Intent(getActivity(), GenitoreActivity.class)));
        view.findViewById(R.id.button_paziente).setOnClickListener(v ->startActivity(new Intent(getActivity(), PazienteActivity.class)));
        view.findViewById(R.id.button_logopedista).setOnClickListener(v ->startActivity(new Intent(getActivity(), LogopedistaActivity.class)));

        view.findViewById(R.id.testPortaleTestButton).setOnClickListener(v -> startActivity(new Intent(getActivity(), TEST_Activity.class)));

        return view;
    }
}
