package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.monitoraggio.risultatiesercizio;

import android.content.pm.ActivityInfo;
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
import androidx.lifecycle.ViewModelProvider;

import java.io.File;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_player.AudioPlayerLink;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_recorder.AudioRecorder;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class RisultatoEsercizioCoppiaImmaginiGenitoreFragment extends AbstractFragmentWithNavigation {


    private SeekBar seekBarEsercizioCoppiaImmagini;
    private AudioRecorder audioRecorder;
    private MediaPlayer mMediaPlayer;
    private AudioPlayerLink audioPlayerLink;
    private EsercizioCoppiaImmagini mEsercizioCoppiaImmagini;
    private ImageButton imageButtonPlay;
    private ImageButton imageButtonPause;
    private ImageView imageViewCheck;
    private ImageView imageViewWrong;
    private ImageView imageViewNonSvoltoEsercizio;
    private int indiceEsercizio;
    private int indiceScenario;
    private int indiceTerapia;
    private GenitoreViewModel mGenitoreViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_risultato_es_coppia_immagini, container, false);

        setToolBar(view, getString(R.string.risultatoEsercizio));
        // a che serve questo?
        //navController.navigateUp();

        savedInstanceState = getArguments();

        if (savedInstanceState != null && savedInstanceState.containsKey("indiceEsercizio") && savedInstanceState.containsKey("indiceScenario") && savedInstanceState.containsKey("indiceTerapia")) {
            indiceEsercizio = savedInstanceState.getInt("indiceEsercizio");
            indiceScenario = savedInstanceState.getInt("indiceScenario");
            indiceTerapia = savedInstanceState.getInt("indiceTerapia");
        } else {
            indiceTerapia = 0;
            indiceEsercizio = 0;
            indiceScenario = 0;
        }

        seekBarEsercizioCoppiaImmagini = view.findViewById(R.id.seekBarScorrimentoAudioEsercizioCoppiaImmagini);
        imageButtonPlay = view.findViewById(R.id.playButton);
        imageButtonPause = view.findViewById(R.id.pauseButton);
        imageViewNonSvoltoEsercizio = view.findViewById(R.id.imageViewNonSvoltoEsercizio);
        imageViewCheck = view.findViewById(R.id.imageViewCheckEsercizio);
        imageViewWrong = view.findViewById(R.id.imageViewWrongEsercizio);

        mGenitoreViewModel = new ViewModelProvider(requireActivity()).get(GenitoreViewModel.class);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.mEsercizioCoppiaImmagini = getEsercizioCoppiaImmaginiFromViewModel(indiceEsercizio,indiceScenario,indiceTerapia);

        this.audioRecorder = initAudioRecorder();
        this.audioPlayerLink = new AudioPlayerLink(mEsercizioCoppiaImmagini.getAudioEsercizio());
        this.mMediaPlayer = audioPlayerLink.getMediaPlayer();

        if(isNonSvolto()){
            imageViewCheck.setVisibility(View.GONE);
            imageViewWrong.setVisibility(View.GONE);
            imageViewNonSvoltoEsercizio.setVisibility(View.VISIBLE);
        }
        else if (isCorrect()) {
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
        return this.mEsercizioCoppiaImmagini.getRisultatoEsercizio().isEsitoCorretto();
    }

    private boolean isNonSvolto() {
        return this.mEsercizioCoppiaImmagini.getRisultatoEsercizio() == null;
    }

    private EsercizioCoppiaImmagini getEsercizioCoppiaImmaginiFromViewModel(int indiceEsercizio, int indiceScenario, int indiceTerapia){
        Log.d("RisultatoDenominazione", ":"+ indiceEsercizio);

        return (EsercizioCoppiaImmagini) mGenitoreViewModel.getPazienteLiveData().getValue().getTerapie().get(indiceTerapia).getScenariGioco().get(indiceScenario).getEsercizi().get(indiceEsercizio);
    }


    @Override
    public void onResume() {
        super.onResume();
        // Blocca l'orientamento in portrait
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Sblocca l'orientamento quando il fragment non è più visibile
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
    }

}
