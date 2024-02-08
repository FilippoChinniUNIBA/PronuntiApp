package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels;

import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioDenominazioneImmagineController;

public class PazienteViewModel extends ViewModel {
	private Paziente mPaziente;
	private EsercizioDenominazioneImmagineController mEsercizioDenominazioneImmagineController;


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

}
