package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class SchedaPazienteFragment extends AbstractFragmentWithNavigation {

    private Button addTerapiaButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_scheda_paziente_logopedista, container, false);

        //TODO recuperare il paziente rtamite l'indice passato (con il bundle) dalla selezione del paziente in PazientiAdapter

        //TODO passare il nome del paziente
        setToolBar(view, "Giovanni Vocestupida");

        addTerapiaButton = view.findViewById(R.id.buttonAddTerapia);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addTerapiaButton.setOnClickListener(v -> {
            //TODO passare tramite bundle l'dindice del paziente e della terapia (ora sta passando null)
            navigateTo(R.id.action_schedaPazienteFragment_to_creazioneTerapiaFragment, null);
        });

    }
}
