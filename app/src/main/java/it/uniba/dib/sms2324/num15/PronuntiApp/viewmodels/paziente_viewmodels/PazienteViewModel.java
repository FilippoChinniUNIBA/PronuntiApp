package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Iterator;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.LogopedistaDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.PazienteDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.classifica.ClassificaController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioCoppiaImmaginiController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioDenominazioneImmagineController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioSequenzaParoleController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.personaggi.PersonaggiController;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.classifica.EntryClassifica;

public class PazienteViewModel extends ViewModel {
	private MutableLiveData<Paziente> mPaziente = new MutableLiveData<>();
	private MutableLiveData<List<Personaggio>> mListaPersonaggi = new MutableLiveData<>();
	private MutableLiveData<List<EntryClassifica>> mClassifica = new MutableLiveData<>();
	private MutableLiveData<String> mTexturePersonaggioSelezionato = new MutableLiveData<>();


	private EsercizioDenominazioneImmagineController mEsercizioDenominazioneImmagineController;
	private EsercizioSequenzaParoleController mEsercizioSequenzaParoleController;
	private EsercizioCoppiaImmaginiController mEsercizioCoppiaImmaginiController;
	private PersonaggiController mPersonaggiController;
	private ClassificaController mClassificaController;


	public LiveData<Paziente> getPazienteLiveData() {
		return mPaziente;
	}
	public void setPaziente(Paziente paziente) {
		mPaziente.setValue(paziente);
	}

	public LiveData<List<Personaggio>> getListaPersonaggiLiveData() {
		return mListaPersonaggi;
	}
	public void setPersonaggi(List<Personaggio> listaPersonaggi) {
		this.mListaPersonaggi.setValue(listaPersonaggi);
	}

	public LiveData<List<EntryClassifica>> getClassificaLiveData() {
		return mClassifica;
	}
	public void setClassifica(List<EntryClassifica> classifica) {
		this.mClassifica.setValue(classifica);
	}

	public LiveData<String> getTexturePersonaggioSelezionatoLiveData() {
		return mTexturePersonaggioSelezionato;
	}
	public void setTexturePersonaggioSelezionato(String texturePersonaggioSelezionato) {
		this.mTexturePersonaggioSelezionato.setValue(texturePersonaggioSelezionato);
	}


	public void aggiornaPazienteRemoto() {
		Paziente paziente = mPaziente.getValue();

		PazienteDAO pazienteDAO = new PazienteDAO();
		pazienteDAO.update(paziente).thenAccept(pazienteAggiornato -> aggiornaClassificaLiveData());

		Log.d("PazienteViewModel.aggiornaPazienteRemoto()", "Paziente aggiornato: " + paziente.toString());
	}

	public void aggiornaClassificaLiveData() {
		PazienteDAO pazienteDAO = new PazienteDAO();
		pazienteDAO.getLogopedistaByIdPaziente(getPazienteLiveData().getValue().getIdProfilo()).thenAccept(logopedista -> {
			logopedista.aggiornaClassificaPazienti();
			LogopedistaDAO logopedistaDAO = new LogopedistaDAO();
			logopedistaDAO.update(logopedista);

			List<EntryClassifica> classifica = ClassificaController.costruisciClassifica(logopedista.getPazienti(), getListaPersonaggiLiveData().getValue());
			setClassifica(classifica);
		});
	}

	public void aggiornaTexturePersonaggioSelezionatoLiveData() {
		String newTexture = PersonaggiController.getTexturePersonaggioSelezionato(mListaPersonaggi.getValue(), mPaziente.getValue().getPersonaggiSbloccati());
		setTexturePersonaggioSelezionato(newTexture);
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

	public PersonaggiController getPersonaggiController() {
		if(this.mPersonaggiController == null){
			this.mPersonaggiController = new PersonaggiController(this);
		}
		return this.mPersonaggiController;
	}

	public ClassificaController getClassificaController() {
		if(this.mClassificaController == null){
			this.mClassificaController = new ClassificaController();
		}
		return this.mClassificaController;
	}

}
