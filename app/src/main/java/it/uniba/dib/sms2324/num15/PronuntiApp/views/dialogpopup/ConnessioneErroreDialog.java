package it.uniba.dib.sms2324.num15.PronuntiApp.views.dialogpopup;

import android.content.Context;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class ConnessioneErroreDialog extends AbstractPopUpDialog{

    public ConnessioneErroreDialog(Context context) {
        super(context);
        setTitolo(context.getString(R.string.errorConnectionTitle));
        setDescrizione(context.getString(R.string.errorConnectionDescription));
        setConfermaButtonText(context.getString(R.string.restartApp));
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

