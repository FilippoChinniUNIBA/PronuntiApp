package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.GenitoreDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.PazienteDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.appuntamenti.AppuntamentiGenitoreController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.scenari.ModificaDataScenariGenitoreController;

public class GenitoreViewModel extends ViewModel {
	private MutableLiveData<Genitore> mGenitore = new MutableLiveData<>();
	private MutableLiveData<Paziente> mPaziente = new MutableLiveData<>();
	private MutableLiveData<List<Appuntamento>> mListaAppuntamenti = new MutableLiveData<>();


	private AppuntamentiGenitoreController appuntamentiGenitoreController;
	private ModificaDataScenariGenitoreController modificaDataScenariGenitoreController;


	public LiveData<Genitore> getGenitoreLiveData() {
		return mGenitore;
	}
	public void setGenitore(Genitore genitore) {
		mGenitore.setValue(genitore);
	}

	public LiveData<Paziente> getPazienteLiveData() {
		return mPaziente;
	}
	public void setPaziente(Paziente paziente) {
		mPaziente.setValue(paziente);
	}

	public LiveData<List<Appuntamento>> getAppuntamentiLiveData() {
		return mListaAppuntamenti;
	}
	public void setAppuntamenti(List<Appuntamento> appuntamenti) {
		this.mListaAppuntamenti.setValue(appuntamenti);
	}


	public void aggiornaGenitoreRemoto() {
		Genitore genitore = mGenitore.getValue();

		GenitoreDAO genitoreDAO = new GenitoreDAO();
		genitoreDAO.update(genitore);

		Log.d("GenitoreViewModel.aggiornaGenitoreRemoto()", "Genitore aggiornato: " + genitore.toString());
	}

	public void aggiornaPazienteRemoto() {
		Paziente paziente = mPaziente.getValue();

		PazienteDAO pazienteDAO = new PazienteDAO();
		pazienteDAO.update(paziente);

		Log.d("GenitoreViewModel.aggiornaPazienteRemoto()", "Paziente aggiornato: " + paziente.toString());
	}

	public Terapia getTerapiaByIndiceFromPaziente(int indiceTerapia){
		return mPaziente.getValue().getTerapie().get(indiceTerapia);
	}

	public int getIndiceUltimaTerapia() {

		if (mPaziente.getValue() != null) {
			if (mPaziente.getValue().getTerapie() != null) {
				return (mPaziente.getValue().getTerapie().size()) - 1;
			}
		}
			return -1;
	}

	public AppuntamentiGenitoreController getAppuntamentiControllerGenitore(){
		if (this.appuntamentiGenitoreController == null){
			this.appuntamentiGenitoreController = new AppuntamentiGenitoreController();
		}
		return this.appuntamentiGenitoreController;
	}

	public ModificaDataScenariGenitoreController getModificaDataScenariController(){
		if(this.modificaDataScenariGenitoreController == null){
			this.modificaDataScenariGenitoreController = new ModificaDataScenariGenitoreController(this);
		}
		return this.modificaDataScenariGenitoreController;
	}

}
