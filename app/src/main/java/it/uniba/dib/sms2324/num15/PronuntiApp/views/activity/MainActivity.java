package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.List;

import java.io.File;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.AudioRecognizer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        //LOGGARE L'UTENTE
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword("tnbwlov@gmail.com", "password")
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("Success","Login avvenuto con successo");
                    } else {
                        Log.e("Faild",task.getException().toString());
                    }
                });

        //OTTENERE USERID
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            Log.d("UserID", "UserID: " + userId);
        } else {
            Log.d("UserID", "Utente non autenticato");
        }

        //RICHIESTA DEI PERMESSI IN LETTURA E SCRITTURA  DELLA MEMORIA ESTERNA
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        } else {
            Log.d("SUCCESS","Permessi ottenuti correttamente");
        }

        File audiobambino = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC),"audio1");

        AudioRecognizer test = new AudioRecognizer(audiobambino);

        Button myButton = findViewById(R.id.button);
        Button myButton2 = findViewById(R.id.button2);
        Context context = this;
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.startRecording(context);
            }
        });

        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.stopRecording(context);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                List<String> words = null;
                try {
                    words = AudioRecognizer.syncRecognizeFile(test.getAudioFile(),context);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(words!= null){
                    for(String word:words){
                        Log.d("Result",word);
                    }
                }
                assert words != null;
                Toast.makeText(context, words.get(0) , Toast.LENGTH_LONG).show();
            }
        });

    }

}