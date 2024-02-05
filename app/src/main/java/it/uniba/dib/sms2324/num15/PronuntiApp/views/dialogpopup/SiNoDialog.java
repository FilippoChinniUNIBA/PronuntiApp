package it.uniba.dib.sms2324.num15.PronuntiApp.views.dialogpopup;

import android.content.Context;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class SiNoDialog extends AbstractPopUpDialog{
        public SiNoDialog(Context context,String titolo, String descrizione) {
        super(context);
        setTitolo(titolo);
        setDescrizione(descrizione);
        setConfermaButtonText(context.getString(R.string.confirm));
        setAnnullaButtonText(context.getString(R.string.cancel));
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

