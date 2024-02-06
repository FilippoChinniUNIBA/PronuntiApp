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
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

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
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.RichiestaConfermaDialog;

public class EsercizioDenominazioneImmagineFragmentCopy extends Fragment {
    private ImageButton buttonAiutiImageView;
    private ImageButton buttonCompletaEsercizioImageView;
    private ImageButton buttonAvviaRegistrazione;
    private View viewOverlayBackground;
    private ImageView immagineEsercizioDenominazioneImageView;
    private View viewSuggestionMic;
    private ScaleAnimation animazioneButtonMic;
    private View viewAnimationMic, viewConfirmMic, viewStopMic;
    private ImageView imageViewConfermaRegistrazione;
    private AudioRecognizer audioRecognizer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla il layout per questo fragment
        View view = inflater.inflate(R.layout.fragment_esercizio_denominazione_immagine_copy, container, false);

        buttonAiutiImageView = view.findViewById(R.id.buttonAiuti);
        buttonCompletaEsercizioImageView = view.findViewById(R.id.buttonCompletaEsercizio);
        buttonAvviaRegistrazione = view.findViewById(R.id.buttonAvviaRegistrazione);
        immagineEsercizioDenominazioneImageView = view.findViewById(R.id.imageViewImmagineEsercizioDenominazione);
        viewOverlayBackground = view.findViewById(R.id.viewOverlaySfondoEsercizioDenominazione);
        viewSuggestionMic = view.findViewById(R.id.textViewEsercizioMicSuggestion);
        viewAnimationMic = view.findViewById(R.id.viewAnimationMic);
        viewConfirmMic = view.findViewById(R.id.viewConfirmMic);
        viewStopMic = view.findViewById(R.id.viewStopRegMic);
        imageViewConfermaRegistrazione = view.findViewById(R.id.confermaRegistrazioneImageView);
        imageViewConfermaRegistrazione.setVisibility(View.INVISIBLE);


        setAnimazioneRegistrazione();
        buttonAvviaRegistrazione.setOnClickListener(v -> {
            setButtonAvviaRegistrazione();
        });

        viewStopMic.setOnClickListener(v -> {
            stopRecording();
        });

        viewConfirmMic.setOnClickListener(v -> {
            RichiestaConfermaDialog richiestaConfermaDialog = new RichiestaConfermaDialog(getContext(), "Sovrascrivi audio?", "Sei sicuro di voler registrare dinuovo la risposta?");
            richiestaConfermaDialog.setOnConfermaButtonClickListener(this::automateStartRecording);
            richiestaConfermaDialog.setOnAnnullaButtonClickListener(() -> {});
            richiestaConfermaDialog.show();
        });

        viewOverlayBackground.setOnClickListener(v -> {
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
                    viewSuggestionMic.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            viewSuggestionMic.startAnimation(fadeIn);
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
                            viewSuggestionMic.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    // Start the animation set
                    viewSuggestionMic.startAnimation(fadeOut);
                }
            }, 10000);
        });



        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Activity curretactivity = requireActivity();

        if (!checkPermissions(curretactivity)) {
            requestPermissions(curretactivity);
        }

        //------------------>FILE DA OTTENERE DAL db
        File immagineEsercizio = new File(curretactivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES),"pinguino.jpg");
        AtomicInteger countAiuti = new AtomicInteger(3);

        //QUANDO VIENE ISTANZIATO UN ESERCIZIO L'IMMAGINE DEVE ESSERE ALLOCATA IN MEMORIA E IL PATH DELLA MEMORIA INTERNA DEVE ESSERE MEMORIZZATO NELL'ESERCIZIO
        EsercizioDenominazioneImmagine esercizioDenominazioneImmagine = new EsercizioDenominazioneImmagine(2500,200,immagineEsercizio, "pinguino", new File("help.mp3"));

        File directoryMusic = curretactivity.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File fileRegistrazione = new File(directoryMusic,"test");
        File fileConvertito = new File(directoryMusic,"test.mp3");

        audioRecognizer = new AudioRecognizer(fileRegistrazione,curretactivity);
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference pinguino = storage.getReference().child("pinguino.jpg");
        Log.d("PROVA PINGUINO", esercizioDenominazioneImmagine.getAudioAiuto().getName());
        StorageReference aiuto = storage.getReference().child("help.mp3");

        CompletableFuture<String> urlFuture = getUrlFromStorageReference(pinguino);
        //->FINE FILE DA OTTENERE DAL db

        MediaPlayer aiutiplayer = new MediaPlayer();
        MediaPlayer correctplayer = MediaPlayer.create(curretactivity,R.raw.correct_sound);
        MediaPlayer errorplayer = MediaPlayer.create(curretactivity,R.raw.error_sound);

        urlFuture.thenAccept(imageUrl -> {
            // Set the image view with the downloaded image
            Picasso.get().load(imageUrl).into(immagineEsercizioDenominazioneImageView);
            buttonAvviaRegistrazione.setOnClickListener(v -> {
                setButtonAvviaRegistrazione();
                Toast.makeText(curretactivity, "Registrazione avviata", Toast.LENGTH_SHORT).show();
            });
            viewStopMic.setOnClickListener(v -> {
                stopRecording();
                Toast.makeText(curretactivity, "Registrazione interrotta", Toast.LENGTH_SHORT).show();
            });
            buttonAiutiImageView.setOnClickListener(v -> {
                if (countAiuti.get() > 0) {
                    Toast.makeText(curretactivity, "Aiuto utilizzato, solo "+ (countAiuti.get() - 1) +" aiuti rimasti!", Toast.LENGTH_SHORT).show();
                    getUrlFromStorageReference(aiuto).thenAccept(audioUrl -> {
                        try {
                            aiutiplayer.reset();
                            aiutiplayer.setDataSource(audioUrl);
                            aiutiplayer.prepare();
                            aiutiplayer.start();
                            countAiuti.decrementAndGet();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } else {
                    Toast.makeText(curretactivity, "Non hai più aiuti!", Toast.LENGTH_SHORT).show();
                }
            });
            buttonCompletaEsercizioImageView.setOnClickListener(v -> {
                try{
                    AudioConverter.convertFile(audioRecognizer.getAudioFile(),fileConvertito);
                    List<String> words = audioRecognizer.getText();
                    uploadFileToStorage(fileConvertito, storage.getReference(), curretactivity);
                    if(words.get(0).toLowerCase().equals(esercizioDenominazioneImmagine.getParolaEsercizio())){
                        correctplayer.start();
                    }else {
                        errorplayer.start();
                    }
                } catch (IndexOutOfBoundsException e) {
                    Toast.makeText(curretactivity, "Parola non riconosciuta, riprova!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    private CompletableFuture<String> getUrlFromStorageReference(StorageReference reference) {
        CompletableFuture<String> future = new CompletableFuture<>();

        reference.getDownloadUrl().addOnSuccessListener(uri -> {
            String imageUrl = uri.toString();
            future.complete(imageUrl);
        }).addOnFailureListener(e -> {
            future.completeExceptionally(e);
        });

        return future;
    }

    private void setAnimazioneRegistrazione(){
        animazioneButtonMic = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animazioneButtonMic.setDuration(500); // Durata dell'animazione in millisecondi
        animazioneButtonMic.setRepeatMode(Animation.REVERSE); // Ripetizione dell'animazione in modalità reverse
        animazioneButtonMic.setRepeatCount(Animation.INFINITE); // Ripetizione dell'animazione infinite volte
    }

    private void stopRecording(){
        viewAnimationMic.clearAnimation();
        buttonAvviaRegistrazione.setBackground(getContext().getDrawable(R.drawable.circle_button_mic_background));
        imageViewConfermaRegistrazione.setVisibility(View.VISIBLE);
        viewStopMic.setVisibility(View.GONE);
        viewConfirmMic.setVisibility(View.VISIBLE);
        audioRecognizer.stopRecording();
    }

    private void automateStartRecording(){
        buttonAvviaRegistrazione.setBackground(null);
        viewAnimationMic.startAnimation(animazioneButtonMic);
        imageViewConfermaRegistrazione.setVisibility(View.INVISIBLE);
        viewStopMic.setVisibility(View.VISIBLE);
        viewConfirmMic.setVisibility(View.GONE);
        audioRecognizer.startRecording();
    }

    private void setButtonAvviaRegistrazione(){
        automateStartRecording();
        imageViewConfermaRegistrazione.setVisibility(View.INVISIBLE);
        viewStopMic.setVisibility(View.VISIBLE);
        viewConfirmMic.setVisibility(View.GONE);
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

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("Denominazione immagine");
        }
    }
}