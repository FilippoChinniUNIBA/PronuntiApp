package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.LogopedistaDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;

public class LogopedistaViewModel extends ViewModel {
	private Logopedista mLogopedista;
	private RegistrazionePazienteGenitoreController mRegistrazionePazienteGenitoreController;

	public Logopedista getLogopedista() {
		return mLogopedista;
	}
	public void setLogopedista(Logopedista logopedista) {
		mLogopedista = logopedista;
	}

	public void aggiornaLogopedistaRemoto() {
		LogopedistaDAO logopedistaDAO = new LogopedistaDAO();
		logopedistaDAO.update(mLogopedista);
		Log.d("LogopedistaViewModel.aggiornaLogopedistaRemoto()", "Logopedista aggiornato: " + mLogopedista.toString());
	}

	public RegistrazionePazienteGenitoreController getRegistrazionePazienteGenitoreController() {
		if (this.mRegistrazionePazienteGenitoreController == null) {
			this.mRegistrazionePazienteGenitoreController = new RegistrazionePazienteGenitoreController();
		}
		return this.mRegistrazionePazienteGenitoreController;
	}



}
