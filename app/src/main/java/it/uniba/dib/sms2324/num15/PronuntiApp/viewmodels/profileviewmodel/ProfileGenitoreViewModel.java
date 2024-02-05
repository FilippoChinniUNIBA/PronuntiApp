package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.profileviewmodel;

import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;

public class ProfileGenitoreViewModel extends ViewModel {
    private Genitore genitore;
    public void setGenitore(Genitore genitore) {
        this.genitore = genitore;
    }
    public Genitore getGenitore() {
        return genitore;
    }
}
