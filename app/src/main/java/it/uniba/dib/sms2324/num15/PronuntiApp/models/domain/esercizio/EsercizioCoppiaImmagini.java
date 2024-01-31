package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class EsercizioCoppiaImmagini extends TemplateEsercizioCoppiaImmagini implements EsercizioEseguibile {
    public EsercizioCoppiaImmagini(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizioCorretta, File immagineEsercizioErrata, File audio) {
        super(ricompensaCorretto, ricompensaErrato, immagineEsercizioCorretta, immagineEsercizioErrata, audio);
    }

    @Override
    public Map<String, Object> toMap() {
        return null;
    }

    @Override
    public Esercizio fromMap(Map<String, Object> fromDatabaseMap) {
        return null;
    }
}
