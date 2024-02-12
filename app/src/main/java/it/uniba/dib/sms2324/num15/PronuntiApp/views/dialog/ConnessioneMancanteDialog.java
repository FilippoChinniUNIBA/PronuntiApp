package it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog;

import android.content.Context;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class ConnessioneMancanteDialog extends AbstractPopUpDialog {

	public ConnessioneMancanteDialog(Context context) {
		super(context);
		setTitolo(context.getString(R.string.errorConnectionTitle));
		setDescrizione(context.getString(R.string.errorConnectionDescription));
		setConfermaButtonText(context.getString(R.string.restartApp));

		alertDialog.setCanceledOnTouchOutside(false);
	}

}

