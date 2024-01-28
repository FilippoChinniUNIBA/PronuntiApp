package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;

public class EsercizioCoppiaImmagini extends TemplateEsercizioCoppiaImmagini implements EsercizioEseguibile{
    public EsercizioCoppiaImmagini(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizioCorretta, File immagineEsercizioErrata, File audio) {
        super(ricompensaCorretto, ricompensaErrato, immagineEsercizioCorretta, immagineEsercizioErrata, audio);
    }
}
