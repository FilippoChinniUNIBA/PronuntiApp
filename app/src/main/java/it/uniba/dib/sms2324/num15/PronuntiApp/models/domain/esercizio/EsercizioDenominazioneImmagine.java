package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class EsercizioDenominazioneImmagine extends TemplateEsercizioDenominazioneImmagine implements EsercizioEseguibile {
    private int countAiuti;
    private String refIdTemplateEsercizio;
    private String refIdScenarioGioco;

    public EsercizioDenominazioneImmagine(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio, int countAiuti, String refIdTemplateEsercizio, String refIdScenarioGioco) {
        super(idEsercizio, ricompensaCorretto, ricompensaErrato, immagineEsercizio);
        this.countAiuti = countAiuti;
        this.refIdTemplateEsercizio = refIdTemplateEsercizio;
        this.refIdScenarioGioco = refIdScenarioGioco;
    }

    public EsercizioDenominazioneImmagine(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio, int countAiuti, String refIdTemplateEsercizio, String refIdScenarioGioco) {
        super(ricompensaCorretto, ricompensaErrato, immagineEsercizio);
        this.countAiuti = countAiuti;
        this.refIdTemplateEsercizio = refIdTemplateEsercizio;
        this.refIdScenarioGioco = refIdScenarioGioco;
    }

    public EsercizioDenominazioneImmagine(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio, int countAiuti, String refIdTemplateEsercizio) {
        super(ricompensaCorretto, ricompensaErrato, immagineEsercizio);
        this.countAiuti = countAiuti;
        this.refIdTemplateEsercizio = refIdTemplateEsercizio;
    }

    public EsercizioDenominazioneImmagine(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio, int countAiuti) {
        super(ricompensaCorretto, ricompensaErrato, immagineEsercizio);
        this.countAiuti = countAiuti;
    }

    public EsercizioDenominazioneImmagine(Map<String,Object> fromDatabaseMap){
        super(fromDatabaseMap);
        EsercizioDenominazioneImmagine E = (EsercizioDenominazioneImmagine) fromMap(fromDatabaseMap);
        this.refIdTemplateEsercizio = E.refIdTemplateEsercizio;
        this.refIdScenarioGioco = E.refIdScenarioGioco;
        this.idEsercizio = E.idEsercizio;
        this.ricompensaCorretto = E.ricompensaCorretto;
        this.ricompensaErrato = E.ricompensaErrato;
        this.countAiuti = E.countAiuti;
    }

    public int getCountAiuti() {
        return countAiuti;
    }

    public String getRefIdTemplateEsercizio() {
        return refIdTemplateEsercizio;
    }

    public String getRefIdScenarioGioco() {
        return refIdScenarioGioco;
    }

    public void setCountAiuti(int countAiuti) {
        this.countAiuti = countAiuti;
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
        return "EsercizioDenominazioneImmagine{" +
                "idEsercizio='" + idEsercizio + '\'' +
                ", ricompensaCorretto=" + ricompensaCorretto +
                ", ricompensaErrato=" + ricompensaErrato +
                ", countAiuti=" + countAiuti +
                ", refIdTemplateEsercizio='" + refIdTemplateEsercizio + '\'' +
                ", refIdScenarioGioco='" + refIdScenarioGioco + '\'' +
                '}';
    }

}
