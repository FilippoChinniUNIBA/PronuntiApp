package it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog;

import android.content.Context;

public class InfoDialog extends AbstractPopUpDialog {

	public InfoDialog(Context context, String descrizione) {
		super(context);
		setTitolo(""); //TODO aggiungi un titolo come string id
		setDescrizione(descrizione);
		setConfermaButtonText("OK"); //TODO aggiungi string id
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
