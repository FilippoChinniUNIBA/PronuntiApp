package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.Esercizio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioEseguibile;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class AbstractFragmenteEsercizioFineScenario extends AbstractFragmentWithNavigation {


    protected boolean checkFineScenario(ScenarioGioco scenarioGioco) {

        for(EsercizioEseguibile s : scenarioGioco.getEsercizi()) {
            if(s.getRisultatoEsercizio()== null)
                return false;
        }
        return true;
    }

}
