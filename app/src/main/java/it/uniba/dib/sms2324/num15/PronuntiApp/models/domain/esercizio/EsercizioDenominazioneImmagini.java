package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;

public class EsercizioDenominazioneImmagini extends TemplateEsercizioDenominazioneImmagini implements Esercizio{
    private int countAiuti;

    public EsercizioDenominazioneImmagini(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio, int countAiuti) {
        super(ricompensaCorretto, ricompensaErrato, immagineEsercizio);
        this.countAiuti = countAiuti;
    }

    public int getCountAiuti() {
        return countAiuti;
    }

    public void setCountAiuti(int countAiuti) {
        this.countAiuti = countAiuti;
    }
}
