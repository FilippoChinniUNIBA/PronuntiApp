package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.personaggi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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

    private List<Personaggio> personaggiSbloccati;
    private List<Personaggio> personaggiAcquistabili;
    private List<String> idsPersonaggiAcquistabili;
    private List<String> idsPersonaggiSbloccati;
    private NestedScrollView nestedScrollView;


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
        //textViewPersonaggiSbloccati = view.findViewById(R.id.textViewPersonaggiSbloccati);
        //textViewPersonaggiAcquistabili = view.findViewById(R.id.textViewPersonaggiAcquistabili);
        nestedScrollView = view.findViewById(R.id.nestedScrollViewPersonaggi);
        recyclerViewPersonaggiSbloccati = view.findViewById(R.id.recyclerViewPersonaggiSbloccati);
        recyclerViewPersonaggiAcquistabili = view.findViewById(R.id.recyclerViewPersonaggiAcquistabili);
        recyclerViewPersonaggiAcquistabili.setHasFixedSize(true);

        idsPersonaggiAcquistabili= new ArrayList<>();
        idsPersonaggiSbloccati = new ArrayList<>();
        personaggiSbloccati = new ArrayList<>();
        personaggiAcquistabili = new ArrayList<>();

        // todo animare le transazioni
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerViewPersonaggiSbloccati.setItemAnimator(itemAnimator);
        recyclerViewPersonaggiAcquistabili.setItemAnimator(itemAnimator);

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
        personaggiSbloccati = getSortedListPersonaggi(personaggi,idsPersonaggiSbloccati);
        personaggiAcquistabili = getSortedListPersonaggi(personaggi,idsPersonaggiAcquistabili);

        PersonaggiSbloccatiAdapter personaggiSbloccatiAdapter = new PersonaggiSbloccatiAdapter(getContext(),personaggiSbloccati,mPazienteViewModel);
        PersonaggiAcquistabiliAdapter personaggiAcquistabiliAdapter = new PersonaggiAcquistabiliAdapter(getContext(), personaggiAcquistabili,personaggiSbloccatiAdapter,nestedScrollView,mPazienteViewModel);

        recyclerViewPersonaggiAcquistabili.setAdapter(personaggiAcquistabiliAdapter);
        recyclerViewPersonaggiSbloccati.setAdapter(personaggiSbloccatiAdapter);
    }

    //Da mettere nel controller


    private void setIdLists(Map<String, Integer> map) {
        setFirstId(map);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int value = Integer.parseInt(String.valueOf(entry.getValue()));
            if (value == 1) {
                idsPersonaggiSbloccati.add(key);
            } else if (value == 0) {
                idsPersonaggiAcquistabili.add(key);
            }
        }
    }
    private void setFirstId(Map<String, Integer> map){
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int value = Integer.parseInt(String.valueOf(entry.getValue()));
            if (value == 2) {
                idsPersonaggiSbloccati.add(key);
            }
        }
    }

    public static List<Personaggio> getSortedListPersonaggi(List<Personaggio> listaPersonaggi, List<String> listaId) {
        //Utilizzo di hashmap per rendere l'ordinamento più efficiente
        Map<String, Personaggio> mappaIdPersonaggio = new HashMap<>();
        for (Personaggio personaggio : listaPersonaggi) {
            mappaIdPersonaggio.put(personaggio.getIdPersonaggio(), personaggio);
        }
        List<Personaggio> listaOrdinata = new ArrayList<>();
        for (String id : listaId) {
            if (mappaIdPersonaggio.containsKey(id)) {
                listaOrdinata.add(mappaIdPersonaggio.get(id));
            }
        }
        return listaOrdinata;
    }

    /*private void setAttributes(List<Personaggio> personaggi) {
        setFirstPersonaggio(personaggi);
        for (Personaggio personaggio : personaggi) {
            if (idsPersonaggiSbloccati.contains(personaggio.getIdPersonaggio())&&!urlsPersonaggiSbloccati.contains(personaggio.getTexturePersonaggio())) {
                urlsPersonaggiSbloccati.add(personaggio.getTexturePersonaggio());
                nomiPersonaggiSbloccati.add(personaggio.getNomePersonaggio());
            } else if (!idsPersonaggiSbloccati.contains(personaggio.getIdPersonaggio())){
                urlsPersonaggiAcquistabili.add(personaggio.getTexturePersonaggio());
                nomiPersonaggiAcquistabili.add(personaggio.getNomePersonaggio());
            }
        }
    }*/

    /*private void setFirstPersonaggio(List<Personaggio> personaggi){
        for (Personaggio personaggio : personaggi) {
            if(idsPersonaggiSbloccati.get(0).equals(personaggio.getIdPersonaggio())){
                urlsPersonaggiSbloccati.add(personaggio.getTexturePersonaggio());
                nomiPersonaggiSbloccati.add(personaggio.getNomePersonaggio());
            }
        }
    }*/

}