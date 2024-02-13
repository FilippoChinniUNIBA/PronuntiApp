package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.init_controllers;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBAppuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.AppuntamentoDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.GenitoreDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.GenitoreActivity;

public class InitGenitore {

	public static CompletableFuture<Intent> buildIntentGenitore(Genitore genitore, Context context) {
		CompletableFuture<Intent> future = new CompletableFuture<>();

		Intent intent = new Intent(context, GenitoreActivity.class);
		intent.putExtra("mGenitore", genitore);

		extraMPaziente(genitore.getIdProfilo()).thenAccept(paziente -> {
			intent.putExtra("mPaziente", paziente);

			extraMAppuntamenti(paziente.getIdProfilo()).thenAccept(appuntamenti -> {
				intent.putExtra("mAppuntamenti", new ArrayList<>(appuntamenti));
				future.complete(intent);
			});
		});

		return future;
	}

	private static CompletableFuture<Paziente> extraMPaziente(String idGenitore) {
		CompletableFuture<Paziente> future = new CompletableFuture<>();

		GenitoreDAO genitoreDAO = new GenitoreDAO();
		genitoreDAO.getPazienteByIdGenitore(idGenitore).thenAccept(future::complete);

		return future;
	}

	private static CompletableFuture<List<Appuntamento>> extraMAppuntamenti(String idPaziente) {
		CompletableFuture<List<Appuntamento>> future = new CompletableFuture<>();

		AppuntamentoDAO appuntamentoDAO = new AppuntamentoDAO();
		appuntamentoDAO.get(CostantiDBAppuntamento.REF_ID_PAZIENTE, idPaziente).thenAccept(future::complete);

		return future;
	}

}
