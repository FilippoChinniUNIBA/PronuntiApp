package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.classifica;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.ClassificaController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class ClassificaFragment extends AbstractFragmentWithNavigation {
    private RecyclerView recyclerViewClassifica;
    private EntryClassificaAdapter entryClassificaAdapter;

    private PazienteViewModel mPazienteViewModel;
    private ClassificaController mController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classifica, container, false);

        recyclerViewClassifica = view.findViewById(R.id.recyclerViewClassifica);
        recyclerViewClassifica.setLayoutManager(new LinearLayoutManager(getContext()));

        this.mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);
        this.mController = mPazienteViewModel.getClassificaController();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO: costruire la classifica creando una lista di tipo EntryClassifica (username, punteggio, url_img)
        // url_img si ottiene da personaggioDao ottenendo prima l'id del personaggio selezionato da Paziente paziente.getPersonaggiSbloccati() e
        // poi cercare la chiave con il valore 2 (che significa selezionato)

        //TODO: togliere TODO sopra

        mPazienteViewModel.getClassificaLiveData().observe(getViewLifecycleOwner(), classifica -> {
                    entryClassificaAdapter = new EntryClassificaAdapter(classifica, mPazienteViewModel.getPazienteLiveData().getValue().getIdProfilo());
                    recyclerViewClassifica.setAdapter(entryClassificaAdapter);
        });
    }

}