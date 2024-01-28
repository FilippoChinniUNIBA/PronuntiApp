package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class TemplateEsercizioCoppiaImmagini extends AbstractEsercizio implements Esercizio, Persistente {
    private File immagineEsercizioCorretta;
    private File immagineEsercizioErrata;
    private File audio;

    public TemplateEsercizioCoppiaImmagini(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizioCorretta, File immagineEsercizioErrata, File audio) {
        super(ricompensaCorretto, ricompensaErrato);
        this.immagineEsercizioCorretta = immagineEsercizioCorretta;
        this.immagineEsercizioErrata = immagineEsercizioErrata;
        this.audio = audio;
    }

    public File getImmagineEsercizioCorretta() {
        return immagineEsercizioCorretta;
    }

    public void setImmagineEsercizioCorretta(File immagineEsercizioCorretta) {
        this.immagineEsercizioCorretta = immagineEsercizioCorretta;
    }

    public File getImmagineEsercizioErrata() {
        return immagineEsercizioErrata;
    }

    public void setImmagineEsercizioErrata(File immagineEsercizioErrata) {
        this.immagineEsercizioErrata = immagineEsercizioErrata;
    }

    public File getAudio() {
        return audio;
    }

    public void setAudio(File audio) {
        this.audio = audio;
    }


    @Override
    public Map<String, Object> toMap() {
        return null;
    }
}
