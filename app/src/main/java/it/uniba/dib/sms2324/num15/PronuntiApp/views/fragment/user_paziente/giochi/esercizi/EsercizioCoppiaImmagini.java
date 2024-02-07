package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.AudioRecognizer;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.FineEsercizioView;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class EsercizioCoppiaImmagini extends AbstractFragmentWithNavigation {

    private TextView textViewEsercizioCoppiaImmagini;
    private SeekBar seekBarScorrimentoAudioEsercizioCoppiaImmagini;
    private ImageButton playButton;
    private ImageButton pauseButton;
    private ImageView imageViewImmagine1, imageViewImmagine2;
    private MediaPlayer mediaPlayer;
    private FineEsercizioView fineEsercizioView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflating del layout del fragment
        View view = inflater.inflate(R.layout.fragment_esercizio_coppia_immaini, container, false);
        fineEsercizioView = view.findViewById(R.id.fineEsercizioView);

        // Trova le viste all'interno del layout del fragment
        textViewEsercizioCoppiaImmagini = view.findViewById(R.id.textViewEsercizioCoppiaImmagini);
        seekBarScorrimentoAudioEsercizioCoppiaImmagini = view.findViewById(R.id.seekBarScorrimentoAudioEsercizioCoppiaImmagini);
        playButton = view.findViewById(R.id.playButton);
        pauseButton = view.findViewById(R.id.pauseButton);
        imageViewImmagine1=view.findViewById(R.id.imageView1ImmagineEsercizioCoppiaImmagini);
        imageViewImmagine2=view.findViewById(R.id.imageView2ImmagineEsercizioCoppiaImmagini);

        // Imposta un listener per il pulsante di riproduzione
        playButton.setOnClickListener(v -> onPlayButtonClick());

        // Imposta un listener per il pulsante di pausa
        pauseButton.setOnClickListener(v -> onPauseButtonClick());

        return view;
    }

    public void onPlayButtonClick() {
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

