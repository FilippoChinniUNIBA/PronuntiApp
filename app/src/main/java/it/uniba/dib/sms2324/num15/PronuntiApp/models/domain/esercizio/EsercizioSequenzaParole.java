package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;

public class EsercizioSequenzaParole extends TemplateEsercizioSequenzaParole implements Esercizio{
    public EsercizioSequenzaParole(int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, String parola1, String parola2, String parola3) {
        super(ricompensaCorretto, ricompensaErrato, audioEsercizio, parola1, parola2, parola3);
    }
}
