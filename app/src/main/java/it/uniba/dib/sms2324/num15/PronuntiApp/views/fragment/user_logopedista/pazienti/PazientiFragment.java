package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.pazienti;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class PazientiFragment extends AbstractFragmentWithNavigation {
    private RecyclerView recyclerViewListaPazienti;
    private PazienteAdapter adapterPazienti;
    private Button addPazientiButton;
    private SearchView searchViewListaPazienti;

    private LogopedistaViewModel mLogopedistaViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pazienti, container, false);
        addPazientiButton = view.findViewById(R.id.addPaziente);
        addPazientiButton.setOnClickListener(v -> navigateTo(R.id.action_pazientiFragment_to_registrazionePazienteGenitoreFragment));
        searchViewListaPazienti = view.findViewById(R.id.searchViewListaPazienti);

        recyclerViewListaPazienti = view.findViewById(R.id.pazientiRecyclerView);
        recyclerViewListaPazienti.setLayoutManager(new LinearLayoutManager(requireContext()));

        mLogopedistaViewModel = new ViewModelProvider(requireActivity()).get(LogopedistaViewModel.class);

        try {
            List<Paziente> pazienti = mLogopedistaViewModel.getLogopedista().getPazienti();
            Log.d("PazientiFragment", "pazienti: " + pazienti);
            adapterPazienti = new PazienteAdapter(pazienti);
            recyclerViewListaPazienti.setAdapter(adapterPazienti);

            recyclerViewListaPazienti.addOnItemTouchListener(new PazienteTouchListener(requireContext(), recyclerViewListaPazienti));



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
        }catch(NullPointerException exception){
            Log.d("PazientiFragment","lista pazienti vuota"+exception);
        }

        return view;
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
}
