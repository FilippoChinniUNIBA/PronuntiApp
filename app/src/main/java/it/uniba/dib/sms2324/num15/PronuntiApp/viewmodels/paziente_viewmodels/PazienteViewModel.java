package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels;

import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioCoppiaImmaginiController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioDenominazioneImmagineController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioSequenzaParoleController;

public class PazienteViewModel extends ViewModel {
	private Paziente mPaziente;
	private EsercizioDenominazioneImmagineController mEsercizioDenominazioneImmagineController;
	private EsercizioSequenzaParoleController mEsercizioSequenzaParoleController;
	private EsercizioCoppiaImmaginiController mEsercizioCoppiaImmaginiController;


	public Paziente getPaziente() {
		return mPaziente;
	}
	public void setPaziente(Paziente paziente) {
		mPaziente = paziente;
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

}
