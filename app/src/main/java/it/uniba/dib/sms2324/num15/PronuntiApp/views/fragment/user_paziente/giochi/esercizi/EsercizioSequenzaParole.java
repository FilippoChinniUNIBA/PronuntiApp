package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
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

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.AudioConverter;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.AudioRecognizer;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.FineEsercizioView;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.RichiestaConfermaDialog;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class EsercizioSequenzaParole extends AbstractFragmentWithNavigation {

    private FineEsercizioView fineEsercizioView;
    private ConstraintLayout constraintLayoutEsercizioSequenzaParole;
    private SeekBar seekBarEsercizioSequenzaParole;
    private ImageButton imageButtonPlay, imageButtonPause;
    private View viewAnimazioneMic, viewConfirmMic, viewStopMic;
    private ImageButton imageButtonAvviaRegistrazione, buttonCompletaEsercizio;
    private ImageView imageViewConfermaRegistrazione;
    private ScaleAnimation animazioneButtonMic;
    private MediaPlayer mediaPlayer;
    private boolean firstClickReproduced = false;
    private TextView textViewEsercizioMicSuggestion;
    private View viewClickForSuggestion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_esercizio_sequenza_parole, container, false);

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

        viewClickForSuggestion.setOnClickListener(v -> {
            showSuggestionMic();
        });

        setAnimazioneRegistrazione();
        imageButtonAvviaRegistrazione.setOnClickListener(v -> {
            setButtonAvviaRegistrazione();
        });

        viewStopMic.setOnClickListener(v -> {
            stopRecording();
        });

        viewConfirmMic.setOnClickListener(v -> {
            sovrascriviAudio();
        });

        // Imposta un listener per il pulsante di riproduzione
        imageButtonPlay.setOnClickListener(v -> {
            onPlayButtonClick();
        });
        // Imposta un listener per il pulsante di pausa
        imageButtonPause.setOnClickListener(v ->{
            onPauseButtonClick();
        });

        imageButtonAvviaRegistrazione.setOnClickListener(v -> {
            setButtonAvviaRegistrazione();
        });
        viewStopMic.setOnClickListener(v -> {
            stopRecording();
        });
        buttonCompletaEsercizio.setOnClickListener(v -> {
            invalidConferma();
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        if (!checkPermissions(requireActivity())) {
            requestPermissions(requireActivity());
        }

    }

    private void showSuggestionMic(){
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
                textViewEsercizioMicSuggestion.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        textViewEsercizioMicSuggestion.startAnimation(fadeIn);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
                fadeOut.setDuration(500);
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // Optional: You can add code to be executed when the animation starts here
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        textViewEsercizioMicSuggestion.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                // Start the animation set
                textViewEsercizioMicSuggestion.startAnimation(fadeOut);
            }
        }, 10000);
    }
    public void onPauseButtonClick() {
        imageButtonPlay.setVisibility(View.VISIBLE);
        imageButtonPause.setVisibility(View.GONE);
        mediaPlayer.pause();
    }

    private void enableAnswer() {
        firstClickReproduced = true;
        buttonCompletaEsercizio.setOnClickListener(v -> {
            setButtonCompletaEsercizioImageView();
        });
    }

    private void onPlayButtonClick() {
        if (!firstClickReproduced) {
            enableAnswer();
        }
        imageButtonPlay.setVisibility(View.GONE);
        imageButtonPause.setVisibility(View.VISIBLE);
        initializeMediaPlayer();
        mediaPlayer.start();
    }

    private void initializeMediaPlayer() {
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.correct_sound);

        mediaPlayer.setOnCompletionListener(mediaPlayer -> {
            Log.d("EsercizioCoppiaImmagini", "Audio completato");
            imageButtonPlay.setVisibility(View.VISIBLE);
            imageButtonPause.setVisibility(View.GONE);
        });

        seekBarEsercizioSequenzaParole.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
                seekBarEsercizioSequenzaParole.setProgress((int) (mediaPlayer.getCurrentPosition() * 100 / mediaPlayer.getDuration()));
            }
        });

        final int delay = 5; // Milliseconds
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying() && seekBarEsercizioSequenzaParole != null) {
                    seekBarEsercizioSequenzaParole.setProgress((int) (mediaPlayer.getCurrentPosition() * 100 / mediaPlayer.getDuration()));
                }
                handler.postDelayed(this, delay);
            }
        };

        mediaPlayer.setOnPreparedListener(mp -> handler.postDelayed(runnable, delay));
    }

    private void stopRecording(){
        viewAnimazioneMic.clearAnimation();
        imageButtonAvviaRegistrazione.setBackground(getContext().getDrawable(R.drawable.circle_button_mic_background));
        imageViewConfermaRegistrazione.setVisibility(View.VISIBLE);
        viewStopMic.setVisibility(View.GONE);
        viewConfirmMic.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), getContext().getString(R.string.stopedRecording), Toast.LENGTH_SHORT).show();

        buttonCompletaEsercizio.setOnClickListener(v -> {
            setButtonCompletaEsercizioImageView();
        });
    }

    private void setButtonCompletaEsercizioImageView(){
        if(verificaAudio()) {
            constraintLayoutEsercizioSequenzaParole.setVisibility(View.GONE);
            fineEsercizioView.setEsercizioCorretto(50);
        }
        else{
            constraintLayoutEsercizioSequenzaParole.setVisibility(View.GONE);
            fineEsercizioView.setEsercizioSbagliato(20);
        }
    }

    private void setAnimazioneRegistrazione() {
        animazioneButtonMic = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animazioneButtonMic.setDuration(500); // Durata dell'animazione in millisecondi
        animazioneButtonMic.setRepeatMode(Animation.REVERSE); // Ripetizione dell'animazione in modalità reverse
        animazioneButtonMic.setRepeatCount(Animation.INFINITE); // Ripetizione dell'animazione infinite volte
    }

    private void invalidConferma() {
        Toast.makeText(getContext(), getContext().getString(R.string.recordBeforeCheck), Toast.LENGTH_SHORT).show();
    }

    private void sovrascriviAudio(){
        RichiestaConfermaDialog richiestaConfermaDialog = new RichiestaConfermaDialog(getContext(), getContext().getString(R.string.overwriteAudio), getContext().getString(R.string.overwriteAudioDescription));
        richiestaConfermaDialog.setOnConfermaButtonClickListener(this::automateStartRecording);
        richiestaConfermaDialog.setOnAnnullaButtonClickListener(() -> {});
        richiestaConfermaDialog.show();
    }

    private void automateStartRecording(){
        imageButtonAvviaRegistrazione.setBackground(null);
        viewAnimazioneMic.startAnimation(animazioneButtonMic);
        imageViewConfermaRegistrazione.setVisibility(View.INVISIBLE);
        viewStopMic.setVisibility(View.VISIBLE);
        viewConfirmMic.setVisibility(View.GONE);

        buttonCompletaEsercizio.setOnClickListener(v->{
            invalidConferma();
        });
    }

    private void setButtonAvviaRegistrazione(){
        automateStartRecording();
        imageViewConfermaRegistrazione.setVisibility(View.INVISIBLE);
        viewStopMic.setVisibility(View.VISIBLE);
        viewConfirmMic.setVisibility(View.GONE);
        Toast.makeText(getContext(), getContext().getString(R.string.startedRecording), Toast.LENGTH_SHORT).show();
    }

    private boolean verificaAudio() {
        //if vero
        return true;
        //else return false;
    }

    private CompletableFuture<Void> uploadFileToStorage(File file, StorageReference storageReference, Activity activity) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        StorageReference fileReference = storageReference.child(file.getName());

        try {
            FileInputStream stream = new FileInputStream(file);
            UploadTask uploadTask = fileReference.putStream(stream);

            uploadTask.addOnSuccessListener(taskSnapshot -> {
                future.complete(null);
            }).addOnFailureListener(exception -> {
                future.completeExceptionally(exception);
            });
        } catch (IOException e) {
            future.completeExceptionally(e);
        }
        return future;
    }


    private boolean checkPermissions(Activity currentactivity) {
        int readStoragePermission = ContextCompat.checkSelfPermission(currentactivity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int writeStoragePermission = ContextCompat.checkSelfPermission(currentactivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int recordAudioPermission = ContextCompat.checkSelfPermission(currentactivity, Manifest.permission.RECORD_AUDIO);

        return readStoragePermission == PackageManager.PERMISSION_GRANTED &&
                writeStoragePermission == PackageManager.PERMISSION_GRANTED &&
                recordAudioPermission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions(Activity currentactivity) {
        ActivityCompat.requestPermissions(currentactivity, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, 1000);
    }
}