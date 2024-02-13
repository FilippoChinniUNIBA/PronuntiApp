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

        nestedScrollView = view.findViewById(R.id.nestedScrollViewPersonaggi);
        recyclerViewPersonaggiSbloccati = view.findViewById(R.id.recyclerViewPersonaggiSbloccati);
        recyclerViewPersonaggiAcquistabili = view.findViewById(R.id.recyclerViewPersonaggiAcquistabili);
        recyclerViewPersonaggiAcquistabili.setHasFixedSize(true);

        idsPersonaggiAcquistabili= new ArrayList<>();
        idsPersonaggiSbloccati = new ArrayList<>();
        personaggiSbloccati = new ArrayList<>();
        personaggiAcquistabili = new ArrayList<>();

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
        personaggiSbloccati = mController.getSortedListPersonaggi(personaggi, idsPersonaggiSbloccati);
        personaggiAcquistabili = mController.getSortedListPersonaggi(personaggi, idsPersonaggiAcquistabili);

        PersonaggiSbloccatiAdapter personaggiSbloccatiAdapter = new PersonaggiSbloccatiAdapter(getContext(), personaggiSbloccati, mController);
        PersonaggiAcquistabiliAdapter personaggiAcquistabiliAdapter = new PersonaggiAcquistabiliAdapter(getContext(), personaggiAcquistabili, personaggiSbloccatiAdapter, nestedScrollView, mController);

        recyclerViewPersonaggiAcquistabili.setAdapter(personaggiAcquistabiliAdapter);
        recyclerViewPersonaggiSbloccati.setAdapter(personaggiSbloccatiAdapter);
    }

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

}