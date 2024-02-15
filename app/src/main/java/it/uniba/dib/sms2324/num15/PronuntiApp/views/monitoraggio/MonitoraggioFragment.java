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
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;


public class MonitoraggioFragment extends AbstractFragmentWithNavigation{
    private RecyclerView recyclerViewScenari;
    private List<ScenarioGioco> listaScenari;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitoraggio, container, false);

        recyclerViewScenari = view.findViewById(R.id.recyclerViewScenari);
        recyclerViewScenari.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listaScenari = new ArrayList<>();

        //TODO prendere scenari da viewModel
        for(int i=0; i<100; i++) {
            EsercizioCoppiaImmagini esercizioCoppiaImmagini = new EsercizioCoppiaImmagini(
                    "id_esercizio_coppia_immagini",
                    10,
                    5,
                    "audio_esercizio_coppia_immagini.mp3",
                    "immagine_corretta.jpg",
                    "immagine_errata.jpg",
                    "ref_id_template_esercizio_coppia_immagini",
                    null
            );

            EsercizioDenominazioneImmagine esercizioDenominazioneImmagine=new EsercizioDenominazioneImmagine(
                    2500,
                    200,
                    "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/pinguino.jpg?alt=media&token=8792af2e-2a3d-4366-9d86-56746a42d2be",
                    "pinguino",
                    "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/help.mp3?alt=media&token=89cbfacf-2a02-46c5-986d-29b2d7e2fcdd");


            // Istanza di EsercizioSequenzaParole
            EsercizioSequenzaParole esercizioSequenzaParole = new EsercizioSequenzaParole(
                    "id_esercizio_sequenza_parole",
                    10,
                    5,
                    "audio_esercizio_sequenza_parole.mp3",
                    "parola1",
                    "parola2",
                    "parola3",
                    "ref_id_template_esercizio_sequenza_parole",
                    null
            );

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
        ScenarioAdapter adapter = new ScenarioAdapter(listaScenari, getParentFragmentManager(), getContext());
        recyclerViewScenari.setAdapter(adapter);
    }

}
