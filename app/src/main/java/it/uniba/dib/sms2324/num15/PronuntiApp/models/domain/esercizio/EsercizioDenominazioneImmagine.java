package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioDenominazioneImmagine;
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
        Map<String, Object> entityMap = super.toMap();
        entityMap.put(CostantiDBEsercizioDenominazioneImmagine.COUNT_AIUTI, this.countAiuti);
        entityMap.put(CostantiDBEsercizioDenominazioneImmagine.ID_TEMPLATE_ESERCIZIO, this.refIdTemplateEsercizio);
        entityMap.put(CostantiDBEsercizioDenominazioneImmagine.ID_SCENARIOGIOCO, this.refIdScenarioGioco);
        return entityMap;
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
