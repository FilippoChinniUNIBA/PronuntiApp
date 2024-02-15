package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.terapie;

import java.time.LocalDate;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.terapia.TerapiaDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;

public class TerapieController {


//TODO come effettuare l'aggiunta della terapia capire cosa restituire dopo
/*
    public Terapia aggiungiTerapia(String idPaziente,String idTerapia, LocalDate dataInizio, LocalDate datafine){

        TerapiaDAO terapiaDAO = new TerapiaDAO();
        Terapia terapia = new Terapia(idTerapia,dataInizio,datafine);
        //terapiaDAO.save(terapia);
        return terapia;
    }
 */

    public int verificaCorrettezzaCampiTerapia(String dataInizio, String dataFine){

        if(dataInizio == null || dataFine == null || dataInizio.isEmpty() || dataFine.isEmpty()){
            return 1;
        }

        try {
            LocalDate.parse(dataInizio);
            if(LocalDate.parse(dataInizio).isAfter(LocalDate.now()))   return 2;   //Data Passata
        } catch (Exception e) {
            return 1;   //Data non valida
        }

        try {
            LocalDate.parse(dataInizio);
            LocalDate.parse(dataFine);
            if(LocalDate.parse(dataInizio).isAfter(LocalDate.parse(dataFine)))   return 3;   //Ordine Date non valido
        } catch (Exception e) {
            return 1;   //Date non valide
        }

        return 0;
    }

}
