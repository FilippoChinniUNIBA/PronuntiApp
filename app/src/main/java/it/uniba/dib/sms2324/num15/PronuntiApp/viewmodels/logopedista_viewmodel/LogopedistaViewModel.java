package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.AppuntamentoDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.LogopedistaDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.Esercizio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.TemplateScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.appuntamenti.ModificaAppuntamentiController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.lista_pazienti.RegistrazionePazienteGenitoreController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.terapie.TerapieController;

public class LogopedistaViewModel extends ViewModel {
	private MutableLiveData<Logopedista> mLogopedista = new MutableLiveData<>();
	private MutableLiveData<List<Appuntamento>> mListaAppuntamenti = new MutableLiveData<>();
	private MutableLiveData<List<TemplateScenarioGioco>> mListaTemplateScenariGioco = new MutableLiveData<>();
	private MutableLiveData<List<Esercizio>> mListaTemplateEsercizi = new MutableLiveData<>();


	private RegistrazionePazienteGenitoreController mRegistrazionePazienteGenitoreController;
	private ModificaAppuntamentiController mModificaAppuntamentiController;
	private TerapieController mTerapieController;
	private ModificaDataScenariLogopedistaController mModificaDataScenariLogopedistaController;

	public LiveData<Logopedista> getLogopedistaLiveData() {
		return mLogopedista;
	}
	public void setLogopedista(Logopedista logopedista) {
		mLogopedista.setValue(logopedista);
	}

	public LiveData<List<Appuntamento>> getAppuntamentiLiveData() {
		return mListaAppuntamenti;
	}
	public void setAppuntamenti(List<Appuntamento> appuntamenti) {
		this.mListaAppuntamenti.setValue(appuntamenti);
	}

	public LiveData<List<TemplateScenarioGioco>> getTemplateScenariGiocoLiveData() {
		return mListaTemplateScenariGioco;
	}
	public void setTemplateScenariGioco(List<TemplateScenarioGioco> templateScenariGioco) {
		this.mListaTemplateScenariGioco.setValue(templateScenariGioco);
	}

	public LiveData<List<Esercizio>> getTemplateEserciziLiveData() {
		return mListaTemplateEsercizi;
	}
	public void setTemplateEsercizi(List<Esercizio> templateEsercizi) {
		this.mListaTemplateEsercizi.setValue(templateEsercizi);
	}


	public void aggiornaLogopedistaRemoto() {
		Logopedista logopedista = mLogopedista.getValue();

		LogopedistaDAO logopedistaDAO = new LogopedistaDAO();
		logopedistaDAO.update(logopedista);

		Log.d("LogopedistaViewModel.aggiornaLogopedistaRemoto()", "Logopedista aggiornato: " + logopedista.toString());
	}

	/*public void aggiornaAppuntamentiRemoto() {
		List<Appuntamento> appuntamenti = mListaAppuntamenti.getValue();

		AppuntamentoDAO appuntamentoDAO = new AppuntamentoDAO();
		appuntamentoDAO.updateListaAppuntamenti(appuntamenti);

		Log.d("LogopedistaViewModel.aggiornaAppuntamentiRemoto()", "Appuntamenti aggiornati: " + appuntamenti.toString());
	}*/

	public void rimuoviAppuntamentoFromListaAppuntamentiLiveData(String idAppuntamento) {
		List<Appuntamento> appuntamenti = mListaAppuntamenti.getValue();
		for (Appuntamento appuntamento : appuntamenti) {
			if (appuntamento.getIdAppuntamento().equals(idAppuntamento)) {
				appuntamenti.remove(appuntamento);
				break;
			}
		}
		mListaAppuntamenti.setValue(appuntamenti);

		Log.d("LogopedistaViewModel.rimuoviAppuntamentoFromListaAppuntamentiLiveData()", "Appuntamento rimosso: " + idAppuntamento);
	}




	public RegistrazionePazienteGenitoreController getRegistrazionePazienteGenitoreController() {
		if (this.mRegistrazionePazienteGenitoreController == null) {
			this.mRegistrazionePazienteGenitoreController = new RegistrazionePazienteGenitoreController();
		}
		return this.mRegistrazionePazienteGenitoreController;
	}

	public ModificaAppuntamentiController getModificaAppuntamentiController(){
		if (this.mModificaAppuntamentiController == null) {
			this.mModificaAppuntamentiController = new ModificaAppuntamentiController();
		}
		return this.mModificaAppuntamentiController;
	}

	public TerapieController getTerapieController(){
		if(this.mTerapieController == null){
			this.mTerapieController = new TerapieController();
		}
		return this.mTerapieController;
	}

	public ModificaDataScenariLogopedistaController getModificaDataScenariLogopedistaController(){
		if(this.mModificaDataScenariLogopedistaController == null){
			this.mModificaDataScenariLogopedistaController = new ModificaDataScenariLogopedistaController(this);
		}
		return this.mModificaDataScenariLogopedistaController;
	}

}
