package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.PazienteActivity;

public class ScenarioFragment extends Fragment {
    private float xDelta, yDelta;
    private int bottomHeight;
    private TextView scenarioTextView;
    private ImageView personaggioImageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla il layout del fragment
        View view = inflater.inflate(R.layout.fragment_scenario, container, false);

        // Trova le view nel layout
        scenarioTextView = view.findViewById(R.id.scenarioFragment);
        personaggioImageView = view.findViewById(R.id.imageViewPersonaggio);

        // Imposta il testo del TextView
        scenarioTextView.setText("Scenario");
        // Imposta l'immagine della ImageView (assicurati che l'immagine "batman" sia presente nella cartella drawable)
        personaggioImageView.setImageResource(R.drawable.batman);


        personaggioImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Rimuovi il listener una volta che la vista è stata completamente inizializzata
                personaggioImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                // Ora puoi ottenere l'altezza della BottomNavigationView in modo sicuro
                int bottomNavHeight = ((PazienteActivity) getActivity()).getBottomNavBar().getHeight();
                Log.d("PazienteActivity", "BottomNavHeight: " + bottomNavHeight);
                // Abilita il drag dell'immagine
                bottomHeight= personaggioImageView.getHeight() + bottomNavHeight;
                enableImageDrag(personaggioImageView);
            }
        });
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void enableImageDrag(ImageView view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final float x = event.getRawX();
                final float y = event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        // Salva le coordinate iniziali del tocco
                        xDelta = x - v.getX();
                        yDelta = y - v.getY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        // Calcola la nuova posizione della ImageView
                        float newX = x - xDelta;
                        float newY = y - yDelta;

                        // Verifica che la ImageView non esca dalla schermata
                        if (newX > 0 && newX < (getScreenWidth() - v.getWidth())) {
                            v.setX(newX);
                        }

                        if (newY > 0 && newY < getScreenHeight() - v.getHeight()-bottomHeight) {
                            v.setY(newY);
                        }

                        break;
                }
                return true;
            }
        });
    }

    private int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    private int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("Giochi del bambino");
           }
    }
}
