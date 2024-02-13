package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.personaggi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.personaggi.PersonaggiController;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class PersonaggiFragment extends AbstractFragmentWithNavigation {
    private RecyclerView recyclerViewPersonaggiSbloccati;
    private RecyclerView recyclerViewPersonaggiAcquistabili;
    private TextView textViewPersonaggiSbloccati;
    private TextView textViewPersonaggiAcquistabili;
    private TextView textViewMonete;

    private List<String> urlsPersonaggiSbloccati;
    private List<String> urlsPersonaggiAcquistabili;
    private List<String> nomiPersonaggiSbloccati;
    private List<String> nomiPersonaggiAcquistabili;
    private List<String> idsPersonaggiAcquistabili;
    private List<String> idsPersonaggiSbloccati;

    private PazienteViewModel mPazienteViewModel;
    private PersonaggiController mController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= inflater.inflate(R.layout.fragment_personaggi, container, false);

        this.mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);
        this.mController = mPazienteViewModel.getPersonaggiController();

        setToolBarNoTitle(view);

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


        //TODO settare monete in onViewCreated
        textViewMonete = view.findViewById(R.id.coinsTextView);
        textViewPersonaggiSbloccati = view.findViewById(R.id.textViewPersonaggiSbloccati);
        textViewPersonaggiAcquistabili = view.findViewById(R.id.textViewPersonaggiAcquistabili);

        recyclerViewPersonaggiSbloccati = view.findViewById(R.id.recyclerViewPersonaggiSbloccati);
        recyclerViewPersonaggiAcquistabili = view.findViewById(R.id.recyclerViewPersonaggiAcquistabili);
        recyclerViewPersonaggiAcquistabili.setHasFixedSize(true);

        urlsPersonaggiSbloccati = new ArrayList<>();
        urlsPersonaggiAcquistabili = new ArrayList<>();
        nomiPersonaggiAcquistabili = new ArrayList<>();
        nomiPersonaggiSbloccati = new ArrayList<>();
        idsPersonaggiAcquistabili= new ArrayList<>();
        idsPersonaggiSbloccati = new ArrayList<>();

        GridLayoutManager layoutManagerSbloccati = new GridLayoutManager(getContext(), 3);
        GridLayoutManager layoutManagerAcquistabili = new GridLayoutManager(getContext(), 3);
        recyclerViewPersonaggiSbloccati.setLayoutManager(layoutManagerSbloccati);
        recyclerViewPersonaggiAcquistabili.setLayoutManager(layoutManagerAcquistabili);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setAdapters();
    }

    private void setAdapters() {
        Map<String, Integer> personaggiPaziente = mPazienteViewModel.getPazienteLiveData().getValue().getPersonaggiSbloccati();
        List<Personaggio> personaggi = mPazienteViewModel.getListaPersonaggiLiveData().getValue();

        setIdLists(personaggiPaziente);
        setAttributes(personaggi);

        PersonaggiAcquistabiliAdapter personaggiAcquistabiliAdapter = new PersonaggiAcquistabiliAdapter(getContext(), urlsPersonaggiAcquistabili, nomiPersonaggiAcquistabili);
        PersonaggiSbloccatiAdapter personaggiSbloccatiAdapter = new PersonaggiSbloccatiAdapter(getContext(), urlsPersonaggiSbloccati, nomiPersonaggiSbloccati);
        recyclerViewPersonaggiAcquistabili.setAdapter(personaggiAcquistabiliAdapter);
        recyclerViewPersonaggiSbloccati.setAdapter(personaggiSbloccatiAdapter);
    }

    private void setAttributes(List<Personaggio> personaggi) {
        for (Personaggio personaggio : personaggi) {
            if (idsPersonaggiSbloccati.contains(personaggio.getIdPersonaggio())) {
                urlsPersonaggiSbloccati.add(personaggio.getTexturePersonaggio());
                nomiPersonaggiSbloccati.add(personaggio.getNomePersonaggio());
            } else {
                urlsPersonaggiAcquistabili.add(personaggio.getTexturePersonaggio());
                nomiPersonaggiAcquistabili.add(personaggio.getNomePersonaggio());
            }
        }
    }

    public void setIdLists(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();

            int value = Integer.parseInt(String.valueOf(entry.getValue()));
            if (value == 1 || value == 2) {
                idsPersonaggiSbloccati.add(key);
            } else {
                idsPersonaggiAcquistabili.add(key);
            }
        }
    }

}