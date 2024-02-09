package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_player.AudioPlayerRaw;

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

    public void setEsercizioCorretto(int coins) {
        AudioPlayerRaw audioPlayerRaw = new AudioPlayerRaw(getContext(), R.raw.correct_sound);
        audioPlayerRaw.playAudio();

        frameLayoutFineEsercizio.setVisibility(View.VISIBLE);
        imageViewUpCoin.setVisibility(View.VISIBLE);
        textViewEsercizioCorretto.setVisibility(View.VISIBLE);

        simulateCoinIncrease(coins);
    }

    public void setEsercizioSbagliato(int coins) {
        AudioPlayerRaw audioPlayerRaw = new AudioPlayerRaw(getContext(), R.raw.error_sound);
        audioPlayerRaw.playAudio();

        frameLayoutFineEsercizio.setVisibility(View.VISIBLE);
        imageViewUpCoin.setVisibility(View.VISIBLE);
        textViewEsercizioSbagliato.setVisibility(View.VISIBLE);

        simulateCoinIncrease(coins);
    }

    private void simulateCoinIncrease(int targetCoins) {
        ValueAnimator animator = ValueAnimator.ofInt(0, targetCoins);
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue = (int) valueAnimator.getAnimatedValue();
                textViewCoins.setText(String.valueOf(animatedValue));
            }
        });
        animator.start();
    }

}
