package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.init_controllers;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.PazienteDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.personaggio.PersonaggioDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.ClassificaController;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.PazienteActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.classifica.EntryClassifica;

public class InitPaziente {

	public static CompletableFuture<Intent> buildIntentPaziente(Paziente paziente, Context context) {
		CompletableFuture<Intent> future = new CompletableFuture<>();

		Intent intent = new Intent(context, PazienteActivity.class);
		intent.putExtra("mPaziente", paziente);

		extraMPersonaggi().thenAccept(personaggi -> {
			intent.putExtra("mPersonaggi", new ArrayList<>(personaggi));

			extraMClassifica(paziente.getIdProfilo(), personaggi).thenAccept(classifica -> {
				intent.putExtra("mClassifica",  new ArrayList<>(classifica));
				future.complete(intent);
			});
		});

		return future;
	}

	private static CompletableFuture<List<Personaggio>> extraMPersonaggi() {
		CompletableFuture<List<Personaggio>> future = new CompletableFuture<>();

		PersonaggioDAO personaggioDAO = new PersonaggioDAO();
		personaggioDAO.getAll().thenAccept(future::complete);

		return future;
	}

	private static CompletableFuture<List<EntryClassifica>> extraMClassifica(String idPaziente, List<Personaggio> personaggi) {
		CompletableFuture<List<EntryClassifica>> future = new CompletableFuture<>();

		PazienteDAO pazienteDAO = new PazienteDAO();
		pazienteDAO.getLogopedistaByIdPaziente(idPaziente).thenAccept(logopedista -> {
			List<EntryClassifica> classifica = ClassificaController.costruisciClassifica(logopedista.getPazienti(), personaggi);
			future.complete(classifica);
		});

		return future;
	}

}
