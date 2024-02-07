package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel;

import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;

public class GenitoreViewModel extends ViewModel {
	private Genitore mGenitore;

	public Genitore getGenitore() {
		return mGenitore;
	}

	public void setGenitore(Genitore genitore) {
		mGenitore = genitore;
	}

}
