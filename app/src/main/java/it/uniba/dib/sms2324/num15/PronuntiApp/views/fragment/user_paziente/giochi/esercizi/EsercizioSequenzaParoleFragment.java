package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.external_api.google_cloud_speech_to_text_api.SpeechToTextAPI;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_player.AudioPlayerLink;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_recorder.AudioRecorder;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioSequenzaParoleController;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.RichiestaConfermaDialog;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class EsercizioSequenzaParoleFragment extends AbstractFragmentWithNavigation {

    private FineEsercizioView fineEsercizioView;
    private ConstraintLayout constraintLayoutEsercizioSequenzaParole;
    private SeekBar seekBarEsercizioSequenzaParole;
    private ImageButton imageButtonPlay, imageButtonPause;
    private View viewAnimazioneMic, viewConfirmMic, viewStopMic;
    private ImageButton imageButtonAvviaRegistrazione, buttonCompletaEsercizio;
    private ImageView imageViewConfermaRegistrazione;
    private ScaleAnimation animazioneButtonMic;
    private TextView textViewEsercizioMicSuggestion;
    private View viewClickForSuggestion;

    private boolean firstClickReproduced = false;


    private AudioRecorder audioRecorder;
    private AudioPlayerLink audioPlayerLink;
    private MediaPlayer mMediaPlayer;

    private PazienteViewModel mPazienteViewModel;
    private EsercizioSequenzaParoleController mController;
    private EsercizioSequenzaParole mEsercizioSequenzaParole;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_esercizio_sequenza_parole, container, false);

        this.mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);
        this.mController = mPazienteViewModel.getEsercizioSequenzaParoleController();

        fineEsercizioView = view.findViewById(R.id.fineEsercizioView);
        textViewEsercizioMicSuggestion = view.findViewById(R.id.textViewEsercizioMicSuggestion);
        viewClickForSuggestion = view.findViewById(R.id.viewClickForSuggestion);
        constraintLayoutEsercizioSequenzaParole = view.findViewById(R.id.constraintLayoutEsercizioSequenzaParole);
        seekBarEsercizioSequenzaParole = view.findViewById(R.id.seekBarScorrimentoAudioEsercizioSequenzaParole);
        imageButtonPlay = view.findViewById(R.id.playButton);
        imageButtonPause = view.findViewById(R.id.pauseButton);
        viewAnimazioneMic = view.findViewById(R.id.viewAnimationMic);
        viewConfirmMic = view.findViewById(R.id.viewConfirmMic);
        viewStopMic = view.findViewById(R.id.viewStopRegMic);
        imageButtonAvviaRegistrazione = view.findViewById(R.id.buttonAvviaRegistrazione);
        buttonCompletaEsercizio = view.findViewById(R.id.buttonCompletaEsercizio);
        imageViewConfermaRegistrazione = view.findViewById(R.id.confermaRegistrazioneImageView);

        setAnimazioneRegistrazione();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO: in sto fragment l'esercizio dovrebbe essere passato dalla classe chiamante
        this.mEsercizioSequenzaParole = new EsercizioSequenzaParole(50, 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/cane_carota_gatto.mp3?alt=media&token=f5058c6f-9ea2-4ffc-8189-e1aef88e69cc", "cane", "carota", "gatto");

        this.mController.setEsercizioSequenzaParole(mEsercizioSequenzaParole);


        this.audioRecorder = initAudioRecorder();
        this.audioPlayerLink = new AudioPlayerLink(mEsercizioSequenzaParole.getAudioEsercizio());
        this.mMediaPlayer = audioPlayerLink.getMediaPlayer();

        imageButtonPlay.setOnClickListener(v -> avviaRiproduzioneAudio());
        imageButtonPause.setOnClickListener(v -> stoppaRiproduzioneAudio());

        imageButtonAvviaRegistrazione.setOnClickListener(v -> avviaPrimaRegistrazione());
        viewStopMic.setOnClickListener(v -> stoppaRegistrazione());
        viewConfirmMic.setOnClickListener(v -> sovrascriviAudio());

        viewClickForSuggestion.setOnClickListener(v -> mostraSuggerimento());

        buttonCompletaEsercizio.setOnClickListener(v -> invalidConferma());
    }

    private void avviaPrimaRegistrazione(){
        if (!checkPermissions(requireActivity())) {
            requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO);
            return;
        }

        avviaRegistrazione();

        imageViewConfermaRegistrazione.setVisibility(View.INVISIBLE);
        viewStopMic.setVisibility(View.VISIBLE);
        viewConfirmMic.setVisibility(View.GONE);

        Toast.makeText(getContext(), getContext().getString(R.string.startedRecording), Toast.LENGTH_SHORT).show();
    }

    private void avviaRegistrazione() {
        imageButtonAvviaRegistrazione.setBackground(null);
        viewAnimazioneMic.startAnimation(animazioneButtonMic);
        imageViewConfermaRegistrazione.setVisibility(View.INVISIBLE);
        viewStopMic.setVisibility(View.VISIBLE);
        viewConfirmMic.setVisibility(View.GONE);

        audioRecorder.startRecording();

        buttonCompletaEsercizio.setOnClickListener(v -> invalidConferma());
    }

    private void stoppaRegistrazione(){
        viewAnimazioneMic.clearAnimation();
        imageButtonAvviaRegistrazione.setBackground(getContext().getDrawable(R.drawable.circle_button_mic_background));
        imageViewConfermaRegistrazione.setVisibility(View.VISIBLE);
        viewStopMic.setVisibility(View.GONE);
        viewConfirmMic.setVisibility(View.VISIBLE);

        audioRecorder.stopRecording();

        Toast.makeText(getContext(), getContext().getString(R.string.stopedRecording), Toast.LENGTH_SHORT).show();

        buttonCompletaEsercizio.setOnClickListener(v -> completaEsercizio());
    }

    private void invalidConferma() {
        Toast.makeText(getContext(), getContext().getString(R.string.recordBeforeCheck), Toast.LENGTH_SHORT).show();
    }

    private void avviaRiproduzioneAudio() {
        if (!firstClickReproduced) {
            abilitaCompletamento();
        }
        imageButtonPlay.setVisibility(View.GONE);
        imageButtonPause.setVisibility(View.VISIBLE);
        inizializzaBarraAvanzamento();
        audioPlayerLink.playAudio();
    }

    private void abilitaCompletamento() {
        firstClickReproduced = true;
        buttonCompletaEsercizio.setOnClickListener(v -> completaEsercizio());
    }

    public void stoppaRiproduzioneAudio() {
        imageButtonPlay.setVisibility(View.VISIBLE);
        imageButtonPause.setVisibility(View.GONE);
        audioPlayerLink.stopAudio();
    }

    private void completaEsercizio(){
        boolean esito;

        if (mController.verificaAudio(audioRecorder.getAudioFile(), getContext())) {
            esito = true;
            fineEsercizioView.setEsercizioCorretto(mEsercizioSequenzaParole.getRicompensaCorretto());
        }
        else{
            esito = false;
            fineEsercizioView.setEsercizioSbagliato(mEsercizioSequenzaParole.getRicompensaErrato());
        }

        constraintLayoutEsercizioSequenzaParole.setVisibility(View.GONE);

        File temp = mController.convertiAudio(audioRecorder.getAudioFile(), new File(getContext().getFilesDir(), "tempAudioConvertito.mp3"));
        //TODO salvare il file temp su Storage e ottenere link
        String audioRegistrato = "";

        Log.d("EsercizioSequenzaParoleFragment.completaEsercizio()", "Esercizio completato: " + mPazienteViewModel.getPazienteLiveData().getValue());
        mEsercizioSequenzaParole.setRisultatoEsercizio(new RisultatoEsercizioSequenzaParole(esito, audioRegistrato));
        Log.d("EsercizioSequenzaParoleFragment.completaEsercizio()", "Esercizio completato: " + mEsercizioSequenzaParole);
        Log.d("EsercizioSequenzaParoleFragment.completaEsercizio()", "Esercizio completato: " + mPazienteViewModel.getPazienteLiveData().getValue());

        //TODO aggiornamento del paziente con l'esito dell'esercizio

        //TODO per Nicola: hai 2 opzioni: o devi settare un listener su fineEsercizioView a fine animazione
        //e fare il navigateTo() in quel listener,
        //oppure devi mettere un onClick sulla schemata finale a fine animazione e fare il navigateTo() in quel onClick
        navigateTo(R.id.action_esercizioSequenzaParole_to_scenarioFragment);
    }

    private void sovrascriviAudio() {
        RichiestaConfermaDialog richiestaConfermaDialog = new RichiestaConfermaDialog(getContext(), getContext().getString(R.string.overwriteAudio), getContext().getString(R.string.overwriteAudioDescription));
        richiestaConfermaDialog.setOnConfermaButtonClickListener(this::avviaRegistrazione);
        richiestaConfermaDialog.setOnAnnullaButtonClickListener(() -> {});
        richiestaConfermaDialog.show();
    }

    private void setAnimazioneRegistrazione() {
        animazioneButtonMic = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animazioneButtonMic.setDuration(500);
        animazioneButtonMic.setRepeatMode(Animation.REVERSE);
        animazioneButtonMic.setRepeatCount(Animation.INFINITE);
    }

    private void mostraSuggerimento(){
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(500);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                textViewEsercizioMicSuggestion.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        textViewEsercizioMicSuggestion.startAnimation(fadeIn);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
                fadeOut.setDuration(500);
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        textViewEsercizioMicSuggestion.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });

                textViewEsercizioMicSuggestion.startAnimation(fadeOut);
            }
        }, 10000);
    }

    private void inizializzaBarraAvanzamento() {
        mMediaPlayer.setOnCompletionListener(mediaPlayer -> {
            Log.d("EsercizioCoppiaImmagini", "Audio completato");
            imageButtonPlay.setVisibility(View.VISIBLE);
            imageButtonPause.setVisibility(View.GONE);
        });

        seekBarEsercizioSequenzaParole.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
                seekBarEsercizioSequenzaParole.setProgress((int) (mediaPlayer.getCurrentPosition() * 100 / mediaPlayer.getDuration()));
            }
        });

        final int delay = 5;
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying() && seekBarEsercizioSequenzaParole != null) {
                    seekBarEsercizioSequenzaParole.setProgress((int) (mMediaPlayer.getCurrentPosition() * 100 / mMediaPlayer.getDuration()));
                }
                handler.postDelayed(this, delay);
            }
        };

        mMediaPlayer.setOnPreparedListener(mp -> handler.postDelayed(runnable, delay));
    }


    private boolean checkPermissions(Activity currentactivity) {
        int recordAudioPermission = ContextCompat.checkSelfPermission(currentactivity, Manifest.permission.RECORD_AUDIO);
        return recordAudioPermission == PackageManager.PERMISSION_GRANTED;
    }

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    this.audioRecorder = initAudioRecorder();
                    avviaPrimaRegistrazione();
                } else {
                    navigateTo(R.id.action_esercizioSequenzaParole_to_scenarioFragment);
                }
            });

    private AudioRecorder initAudioRecorder() {
        File cartellaApp = getContext().getFilesDir();
        File audioRegistrazione = new File(cartellaApp, "tempAudioRegistrato");

        return new AudioRecorder(audioRegistrazione);
    }

}