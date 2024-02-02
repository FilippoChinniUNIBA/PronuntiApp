package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class EsercizioSequenzaParole extends TemplateEsercizioSequenzaParole implements EsercizioEseguibile {
    private String refIdTemplateEsercizio;
    private String refIdScenarioGioco;

    public EsercizioSequenzaParole(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, String parola1, String parola2, String parola3, String refIdTemplateEsercizio, String refIdScenarioGioco) {
        super(idEsercizio, ricompensaCorretto, ricompensaErrato, audioEsercizio, parola1, parola2, parola3);
        this.refIdTemplateEsercizio = refIdTemplateEsercizio;
        this.refIdScenarioGioco = refIdScenarioGioco;
    }

    public EsercizioSequenzaParole(int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, String parola1, String parola2, String parola3, String refIdTemplateEsercizio, String refIdScenarioGioco) {
        super(ricompensaCorretto, ricompensaErrato, audioEsercizio, parola1, parola2, parola3);
        this.refIdTemplateEsercizio = refIdTemplateEsercizio;
        this.refIdScenarioGioco = refIdScenarioGioco;
    }

    public EsercizioSequenzaParole(int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, String parola1, String parola2, String parola3, String refIdTemplateEsercizio) {
        super(ricompensaCorretto, ricompensaErrato, audioEsercizio, parola1, parola2, parola3);
        this.refIdTemplateEsercizio = refIdTemplateEsercizio;
    }

    public EsercizioSequenzaParole(int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, String parola1, String parola2, String parola3) {
        super(ricompensaCorretto, ricompensaErrato, audioEsercizio, parola1, parola2, parola3);
    }

    public EsercizioSequenzaParole(Map<String,Object> fromDatabaseMap){
        super(fromDatabaseMap);
        EsercizioSequenzaParole E = (EsercizioSequenzaParole) fromMap(fromDatabaseMap);
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
        return null;
    }

    @Override
    public Esercizio fromMap(Map<String, Object> fromDatabaseMap) {
        return null;
    }

    @Override
    public String toString() {
        return "EsercizioSequenzaParole{" +
                "idEsercizio='" + idEsercizio + '\'' +
                ", ricompensaCorretto=" + ricompensaCorretto +
                ", ricompensaErrato=" + ricompensaErrato +
                ", refIdTemplateEsercizio='" + refIdTemplateEsercizio + '\'' +
                ", refIdScenarioGioco='" + refIdScenarioGioco + '\'' +
                '}';
    }

}
