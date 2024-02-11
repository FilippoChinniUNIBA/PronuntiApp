package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBAppuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.AppuntamentoDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.GenitoreDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;

public class AppuntamentiControllerGenitore {
    public CompletableFuture<List<Appuntamento>> retrieveAppuntamentiGenitore(String idGenitore) {

        GenitoreDAO genitoreDAO = new GenitoreDAO();
        AppuntamentoDAO appuntamentoDAO = new AppuntamentoDAO();

        CompletableFuture<List<Appuntamento>> future = new CompletableFuture<>();

        List<Appuntamento> result = new ArrayList<>();

        genitoreDAO.getPazienteByIdGenitore(idGenitore).thenAccept(paziente -> {
            Log.e("AppuntamentoLogopedistaFragment", "paziente"+paziente.toString());

            appuntamentoDAO.get(CostantiDBAppuntamento.REF_ID_PAZIENTE, paziente.getIdProfilo()).thenAccept(appuntamenti -> {
                        for (Appuntamento appuntamento : appuntamenti) {
                            result.add(appuntamento);
                        }
                        future.complete(result);
                    });

                });

        return future;

    }


}