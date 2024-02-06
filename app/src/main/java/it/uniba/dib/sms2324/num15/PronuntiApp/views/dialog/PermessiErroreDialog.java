package it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog;

import android.content.Context;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class PermessiErroreDialog extends AbstractPopUpDialog {

	public PermessiErroreDialog(Context context, String descrizione) {
		super(context);
		setTitolo(context.getString(R.string.errorPermissionTitle));
		setDescrizione(descrizione);
		setConfermaButtonText(context.getString(R.string.errorPermissionOption1));
		setAnnullaButtonText(context.getString(R.string.errorPermissionOption2));
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
