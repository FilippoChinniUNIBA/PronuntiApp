package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class EsercizioCoppiaImmaginiFragment extends AbstractFragmentWithNavigation {

    private TextView textViewEsercizioCoppiaImmagini, textViewEsercizioPlaySuggestion;
    private SeekBar seekBarScorrimentoAudioEsercizioCoppiaImmagini;
    private ImageButton playButton;
    private ImageButton pauseButton;
    private ImageView imageButtonImmagine1, imageButtonImmagine2;
    private MediaPlayer mediaPlayer;
    private FineEsercizioView fineEsercizioView;
    private ConstraintLayout constraintLayoutEsercizioCoppiaImmagini;
    private FrameLayout fl1ImmagineEsercizioCoppiaImmagini, fl2ImmagineEsercizioCoppiaImmagini;
    private boolean isLongClick = false, firstClickReproduced = false;
    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflating del layout del fragment
        View view = inflater.inflate(R.layout.fragment_esercizio_coppia_immagini, container, false);
        fineEsercizioView = view.findViewById(R.id.fineEsercizioView);

        constraintLayoutEsercizioCoppiaImmagini = view.findViewById(R.id.constraintLayoutEsercizioCoppiaImmagini);
        fl1ImmagineEsercizioCoppiaImmagini = view.findViewById(R.id.fl1ImmagineEsercizioCoppiaImmagini);
        fl2ImmagineEsercizioCoppiaImmagini = view.findViewById(R.id.fl2ImmagineEsercizioCoppiaImmagini);

        // Trova le viste all'interno del layout del fragment
        textViewEsercizioCoppiaImmagini = view.findViewById(R.id.textViewEsercizioCoppiaImmagini);
        textViewEsercizioPlaySuggestion = view.findViewById(R.id.textViewEsercizioPlaySuggestion);
        seekBarScorrimentoAudioEsercizioCoppiaImmagini = view.findViewById(R.id.seekBarScorrimentoAudioEsercizioCoppiaImmagini);
        playButton = view.findViewById(R.id.playButton);
        pauseButton = view.findViewById(R.id.pauseButton);
        imageButtonImmagine1=view.findViewById(R.id.imageView1ImmagineEsercizioCoppiaImmagini);
        imageButtonImmagine2=view.findViewById(R.id.imageView2ImmagineEsercizioCoppiaImmagini);

        imageButtonImmagine1.setOnClickListener(v -> showSuggestionPlay());
        imageButtonImmagine2.setOnClickListener(v -> showSuggestionPlay());

        // Imposta un listener per il pulsante di riproduzione
        playButton.setOnClickListener(v -> {
            onPlayButtonClick();
        });
        // Imposta un listener per il pulsante di pausa
        pauseButton.setOnClickListener(v ->{
            onPauseButtonClick();
        });

        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void enableAnswer(){
        firstClickReproduced = true;
        textViewEsercizioPlaySuggestion.setVisibility(View.GONE);
        imageButtonImmagine1.setOnClickListener(v -> {
            borderImageSelector(fl1ImmagineEsercizioCoppiaImmagini);
            new Handler().postDelayed(this::verifyImageClick, 1000);
        });
        imageButtonImmagine2.setOnClickListener(v -> {
            borderImageSelector(fl2ImmagineEsercizioCoppiaImmagini);
            new Handler().postDelayed(this::verifyImageClick, 1000);
        });

        imageButtonImmagine1.setOnLongClickListener(v -> {
            borderImageSelector(fl1ImmagineEsercizioCoppiaImmagini);
            return true;
        });
        imageButtonImmagine1.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (!isLongClick) {
                    fl1ImmagineEsercizioCoppiaImmagini.setBackground(null);
                }
                isLongClick = false;
            }
            return false;
        });

        imageButtonImmagine2.setOnLongClickListener(v -> {
            borderImageSelector(fl2ImmagineEsercizioCoppiaImmagini);
            return true;
        });
        imageButtonImmagine2.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (!isLongClick) {
                    fl2ImmagineEsercizioCoppiaImmagini.setBackground(null);
                }
                isLongClick = false;
            }
            return false;
        });
    }

    private void showSuggestionPlay(){
        // Create a fade-in animation with a duration of 500 milliseconds
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(500);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Optional: You can add code to be executed when the animation starts here
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                textViewEsercizioPlaySuggestion.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        textViewEsercizioPlaySuggestion.startAnimation(fadeIn);
        new Handler().postDelayed(() -> {
            AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
            fadeOut.setDuration(500);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    // Optional: You can add code to be executed when the animation starts here
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    textViewEsercizioPlaySuggestion.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            // Start the animation set
            textViewEsercizioPlaySuggestion.startAnimation(fadeOut);
        }, 10000);
    }

    private void onPlayButtonClick() {
        if (!firstClickReproduced) {
            enableAnswer();
        }
        playButton.setVisibility(View.GONE);
        pauseButton.setVisibility(View.VISIBLE);
        initializeMediaPlayer();
        mediaPlayer.start();
    }

    public void onPauseButtonClick() {
        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.GONE);
        mediaPlayer.pause();
    }

    private void borderImageSelector(FrameLayout immagine){
        immagine.setBackgroundResource(R.drawable.selector_image_background);
    }

    public void verifyImageClick(){
        constraintLayoutEsercizioCoppiaImmagini.setVisibility(View.GONE);
        //if()
        //    fineEsercizioView.setEsercizioCorretto(100);
        //else fineEsercizioView.setEsercizioSbagliato(50);

        fineEsercizioView.setEsercizioCorretto(50);
    }

    private void initializeMediaPlayer() {
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.correct_sound);

        mediaPlayer.setOnCompletionListener(mediaPlayer -> {
            Log.d("EsercizioCoppiaImmagini", "Audio completato");
            playButton.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.GONE);
        });

        seekBarScorrimentoAudioEsercizioCoppiaImmagini.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress * mediaPlayer.getDuration() / 100);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress() * mediaPlayer.getDuration() / 100);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress() * mediaPlayer.getDuration() / 100);
            }
        });

        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                seekBarScorrimentoAudioEsercizioCoppiaImmagini.setProgress((int) (mediaPlayer.getCurrentPosition() * 100 / mediaPlayer.getDuration()));
            }
        });

        final int delay = 5; // Milliseconds
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying() && seekBarScorrimentoAudioEsercizioCoppiaImmagini != null) {
                    seekBarScorrimentoAudioEsercizioCoppiaImmagini.setProgress((int) (mediaPlayer.getCurrentPosition() * 100 / mediaPlayer.getDuration()));
                }
                handler.postDelayed(this, delay);
            }
        };

        mediaPlayer.setOnPreparedListener(mp -> handler.postDelayed(runnable, delay));
    }
}

