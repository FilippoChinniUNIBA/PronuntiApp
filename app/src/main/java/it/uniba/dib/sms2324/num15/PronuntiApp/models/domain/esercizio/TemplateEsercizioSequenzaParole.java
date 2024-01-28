package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;

public class TemplateEsercizioSequenzaParole extends AbstractEsercizio implements Esercizio{

    private File audioEsercizio;
    private String parola1,parola2,parola3;

    public TemplateEsercizioSequenzaParole(int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, String parola1, String parola2, String parola3) {
        super(ricompensaCorretto, ricompensaErrato);
        this.audioEsercizio = audioEsercizio;
        this.parola1 = parola1;
        this.parola2 = parola2;
        this.parola3 = parola3;
    }

    public TemplateEsercizioSequenzaParole(int ricompensaCorretto, int ricompensaErrato) {
        super(ricompensaCorretto, ricompensaErrato);
    }

    public File getAudioEsercizio() {
        return audioEsercizio;
    }

    public void setAudioEsercizio(File audioEsercizio) {
        this.audioEsercizio = audioEsercizio;
    }

    public String getParola1() {
        return parola1;
    }

    public void setParola1(String parola1) {
        this.parola1 = parola1;
    }

    public String getParola2() {
        return parola2;
    }

    public void setParola2(String parola2) {
        this.parola2 = parola2;
    }

    public String getParola3() {
        return parola3;
    }

    public void setParola3(String parola3) {
        this.parola3 = parola3;
    }
}
