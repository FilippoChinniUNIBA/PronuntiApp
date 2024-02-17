package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti.monitoraggio;

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

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioEseguibile;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.monitoraggio.NavigateTo;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.monitoraggio.ScenarioAdapter;


public class MonitoraggioLogopedistaFragment extends AbstractFragmentWithNavigation implements NavigateTo {
    private RecyclerView recyclerViewScenari;
    private List<ScenarioGioco> listaScenari;
    private String idTerapiaScelta;
    private Terapia terapiaScelta;
    private String idPaziente;
    private int indiceTerapia;
    private LogopedistaViewModel mLogopedistaViewModel;
    private int indicePaziente;
    private TextView textViewDataInizioTerapia;
    private TextView textViewDataFineTerapia;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitoraggio, container, false);

        savedInstanceState = getArguments();

        Log.d("MonitoraggioLogopedistaFragmetn","savedInstanceState "+savedInstanceState.toString());

        this.idPaziente = savedInstanceState.getString("idPaziente");
        this.indiceTerapia = savedInstanceState.getInt("indiceTerapia");
        this.indicePaziente = savedInstanceState.getInt("indicePaziente");

        recyclerViewScenari = view.findViewById(R.id.recyclerViewScenari);
        recyclerViewScenari.setLayoutManager(new LinearLayoutManager(getContext()));

        textViewDataInizioTerapia = view.findViewById(R.id.textViewDataInizioTerapia);
        textViewDataFineTerapia = view.findViewById(R.id.textViewDataFineTerapia);


        mLogopedistaViewModel = new ViewModelProvider(requireActivity()).get(LogopedistaViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       listaScenari = monitoraggioTerapie();
        setTextViewDataInizioTerapia();
        setTextViewDataFineTerapia();

        //TODO riga commentata perchè serve un controller nel adapter per modificare la data dello scenario;
        ScenarioAdapter adapter = new ScenarioAdapter(listaScenari, this, R.id.action_schedaPazienteFragment_to_risultatoEsercizioDenominazioneImmagineLogopedistaFragment
                ,
                R.id.action_schedaPazienteFragment_to_risultatoEsercizioCoppiaImmaginiLogopedistaFragment,
                R.id.action_schedaPazienteFragment_to_risultatoEsercizioSequenzaParoleLogopedistaFragment
                , null, indiceTerapia,idPaziente);

        recyclerViewScenari.setAdapter(adapter);
    }

    @Override
    public void navigateToId(int id, Bundle bundle){
        Log.d("MonitoraggioFragment", "navigateToEsercizio: ");
        setArguments(bundle);
        navigateTo(id, bundle);
    }

    /*private void monitoraggioTerapie(){

        Paziente paziente = mPazienteViewModel.getPazienteLiveData().getValue();

        for (Terapia terapia : paziente.getTerapie()) {
            if (terapia.getIdTerapia().equals(idTerapiaScelta)) {
                this.terapiaScelta = terapia;
            }
        }
    }*/

    private List<ScenarioGioco> monitoraggioTerapie(){

        List<ScenarioGioco> listaScenari = new ArrayList<>();

        for (Paziente paziente: mLogopedistaViewModel.getLogopedistaLiveData().getValue().getPazienti()) {
            if (paziente.getIdProfilo().equals(idPaziente)) {
                listaScenari.addAll(paziente.getTerapie().get(indiceTerapia).getScenariGioco());
                break;
            }
        }
        return listaScenari;
    }

    private void setTextViewDataInizioTerapia() {
        //qui utilizzo indice paziente anche se potrei utilizzare un foreach, chiedere chiarimenti
        if (mLogopedistaViewModel.getLogopedistaLiveData().getValue().getPazienti().get(indicePaziente).getTerapie() != null) {
            textViewDataInizioTerapia.setText(mLogopedistaViewModel.getLogopedistaLiveData().getValue().getPazienti().get(indicePaziente).getTerapie().get(indiceTerapia).getDataInizio().toString());
        }

    }

    private void setTextViewDataFineTerapia(){
        if (mLogopedistaViewModel.getLogopedistaLiveData().getValue().getPazienti().get(indicePaziente).getTerapie() != null) {
            textViewDataFineTerapia.setText(mLogopedistaViewModel.getLogopedistaLiveData().getValue().getPazienti().get(indicePaziente).getTerapie().get(indiceTerapia).getDataFine().toString());
        }
    }

}
