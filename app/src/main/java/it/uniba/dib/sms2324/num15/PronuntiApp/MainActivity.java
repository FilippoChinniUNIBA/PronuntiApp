package it.uniba.dib.sms2324.num15.PronuntiApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
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
                        Log.e("Faild","Credenziali errate");
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
        /*
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
    }

}