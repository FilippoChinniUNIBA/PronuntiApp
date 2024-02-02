package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioAbstract;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class EsercizioCoppiaImmagini extends TemplateEsercizioCoppiaImmagini implements EsercizioEseguibile {
    private String refIdTemplateEsercizio;
    private String refIdScenarioGioco;

    public EsercizioCoppiaImmagini(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, File immagineEsercizioCorretta, File immagineEsercizioErrata, File audio, String refIdTemplateEsercizio, String refIdScenarioGioco) {
        super(idEsercizio, ricompensaCorretto, ricompensaErrato, immagineEsercizioCorretta, immagineEsercizioErrata, audio);
        this.refIdTemplateEsercizio = refIdTemplateEsercizio;
        this.refIdScenarioGioco = refIdScenarioGioco;
    }

    public EsercizioCoppiaImmagini(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizioCorretta, File immagineEsercizioErrata, File audio, String refIdTemplateEsercizio, String refIdScenarioGioco) {
        super(ricompensaCorretto, ricompensaErrato, immagineEsercizioCorretta, immagineEsercizioErrata, audio);
        this.refIdTemplateEsercizio = refIdTemplateEsercizio;
        this.refIdScenarioGioco = refIdScenarioGioco;
    }

    public EsercizioCoppiaImmagini(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizioCorretta, File immagineEsercizioErrata, File audio, String refIdTemplateEsercizio) {
        super(ricompensaCorretto, ricompensaErrato, immagineEsercizioCorretta, immagineEsercizioErrata, audio);
        this.refIdTemplateEsercizio = refIdTemplateEsercizio;
    }

    public EsercizioCoppiaImmagini(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizioCorretta, File immagineEsercizioErrata, File audio) {
        super(ricompensaCorretto, ricompensaErrato, immagineEsercizioCorretta, immagineEsercizioErrata, audio);
    }

    public EsercizioCoppiaImmagini(Map<String,Object> fromDatabaseMap){
        super(fromDatabaseMap);
        EsercizioCoppiaImmagini E = (EsercizioCoppiaImmagini) fromMap(fromDatabaseMap);
        this.refIdTemplateEsercizio = E.refIdTemplateEsercizio;
        this.refIdScenarioGioco = E.refIdScenarioGioco;
        this.idEsercizio = E.idEsercizio;
        this.ricompensaCorretto = E.ricompensaCorretto;
        this.ricompensaErrato = E.ricompensaErrato;
    }

    public String getRefIdTemplateEsercizio() {
        return refIdTemplateEsercizio;
    }

    public String getRefIdScenarioGioco() {
        return refIdScenarioGioco;
    }

    public void setRefIdTemplateEsercizio(String refIdTemplateEsercizio) {
        this.refIdTemplateEsercizio = refIdTemplateEsercizio;
    }

    public void setRefIdScenarioGioco(String refIdScenarioGioco) {
        this.refIdScenarioGioco = refIdScenarioGioco;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> entityMap = super.toMap();
        return entityMap;
    }

    @Override
    public Esercizio fromMap(Map<String, Object> fromDatabaseMap) {
        return null;
    }

    @Override
    public String toString() {
        return "EsercizioCoppiaImmagini{" +
                "idEsercizio='" + idEsercizio + '\'' +
                ", ricompensaCorretto=" + ricompensaCorretto +
                ", ricompensaErrato=" + ricompensaErrato +
                ", refIdTemplateEsercizio='" + refIdTemplateEsercizio + '\'' +
                ", refIdScenarioGioco='" + refIdScenarioGioco + '\'' +
                '}';
    }

}
