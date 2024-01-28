package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class EsercizioSequenzaParole extends TemplateEsercizioSequenzaParole implements EsercizioEseguibile, Persistente {
    public EsercizioSequenzaParole(int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, String parola1, String parola2, String parola3) {
        super(ricompensaCorretto, ricompensaErrato, audioEsercizio, parola1, parola2, parola3);
    }
    @Override
    public Map<String, Object> toMap() {
        return null;
    }
}
