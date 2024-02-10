package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_player.AudioPlayerRaw;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

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

    public void setEsercizioCorretto(int coins, int idNagiation, AbstractFragmentWithNavigation fragment) {
        AudioPlayerRaw audioPlayerRaw = new AudioPlayerRaw(getContext(), R.raw.correct_sound);
        audioPlayerRaw.playAudio();

        frameLayoutFineEsercizio.setVisibility(View.VISIBLE);
        imageViewUpCoin.setVisibility(View.VISIBLE);
        textViewEsercizioCorretto.setVisibility(View.VISIBLE);

        animazioneIncrementoValuta(coins, idNagiation, fragment);
    }

    public void setEsercizioSbagliato(int coins, int idNagiation, AbstractFragmentWithNavigation fragment) {
        AudioPlayerRaw audioPlayerRaw = new AudioPlayerRaw(getContext(), R.raw.error_sound);
        audioPlayerRaw.playAudio();

        frameLayoutFineEsercizio.setVisibility(View.VISIBLE);
        imageViewUpCoin.setVisibility(View.VISIBLE);
        textViewEsercizioSbagliato.setVisibility(View.VISIBLE);

        animazioneIncrementoValuta(coins, idNagiation, fragment);
    }

    private void animazioneIncrementoValuta(int targetCoins, int idNagiation, AbstractFragmentWithNavigation fragment) {
        ValueAnimator animator = ValueAnimator.ofInt(0, targetCoins);
        animator.setDuration(1500);
        animator.addUpdateListener(valueAnimator -> {
            int animatedValue = (int) valueAnimator.getAnimatedValue();
            textViewCoins.setText(String.valueOf(animatedValue));
        });
        animator.start();

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {}

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                new Handler().postDelayed(()->{fragment.navigateTo(idNagiation);},1000);
            }
            @Override
            public void onAnimationCancel(@NonNull Animator animation) {}

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {}
        });

    }

}
