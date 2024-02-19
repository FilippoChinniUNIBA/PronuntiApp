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
import androidx.lifecycle.ViewModelProvider;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;


public class CreazioneTerapieFragment extends AbstractFragmentWithNavigation implements SaveScenario{

    private TextInputEditText dataInizio;
    private TextInputEditText dataFine;
    private Button buttonAddScenario;
    private Button buttonSalvataggioTerapia;
    private Terapia terapia;
    private boolean isFirstScenario = true;
    private LogopedistaViewModel mLogopedistaViewModel;
    private Bundle bundle;
    private String idPaziente;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_creazione_terapia, container, false);
        mLogopedistaViewModel = new ViewModelProvider(requireActivity()).get(LogopedistaViewModel.class);

        dataFine = view.findViewById(R.id.textInputEditTextDataFineTerapia);
        dataInizio = view.findViewById(R.id.textInputEditTextDataInizioTerapia);

        buttonAddScenario = view.findViewById(R.id.buttonAddScenario);
        buttonSalvataggioTerapia = view.findViewById(R.id.buttonSalvaTerapia);
        bundle = getArguments();
        idPaziente = bundle.getString("idPaziente");
        buttonSalvataggioTerapia.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLogopedistaViewModel.getLogopedistaLiveData().observe(getViewLifecycleOwner(), Void -> {
            Log.d("idPaziente",idPaziente);
            Paziente paziente = mLogopedistaViewModel.getPazienteById(idPaziente);
            String nomePaziente = paziente.getNome();
            String cognomePaziente = paziente.getCognome();
            setToolBar(view,getString(R.string.creaTerapia) + " " + nomePaziente+" "+cognomePaziente);

            dataInizio.setOnClickListener(v -> DatePickerCustom.showDatePickerDialog(getContext(), dataInizio));
            dataFine.setOnClickListener(v -> DatePickerCustom.showDatePickerDialog(getContext(), dataFine));

            buttonAddScenario.setOnClickListener(v -> addScenario());

            buttonSalvataggioTerapia.setOnClickListener(v-> saveTerapia(idPaziente,nomePaziente,cognomePaziente));
        });


    }
    private void showErrorDialog(int messaggio){
        InfoDialog infoDialog = new InfoDialog(getContext(), getString(R.string.compilaPrimaTutto), getString(R.string.tastoRiprova));
        infoDialog.setOnConfermaButtonClickListener(null);
        infoDialog.show();
    }
    private void addScenario(){
        if(dataInizio.getText().toString().isEmpty() || dataFine.getText().toString().isEmpty()){
            showErrorDialog(R.string.compilaPrimaTutto);
        }else if(LocalDate.parse(dataInizio.getText().toString()).isAfter(LocalDate.parse(dataFine.getText().toString()))){
            showErrorDialog(R.string.insertDate);
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

    private void saveTerapia(String idPaziente,String nome,String cognome){
        mLogopedistaViewModel.addTerapiaInPaziente(terapia,idPaziente);
        mLogopedistaViewModel.aggiornaLogopedistaRemoto();
        Bundle bundle1 = new Bundle();
        bundle1.putString("idPaziente",idPaziente);
        bundle1.putString("nomePaziente",nome);
        bundle1.putString("cognomePaziente",cognome);
        navigateTo(R.id.action_creazioneTerapiaFragment_to_schedaPazienteFragment,bundle1);
    }

    @Override
    public void saveScenario(ScenarioGioco scenarioGioco) {
        Log.d("Terapia","terapia in saveScenario: " + terapia.toString());
        terapia.addScenario(scenarioGioco);
        buttonAddScenario.setVisibility(View.VISIBLE);
        buttonSalvataggioTerapia.setVisibility(View.VISIBLE);

    }

}
