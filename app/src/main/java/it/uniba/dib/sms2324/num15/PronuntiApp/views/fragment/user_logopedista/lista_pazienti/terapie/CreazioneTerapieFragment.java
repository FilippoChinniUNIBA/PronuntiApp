package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti.terapie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.utils_fragments.DatePickerCustom;

public class CreazioneTerapieFragment extends AbstractFragmentWithNavigation {

    private TextInputEditText dataInizio;
    private TextInputEditText dataFine;
    private Button buttonAddScenario;
    private Button buttonSalvataggioTerapia;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_creazione_terapia, container, false);
        //TODO ricevere da bundle idPaziente

        //TODO passare il nome e cognome del paziente preso dal viewModel
        setToolBar(view,getString(R.string.creaTerapia) + " " + "Giovanni Vocestupida");

        dataFine = view.findViewById(R.id.textInputEditTextDataInizioTerapia);
        dataInizio = view.findViewById(R.id.textInputEditTextDataFineTerapia);

        buttonAddScenario = view.findViewById(R.id.buttonAddScenario);
        buttonSalvataggioTerapia = view.findViewById(R.id.buttonSalvaTerapia);

        //TODO Nicola da mostrare dopo quando non loso
        buttonSalvataggioTerapia.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInizio.setOnClickListener(v -> DatePickerCustom.showDatePickerDialog(getContext(), dataInizio));
        dataFine.setOnClickListener(v -> DatePickerCustom.showDatePickerDialog(getContext(), dataFine));

        buttonAddScenario.setOnClickListener(v -> {
            //TODO Nicola da mostrare dinuovo quando clicchi su salva terapia
            buttonAddScenario.setVisibility(View.GONE);
            getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewNuovoScenario, new CreazioneScenarioFragment()).commit();
            }
        );

        buttonSalvataggioTerapia.setOnClickListener(v-> saveTerapia());
    }

    private void saveTerapia(){
        //TODO implementare funzionalità per salvare la terapia facendo il get dei dati inseriti

        navigateTo(R.id.action_creazioneTerapiaFragment_to_schedaPazienteFragment);

    }
}
