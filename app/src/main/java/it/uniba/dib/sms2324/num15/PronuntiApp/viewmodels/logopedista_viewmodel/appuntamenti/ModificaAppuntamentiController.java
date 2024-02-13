package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.appuntamenti;

import android.util.Log;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.AppuntamentoDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;

public class ModificaAppuntamentiController {

	public CompletableFuture<Appuntamento> creazioneAppuntamento(String idLogopedista, String idPaziente, LocalDate data, LocalTime orario, String luogo) {
		CompletableFuture<Appuntamento> future = new CompletableFuture<>();

		Appuntamento appuntamento = new Appuntamento(idLogopedista, idPaziente, data, orario, luogo);

		AppuntamentoDAO appuntamentoDAO = new AppuntamentoDAO();
		future = appuntamentoDAO.saveWithFuture(appuntamento);

		Log.d("ModificaAppuntamentiController.creazioneAppuntamento()", "Appuntamento aggiunto: " + appuntamento.toString());

		return future;
	}

	public static void eliminazioneAppuntamento(String idAppuntamentoCustom) {
		AppuntamentoDAO appuntamentoDAO = new AppuntamentoDAO();
		appuntamentoDAO.deleteById(idAppuntamentoCustom);

		Log.d("ModificaAppuntamentiController.eliminazioneAppuntamento()", "Appuntamento eliminato: " + idAppuntamentoCustom);
	}

}
