package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.appuntamenti;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.pazienti.PazienteAdapter;

public class AppuntamentiLogopedistaFragment extends Fragment {
    private RecyclerView recyclerViewAppuntamenti;
    private Button addAppuntamentoButton;
    private CardView cardViewAppuntamento;
    private RecyclerView recyclerViewPazienteAppuntamentoLogopedista;
    private AppuntamentiLogopedistaAdapter adapterAppuntamenti;
    private Button confermaAppuntamentoButton;
    private NestedScrollView nestedScrollView;
    private TextInputEditText editTextAppuntamentoPaziente;
    private TextInputEditText editTextLuogo;
    private TextInputEditText editTextDataAppuntemento;
    private GridLayout gridLayoutOrarioAppuntamento;
    private DatePickerDialog datePickerDialog;
    private TextInputEditText searchViewAppuntamentiLogopedista;
    private PazienteAdapter adapterPazientiAppuntamentoLogopedista;
    private LinearLayout linearLayoutPazienteAppuntamentoLogopedista;
    private View viewOverlay;

    private String orarioAppuntamento;

    private List<Paziente> pazienti;
    private List<AppuntamentoCustom> appuntamenti;


    public AppuntamentiLogopedistaFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appuntamenti_logopedista, container, false);

        viewOverlay = view.findViewById(R.id.viewOverlayAppuntamentiLogopedista);
        viewOverlay.setVisibility(View.GONE);
        nestedScrollView = view.findViewById(R.id.nestedScrollViewAppuntamentiLogopedista);
        cardViewAppuntamento = view.findViewById(R.id.cardViewNuovoAppuntamentoLogopedista);
        cardViewAppuntamento.setVisibility(View.GONE);
        addAppuntamentoButton = view.findViewById(R.id.buttonNuovoAppuntamentoLogopedista);
        confermaAppuntamentoButton = view.findViewById(R.id.buttonConfermaAppuntamentoLogopedista);
        searchViewAppuntamentiLogopedista = view.findViewById(R.id.searchBarAppuntamentiLogopedista);
        recyclerViewAppuntamenti = view.findViewById(R.id.recyclerViewAppuntamentiLogopedista);
        recyclerViewAppuntamenti.setLayoutManager(new LinearLayoutManager(requireContext()));

        //TODO costruire AppuntamentiCustom e passare la lista di appuntamentiCustom
        appuntamenti = new ArrayList<>();
        for(int i=0; i<100; i++){
            appuntamenti.add(new AppuntamentoCustom("nomePaziente"+i,"cognomePaziente"+i,"a casa mia", LocalDate.now(), LocalTime.now()));
        }

        adapterAppuntamenti = new AppuntamentiLogopedistaAdapter(appuntamenti);
        recyclerViewAppuntamenti.setAdapter(adapterAppuntamenti);

        //nuovo appuntamento
        editTextAppuntamentoPaziente = view.findViewById(R.id.textInputEditTextPazienteAppuntamentoLogopedista);
        editTextLuogo = view.findViewById(R.id.textInputEditTextLuogoAppuntamentoLogopedista);
        editTextDataAppuntemento = view.findViewById(R.id.textInputEditTextDataAppuntamentoLogopedista);
        gridLayoutOrarioAppuntamento = view.findViewById(R.id.gridLayoutAppuntamentoLogopedista);
        recyclerViewPazienteAppuntamentoLogopedista = view.findViewById(R.id.recyclerViewPazienteAppuntamentoLogopedista);
        recyclerViewPazienteAppuntamentoLogopedista.setLayoutManager(new LinearLayoutManager(requireContext()));
        linearLayoutPazienteAppuntamentoLogopedista = view.findViewById(R.id.llPazienteAppuntamentoLogopedista);

        //TODO passare la lista di pazienti
        pazienti = new ArrayList<>();
        for (int i=0; i<100; i++){
            pazienti.add(new Paziente("nomePaziente"+i,"cognomePaziente"+i,"username"+i,"email"+i, "password", 12, java.time.LocalDate.now(), 'M', 0, 0, null));
        }

        adapterPazientiAppuntamentoLogopedista = new PazienteAdapter(pazienti);
        recyclerViewPazienteAppuntamentoLogopedista.setAdapter(adapterPazientiAppuntamentoLogopedista);
        linearLayoutPazienteAppuntamentoLogopedista.setVisibility(View.GONE);
        //logopedistaViewModel = new ViewModelProvider(requireActivity()).get(LogopedistaViewModel.class);
        //mController = logopedistaViewModel.getCreazioneAppuntamentoController();

        return view;
    }

   @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addAppuntamentoButton.setOnClickListener(v -> {
           cardViewAppuntamento.setVisibility(View.VISIBLE);
           addAppuntamentoButton.setVisibility(View.GONE);
            nestedScrollView.smoothScrollTo(0, 0);
        });

       confermaAppuntamentoButton.setOnClickListener(v -> {
           cardViewAppuntamento.setVisibility(View.GONE);
           addAppuntamentoButton.setVisibility(View.VISIBLE);
           recyclerViewAppuntamenti.getAdapter().notifyDataSetChanged();
           //TODO aggiungere funzionalità per aggiungere un appuntamento
           //l'orario selezionano sta nella variabile orarioAppuntamento
             //la data selezionata sta nella variabile editTextDataAppuntemento
                //il paziente selezionato sta nella variabile editTextAppuntamentoPaziente ecc
       });

       editTextAppuntamentoPaziente.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(s.length()>0) {
                   linearLayoutPazienteAppuntamentoLogopedista.setVisibility(View.VISIBLE);
                   viewOverlay.setVisibility(View.VISIBLE);
               }
               else
                   linearLayoutPazienteAppuntamentoLogopedista.setVisibility(View.GONE);
               adapterPazientiAppuntamentoLogopedista.getFilter().filter(s);
           }

           @Override
           public void afterTextChanged(Editable s) {}
       });

       for (int i = 0; i < gridLayoutOrarioAppuntamento.getChildCount(); i++) {
           View child = gridLayoutOrarioAppuntamento.getChildAt(i);

           if (child instanceof TextView) {
               final TextView textView = (TextView) child;
               textView.setOnClickListener(v -> handleTextViewSelection(textView));
           }
       }

       recyclerViewPazienteAppuntamentoLogopedista.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

           @Override
           public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
               return e.getAction() == MotionEvent.ACTION_DOWN;
           }

           @Override
           public void onTouchEvent(RecyclerView rv, MotionEvent e) {
               if(e.getAction() == MotionEvent.ACTION_MOVE){
                   Log.d("PazienteTouchListener", "onTouchEvent: ACTION_MOVE " + e);
               }
               else if (e.getAction() == MotionEvent.ACTION_UP){
                   Log.d("PazienteTouchListener", "onTouchEvent: ACTION_UP " + e);
                   View childView = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(childView);
                    linearLayoutPazienteAppuntamentoLogopedista.setVisibility(View.GONE);
                    RecyclerView.Adapter adapter = rv.getAdapter();
                    Paziente paziente = ((PazienteAdapter) adapter).getItem(position);
                    editTextAppuntamentoPaziente.setText(paziente.getNome() + " " + paziente.getCognome());
                    linearLayoutPazienteAppuntamentoLogopedista.setVisibility(View.GONE);
               }
           }

           @Override
           public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
               // Non utilizzato in questo caso
           }
       });

       editTextDataAppuntemento.setOnClickListener(v -> showDatePickerDialog());

       searchViewAppuntamentiLogopedista.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               Log.d("PazientiFragment", "onQueryTextSubmit: " + s);
               adapterAppuntamenti.getFilter().filter(s);
           }

           @Override
           public void afterTextChanged(Editable s) {}
   });

       viewOverlay.setOnClickListener(v -> {
           linearLayoutPazienteAppuntamentoLogopedista.setVisibility(View.GONE);
           viewOverlay.setVisibility(View.GONE);
       });
        /*addAppuntamentoButton.setOnClickListener(v -> {
            Logopedista mLogopedista = logopedistaViewModel.getLogopedista();
            String idLogopedista = mLogopedista.getIdProfilo();
            eseguiAggiuntaPrenotazione(idLogopedista);

        });*/
    }

    private void handleTextViewSelection(TextView selectedTextView) {
        selectedTextView.setBackground(getContext().getDrawable(R.drawable.rectangle_rounded_border_selector_bkg));
        for (int i = 0; i < gridLayoutOrarioAppuntamento.getChildCount(); i++) {
            View child = gridLayoutOrarioAppuntamento.getChildAt(i);
            if (child instanceof TextView) {
                TextView textView = (TextView) child;
                if (textView != selectedTextView) {
                    textView.setBackground(getContext().getDrawable(R.drawable.rectangle_rounded_border_bkg));
                }
            }
        }
        String selectedText = selectedTextView.getText().toString();
        orarioAppuntamento = selectedText;
    }

    private void showDatePickerDialog() {
        LocalDate now = LocalDate.now();
        datePickerDialog = new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) -> {
            String date = formatDate(year, month, dayOfMonth);
            editTextDataAppuntemento.setText(date);
        }, now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth());
        datePickerDialog.show();
    }

    private String formatDate(int year, int month, int dayOfMonth) {
        LocalDate selectedDate = LocalDate.of(year, month + 1, dayOfMonth);
        return selectedDate.toString();
    }

    /*private CompletableFuture<Appuntamento> eseguiAggiuntaPrenotazione(String idLogopedista) {

        String idGenitore = editTextGenitore.getText().toString();
        String luogoAppuntamento = editTextLuogo.getText().toString();
        LocalDate dataAppuntamento = LocalDate.parse(editTextDataAppuntemento.getText().toString());
        LocalTime orarioAppuntamento = null;

        CompletableFuture<Appuntamento> futureAppuntamento = new CompletableFuture<>();
        Appuntamento appuntamento = mController.creazioneAppuntamento(idLogopedista,idGenitore,dataAppuntamento,orarioAppuntamento,luogoAppuntamento);
        Log.d("AppuntamentiLogopedistaFragment.eseguiAggiuntaPrenotazione()", appuntamento.toString());
        futureAppuntamento.complete(appuntamento);
        return futureAppuntamento;
    }*/

}
