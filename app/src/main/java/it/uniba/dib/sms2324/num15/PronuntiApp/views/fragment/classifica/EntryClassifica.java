package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.classifica;

public class EntryClassifica {
    private String username;
    private int punteggio;
    private String urlImg;

    public EntryClassifica(String username, int punteggio, String urlImg) {
        this.username = username;
        this.punteggio = punteggio;
        this.urlImg = urlImg;
    }

    public String getUsername() {
        return username;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public String getUrlImg() {
        return urlImg;
    }

}
