package it.uniba.dib.sms2324.num15.PronuntiApp.views.dialogpopup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public abstract class AbstractPopUpDialog extends AlertDialog.Builder {
    protected Context context;
    protected AlertDialog alertDialog;
    private TextView textViewTitoloPopUp;
    private TextView textViewDescrizione;
    private Button buttonConferma;
    private Button buttonAnnulla;

    // Layout IDs
    private static final int LAYOUT_ID = R.layout.dialog_pop_up;

    public AbstractPopUpDialog(@NonNull Context context) {
        super(context, R.style.CustomDialogTheme);
        this.context = context;

        // Inizializzazione della vista del dialogo
        View view = LayoutInflater.from(context).inflate(LAYOUT_ID, null);
        setView(view);

        // Riferimenti agli elementi di layout
        textViewTitoloPopUp = view.findViewById(R.id.textViewTitoloPopUp);
        textViewDescrizione = view.findViewById(R.id.textViewDescrizionePopUp);
        buttonConferma = view.findViewById(R.id.buttonConfermaPopUp);
        buttonAnnulla = view.findViewById(R.id.buttonAnnullaPopUp);
    }

    @Override
    public AlertDialog show() {
        if (alertDialog == null) {
            alertDialog = create();
            buttonConferma.setOnClickListener(v-> onConfermaButtonClicked());
            buttonAnnulla.setOnClickListener(v-> onAnnullaButtonClicked());
        }
        alertDialog.show();
        return alertDialog;
    }

    // Metodi per impostare i valori dinamicamente
    protected void setTitolo(String titolo) {
        textViewTitoloPopUp.setText(titolo);
    }

    protected void setDescrizione(String descrizione) {
        textViewDescrizione.setText(descrizione);
    }

    protected void setConfermaButtonText(String text) {
        buttonConferma.setVisibility(View.VISIBLE);
        buttonConferma.setText(text);
    }

    protected void setAnnullaButtonText(String text) {
        buttonAnnulla.setVisibility(View.VISIBLE);
        buttonAnnulla.setText(text);
    }

    protected abstract void onConfermaButtonClicked();

    protected abstract void onAnnullaButtonClicked();
}
