package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;

public class EsercizioDenominazioneImmagini extends TemplateEsercizioDenominazioneImmagini {
    private int countAiuti;
    public EsercizioDenominazioneImmagini(File immagineEsercizio) {
        super(immagineEsercizio);
    }

    public int getCountAiuti() {
        return countAiuti;
    }

    public void setCountAiuti(int countAiuti) {
        this.countAiuti = countAiuti;
    }
}
