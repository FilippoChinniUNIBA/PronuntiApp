package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Profilo;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.init_controllers.InitGenitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.init_controllers.InitPaziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.ConnessioneMancanteDialog;

public abstract class AbstractAppActivity extends AppCompatActivity {
    protected BottomNavigationView bottomNavigationView;
    protected NavController navcontroller;


    private Context getThisContext() {
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, filter);
    }

    protected void setOnBackPressedCallback(int id) {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            private boolean doubleBackToExit= false;
            @Override
            public void handleOnBackPressed() {
                if (navcontroller.getCurrentDestination().getId() == id && doubleBackToExit) {
                    finishAffinity();
                } else if (navcontroller.getCurrentDestination().getId() == id) {
                    doubleBackToExit = true;
                    Toast.makeText(getApplicationContext(),getString(R.string.closeApp) , Toast.LENGTH_SHORT).show();
                }
                else {
                    navcontroller.navigate(id);
                }
            }
        });
    }


    private boolean isConnessioneInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!isConnessioneInternet()) {
                Log.d("AbstractAppActivity.BroadcastReceiver()", "Connessione assente");

                ConnessioneMancanteDialog dialog = new ConnessioneMancanteDialog(getThisContext());
                dialog.setOnConfermaButtonClickListener(() -> riavviaApplicazione());
                runOnUiThread(dialog::show);
            }
        }
    };

    private void riavviaApplicazione() {
        runOnUiThread(() -> {
            Intent restartIntent = new Intent(getThisContext(), EntryActivity.class);
            startActivity(restartIntent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (navcontroller.navigateUp()) {
            return true;
        } else {
            return super.onSupportNavigateUp();
        }
    }


    public void navigaConProfilo(Profilo profilo, Context context) {
        if (profilo instanceof Logopedista) {
            Intent intent = buildIntentLogopedista((Logopedista) profilo, context);
            startActivity(intent);
        } else if (profilo instanceof Genitore) {
            InitGenitore.buildIntentGenitore((Genitore) profilo, context).thenAccept(intent -> {
                startActivity(intent);
            });
        } else if (profilo instanceof Paziente) {
            InitPaziente.buildIntentPaziente((Paziente) profilo, context).thenAccept(intent -> {
                startActivity(intent);
            });
        }
    }

    private static Intent buildIntentLogopedista(Logopedista logopedista, Context context) {
        Intent intent = new Intent(context, LogopedistaActivity.class);
        intent.putExtra("mLogopedista", logopedista);
        return intent;
    }

}
