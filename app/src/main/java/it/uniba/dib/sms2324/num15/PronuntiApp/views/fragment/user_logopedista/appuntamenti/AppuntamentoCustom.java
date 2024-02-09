package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.appuntamenti;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppuntamentoCustom {

    private String nomePaziente;
    private String cognomePaziente;
    private String luogoAppuntamento;
    private LocalDate dataAppuntamento;
    private LocalTime oraAppuntamento;

    public AppuntamentoCustom(String nomePaziente, String cognomePaziente, String luogoAppuntamento, LocalDate dataAppuntamento, LocalTime oraAppuntamento) {
        this.nomePaziente = nomePaziente;
        this.cognomePaziente = cognomePaziente;
        this.luogoAppuntamento = luogoAppuntamento;
        this.dataAppuntamento = dataAppuntamento;
        this.oraAppuntamento = oraAppuntamento;
    }

    public String getNomePaziente() {
        return nomePaziente;
    }

    public String getCognomePaziente() {
        return cognomePaziente;
    }

    public String getLuogoAppuntamento() {
        return luogoAppuntamento;
    }

    public LocalDate getDataAppuntamento() {
        return dataAppuntamento;
    }

    public LocalTime getOraAppuntamento() {
        return oraAppuntamento;
    }




    // Getters e setters per tutti gli attributi

    @Override
    public String toString() {
        return "Appuntamento{" +
                "nomePaziente='" + nomePaziente + '\'' +
                ", cognomePaziente='" + cognomePaziente + '\'' +
                ", luogoAppuntamento='" + luogoAppuntamento + '\'' +
                ", dataAppuntamento=" + dataAppuntamento +
                ", oraAppuntamento=" + oraAppuntamento +
                '}';
    }
}
