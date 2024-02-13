package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.classifica;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.classifica.ClassificaController;
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

        this.mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);
        this.mController = mPazienteViewModel.getClassificaController();

        setToolBar(view, getString(R.string.classifica));

        recyclerViewClassifica = view.findViewById(R.id.recyclerViewClassifica);
        recyclerViewClassifica.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPazienteViewModel.getClassificaLiveData().observe(getViewLifecycleOwner(), classifica -> {

            entryClassificaAdapter = new EntryClassificaAdapter(classifica, mPazienteViewModel.getPazienteLiveData().getValue().getUsername());
            recyclerViewClassifica.setAdapter(entryClassificaAdapter);
        });
    }

}