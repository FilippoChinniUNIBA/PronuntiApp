package it.uniba.dib.sms2324.num15.PronuntiApp.views.monitoraggio;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;


public class MonitoraggioFragment extends AbstractFragmentWithNavigation implements NavigateTo {
    private RecyclerView recyclerViewScenari;
    private List<ScenarioGioco> listaScenari;
    private PazienteViewModel mPazienteViewModel;
    private String idTerapiaScelta;
    private Terapia terapiaScelta;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitoraggio, container, false);

        recyclerViewScenari = view.findViewById(R.id.recyclerViewScenari);
        recyclerViewScenari.setLayoutManager(new LinearLayoutManager(getContext()));

        mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        monitoraggioTerapie();
        listaScenari = new ArrayList<>();
        listaScenari.addAll(terapiaScelta.getScenariGioco());

        //TODO prendere scenari da viewModel
        for(int i=0; i<100; i++) {
            EsercizioCoppiaImmagini esercizioCoppiaImmagini = new EsercizioCoppiaImmagini(50,20,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/struzzo.mp3?alt=media&token=db982084-a8eb-48be-b5ae-a81ceb334ea4","https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/struzzo.jpg?alt=media&token=50abcf18-c404-48c1-bb3a-b37436898b8d","https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/macchina.jpg?alt=media&token=88ac2ae0-d403-41b0-adfd-2a1e106a3462");


            EsercizioDenominazioneImmagine esercizioDenominazioneImmagine=new EsercizioDenominazioneImmagine(
                    2500,
                    200,
                    "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/pinguino.jpg?alt=media&token=8792af2e-2a3d-4366-9d86-56746a42d2be",
                    "pinguino",
                    "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/help.mp3?alt=media&token=89cbfacf-2a02-46c5-986d-29b2d7e2fcdd");


            // Istanza di EsercizioSequenzaParole
            EsercizioSequenzaParole esercizioSequenzaParole = new EsercizioSequenzaParole(50, 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/cane_carota_gatto.mp3?alt=media&token=f5058c6f-9ea2-4ffc-8189-e1aef88e69cc", "cane", "carota", "gatto");


            List<EsercizioEseguibile> esercizi = new ArrayList<>();
            esercizi.add(esercizioCoppiaImmagini);
            esercizi.add(esercizioDenominazioneImmagine);
            esercizi.add(esercizioSequenzaParole);

            // Istanza di uno scenario
            ScenarioGioco scenario = new ScenarioGioco(
                    "idScenario123",
                    "sfondo.jpg",
                    "tappa1.jpg",
                    "tappa2.jpg",
                    "tappa3.jpg",
                    LocalDate.now().minusDays(i),
                    100, // Ricompensa finale
                    esercizi, // Lista di esercizi
                    "refTemplate123"
            );

            listaScenari.add(scenario);

        }

        // Creazione e settaggio dell'adapter
        ScenarioAdapter adapter = new ScenarioAdapter(listaScenari, this);
        recyclerViewScenari.setAdapter(adapter);
    }

    @Override
    public void navigateToEsercizio(int id, Bundle bundle){
        Log.d("MonitoraggioFragment", "navigateToEsercizio: ");
        setArguments(bundle);
        navigateTo(id, bundle);
    }

    private void monitoraggioTerapie(){

        Paziente paziente = mPazienteViewModel.getPazienteLiveData().getValue();

        for (Terapia terapia : paziente.getTerapie()) {
            if (terapia.getIdTerapia().equals(idTerapiaScelta)) {
                this.terapiaScelta = terapia;
            }
        }
    }



}
