package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.signInUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.GenitoreActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.LogopedistaActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.PazienteActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.signInUp.AbstractFragmentSignInUp;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.signInUp.LoginFragment;

public class AvvioRapidoFragment extends AbstractFragmentSignInUp {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avvio_rapido, container, false);

        view.findViewById(R.id.button_genitore).setOnClickListener(v ->startActivity(new Intent(getActivity(), GenitoreActivity.class)));
        view.findViewById(R.id.button_paziente).setOnClickListener(v ->startActivity(new Intent(getActivity(), PazienteActivity.class)));
        view.findViewById(R.id.button_logopedista).setOnClickListener(v ->startActivity(new Intent(getActivity(), LogopedistaActivity.class)));


        return view;
    }
}
