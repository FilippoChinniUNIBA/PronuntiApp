package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.profileviewmodel;

import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;

public class ProfilePazienteViewModel extends ViewModel {

    private Paziente paziente;

    public void setPaziente(Paziente paziente1){
        this.paziente=paziente1;
    }

    public Paziente getPaziente() {
        return paziente;
    }
}
