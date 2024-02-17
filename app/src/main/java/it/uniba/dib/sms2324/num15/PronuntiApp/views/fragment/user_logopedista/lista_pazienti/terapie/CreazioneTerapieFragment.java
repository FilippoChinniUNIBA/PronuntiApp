package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti.terapie;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.InfoDialog;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.utils_fragments.DatePickerCustom;

public class CreazioneTerapieFragment extends AbstractFragmentWithNavigation implements SaveScenario{

    private TextInputEditText dataInizio;
    private TextInputEditText dataFine;
    private Button buttonAddScenario;
    private Button buttonSalvataggioTerapia;
    private Terapia terapia;
    private boolean isFirstScenario = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_creazione_terapia, container, false);
        //TODO ricevere da bundle idPaziente

        //TODO passare qua il nome e cognome del paziente preso dal viewModel
        setToolBar(view,getString(R.string.creaTerapia) + " " + "Giovanni Vocestupida");

        dataFine = view.findViewById(R.id.textInputEditTextDataInizioTerapia);
        dataInizio = view.findViewById(R.id.textInputEditTextDataFineTerapia);

        buttonAddScenario = view.findViewById(R.id.buttonAddScenario);
        buttonSalvataggioTerapia = view.findViewById(R.id.buttonSalvaTerapia);

        buttonSalvataggioTerapia.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInizio.setOnClickListener(v -> DatePickerCustom.showDatePickerDialog(getContext(), dataInizio));
        dataFine.setOnClickListener(v -> DatePickerCustom.showDatePickerDialog(getContext(), dataFine));

        buttonAddScenario.setOnClickListener(v -> addScenario());

        buttonSalvataggioTerapia.setOnClickListener(v-> saveTerapia());
    }
    private void showErrorDialog(){
        InfoDialog infoDialog = new InfoDialog(getContext(), getString(R.string.compilaPrimaTutto), getString(R.string.tastoRiprova));
        infoDialog.setOnConfermaButtonClickListener(null);
        infoDialog.show();
    }
    private void addScenario(){
        if(dataInizio.getText().toString().isEmpty() || dataFine.getText().toString().isEmpty()){
            showErrorDialog();
        }
        else {
            if(isFirstScenario){
                Log.d("Terapia","isFristScenario");
                terapia = new Terapia(LocalDate.parse(dataInizio.getText().toString()), LocalDate.parse(dataFine.getText().toString()));
                isFirstScenario = false;
            }
            Log.d("Terapia",terapia.toString());
            buttonAddScenario.setVisibility(View.GONE);
            getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewNuovoScenario, new CreazioneScenarioFragment(this)).commit();
            buttonSalvataggioTerapia.setVisibility(View.GONE);
        }
    }

    private void saveTerapia(){
        //TODO implementare funzionalità per salvare la terapia in db
        // la terapia è già stata creata e dovrebbe stare in "terapia"
        //terapia
        Log.d("Terapia",terapia.toString());
        navigateTo(R.id.action_creazioneTerapiaFragment_to_schedaPazienteFragment);
        //TODO controllare che effettivamente viene salvata in db e viene mostrata in scheda paziente
    }

    @Override
    public void saveScenario(ScenarioGioco scenarioGioco) {
        Log.d("Terapia","terapia in saveScenario: " + terapia.toString());
        terapia.addScenario(scenarioGioco);
        buttonAddScenario.setVisibility(View.VISIBLE);
        buttonSalvataggioTerapia.setVisibility(View.VISIBLE);

    }

}
