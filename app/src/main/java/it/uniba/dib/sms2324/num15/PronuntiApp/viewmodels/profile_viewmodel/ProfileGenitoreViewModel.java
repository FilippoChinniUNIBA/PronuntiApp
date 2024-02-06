package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.profile_viewmodel;

import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;

public class ProfileGenitoreViewModel extends ViewModel {
    private static Genitore genitore;
    public void setGenitore(Genitore genitore) {
        this.genitore = genitore;
    }
    public Genitore getGenitore() {
        return genitore;
    }
}
