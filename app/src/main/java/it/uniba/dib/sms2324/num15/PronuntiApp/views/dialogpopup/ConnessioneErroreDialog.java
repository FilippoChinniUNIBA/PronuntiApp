package it.uniba.dib.sms2324.num15.PronuntiApp.views.dialogpopup;

import android.content.Context;

public class ConnessioneErroreDialog extends AbstractPopUpDialog{

    public ConnessioneErroreDialog(Context context) {
        super(context);
        setTitolo("Connessione Mancante");
        setDescrizione("La connessione Internet non è disponibile. Verifica la tua connessione, l'app verrà riavviata automaticamente.");
        setConfermaButtonText("Riavvia");
    }

    @Override
    protected void onConfermaButtonClicked() {
        this.alertDialog.dismiss(); // Usa il riferimento corretto
    }

    @Override
    protected void onAnnullaButtonClicked() {
        this.alertDialog.dismiss(); // Usa il riferimento corretto
    }
}

