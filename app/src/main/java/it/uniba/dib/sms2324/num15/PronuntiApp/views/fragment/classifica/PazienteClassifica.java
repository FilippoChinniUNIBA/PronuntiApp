package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.classifica;

public class PazienteClassifica {

    private String username;
    private int punteggio;
    private String url_img;

    public PazienteClassifica(String username, int punteggio, String url_img) {
        this.username = username;
        this.punteggio = punteggio;
        this.url_img = url_img;
    }

    public String getUsername() {
        return username;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public String getUrl_img() {
        return url_img;
    }


}
