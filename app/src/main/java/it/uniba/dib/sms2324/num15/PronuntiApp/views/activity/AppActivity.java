package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE.TestMenuTestFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.navBar.NavigatioSelectorLogopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.navBar.NavigationItemSelector;

public class AppActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private NavigationItemSelector navigationItemSelector;

    private Button buttonToTest;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);


        //BUTTONS FOR TEST
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);

        //--->ASSEGNAZIONE NAV BAR


        //GENITORE
        //bottomNavigationView.inflateMenu(R.menu.bottom_navbar_genitore);
        //navigationItemSelector = new NavigationItemSelectorGenitore(getSupportFragmentManager(), R.id.flFragment);

        //PAZIENTE
        //bottomNavigationView.inflateMenu(R.menu.bottom_navbar_paziente);
        //navigationItemSelector = new NavigationItemSelectorPaziente(getSupportFragmentManager(), R.id.flFragment);

        //LOGOPEDISTA
        bottomNavigationView.inflateMenu(R.menu.bottom_navbar_logopedista);
        navigationItemSelector = new NavigatioSelectorLogopedista(getSupportFragmentManager(), R.id.appFrameLayout);


        //first fragment selected
        //bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(0).getItemId());

        bottomNavigationView.setOnItemSelectedListener(item -> navigationItemSelector.selectItem(item.getItemId()));

        //BUTTONS FOR TEST
        buttonToTest = findViewById(R.id.buttonTest);
        buttonToTest.setOnClickListener(v -> {
            TestMenuTestFragment testMenuTestFragment = new TestMenuTestFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.appFrameLayout, testMenuTestFragment)
                    .addToBackStack(null)
                    .commit();
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