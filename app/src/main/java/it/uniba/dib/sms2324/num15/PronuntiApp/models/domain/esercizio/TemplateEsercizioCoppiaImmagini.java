package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;

public class TemplateEsercizioCoppiaImmagini extends AbstractEsercizio {
    private File immagineEsercizioCorretta;
    private File immagineEsercizioErrata;
    private File audio;

    public TemplateEsercizioCoppiaImmagini(File immagineEsercizioCorretta, File immagineEsercizioErrata, File audio) {
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

    public void setimmagineEsercizioErrata(File immagineEsercizioErrata) {
        this.immagineEsercizioErrata = immagineEsercizioErrata;
    }

    public File getAudio() {
        return audio;
    }

    public void setAudio(File audio) {
        this.audio = audio;
    }


}
