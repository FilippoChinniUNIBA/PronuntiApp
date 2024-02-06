package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class EsercizioDenominazioneImmagineFragment extends Fragment {

    private Button buttonAvviaRegistrazione;
    private Button buttonFermaRegistrazione;
    private Button buttonAiuti;
    private Button buttonCompletaEsercizio;
    private ImageView imageViewImmagineEsercizioDenominazione;



    public EsercizioDenominazioneImmagineFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_esercizio_denominazione_immagine, container, false);

        buttonAvviaRegistrazione = view.findViewById(R.id.buttonAvviaRegistrazione);
        buttonFermaRegistrazione = view.findViewById(R.id.buttonFermaRegistrazione);
        buttonAiuti = view.findViewById(R.id.buttonAiuti);
        buttonCompletaEsercizio = view.findViewById(R.id.buttonCompletaEsercizio);
        imageViewImmagineEsercizioDenominazione = view.findViewById(R.id.cardViewImmagineEsercizioDenominazione);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Activity curretactivity = requireActivity();

        if (!checkPermissions(curretactivity)) {
            requestPermissions(curretactivity);
        }

        File immagineEsercizio = new File(curretactivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES),"pinguino.jpg");
        AtomicInteger countAiuti = new AtomicInteger(3);

        //QUANDO VIENE ISTANZIATO UN ESERCIZIO L'IMMAGINE DEVE ESSERE ALLOCATA IN MEMORIA E IL PATH DELLA MEMORIA INTERNA DEVE ESSERE MEMORIZZATO NELL'ESERCIZIO
        EsercizioDenominazioneImmagine esercizioDenominazioneImmagine = new EsercizioDenominazioneImmagine(2500,200,immagineEsercizio, "pinguino", new File("help.mp3"));

        File directoryMusic = curretactivity.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File fileRegistrazione = new File(directoryMusic,"test");
        File fileConvertito = new File(directoryMusic,"test.mp3");

        AudioRecognizer audioRecognizer = new AudioRecognizer(fileRegistrazione,curretactivity);
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference pinguino = storage.getReference().child("pinguino.jpg");
        Log.d("PROVA PINGUINO", esercizioDenominazioneImmagine.getAudioAiuto().getName());
        StorageReference aiuto = storage.getReference().child("help.mp3");

        CompletableFuture<String> urlFuture = getUrlFromStorageReference(pinguino);

        MediaPlayer aiutiplayer = new MediaPlayer();
        MediaPlayer correctplayer = MediaPlayer.create(curretactivity,R.raw.correct_sound);
        MediaPlayer errorplayer = MediaPlayer.create(curretactivity,R.raw.error_sound);


        urlFuture.thenAccept(imageUrl -> {
            Picasso.get().load(imageUrl).into(imageViewImmagineEsercizioDenominazione);
            buttonAvviaRegistrazione.setOnClickListener(v -> {
                startRecording(audioRecognizer);
                Toast.makeText(curretactivity, "Registrazione avviata", Toast.LENGTH_SHORT).show();
            });
            buttonFermaRegistrazione.setOnClickListener(v -> {
                stopRecording(audioRecognizer);
                Toast.makeText(curretactivity, "Registrazione interrotta", Toast.LENGTH_SHORT).show();
            });
            buttonAiuti.setOnClickListener(v -> {
                if (countAiuti.get() > 0) {
                    getUrlFromStorageReference(aiuto).thenAccept(audioUrl -> {
                        try {
                            aiutiplayer.reset();
                            aiutiplayer.setDataSource(audioUrl);
                            aiutiplayer.prepare();
                            aiutiplayer.start();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    countAiuti.getAndDecrement();
                }else{
                    Toast.makeText(curretactivity, "Aiuti disponibili esauriti", Toast.LENGTH_SHORT).show();
                }
            });
            buttonCompletaEsercizio.setOnClickListener(v -> {
                try {
                    AudioConverter.convertFile(audioRecognizer.getAudioFile(),fileConvertito);
                    List<String> words = audioRecognizer.getText();
                    Log.d("Words",words.get(0));
                    uploadFileAsync(fileConvertito, storage.getReference(), curretactivity);
                    if(words.get(0).equals(esercizioDenominazioneImmagine.getParolaEsercizio())){
                        correctplayer.start();
                    }else {
                        errorplayer.start();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            });
        });

    }

    private void startRecording(AudioRecognizer audioRecognizer){
        try{
            audioRecognizer.startRecording();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopRecording(AudioRecognizer audioRecognizer){
        try {
            audioRecognizer.stopRecording();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void uploadFileAsync(File fileToUpload,StorageReference storageReference,Activity currentactivity) {
        CompletableFuture<Void> uploadFuture = uploadFileToStorage(fileToUpload,storageReference,currentactivity);
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

    private void showToast(String message,Activity currentactivity) {
        Toast.makeText(currentactivity, message, Toast.LENGTH_SHORT).show();
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


