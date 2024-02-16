package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.scenari;

import android.util.Log;

import java.time.LocalDate;

import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;

public class ModificaDataScenariController {
    private GenitoreViewModel mGenitoreViewModel;
    public ModificaDataScenariController(GenitoreViewModel mGenitoreViewModel) {
        this.mGenitoreViewModel = mGenitoreViewModel;
    }

    public void modificaDataScenari(LocalDate date, int indiceTerapia,int position){
        mGenitoreViewModel.getPazienteLiveData().getValue().getTerapie().get(indiceTerapia).getScenariGioco().get(position).setDataInizio(date);

        Log.d("ScenarioAdapter",""+ mGenitoreViewModel.getPazienteLiveData().getValue().getTerapie().get(indiceTerapia).getScenariGioco().get(position).toString());

        mGenitoreViewModel.aggiornaPazienteRemoto();
    }

}
