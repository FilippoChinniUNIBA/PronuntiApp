package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.appuntamenti;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.GenitoreDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.AppuntamentiControllerGenitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;

public class AppuntamentiGenitoreFragment extends Fragment {
    private RecyclerView recyclerViewAppuntamentiGenitore;
    private AppuntamentoGenitoreAdapter appuntamentoAdapter;
    private List<Appuntamento> appuntamenti; // Lista degli appuntamenti (da sostituire con la tua implementazione)
    private GenitoreViewModel mGenitoreViewModel;
    private String idGenitore;
    private AppuntamentiControllerGenitore mAppuntamentiControllerGenitore;

    public AppuntamentiGenitoreFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appuntamenti_genitore, container, false);
        recyclerViewAppuntamentiGenitore = view.findViewById(R.id.recyclerViewAppuntamentiGenitore);
        recyclerViewAppuntamentiGenitore.setLayoutManager(new LinearLayoutManager(getContext()));


        mGenitoreViewModel = new ViewModelProvider(requireActivity()).get(GenitoreViewModel.class);
        this.idGenitore = mGenitoreViewModel.getGenitoreLiveData().getValue().getIdProfilo();
        this.mAppuntamentiControllerGenitore = mGenitoreViewModel.getAppuntamentiControllerGenitore();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GenitoreDAO genitoreDAO = new GenitoreDAO();

        Log.e("AppuntamentoGenitoreFragment","appuntamenti" +genitoreDAO.getPazienteByIdGenitore(idGenitore));

        try {
            CompletableFuture<List<Appuntamento>> appuntamentiFuture = mAppuntamentiControllerGenitore.retrieveAppuntamentiGenitore(idGenitore);
            appuntamentiFuture.thenAccept(appuntamenti -> {
                Log.e("AppuntamentoGenitoreFragment","appuntamenti" + appuntamenti.toString());
                appuntamentoAdapter = new AppuntamentoGenitoreAdapter(appuntamenti);
                recyclerViewAppuntamentiGenitore.setAdapter(appuntamentoAdapter);
            });
        }catch(NullPointerException exception){
            Log.e("AppuntamentoLogopedistaFragment", "NullPointerException", exception);
        }

    }
}