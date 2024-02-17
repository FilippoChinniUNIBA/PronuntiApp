package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import java.util.Comparator;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.monitoraggio.MonitoraggioGenitoreFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti.monitoraggio.MonitoraggioLogopedistaFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti.monitoraggio.TerapieLogopedistaFragment;

public class SchedaPazienteFragment extends AbstractFragmentWithNavigation {

    private Button addTerapiaButton;
    private String idPaziente;
    private String nomePaziente;
    private String cognomePaziente;
    private LogopedistaViewModel mLogopedistaViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_scheda_paziente_logopedista, container, false);

        savedInstanceState = getArguments();

        if(savedInstanceState != null){
            idPaziente = savedInstanceState.getString("idPaziente");
            nomePaziente = savedInstanceState.getString("nomePaziente");
            cognomePaziente = savedInstanceState.getString("cognomePaziente");
        }

        this.mLogopedistaViewModel = new ViewModelProvider(requireActivity()).get(LogopedistaViewModel.class);

        setToolBar(view, nomePaziente+" "+cognomePaziente);

        addTerapiaButton = view.findViewById(R.id.buttonAddTerapia);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = new Bundle();

        int indicePaziente = 0;

        int indiceTerapia = -1;

        Logopedista logopedista = mLogopedistaViewModel.getLogopedistaLiveData().getValue();
        for (Paziente paziente: logopedista.getPazienti()) {
            if (paziente.getIdProfilo().equals(idPaziente)) {
                if (paziente.getTerapie() != null) {
                    indiceTerapia = paziente.getTerapie().size() - 1;
                    if (indiceTerapia != -1) {
                        bundle.putString("idPaziente", idPaziente);
                        bundle.putInt("indicePaziente", indicePaziente);
                        indicePaziente++;
                        bundle.putInt("indiceTerapia", indiceTerapia);
                        break;
                    }
                }
            }
                indicePaziente++;
        }

        if(indiceTerapia != -1) {
            TerapieLogopedistaFragment terapieLogopedistaFragment = new TerapieLogopedistaFragment();
            terapieLogopedistaFragment.setArguments(bundle);

            getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewTerapie, terapieLogopedistaFragment).commit();
        }

        addTerapiaButton.setOnClickListener(v -> {
            //TODO passare tramite bundle l'dindice del paziente e della terapia (ora sta passando null)
            navigateTo(R.id.action_schedaPazienteFragment_to_creazioneTerapiaFragment, bundle);
        });

    }
}
