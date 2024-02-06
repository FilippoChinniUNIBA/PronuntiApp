package it.uniba.dib.sms2324.num15.PronuntiApp.views;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class FineEsercizioView extends FrameLayout {

    private FrameLayout frameLayoutFineEsercizio;
    private ImageView imageViewUpCoin;
    private TextView textViewCoins, textViewEsercizioCorretto, textViewEsercizioSbagliato;

    public FineEsercizioView(Context context) {
        super(context);
        initView(context);
    }

    public FineEsercizioView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_fine_esercizio, this, true);

        frameLayoutFineEsercizio = view.findViewById(R.id.frameLayoutFineEsercizioDenominazioneImmagineFine);
        frameLayoutFineEsercizio.setVisibility(View.GONE);
        imageViewUpCoin = view.findViewById(R.id.imageViewUpCoinFineEsercizio);
        textViewCoins = view.findViewById(R.id.textViewCoinsFineEsercizio);
        textViewEsercizioCorretto = view.findViewById(R.id.textViewEsercizioCorretto);
        textViewEsercizioSbagliato = view.findViewById(R.id.textViewEsercizioSbagliato);
    }

    public void setEsercizioCorretto() {
        frameLayoutFineEsercizio.setVisibility(View.VISIBLE);
        imageViewUpCoin.setVisibility(View.VISIBLE);
        textViewEsercizioCorretto.setVisibility(View.VISIBLE);
    }

    public void setEsercizioSbagliato() {
        frameLayoutFineEsercizio.setVisibility(View.VISIBLE);
        imageViewUpCoin.setVisibility(View.VISIBLE);
        textViewEsercizioSbagliato.setVisibility(View.VISIBLE);
    }
}
