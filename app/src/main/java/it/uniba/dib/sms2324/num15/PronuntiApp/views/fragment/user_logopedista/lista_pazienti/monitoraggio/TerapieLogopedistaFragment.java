package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti.monitoraggio;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Comparator;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.monitoraggio.NavTerapieGenitoreFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti.monitoraggio.MonitoraggioLogopedistaFragment;

public class TerapieLogopedistaFragment extends AbstractFragmentWithNavigation {
    private String idPaziente;
    private LogopedistaViewModel mLogopedistaViewModel;
    private int indicePaziente;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         View view = inflater.inflate(R.layout.fragment_terapie, container, false);

         mLogopedistaViewModel = new ViewModelProvider(requireActivity()).get(LogopedistaViewModel.class);

         savedInstanceState = getArguments();
         if(savedInstanceState != null){
             this.idPaziente = savedInstanceState.getString("idPaziente");
             this.indicePaziente = savedInstanceState.getInt("indicePaziente");
         }

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Logopedista logopedista = mLogopedistaViewModel.getLogopedistaLiveData().getValue();
            for (Paziente paziente: logopedista.getPazienti()) {
                if (paziente.getIdProfilo().equals(idPaziente)){
                    paziente.getTerapie().sort(Comparator.comparing(Terapia::getDataInizio));
                    int indiceTerapia = paziente.getTerapie().size() - 1;
                    if(indiceTerapia != -1) {
                       cambiaFragmentMonitoraggioLogopedista(indiceTerapia);
                    }
                }
            }
    }

    public void cambiaFragmentMonitoraggioLogopedista(int indiceTerapia){
        if(indiceTerapia != -1) {
            Bundle bundle = new Bundle();

            bundle.putString("idPaziente", idPaziente);
            bundle.putInt("indiceTerapia", indiceTerapia);
            bundle.putInt("indicePaziente",indicePaziente);

            //qua ho fatto così perchè mi sarei comunque dovuto passare l'id del paziente se avessi vouluto trovare l'indice della terapia con il viewmodel del logopedista;
            NavTerapieLogopedistFragment navTerapieLogopedistFragment = new NavTerapieLogopedistFragment();
            navTerapieLogopedistFragment.setArguments(bundle);

            MonitoraggioLogopedistaFragment monitoraggioLogopedistaFragment = new MonitoraggioLogopedistaFragment();
            monitoraggioLogopedistaFragment.setArguments(bundle);

            getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMonitoraggio, monitoraggioLogopedistaFragment).replace(R.id.fragmentContainerViewNavTerapie,navTerapieLogopedistFragment).commit();
        }
    }

}
