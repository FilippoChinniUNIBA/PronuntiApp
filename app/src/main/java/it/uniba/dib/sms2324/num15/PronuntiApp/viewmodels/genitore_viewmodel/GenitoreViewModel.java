package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.GenitoreDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.LogopedistaDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;

public class GenitoreViewModel extends ViewModel {
	private MutableLiveData<Genitore> mGenitoreLiveData = new MutableLiveData<>();

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

}
