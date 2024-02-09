package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel;

import java.time.LocalDate;
import java.time.LocalTime;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.AppuntamentoDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;

public class CreazioneAppuntementoController {

    public Appuntamento creazioneAppuntamento(String idLogopedista, String idGenitore, String luogo, LocalDate data, LocalTime orario){

        Appuntamento appuntamento = new Appuntamento(idLogopedista,idGenitore,data,orario,luogo);
        AppuntamentoDAO appuntamentoDAO = new AppuntamentoDAO();
        appuntamentoDAO.save(appuntamento);

        return appuntamento;
    }



}
