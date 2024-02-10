package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBAppuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.AppuntamentoDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;

public class CreazioneAppuntamentoController {

    List<Appuntamento> appuntamento;

    public Appuntamento creazioneAppuntamento(String idLogopedista, String idPaziente, LocalDate data, LocalTime orario, String luogo) {
        Appuntamento appuntamento = new Appuntamento(idLogopedista, idPaziente, data, orario, luogo);
        AppuntamentoDAO appuntamentoDAO = new AppuntamentoDAO();
        appuntamentoDAO.save(appuntamento);

        return appuntamento;
    }

    public CompletableFuture<List<Appuntamento>> retrieveAppuntamenti(String idLogopedista){
        CompletableFuture<List<Appuntamento>> future = new CompletableFuture<>();

        List<Appuntamento> result = new ArrayList<>();

        AppuntamentoDAO appuntamentoDAO = new AppuntamentoDAO();
        appuntamentoDAO.get(CostantiDBAppuntamento.REF_ID_LOGOPEDISTA,idLogopedista).thenAccept(appuntamenti -> {
            for(Appuntamento appuntamento : appuntamenti ){
                result.add(appuntamento);
            }
            future.complete(result);
        });

        return future;
    }



}
