package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.terapie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.terapie.TerapieController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.InfoDialog;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.utils_fragments.DatePickerCustom;

public class CreazioneTerapiaFragment extends AbstractFragmentWithNavigation {
    private TextInputEditText editTextIdTerapia;
    private TextInputEditText editTextDataInizioTerapia;
    private TextInputEditText editTextDataFineTerapia;
    private Button buttonFilePickerTerapia;
    private Button buttonVaiCreaScenario;
    private LogopedistaViewModel mLogopedistaViewModel;
    private PazienteViewModel mPazienteViewModel;
    private TerapieController mController;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_creazione_terapia,container,false);

        editTextIdTerapia = view.findViewById(R.id.textInputEditTextIdTerapia);
        editTextDataInizioTerapia = view.findViewById(R.id.textInputEditTextDataInizioTerapia);
        editTextDataFineTerapia = view.findViewById(R.id.textInputEditTextDataFineTerapia);
        buttonFilePickerTerapia = view.findViewById(R.id.buttonOpenFilePicker);
        buttonVaiCreaScenario = view.findViewById(R.id.buttonVaiACreaScenario);

        mLogopedistaViewModel = new ViewModelProvider(requireActivity()).get(LogopedistaViewModel.class);
        mController = mLogopedistaViewModel.getTerapieController();
        mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextDataInizioTerapia.setOnClickListener(v -> DatePickerCustom.showDatePickerDialog(getContext(), editTextDataInizioTerapia));
        editTextDataFineTerapia.setOnClickListener(v -> DatePickerCustom.showDatePickerDialog(getContext(), editTextDataFineTerapia));
        //buttonFilePickerTerapia.setOnClickListener(v-> startFilePicker);

        buttonVaiCreaScenario.setOnClickListener(v-> {
            Logopedista logopedista = mLogopedistaViewModel.getLogopedistaLiveData().getValue();

            eseguiAggiuntaTerapia();

            /*for(Paziente paziente : logopedista.getPazienti()){
                if(paziente.getIdProfilo().equals(mPazienteViewModel.getPazienteLiveData().getValue().getIdProfilo())){
                    paziente.getTerapie().add(terapiaAggiunta);
                }
            }*/
            mLogopedistaViewModel.aggiornaLogopedistaRemoto();
        });

    }

    private void eseguiAggiuntaTerapia(){

        String idTerapia = editTextIdTerapia.getText().toString();

        int statusCampiValidi = mController.verificaCorrettezzaCampiTerapia(editTextDataInizioTerapia.getText().toString(),editTextDataFineTerapia.getText().toString());
        if (statusCampiValidi != 0) {
            creaDialogErroreCampi(statusCampiValidi);
        }else{
            LocalDate dataInizioTerapia = LocalDate.parse(editTextDataInizioTerapia.getText().toString());
            LocalDate dataFineTerapia = LocalDate.parse(editTextDataFineTerapia.getText().toString());
            String idPaziente = mPazienteViewModel.getPazienteLiveData().getValue().getIdProfilo();
            //mController.aggiungiTerapia(idPaziente,idTerapia,dataInizioTerapia,dataFineTerapia);
        }


    }
    public void creaDialogErroreCampi(int tipoErrore) {
        String messaggioErrore = "";
        switch (tipoErrore) {
            case 1:
                messaggioErrore = getString(R.string.erroreCreazioneTerapiaCampiIncompleti);
                break;
            case 2:
                messaggioErrore = getString(R.string.erroreCreazioneTerapiaDataNonValida);
                break;
            case 3:
                messaggioErrore = getString(R.string.erroreCreazioneTerapiaOrdineDateNonValido);
                break;
        }
        InfoDialog infoDialog = new InfoDialog(getContext(), messaggioErrore, getString(R.string.tastoRiprova));
        infoDialog.show();
        infoDialog.setOnConfermaButtonClickListener(null);
    }
}
