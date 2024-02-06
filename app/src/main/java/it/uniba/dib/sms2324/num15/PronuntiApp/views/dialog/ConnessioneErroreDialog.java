package it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog;

import android.content.Context;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class ConnessioneErroreDialog extends AbstractPopUpDialog {

	public ConnessioneErroreDialog(Context context) {
		super(context);
		setTitolo(context.getString(R.string.errorConnectionTitle));
		setDescrizione(context.getString(R.string.errorConnectionDescription));
		setConfermaButtonText(context.getString(R.string.restartApp));
	}

}

