package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.appuntamenti;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBAppuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.AppuntamentoDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;

public class CreazioneAppuntamentoController {

    public Appuntamento creazioneAppuntamento(String idLogopedista, String idPaziente, LocalDate data, LocalTime orario, String luogo) {
		Appuntamento appuntamento = new Appuntamento(idLogopedista, idPaziente, data, orario, luogo);

		AppuntamentoDAO appuntamentoDAO = new AppuntamentoDAO();
        appuntamentoDAO.save(appuntamento);

        return appuntamento;
    }

    static public void eliminazioneAppuntamento(String idAppuntamentoCustom){
        AppuntamentoDAO appuntamentoDAO = new AppuntamentoDAO();
        appuntamentoDAO.deleteById(idAppuntamentoCustom);
    }

}
