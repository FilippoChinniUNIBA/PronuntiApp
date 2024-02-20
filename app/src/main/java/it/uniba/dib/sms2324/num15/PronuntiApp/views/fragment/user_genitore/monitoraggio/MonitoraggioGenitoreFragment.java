package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.monitoraggio;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioEseguibile;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.monitoraggio.NavigateTo;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.monitoraggio.ScenarioAdapter;


public class MonitoraggioGenitoreFragment extends AbstractFragmentWithNavigation implements NavigateTo {
    private RecyclerView recyclerViewScenari;
    private List<ScenarioGioco> listaScenari;
    private int indiceTerapia;
    private GenitoreViewModel mGenitoreViewModel;
    private TextView textViewDataInizioTerapia;
    private TextView textViewDataFineTerapia;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_monitoraggio, container, false);

        savedInstanceState = getArguments();

        if (savedInstanceState != null && savedInstanceState.containsKey("indiceTerapiaScelta")) {
            indiceTerapia = savedInstanceState.getInt("indiceTerapiaScelta");
        } else {
            indiceTerapia = 0;
        }

        mGenitoreViewModel = new ViewModelProvider(requireActivity()).get(GenitoreViewModel.class);


        textViewDataInizioTerapia = view.findViewById(R.id.textViewDataInizioTerapia);
        textViewDataFineTerapia= view.findViewById(R.id.textViewDataFineTerapia);

        recyclerViewScenari = view.findViewById(R.id.recyclerViewScenari);
        recyclerViewScenari.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listaScenari = monitoraggioTerapie();
        setTextViewDataInizioTerapia();
        setTextViewDataFineTerapia();
        Log.d("Monitoraggio genitore",""+ R.id.action_terapieFragment_to_risultatoEsercizioDenominazioneImmagineFragment);


        ScenarioAdapter adapter = new ScenarioAdapter(listaScenari, this, R.id.action_terapieFragment_to_risultatoEsercizioDenominazioneImmagineFragment, R.id.action_terapieFragment_to_risultatoEsercizioCoppiaImmaginiFragment, R.id.action_terapieFragment_to_risultatoEsercizioSequenzaParoleFragment,mGenitoreViewModel.getModificaDataScenariController(),indiceTerapia,"",0);
        recyclerViewScenari.setAdapter(adapter);
    }

    @Override
    public void navigateToId(int id, Bundle bundle){
        navigateTo(id, bundle);
    }

    private List<ScenarioGioco> monitoraggioTerapie(){
        if(mGenitoreViewModel.getPazienteLiveData().getValue().getTerapie() != null) {
            Terapia terapiaScelta = mGenitoreViewModel.getPazienteLiveData().getValue().getTerapie().get(indiceTerapia);
            return new ArrayList<>(terapiaScelta.getScenariGioco());
        }
        return new ArrayList<>();
    }

    private void setTextViewDataInizioTerapia() {
        if (mGenitoreViewModel.getPazienteLiveData().getValue().getTerapie() != null) {
            textViewDataInizioTerapia.setText(mGenitoreViewModel.getPazienteLiveData().getValue().getTerapie().get(indiceTerapia).getDataInizio().toString());
        }

    }

    private void setTextViewDataFineTerapia(){
        if (mGenitoreViewModel.getPazienteLiveData().getValue().getTerapie() != null) {
            textViewDataFineTerapia.setText(mGenitoreViewModel.getPazienteLiveData().getValue().getTerapie().get(indiceTerapia).getDataFine().toString());
        }
    }
}
