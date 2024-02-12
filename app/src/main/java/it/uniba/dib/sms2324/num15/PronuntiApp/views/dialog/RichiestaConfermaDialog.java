package it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class RichiestaConfermaDialog extends AbstractPopUpDialog {

	public RichiestaConfermaDialog(Context context, String titolo, String descrizione) {
		super(context);
		setTitolo(titolo);
		setDescrizione(descrizione);
		setConfermaButtonText(context.getString(R.string.confirm));
		setAnnullaButtonText(context.getString(R.string.cancel));
		alertDialog = create();
		alertDialog.setCanceledOnTouchOutside(false);
	}

	@Override
	public AlertDialog show() {
		if (alertDialog != null) {
			alertDialog.show();
		}
		return alertDialog;
	}

}

