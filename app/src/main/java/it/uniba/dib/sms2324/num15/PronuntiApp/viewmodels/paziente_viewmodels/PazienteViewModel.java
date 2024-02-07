package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels;

import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;

public class PazienteViewModel extends ViewModel {
	private Paziente mPaziente;

	public Paziente getPaziente() {
		return mPaziente;
	}

	public void setPaziente(Paziente paziente) {
		mPaziente = paziente;
	}

}
