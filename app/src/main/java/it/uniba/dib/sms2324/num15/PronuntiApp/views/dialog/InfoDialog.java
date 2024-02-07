package it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog;

import android.content.Context;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class InfoDialog extends AbstractPopUpDialog {

	public InfoDialog(Context context, String descrizione, String testoBottoneConferma) {
		super(context);
		setTitolo(context.getString(R.string.infoTitle));
		setDescrizione(descrizione);
		setConfermaButtonText(testoBottoneConferma);
	}


}
