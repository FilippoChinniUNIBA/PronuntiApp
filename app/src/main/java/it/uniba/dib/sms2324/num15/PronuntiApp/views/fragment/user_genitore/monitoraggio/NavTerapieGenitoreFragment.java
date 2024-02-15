package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.monitoraggio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class NavTerapieGenitoreFragment extends AbstractFragmentWithNavigation {

    private ImageButton imageButtonProssimaTerapia;
    private ImageButton imageButtonTerapiaPrecedente;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_nav_terapie, container, false);
        imageButtonProssimaTerapia = view.findViewById(R.id.buttonAvantiTerapia);
        imageButtonTerapiaPrecedente = view.findViewById(R.id.buttonIndietroTerapia);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageButtonProssimaTerapia.setOnClickListener(v -> {
            //TODO implementare funzionalità per passare alla terapia successiva
            getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMonitoraggio, new MonitoraggioGenitoreFragment()).commit();
        });

        imageButtonTerapiaPrecedente.setOnClickListener(v -> {
            //TODO implementare funzionalità per passare alla terapia precedente
            getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMonitoraggio, new MonitoraggioGenitoreFragment()).commit();
        });

    }

}
