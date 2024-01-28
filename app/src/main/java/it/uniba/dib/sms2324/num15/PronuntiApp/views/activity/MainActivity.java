package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.File;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.AudioRecognizer;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.NavigatioItemSelectorLogopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.NavigationItemSelector;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private NavigationItemSelector navigationItemSelector;

    private Button buttonAvviaRegistrazione;
    private Button buttonStopRegistrazione;
    private TextView textViewSpeechToTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAvviaRegistrazione = findViewById(R.id.avviaRegistrazione);
        buttonStopRegistrazione = findViewById(R.id.stopRegistrazione);
        textViewSpeechToTextView = findViewById(R.id.speechToText);

        //BUTTONS FOR TEST
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);

        //CREAZIONE ISTANZA DI AUDIORECOGNIZER
        AudioRecognizer audioRecognizer = new AudioRecognizer(new File (getExternalFilesDir(Environment.DIRECTORY_MUSIC),"test.wav"),this);

        setupButtons(audioRecognizer);

        //--->ASSEGNAZIONE NAV BAR


        //GENITORE
        //bottomNavigationView.inflateMenu(R.menu.bottom_navbar_genitore);
        //navigationItemSelector = new NavigationItemSelectorGenitore(getSupportFragmentManager(), R.id.flFragment);

        //PAZIENTE
        //bottomNavigationView.inflateMenu(R.menu.bottom_navbar_paziente);
        //navigationItemSelector = new NavigationItemSelectorPaziente(getSupportFragmentManager(), R.id.flFragment);

        //LOGOPEDISTA
        bottomNavigationView.inflateMenu(R.menu.bottom_navbar_logopedista);
        navigationItemSelector = new NavigatioItemSelectorLogopedista(getSupportFragmentManager(), R.id.flFragment);


        //first fragment selected
        //bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(0).getItemId());


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return navigationItemSelector.selectItem(item.getItemId());
            }
        });
    }


    private void setupButtons(AudioRecognizer audioRecognizer) {
        buttonAvviaRegistrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioRecognizer.startRecording();
                Toast.makeText(getApplicationContext(), "Registrazione avviata", Toast.LENGTH_SHORT).show();
            }
        });

        buttonStopRegistrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Registrazione interrota", Toast.LENGTH_SHORT).show();
                audioRecognizer.stopRecording();
                try {
                    Thread.sleep(5000);
                    List<String> words =audioRecognizer.getText();
                    textViewSpeechToTextView.setText(words.toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }
}

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("pasquale nodo1");
        myRef.setValue("Prova pasq");
        FirebaseApp.initializeApp(this);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword("tnbwlov@gmail.com", "password")
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("Success","Login avvenuto con successo");
                    } else {
                        Log.e("Faild",task.getException().toString());
                    }
                });
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();
            // Ora puoi utilizzare l'ID dell'utente come necessario
            Log.d("UserID", "UserID: " + userId);
        } else {
            // L'utente non è attualmente autenticato
            Log.d("UserID", "Utente non autenticato");
        }

        firebaseAuth.createUserWithEmailAndPassword("tnbwlov@gmail.com", "password").addOnCompleteListener(this,
                task -> {
                    if (task.isSuccessful()) {
                        Log.d("success","done");
                    } else {
                        Exception exception = task.getException();
                        // Puoi stampare l'errore o utilizzarlo per ulteriori debug
                        Log.e("TAG", "Errore durante la creazione dell'utente", exception);
                    }
                });*/