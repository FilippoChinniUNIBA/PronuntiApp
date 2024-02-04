package it.uniba.dib.sms2324.num15.PronuntiApp.views.dialogpopup;

import android.content.Context;

public class PermessiErroreDialog extends AbstractPopUpDialog{

    public PermessiErroreDialog(Context context, String descrizione) {
        super(context);
        setTitolo("Permessi necessari");
        setDescrizione(descrizione);
        setConfermaButtonText("Richiedimeli");
        setAnnullaButtonText("Non voglio fornirli");
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
