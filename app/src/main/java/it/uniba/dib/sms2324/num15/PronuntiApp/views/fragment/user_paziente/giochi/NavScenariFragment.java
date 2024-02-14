package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class NavScenariFragment extends AbstractFragmentWithNavigation {
    private TextView textViewDataScenario;
    private ImageButton buttonIndietroScenario;
    private ImageButton buttonAvantiScenario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         View view = inflater.inflate(R.layout.fragment_nav_scenari,container,false);
         textViewDataScenario = view.findViewById(R.id.textViewDataScenario);
         buttonIndietroScenario = view.findViewById(R.id.buttonIndietroScenario);
         buttonAvantiScenario = view.findViewById(R.id.buttonAvantiScenario);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
