package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;

public class TemplateEsercizioDenominazioneImmagini extends AbstractEsercizio {

   private File immagineEsercizio;

    public TemplateEsercizioDenominazioneImmagini(File immagineEsercizio) {
        this.immagineEsercizio = immagineEsercizio;
    }

    public File getImmagineEsercizio() {
        return immagineEsercizio;
    }

    public void setImmagineEsercizio(File immagineEsercizio) {
        this.immagineEsercizio = immagineEsercizio;
    }
}
