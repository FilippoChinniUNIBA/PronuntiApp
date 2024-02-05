package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.profile_viewmodel;

import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;

public class ProfileLogopedistaViewModel extends ViewModel {
    Logopedista logopedista;
    public void setLogopedista(Logopedista logopedista1){
        this.logopedista = logopedista1;
    }

    public Logopedista getLogopedista() {
        return logopedista;
    }
}
