package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioAbstract;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class TemplateEsercizioCoppiaImmagini extends AbstractEsercizio implements Esercizio {
    private File immagineEsercizioCorretta;
    private File immagineEsercizioErrata;
    private File audio;

    public TemplateEsercizioCoppiaImmagini(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizioCorretta, File immagineEsercizioErrata, File audio) {
        super(ricompensaCorretto, ricompensaErrato);
        this.immagineEsercizioCorretta = immagineEsercizioCorretta;
        this.immagineEsercizioErrata = immagineEsercizioErrata;
        this.audio = audio;
    }

    public TemplateEsercizioCoppiaImmagini(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, File immagineEsercizioCorretta, File immagineEsercizioErrata, File audio) {
        super(idEsercizio, ricompensaCorretto, ricompensaErrato);
        this.immagineEsercizioCorretta = immagineEsercizioCorretta;
        this.immagineEsercizioErrata = immagineEsercizioErrata;
        this.audio = audio;
    }

    public File getImmagineEsercizioCorretta() {
        return immagineEsercizioCorretta;
    }

    public File getImmagineEsercizioErrata() {
        return immagineEsercizioErrata;
    }

    public File getAudio() {
        return audio;
    }

    public void setImmagineEsercizioCorretta(File immagineEsercizioCorretta) {
        this.immagineEsercizioCorretta = immagineEsercizioCorretta;
    }

    public void setImmagineEsercizioErrata(File immagineEsercizioErrata) {
        this.immagineEsercizioErrata = immagineEsercizioErrata;
    }

    public void setAudio(File audio) {
        this.audio = audio;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> entityMap = super.toMap();
        //entityMap.put(CostantiDBTemplateEsercizioCoppiaImmagini.ID_TEMPLATE_ESERCIZIO, this.idEsercizio);
        entityMap.put(CostantiDBTemplateEsercizioCoppiaImmagini.IMMAGINE_ESERCIZIO_CORRETTA, this.immagineEsercizioCorretta.getPath());
        entityMap.put(CostantiDBTemplateEsercizioCoppiaImmagini.IMMAGINE_ESERCIZIO_ERRATA, this.immagineEsercizioErrata.getPath());
        entityMap.put(CostantiDBTemplateEsercizioCoppiaImmagini.AUDIO_ESERCIZIO, this.audio.getPath());
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
                ", audio=" + audio +
                '}';
    }

}
