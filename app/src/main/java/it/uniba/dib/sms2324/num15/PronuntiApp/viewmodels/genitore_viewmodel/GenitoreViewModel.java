package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.GenitoreDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.LogopedistaDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;

public class GenitoreViewModel extends ViewModel {
	private MutableLiveData<Genitore> mGenitoreLiveData = new MutableLiveData<>();
	private AppuntamentiControllerGenitore appuntamentiControllerGenitore;

	public LiveData<Genitore> getGenitoreLiveData() {
		return mGenitoreLiveData;
	}

	public void setGenitore(Genitore genitore) {
		mGenitoreLiveData.setValue(genitore);
	}

	public void aggiornaGenitoreRemoto() {
		Genitore genitore = mGenitoreLiveData.getValue();

		GenitoreDAO genitoreDAO = new GenitoreDAO();
		genitoreDAO.update(genitore);

		Log.d("GenitoreViewModel.aggiornaGenitoreRemoto()", "Genitore aggiornato: " + genitore.toString());
	}

	public CompletableFuture<Paziente> getPazienteGenitore(String idGenitore){
		GenitoreDAO genitoreDAO = new GenitoreDAO();

		CompletableFuture<Paziente> future = new CompletableFuture<>();
		genitoreDAO.getPazienteByIdGenitore(idGenitore).thenAccept(paziente -> {
			future.complete(paziente);
		});
		return future;
	}

	public AppuntamentiControllerGenitore getAppuntamentiControllerGenitore(){
		if (this.appuntamentiControllerGenitore == null){
			this.appuntamentiControllerGenitore = new AppuntamentiControllerGenitore();
		}
		return this.appuntamentiControllerGenitore;
	}

}
