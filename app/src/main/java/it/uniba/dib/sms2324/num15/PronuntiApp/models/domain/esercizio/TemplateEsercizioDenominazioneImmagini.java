package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;

public class TemplateEsercizioDenominazioneImmagini extends AbstractEsercizio {

   private File immagineEsercizio;

    public TemplateEsercizioDenominazioneImmagini(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio) {
        super(ricompensaCorretto, ricompensaErrato);
        this.immagineEsercizio = immagineEsercizio;
    }

    public File getImmagineEsercizio() {
        return immagineEsercizio;
    }

    public void setImmagineEsercizio(File immagineEsercizio) {
        this.immagineEsercizio = immagineEsercizio;
    }
}
