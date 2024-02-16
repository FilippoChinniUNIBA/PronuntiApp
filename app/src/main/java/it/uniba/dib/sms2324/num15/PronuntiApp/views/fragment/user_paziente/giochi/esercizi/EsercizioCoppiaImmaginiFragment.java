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

import java.util.List;
import java.util.Random;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_player.AudioPlayerLink;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioCoppiaImmaginiController;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.scenari.ScenarioFragment;

public class EsercizioCoppiaImmaginiFragment extends AbstractFragmentWithNavigation {
    private TextView textViewEsercizioCoppiaImmagini, textViewEsercizioPlaySuggestion;
    private SeekBar seekBarScorrimentoAudioEsercizioCoppiaImmagini;
    private ImageButton playButton;
    private ImageButton pauseButton;
    private ImageView imageButtonImmagine1, imageButtonImmagine2;
    private FineEsercizioView fineEsercizioView;
    private ConstraintLayout constraintLayoutEsercizioCoppiaImmagini;
    private FrameLayout fl1ImmagineEsercizioCoppiaImmagini, fl2ImmagineEsercizioCoppiaImmagini;

    private boolean isLongClick = false;
    private boolean firstClickReproduced = false;
    private int correctImageView;


    private AudioPlayerLink audioPlayerLink;
    private MediaPlayer mMediaPlayer;

    private PazienteViewModel mPazienteViewModel;
    private EsercizioCoppiaImmaginiController mController;
    private EsercizioCoppiaImmagini mEsercizioCoppiaImmagini;
    private Bundle bundle;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_esercizio_coppia_immagini, container, false);

        this.mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);
        this.mController = mPazienteViewModel.getEsercizioCoppiaImmaginiController();

        fineEsercizioView = view.findViewById(R.id.fineEsercizioView);
        constraintLayoutEsercizioCoppiaImmagini = view.findViewById(R.id.constraintLayoutEsercizioCoppiaImmagini);
        fl1ImmagineEsercizioCoppiaImmagini = view.findViewById(R.id.fl1ImmagineEsercizioCoppiaImmagini);
        fl2ImmagineEsercizioCoppiaImmagini = view.findViewById(R.id.fl2ImmagineEsercizioCoppiaImmagini);
        textViewEsercizioCoppiaImmagini = view.findViewById(R.id.textViewEsercizioCoppiaImmagini);
        textViewEsercizioPlaySuggestion = view.findViewById(R.id.textViewEsercizioPlaySuggestion);
        seekBarScorrimentoAudioEsercizioCoppiaImmagini = view.findViewById(R.id.seekBarScorrimentoAudioEsercizioCoppiaImmagini);
        playButton = view.findViewById(R.id.playButton);
        pauseButton = view.findViewById(R.id.pauseButton);
        imageButtonImmagine1=view.findViewById(R.id.imageView1ImmagineEsercizioCoppiaImmagini);
        imageButtonImmagine2=view.findViewById(R.id.imageView2ImmagineEsercizioCoppiaImmagini);


        this.correctImageView = mController.generaPosizioneImmagineCorretta();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bundle = getArguments();

        mPazienteViewModel.getPazienteLiveData().observe(getViewLifecycleOwner(), paziente -> {
            List<Terapia> terapie = paziente.getTerapie();
            int sizeTerapie = terapie.size();
            ScenarioGioco scenarioGioco = terapie.get(sizeTerapie-1).getScenariGioco().get(bundle.getInt("indiceScenarioCorrente"));
            mEsercizioCoppiaImmagini = (EsercizioCoppiaImmagini) scenarioGioco.getEsercizi().get(bundle.getInt("indiceEsercizio"));


            this.mController.setEsercizioCoppiaImmagini(mEsercizioCoppiaImmagini);


            this.audioPlayerLink = new AudioPlayerLink(mEsercizioCoppiaImmagini.getAudioEsercizio());
            this.mMediaPlayer = audioPlayerLink.getMediaPlayer();

            if (correctImageView == 1) {
                Picasso.get().load(mEsercizioCoppiaImmagini.getImmagineEsercizioCorretta()).into(imageButtonImmagine1);
                Picasso.get().load(mEsercizioCoppiaImmagini.getImmagineEsercizioErrata()).into(imageButtonImmagine2);
            } else {
                Picasso.get().load(mEsercizioCoppiaImmagini.getImmagineEsercizioCorretta()).into(imageButtonImmagine2);
                Picasso.get().load(mEsercizioCoppiaImmagini.getImmagineEsercizioErrata()).into(imageButtonImmagine1);
            }

            playButton.setOnClickListener(v -> avviaRiproduzioneAudio());
            pauseButton.setOnClickListener(v -> stoppaRiproduzioneAudio());

            imageButtonImmagine1.setOnClickListener(v -> mostraSuggerimento());
            imageButtonImmagine2.setOnClickListener(v -> mostraSuggerimento());
        });
    }

    private void avviaRiproduzioneAudio() {
        if (!firstClickReproduced) {
            abilitaCompletamento();
        }
        playButton.setVisibility(View.GONE);
        pauseButton.setVisibility(View.VISIBLE);
        inizializzaBarraAvanzamento();
        audioPlayerLink.playAudio();
    }

    public void stoppaRiproduzioneAudio() {
        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.GONE);
        audioPlayerLink.playAudio();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void abilitaCompletamento(){
        firstClickReproduced = true;
        textViewEsercizioPlaySuggestion.setVisibility(View.GONE);
        imageButtonImmagine1.setOnClickListener(v -> {
            v.setOnClickListener(null);
            imageButtonImmagine2.setOnClickListener(null);
            borderImageSelector(fl1ImmagineEsercizioCoppiaImmagini);
            new Handler().postDelayed(() -> completaEsercizio(1), 1000);
        });
        imageButtonImmagine2.setOnClickListener(v -> {
            v.setOnClickListener(null);
            imageButtonImmagine1.setOnClickListener(null);
            borderImageSelector(fl2ImmagineEsercizioCoppiaImmagini);
            new Handler().postDelayed(() -> completaEsercizio(2), 1000);
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

    public void completaEsercizio(int immagineScelta) {
        boolean esito;

        if (mController.verificaSceltaImmagine(immagineScelta, correctImageView)) {
            esito = true;
            fineEsercizioView.setEsercizioCorretto(mEsercizioCoppiaImmagini.getRicompensaCorretto(), R.id.action_esercizioCoppiaImmagini_to_scenarioFragment, this);

            //TODO togliere poi quando si fa il vero risultato dell'es
            mPazienteViewModel.getPazienteLiveData().getValue().incrementaValuta(mEsercizioCoppiaImmagini.getRicompensaCorretto());
            mPazienteViewModel.getPazienteLiveData().getValue().incrementaPunteggioTot(mEsercizioCoppiaImmagini.getRicompensaCorretto());
            mPazienteViewModel.aggiornaPazienteRemoto();
        } else {
            esito = false;
            fineEsercizioView.setEsercizioSbagliato(mEsercizioCoppiaImmagini.getRicompensaErrato(), R.id.action_esercizioCoppiaImmagini_to_scenarioFragment, this);

            //TODO togliere poi quando si fa il vero risultato dell'es
            mPazienteViewModel.getPazienteLiveData().getValue().incrementaValuta(mEsercizioCoppiaImmagini.getRicompensaErrato());
            mPazienteViewModel.getPazienteLiveData().getValue().incrementaPunteggioTot(mEsercizioCoppiaImmagini.getRicompensaErrato());
        }

        constraintLayoutEsercizioCoppiaImmagini.setVisibility(View.GONE);

        Log.d("EsercizioCoppiaImmaginiFragment.completaEsercizio()", "Esercizio completato: " + mPazienteViewModel.getPazienteLiveData().getValue());
        mEsercizioCoppiaImmagini.setRisultatoEsercizio(new RisultatoEsercizioCoppiaImmagini(esito));
        Log.d("EsercizioCoppiaImmaginiFragment.completaEsercizio()", "Esercizio completato: " + mEsercizioCoppiaImmagini);
        Log.d("EsercizioCoppiaImmaginiFragment.completaEsercizio()", "Esercizio completato: " + mPazienteViewModel.getPazienteLiveData().getValue());

        //TODO aggiornamento del paziente con l'esito dell'esercizio

    }

    private void borderImageSelector(FrameLayout immagine){
        immagine.setBackgroundResource(R.drawable.selector_image_background);
    }

    private void mostraSuggerimento(){
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(500);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                textViewEsercizioPlaySuggestion.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        textViewEsercizioPlaySuggestion.startAnimation(fadeIn);
        new Handler().postDelayed(() -> {
            AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
            fadeOut.setDuration(500);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {
                    textViewEsercizioPlaySuggestion.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
            textViewEsercizioPlaySuggestion.startAnimation(fadeOut);
        }, 10000);
    }

    private void inizializzaBarraAvanzamento() {

        mMediaPlayer.setOnCompletionListener(mediaPlayer -> {
            Log.d("EsercizioCoppiaImmagini", "Audio completato");
            playButton.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.GONE);
        });

        seekBarScorrimentoAudioEsercizioCoppiaImmagini.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mMediaPlayer.seekTo(progress * mMediaPlayer.getDuration() / 100);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mMediaPlayer.seekTo(seekBar.getProgress() * mMediaPlayer.getDuration() / 100);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mMediaPlayer.seekTo(seekBar.getProgress() * mMediaPlayer.getDuration() / 100);
            }
        });

        mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
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
                if (mMediaPlayer != null && mMediaPlayer.isPlaying() && seekBarScorrimentoAudioEsercizioCoppiaImmagini != null) {
                    seekBarScorrimentoAudioEsercizioCoppiaImmagini.setProgress((int) (mMediaPlayer.getCurrentPosition() * 100 / mMediaPlayer.getDuration()));
                }
                handler.postDelayed(this, delay);
            }
        };

        mMediaPlayer.setOnPreparedListener(mp -> handler.postDelayed(runnable, delay));
    }

}

