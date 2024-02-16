package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.monitoraggio.risultatiesercizio;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_player.AudioPlayerLink;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_recorder.AudioRecorder;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class RisultatoEsercizioCoppiaImmaginiFragment extends AbstractFragmentWithNavigation {


    private SeekBar seekBarEsercizioCoppiaImmagini;
    private AudioRecorder audioRecorder;
    private MediaPlayer mMediaPlayer;
    private AudioPlayerLink audioPlayerLink;
    private EsercizioCoppiaImmagini mEsercizioCoppiaImmagini;
    private ImageButton imageButtonPlay;
    private ImageButton imageButtonPause;
    private ImageView imageViewCheck;
    private ImageView imageViewWrong;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_risultato_es_coppia_immagini, container, false);

        setToolBar(view, getString(R.string.risultatoEsercizio));


        //TODO prendere esercizio da id passato da fragment chiamante
        seekBarEsercizioCoppiaImmagini = view.findViewById(R.id.seekBarScorrimentoAudioEsercizioCoppiaImmagini);
        imageButtonPlay = view.findViewById(R.id.playButton);
        imageButtonPause = view.findViewById(R.id.pauseButton);

        imageViewCheck = view.findViewById(R.id.imageViewCheckEsercizio);
        imageViewWrong = view.findViewById(R.id.imageViewWrongEsercizio);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO: in sto fragment l'esercizio dovrebbe essere passato dalla classe chiamante
        this.mEsercizioCoppiaImmagini = new EsercizioCoppiaImmagini(50,20,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/struzzo.mp3?alt=media&token=db982084-a8eb-48be-b5ae-a81ceb334ea4","https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/struzzo.jpg?alt=media&token=50abcf18-c404-48c1-bb3a-b37436898b8d","https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/macchina.jpg?alt=media&token=88ac2ae0-d403-41b0-adfd-2a1e106a3462");

        this.audioRecorder = initAudioRecorder();
        this.audioPlayerLink = new AudioPlayerLink(mEsercizioCoppiaImmagini.getAudioEsercizio());
        this.mMediaPlayer = audioPlayerLink.getMediaPlayer();

        if (isCorrect()) {
            imageViewCheck.setVisibility(View.VISIBLE);
            imageViewWrong.setVisibility(View.GONE);
        } else {
            imageViewCheck.setVisibility(View.GONE);
            imageViewWrong.setVisibility(View.VISIBLE);
        }

        imageButtonPlay.setOnClickListener(v -> avviaRiproduzioneAudio());
        imageButtonPause.setOnClickListener(v -> stoppaRiproduzioneAudio());
    }

    private AudioRecorder initAudioRecorder() {
        File cartellaApp = getContext().getFilesDir();
        File audioRegistrazione = new File(cartellaApp, "tempAudioRegistrato");

        return new AudioRecorder(audioRegistrazione);
    }

    private void avviaRiproduzioneAudio() {
        imageButtonPlay.setVisibility(View.GONE);
        imageButtonPause.setVisibility(View.VISIBLE);
        inizializzaBarraAvanzamento();
        audioPlayerLink.playAudio();
    }

    private void inizializzaBarraAvanzamento() {
        mMediaPlayer.setOnCompletionListener(mediaPlayer -> {
            Log.d("EsercizioCoppiaImmagini", "Audio completato");
            imageButtonPlay.setVisibility(View.VISIBLE);
            imageButtonPause.setVisibility(View.GONE);
        });

        seekBarEsercizioCoppiaImmagini.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

        mMediaPlayer.setOnSeekCompleteListener(mediaPlayer ->
                seekBarEsercizioCoppiaImmagini.setProgress((int) (mediaPlayer.getCurrentPosition() * 100 / mediaPlayer.getDuration())));

        final int delay = 5;
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying() && seekBarEsercizioCoppiaImmagini != null) {
                    seekBarEsercizioCoppiaImmagini.setProgress((int) (mMediaPlayer.getCurrentPosition() * 100 / mMediaPlayer.getDuration()));
                }
                handler.postDelayed(this, delay);
            }
        };

        mMediaPlayer.setOnPreparedListener(mp -> handler.postDelayed(runnable, delay));
    }
    public void stoppaRiproduzioneAudio() {
        imageButtonPlay.setVisibility(View.VISIBLE);
        imageButtonPause.setVisibility(View.GONE);
        audioPlayerLink.stopAudio();
    }

    private boolean isCorrect() {
        //TODO prendere esito risultato da viewmodel
        return false;
    }


}
