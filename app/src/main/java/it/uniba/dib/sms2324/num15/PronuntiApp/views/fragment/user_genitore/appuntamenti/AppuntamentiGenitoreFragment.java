package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.appuntamenti;

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
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.GenitoreDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.AppuntamentiGenitoreController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class AppuntamentiGenitoreFragment extends AbstractFragmentWithNavigation {
    private RecyclerView recyclerViewAppuntamentiGenitore;
    private AppuntamentoGenitoreAdapter appuntamentoAdapter;

    private GenitoreViewModel mGenitoreViewModel;
    private AppuntamentiGenitoreController mController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appuntamenti_genitore, container, false);

        this.mGenitoreViewModel = new ViewModelProvider(requireActivity()).get(GenitoreViewModel.class);
        this.mController = mGenitoreViewModel.getAppuntamentiControllerGenitore();

        setToolBar(view,getString(R.string.i_tuoi_appuntamenti));

        recyclerViewAppuntamentiGenitore = view.findViewById(R.id.recyclerViewAppuntamentiGenitore);
        recyclerViewAppuntamentiGenitore.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Appuntamento> appuntamenti = mGenitoreViewModel.getAppuntamentiLiveData().getValue();

        appuntamentoAdapter = new AppuntamentoGenitoreAdapter(appuntamenti);
        recyclerViewAppuntamentiGenitore.setAdapter(appuntamentoAdapter);
    }

}