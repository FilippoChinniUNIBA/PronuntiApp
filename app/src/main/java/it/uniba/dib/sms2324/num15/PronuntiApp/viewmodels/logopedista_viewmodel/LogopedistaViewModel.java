package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.LogopedistaDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;

public class LogopedistaViewModel extends ViewModel {
	private MutableLiveData<Logopedista> mLogopedistaLiveData = new MutableLiveData<>();
	private MutableLiveData<List<Appuntamento>> mAppuntamentiLiveData = new MutableLiveData<>();
	private RegistrazionePazienteGenitoreController mRegistrazionePazienteGenitoreController;
	private CreazioneAppuntamentoController mCreazioneAppuntamentoController;

	public LiveData<Logopedista> getLogopedistaLiveData() {
		return mLogopedistaLiveData;
	}
	public void setLogopedista(Logopedista logopedista) {
		mLogopedistaLiveData.setValue(logopedista);
	}

	public LiveData<List<Appuntamento>> getmAppuntamentiLiveData() {
		return mAppuntamentiLiveData;
	}

	public void setAppuntamenti(List<Appuntamento> appuntamenti) {
		this.mAppuntamentiLiveData.setValue(appuntamenti);
	}

	public void aggiornaLogopedistaRemoto() {
		Logopedista logopedista = mLogopedistaLiveData.getValue();

		LogopedistaDAO logopedistaDAO = new LogopedistaDAO();
		logopedistaDAO.update(logopedista);

		Log.d("LogopedistaViewModel.aggiornaLogopedistaRemoto()", "Logopedista aggiornato: " + logopedista.toString());
	}

	public RegistrazionePazienteGenitoreController getRegistrazionePazienteGenitoreController() {
		if (this.mRegistrazionePazienteGenitoreController == null) {
			this.mRegistrazionePazienteGenitoreController = new RegistrazionePazienteGenitoreController();
		}
		return this.mRegistrazionePazienteGenitoreController;
	}

	public CreazioneAppuntamentoController getCreazioneAppuntamentoController(){
		if (this.mCreazioneAppuntamentoController == null) {
			this.mCreazioneAppuntamentoController = new CreazioneAppuntamentoController();
		}
		return this.mCreazioneAppuntamentoController;
	}

}
