package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.GenitoreDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.LogopedistaDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;

public class GenitoreViewModel extends ViewModel {
	private Genitore mGenitore;

	public Genitore getGenitore() {
		return mGenitore;
	}

	public void setGenitore(Genitore genitore) {
		mGenitore = genitore;
	}

	public void aggiornaGenitoreRemoto() {
		GenitoreDAO genitoreDao = new GenitoreDAO();
		genitoreDao.update(mGenitore);

		Log.d("GenitoreViewModel.aggiornaGenitoreRemoto()", "Genitore aggiornato: " + mGenitore.toString());
	}

}
