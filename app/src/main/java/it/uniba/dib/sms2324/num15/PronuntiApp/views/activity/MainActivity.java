package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.HomeFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.RisultatiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ProfileFragment;
    public class MainActivity extends AppCompatActivity
            implements BottomNavigationView
            .OnNavigationItemSelectedListener {

        BottomNavigationView bottomNavigationView;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            bottomNavigationView
                    = findViewById(R.id.bottomNavigationView);

            bottomNavigationView
                    .setOnNavigationItemSelectedListener(this);
            bottomNavigationView.setSelectedItemId(R.id.home);
        }
        HomeFragment firstFragment = new HomeFragment();
        RisultatiFragment secondFragment = new RisultatiFragment();
        ProfileFragment thirdFragment = new ProfileFragment();

        @Override
        public boolean
        onNavigationItemSelected(@NonNull MenuItem item)
        {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, firstFragment).commit();
                return true;
            } else if (itemId == R.id.results) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, secondFragment).commit();
                return true;
            } else if (itemId == R.id.profile) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, thirdFragment).commit();
                return true;
            }

            return false;
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