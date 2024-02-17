package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel;

import android.util.Log;

import java.time.LocalDate;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.scenari.ModificaDataScenariController;

public class ModificaDataScenariLogopedistaController implements ModificaDataScenariController {
    private LogopedistaViewModel mLogopedistaViewModel;
    public ModificaDataScenariLogopedistaController(LogopedistaViewModel mLogopedistaViewModel) {
        this.mLogopedistaViewModel = mLogopedistaViewModel;
    }
    @Override
    public void modificaDataScenari(LocalDate date, int indiceTerapia, int position, String idPaziente, int indicePaziente) {
        for (Paziente paziente: mLogopedistaViewModel.getLogopedistaLiveData().getValue().getPazienti()) {
            if(paziente.getIdProfilo().equals(idPaziente)){
                mLogopedistaViewModel.getLogopedistaLiveData().getValue().getPazienti().get(indicePaziente).getTerapie().get(indiceTerapia).getScenariGioco().get(position).setDataInizio(date);
                mLogopedistaViewModel.aggiornaLogopedistaRemoto();
                break;
            }
        }
    }
}
