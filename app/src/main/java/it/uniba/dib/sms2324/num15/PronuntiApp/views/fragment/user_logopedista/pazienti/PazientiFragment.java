package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.pazienti;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class PazientiFragment extends AbstractFragmentWithNavigation {

    private RecyclerView recyclerViewListaPazienti;
    private PazienteAdapter adapterPazienti;
    private Button addPazientiButton;
    private SearchView searchViewListaPazienti;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pazienti, container, false);
        addPazientiButton = view.findViewById(R.id.addPaziente);
        addPazientiButton.setOnClickListener(v -> navigateTo(R.id.action_pazientiFragment_to_registrazionePazienteGenitoreFragment));
        searchViewListaPazienti = view.findViewById(R.id.searchViewListaPazienti);

        recyclerViewListaPazienti = view.findViewById(R.id.pazientiRecyclerView);
        recyclerViewListaPazienti.setLayoutManager(new LinearLayoutManager(requireContext()));

        ArrayList<Paziente> pazienti= new ArrayList<>();

        //TODO: Recupera i pazienti dal controller
        for (int i = 0; i < 100; i++) {
            Paziente paziente = new Paziente("nome"+i, "cognome"+i, "username"+i, "email"+i, "password", i, java.time.LocalDate.now(), 'M', 0, 0, null);
            paziente.setNome("Nome" + i);
            paziente.setCognome("Cognome" + i);
            paziente.setEta(10 + i);
            paziente.setSesso('M');
            pazienti.add(paziente);
        }
        Log.d("PazientiFragment", "pazienti: " + pazienti);
        adapterPazienti = new PazienteAdapter(pazienti);
        recyclerViewListaPazienti.setAdapter(adapterPazienti);

        recyclerViewListaPazienti.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                Log.d("PazientiFragment", "onInterceptTouchEvent: " + childView);
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                Log.d("PazientiFragment", "onTouchEvent: " + childView);
                if (childView != null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    childView.setBackgroundResource(R.drawable.rectangle_rounded_border_selector_bkg);

                    // You can also trigger navigation or other actions here.
                    Paziente pazienteSelezionato = pazienti.get(recyclerViewListaPazienti.getChildAdapterPosition(childView));
                    // TODO: Implementare la navigazione verso i risultati del paziente
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                // This method is not relevant for this implementation.
            }
        });



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
