package it.uniba.dib.sms2324.num15.PronuntiApp.views.dialogpopup;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

public class DialogErroreConnessione extends AbstractPopUpDialog{

    public DialogErroreConnessione(Context context) {
        super(context);
        setTitolo("Connessione Mancante");
        setDescrizione("La connessione Internet non è disponibile. Verifica la tua connessione e riprova.");
        setConfermaButtonText("Riprova");
        setAnnullaButtonText("Chiudi l'app");
    }

    @Override
    protected void onConfermaButtonClicked() {
        DialogErroreConnessione.this.alertDialog.dismiss(); // Usa il riferimento corretto
    }

    @Override
    protected void onAnnullaButtonClicked() {
        DialogErroreConnessione.this.alertDialog.dismiss(); // Usa il riferimento corretto
    }
}

