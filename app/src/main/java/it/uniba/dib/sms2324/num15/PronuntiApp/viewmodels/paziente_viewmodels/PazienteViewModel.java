package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.PazienteDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioCoppiaImmaginiController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioDenominazioneImmagineController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioSequenzaParoleController;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.classifica.EntryClassifica;

public class PazienteViewModel extends ViewModel {
	private MutableLiveData<Paziente> mPaziente = new MutableLiveData<>();
	private MutableLiveData<List<Personaggio>> mPersonaggi = new MutableLiveData<>();
	private MutableLiveData<List<EntryClassifica>> mClassifica = new MutableLiveData<>();

	private EsercizioDenominazioneImmagineController mEsercizioDenominazioneImmagineController;
	private EsercizioSequenzaParoleController mEsercizioSequenzaParoleController;
	private EsercizioCoppiaImmaginiController mEsercizioCoppiaImmaginiController;
	private ClassificaPazienteController mClassificaPazienteController;

	public LiveData<Paziente> getPazienteLiveData() {
		return mPaziente;
	}
	public void setPaziente(Paziente paziente) {
		mPaziente.setValue(paziente);
	}

	public LiveData<List<Personaggio>> getPersonaggiLiveData() {
		return mPersonaggi;
	}
	public void setPersonaggi(List<Personaggio> personaggi) {
		this.mPersonaggi.setValue(personaggi);
	}

	public LiveData<List<EntryClassifica>> getClassificaLiveData() {
		return mClassifica;
	}
	public void setClassifica(List<EntryClassifica> classifica) {
		this.mClassifica.setValue(classifica);
	}


	public void aggiornaPazienteRemoto() {
		Paziente paziente = mPaziente.getValue();

		PazienteDAO pazienteDAO = new PazienteDAO();
		pazienteDAO.update(paziente);

		Log.d("PazienteViewModel.aggiornaPazienteRemoto()", "Paziente aggiornato: " + paziente.toString());
	}

	public void aggiornaClassificaRemota() {
		Paziente paziente = mPaziente.getValue();

		//mClassificaPazienteController.aggiornaClassificaRemota(paziente);
	}


	public EsercizioDenominazioneImmagineController getEsercizioDenominazioneImmagineController() {
		if (this.mEsercizioDenominazioneImmagineController == null) {
			this.mEsercizioDenominazioneImmagineController = new EsercizioDenominazioneImmagineController();
		}
		return this.mEsercizioDenominazioneImmagineController;
	}

	public EsercizioSequenzaParoleController getEsercizioSequenzaParoleController() {
		if (this.mEsercizioSequenzaParoleController == null) {
			this.mEsercizioSequenzaParoleController = new EsercizioSequenzaParoleController();
		}
		return this.mEsercizioSequenzaParoleController;
	}

	public EsercizioCoppiaImmaginiController getEsercizioCoppiaImmaginiController() {
		if (this.mEsercizioCoppiaImmaginiController == null) {
			this.mEsercizioCoppiaImmaginiController = new EsercizioCoppiaImmaginiController();
		}
		return this.mEsercizioCoppiaImmaginiController;
	}

	public  ClassificaPazienteController getClassificaPazienteController(){
		if(this.mClassificaPazienteController == null){
			this.mClassificaPazienteController = new ClassificaPazienteController();
		}
		return this.mClassificaPazienteController;
	}

}
