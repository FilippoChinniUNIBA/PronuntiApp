package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.monitoraggio.NavigateTo;

public class PazientiFragment extends AbstractFragmentWithNavigation implements NavigateTo {
    private RecyclerView recyclerViewListaPazienti;
    private PazienteAdapter adapterPazienti;
    private Button addPazientiButton;
    private SearchView searchViewListaPazienti;
    private PazienteViewModel mPazienteViewModel;
    private LogopedistaViewModel mLogopedistaViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pazienti, container, false);

        mLogopedistaViewModel = new ViewModelProvider(requireActivity()).get(LogopedistaViewModel.class);
        mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);

        initViews(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    private void initViews(View view) {
        addPazientiButton = view.findViewById(R.id.addPaziente);
        addPazientiButton.setOnClickListener(v -> navigateTo(R.id.action_pazientiFragment_to_registrazionePazienteGenitoreFragment));

        searchViewListaPazienti = view.findViewById(R.id.searchViewListaPazienti);
        recyclerViewListaPazienti = view.findViewById(R.id.pazientiRecyclerView);
        recyclerViewListaPazienti.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void loadData() {
        mLogopedistaViewModel.getLogopedistaLiveData().observe(getViewLifecycleOwner(), logopedista -> {

            List<Paziente> pazienti = new ArrayList<>();
            if(logopedista.getPazienti() != null) {
                pazienti = logopedista.getPazienti();
            }
            Log.d("PazientiFragment.loadData()", "pazienti: " + ((pazienti == null) ? "null" : pazienti.toString()));

            adapterPazienti = new PazienteAdapter(pazienti, this);
            recyclerViewListaPazienti.setAdapter(adapterPazienti);

            //recyclerViewListaPazienti.addOnItemTouchListener(new PazienteTouchListener(requireContext(), recyclerViewListaPazienti));

            searchViewListaPazienti.setOnCloseListener(() -> {
                addPazientiButton.setText("Paziente +");
                return false;
            });
            searchViewListaPazienti.setOnSearchClickListener(v -> addPazientiButton.setText("+"));

            searchViewListaPazienti.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.d("PazientiFragment", "onQueryTextSubmit: " + query);
                    adapterPazienti.getFilter().filter(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapterPazienti.getFilter().filter(newText);
                    return true;
                }
            });
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("I tuoi pazienti");
        }
        if(searchViewListaPazienti.isIconified())
            addPazientiButton.setText("Paziente +");
        else addPazientiButton.setText("+");
    }

    @Override
    public void navigateToId(int id, Bundle bundle) {
        navigateTo(id, bundle);
    }
}
