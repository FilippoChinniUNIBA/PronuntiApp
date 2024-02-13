package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.init_controllers;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBAppuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.esercizio.TemplateEsercizioDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.AppuntamentoDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.scenariogioco.TemplateScenarioGiocoDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.Esercizio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.TemplateScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.LogopedistaActivity;

public class InitLogopedista {

	public static CompletableFuture<Intent> buildIntentLogopedista(Logopedista logopedista, Context context) {
		CompletableFuture<Intent> future = new CompletableFuture<>();

		Intent intent = new Intent(context, LogopedistaActivity.class);
		intent.putExtra("mLogopedista", logopedista);

		CompletableFuture<List<Appuntamento>> futureAppuntamenti = extraMAppuntamenti(logopedista.getIdProfilo());
		CompletableFuture<List<TemplateScenarioGioco>> futureScenariGioco = extraMTemplateScenariGioco();
		CompletableFuture<List<Esercizio>> futureEsercizi = extraMTemplateEsercizi();

		CompletableFuture.allOf(futureAppuntamenti, futureScenariGioco, futureEsercizi).thenRun(() -> {
			intent.putExtra("mAppuntamenti", new ArrayList<>(futureAppuntamenti.join()));
			intent.putExtra("mTemplateScenariGioco", new ArrayList<>(futureScenariGioco.join()));
			intent.putExtra("mTemplateEsercizi", new ArrayList<>(futureEsercizi.join()));
			future.complete(intent);
		});

		return future;
	}

	private static CompletableFuture<List<Appuntamento>> extraMAppuntamenti(String idLogopedista) {
		CompletableFuture<List<Appuntamento>> future = new CompletableFuture<>();

		AppuntamentoDAO appuntamentoDAO = new AppuntamentoDAO();
		appuntamentoDAO.get(CostantiDBAppuntamento.REF_ID_LOGOPEDISTA, idLogopedista).thenAccept(future::complete);

		return future;
	}

	private static CompletableFuture<List<TemplateScenarioGioco>> extraMTemplateScenariGioco() {
		CompletableFuture<List<TemplateScenarioGioco>> future = new CompletableFuture<>();

		TemplateScenarioGiocoDAO templateScenarioGiocoDAO = new TemplateScenarioGiocoDAO();
		templateScenarioGiocoDAO.getAll().thenAccept(future::complete);

		return future;
	}

	private static CompletableFuture<List<Esercizio>> extraMTemplateEsercizi() {
		CompletableFuture<List<Esercizio>> future = new CompletableFuture<>();

		TemplateEsercizioDAO templateEsercizioDAO = new TemplateEsercizioDAO();
		templateEsercizioDAO.getAll().thenAccept(future::complete);

		return future;
	}

}
