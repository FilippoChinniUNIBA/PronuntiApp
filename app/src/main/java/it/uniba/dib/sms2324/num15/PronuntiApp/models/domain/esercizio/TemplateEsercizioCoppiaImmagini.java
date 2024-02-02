package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioCoppiaImmagini;

public class TemplateEsercizioCoppiaImmagini extends AbstractEsercizio implements Esercizio {
    private File audioEsercizio;
    private File immagineEsercizioCorretta;
    private File immagineEsercizioErrata;

    public TemplateEsercizioCoppiaImmagini(int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, File immagineEsercizioCorretta, File immagineEsercizioErrata) {
        super(ricompensaCorretto, ricompensaErrato);
        this.audioEsercizio = audioEsercizio;
        this.immagineEsercizioCorretta = immagineEsercizioCorretta;
        this.immagineEsercizioErrata = immagineEsercizioErrata;
    }

    public TemplateEsercizioCoppiaImmagini(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, File immagineEsercizioCorretta, File immagineEsercizioErrata) {
        super(idEsercizio, ricompensaCorretto, ricompensaErrato);
        this.audioEsercizio = audioEsercizio;
        this.immagineEsercizioCorretta = immagineEsercizioCorretta;
        this.immagineEsercizioErrata = immagineEsercizioErrata;
    }

    public TemplateEsercizioCoppiaImmagini(Map<String,Object> fromDatabaseMap){
        super(fromDatabaseMap);
        TemplateEsercizioCoppiaImmagini T = (TemplateEsercizioCoppiaImmagini) fromMap(fromDatabaseMap);
        this.audioEsercizio = T.audioEsercizio;
        this.immagineEsercizioCorretta = T.immagineEsercizioCorretta;
        this.immagineEsercizioErrata = T.immagineEsercizioErrata;
    }

    public File getImmagineEsercizioCorretta() {
        return immagineEsercizioCorretta;
    }

    public File getImmagineEsercizioErrata() {
        return immagineEsercizioErrata;
    }

    public File getAudioEsercizio() {
        return audioEsercizio;
    }

    public void setImmagineEsercizioCorretta(File immagineEsercizioCorretta) {
        this.immagineEsercizioCorretta = immagineEsercizioCorretta;
    }

    public void setImmagineEsercizioErrata(File immagineEsercizioErrata) {
        this.immagineEsercizioErrata = immagineEsercizioErrata;
    }

    public void setAudioEsercizio(File audioEsercizio) {
        this.audioEsercizio = audioEsercizio;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> entityMap = super.toMap();
        //entityMap.put(CostantiDBTemplateEsercizioCoppiaImmagini.ID_TEMPLATE_ESERCIZIO, this.idEsercizio);
        entityMap.put(CostantiDBTemplateEsercizioCoppiaImmagini.IMMAGINE_ESERCIZIO_CORRETTA, this.immagineEsercizioCorretta.getPath());
        entityMap.put(CostantiDBTemplateEsercizioCoppiaImmagini.IMMAGINE_ESERCIZIO_ERRATA, this.immagineEsercizioErrata.getPath());
        entityMap.put(CostantiDBTemplateEsercizioCoppiaImmagini.AUDIO_ESERCIZIO, this.audioEsercizio.getPath());
        return entityMap;
    }

    @Override
    public Esercizio fromMap(Map<String, Object> fromDatabaseMap) {
        return null;
    }

    @Override
    public String toString() {
        return "TemplateEsercizioCoppiaImmagini{" +
                "idEsercizio='" + idEsercizio + '\'' +
                ", ricompensaCorretto=" + ricompensaCorretto +
                ", ricompensaErrato=" + ricompensaErrato +
                ", immagineEsercizioCorretta=" + immagineEsercizioCorretta +
                ", immagineEsercizioErrata=" + immagineEsercizioErrata +
                ", audioEsercizio=" + audioEsercizio +
                '}';
    }

}
