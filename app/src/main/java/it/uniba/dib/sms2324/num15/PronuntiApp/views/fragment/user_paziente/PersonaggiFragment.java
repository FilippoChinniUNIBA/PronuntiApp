package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class PersonaggiFragment extends AbstractFragmentWithNavigation {
    private RecyclerView recyclerViewPersonaggiSbloccati;
    private RecyclerView recyclerViewPersonaggiAcquistabili;
    private TextView textViewPersonaggiSbloccati;
    private TextView textViewPersonaggiAcquistabili;

    public PersonaggiFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= inflater.inflate(R.layout.fragment_personaggi, container, false);

        /*
        TypedArray typedArray = getResources().obtainTypedArray(R.array.personaggi_images);

        // Converti l'array in una lista di Integer
        ArrayList<Integer> imageList = new ArrayList<>();
        for (int i = 0; i < typedArray.length(); i++) {
            imageList.add(typedArray.getResourceId(i, 0));
        }

        typedArray.recycle();

        // Ottieni la lista di nomi dei personaggi
        String[] characterNamesArray = getResources().getStringArray(R.array.character_names);
        ArrayList<String> characterNames = new ArrayList<>(Arrays.asList(characterNamesArray));

        // Ottieni la lista dei costi dei personaggi
        String[] characterCostsArray = getResources().getStringArray(R.array.character_costs);
        ArrayList<String> characterCosts = new ArrayList<>(Arrays.asList(characterCostsArray));

        */

        // Inizializza le viste
        textViewPersonaggiSbloccati = view.findViewById(R.id.textViewPersonaggiSbloccati);
        textViewPersonaggiAcquistabili = view.findViewById(R.id.textViewPersonaggiAcquistabili);

        recyclerViewPersonaggiSbloccati = view.findViewById(R.id.recyclerViewPersonaggiSbloccati);
        recyclerViewPersonaggiAcquistabili = view.findViewById(R.id.recyclerViewPersonaggiAcquistabili);

        // Configura il layout manager per le RecyclerView
        GridLayoutManager layoutManagerSbloccati = new GridLayoutManager(getContext(), 3);
        GridLayoutManager layoutManagerAcquistabili = new GridLayoutManager(getContext(), 3);
        recyclerViewPersonaggiSbloccati.setLayoutManager(layoutManagerSbloccati);
        recyclerViewPersonaggiAcquistabili.setLayoutManager(layoutManagerAcquistabili);

        // Configura l'adapter per le RecyclerView
        //recyclerViewPersonaggiSbloccati.setAdapter(new PersonaggiSbloccatiAdapter());
        //recyclerViewPersonaggiAcquistabili.setAdapter(new PersonaggiAcquistabiliAdapter());

        // Altre inizializzazioni e configurazioni possono essere fatte qui
        return view;
    }

}