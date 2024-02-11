package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.LogopedistaDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.PazienteDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioCoppiaImmaginiController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioDenominazioneImmagineController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioSequenzaParoleController;

public class PazienteViewModel extends ViewModel {
	private MutableLiveData<Paziente> mPazienteLiveData = new MutableLiveData<>();

	private EsercizioDenominazioneImmagineController mEsercizioDenominazioneImmagineController;
	private EsercizioSequenzaParoleController mEsercizioSequenzaParoleController;
	private EsercizioCoppiaImmaginiController mEsercizioCoppiaImmaginiController;
	private ClassificaPazienteController mClassificaPazienteController;

	public LiveData<Paziente> getPazienteLiveData() {
		return mPazienteLiveData;
	}
	public void setPaziente(Paziente paziente) {
		mPazienteLiveData.setValue(paziente);
	}


	public void aggiornaPazienteRemoto() {
		Paziente paziente = mPazienteLiveData.getValue();

		PazienteDAO pazienteDAO = new PazienteDAO();
		pazienteDAO.update(paziente);

		Log.d("PazienteViewModel.aggiornaPazienteRemoto()", "Paziente aggiornato: " + paziente.toString());
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
