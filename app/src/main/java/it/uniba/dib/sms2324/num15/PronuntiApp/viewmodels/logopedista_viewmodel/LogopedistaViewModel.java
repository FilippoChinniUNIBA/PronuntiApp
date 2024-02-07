package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel;

import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;

public class LogopedistaViewModel extends ViewModel {
	private Logopedista mLogopedista;

	public Logopedista getLogopedista() {
		return mLogopedista;
	}

	public void setLogopedista(Logopedista logopedista) {
		mLogopedista = logopedista;
	}

}
