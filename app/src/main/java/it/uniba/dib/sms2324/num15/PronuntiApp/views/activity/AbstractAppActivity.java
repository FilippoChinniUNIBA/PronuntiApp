package it.uniba.dib.sms2324.num15.PronuntiApp.views.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public abstract class AbstractAppActivity extends AppCompatActivity {
    protected BottomNavigationView bottomNavigationView;
    protected NavController navcontroller;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Override
    public boolean onSupportNavigateUp() {
        if (navcontroller.navigateUp()) {
            return true;
        } else {
            return super.onSupportNavigateUp();
        }
    }

}
