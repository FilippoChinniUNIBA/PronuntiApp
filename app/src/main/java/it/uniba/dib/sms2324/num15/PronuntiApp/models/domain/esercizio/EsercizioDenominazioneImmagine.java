package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class EsercizioDenominazioneImmagine extends TemplateEsercizioDenominazioneImmagine implements EsercizioEseguibile, Persistente {
    private int countAiuti;

    public EsercizioDenominazioneImmagine(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio, int countAiuti) {
        super(ricompensaCorretto, ricompensaErrato, immagineEsercizio);
        this.countAiuti = countAiuti;
    }

    public int getCountAiuti() {
        return countAiuti;
    }

    public void setCountAiuti(int countAiuti) {
        this.countAiuti = countAiuti;
    }
    @Override
    public Map<String, Object> toMap() {
        return null;
    }
}
