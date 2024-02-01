package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class TemplateEsercizioDenominazioneImmagine extends AbstractEsercizio implements Esercizio {

   private File immagineEsercizio;

    public TemplateEsercizioDenominazioneImmagine(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio) {
        super(ricompensaCorretto, ricompensaErrato);
        this.immagineEsercizio = immagineEsercizio;
    }

    public File getImmagineEsercizio() {
        return immagineEsercizio;
    }

    public void setImmagineEsercizio(File immagineEsercizio) {
        this.immagineEsercizio = immagineEsercizio;
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
        return "TemplateEsercizioDenominazioneImmagine{" +
                "immagineEsercizio=" + immagineEsercizio +
                ", idEsercizio='" + idEsercizio + '\'' +
                ", ricompensaCorretto=" + ricompensaCorretto +
                ", ricompensaErrato=" + ricompensaErrato +
                '}';
    }
}
