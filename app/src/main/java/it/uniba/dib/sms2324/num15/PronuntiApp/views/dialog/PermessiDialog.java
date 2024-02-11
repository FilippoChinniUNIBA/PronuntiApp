package it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog;

import android.content.Context;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class PermessiDialog extends AbstractPopUpDialog {

	public PermessiDialog(Context context, String descrizione) {
		super(context);
		setTitolo(context.getString(R.string.errorPermissionTitle));
		setDescrizione(descrizione);
		setConfermaButtonText(context.getString(R.string.errorPermissionOption1));
		setAnnullaButtonText(context.getString(R.string.errorPermissionOption2));
	}

}
