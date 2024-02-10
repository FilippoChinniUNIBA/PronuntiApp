package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.appuntamenti;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;

public class AppuntamentiGenitoreFragment extends Fragment {
    private RecyclerView recyclerViewAppuntamentiGenitore;
    private AppuntamentoGenitoreAdapter appuntamentoAdapter;
    private List<Appuntamento> appuntamenti; // Lista degli appuntamenti (da sostituire con la tua implementazione)

    public AppuntamentiGenitoreFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appuntamenti_genitore, container, false);
        recyclerViewAppuntamentiGenitore = view.findViewById(R.id.recyclerViewAppuntamentiGenitore);

        recyclerViewAppuntamentiGenitore.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO: prendere gli appuntamenti dal database
        appuntamenti = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            if(i<3)
                appuntamenti.add(new Appuntamento("idAppuntamento"+i, "refIdLogopedista"+i, "refIdGenitore"+i, LocalDate.now().minusDays(1), LocalTime.now(), "Via dei mille 10, 20123 Milano "+i));
            else
                appuntamenti.add(new Appuntamento("idAppuntamento"+i, "refIdLogopedista"+i, "refIdGenitore"+i, LocalDate.now(), LocalTime.now().plusHours(1), "Via dei mille 10, 20123 Milano "+i));
        }
        appuntamentoAdapter = new AppuntamentoGenitoreAdapter(appuntamenti);
        recyclerViewAppuntamentiGenitore.setAdapter(appuntamentoAdapter);
    }
}