package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class ScenarioFragment extends AbstractFragmentWithNavigation {
    private float xDelta, yDelta;
    private float bottomHeight;
    private ImageView personaggioImageView;
    private ImageView posizioneGioco1ImageView, posizioneGioco2ImageView, posizioneGioco3ImageView;
    private CurvedLineView curvedLineView1to2, curvedLineView2to3;
    private float personaggioX, personaggioY, personaggioWidth, personaggioHeight;
    private ConstraintLayout constraintLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla il layout del fragment
        View view = inflater.inflate(R.layout.fragment_scenario, container, false);

        //set background e caselle giochi
        constraintLayout = view.findViewById(R.id.constraintLayoutScenario);

        // Trova le view nel layout
        personaggioImageView = view.findViewById(R.id.imageViewPersonaggio);

        posizioneGioco1ImageView= view.findViewById(R.id.posizione_primo_esercizio);
        posizioneGioco2ImageView= view.findViewById(R.id.posizione_secondo_esercizio);
        posizioneGioco3ImageView= view.findViewById(R.id.posizione_terzo_esercizio);


        // Imposta l'immagine delle ImageView dal ViewModel
        constraintLayout.setBackground(getContext().getDrawable(R.drawable.background_space_scenario));
        personaggioImageView.setImageResource(R.drawable.batman);
        posizioneGioco1ImageView.setImageResource(R.drawable.uranio);
        posizioneGioco2ImageView.setImageResource(R.drawable.giove);
        posizioneGioco3ImageView.setImageResource(R.drawable.earth);


        curvedLineView1to2 = view.findViewById(R.id.curvedLineView1to2);
        curvedLineView2to3 = view.findViewById(R.id.curvedLineView2to3);
        personaggioImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Rimuovi il listener una volta che la vista è stata completamente inizializzata
                personaggioImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                // Ora puoi ottenere l'altezza della BottomNavigationView in modo sicuro
                /*int bottomNavHeight = ((PazienteActivity) getActivity()).getBottomNavBar().getHeight();
                Log.d("PazienteActivity", "BottomNavHeight: " + bottomNavHeight);
                */
                // Abilita il drag dell'immagine
                bottomHeight= personaggioImageView.getHeight()+ personaggioImageView.getHeight()*0.2f;
                Log.d("Altezza minima personaggio", String.valueOf(bottomHeight));
                enableImageDrag(personaggioImageView);
            }
        });

        posizioneGioco3ImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Rimuovi il listener una volta che la vista è stata completamente inizializzata
                posizioneGioco3ImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                curvedLineView1to2.setStartPoint(posizioneGioco1ImageView.getX() + posizioneGioco1ImageView.getWidth() / 2, posizioneGioco1ImageView.getY() + posizioneGioco1ImageView.getHeight() / 2);
                curvedLineView1to2.setEndPoint(posizioneGioco2ImageView.getX() + posizioneGioco2ImageView.getWidth() / 2, posizioneGioco2ImageView.getY() + posizioneGioco2ImageView.getHeight() / 2);
                curvedLineView2to3.setStartPoint(posizioneGioco2ImageView.getX() + posizioneGioco2ImageView.getWidth() / 2, posizioneGioco2ImageView.getY() + posizioneGioco2ImageView.getHeight() / 2);
                curvedLineView2to3.setEndPoint(posizioneGioco3ImageView.getX() + posizioneGioco3ImageView.getWidth() / 2, posizioneGioco3ImageView.getY() + posizioneGioco3ImageView.getHeight() / 2);
            }
        });
        return view;
    }

    private void setPersonaggioPosition() {
        personaggioX = personaggioImageView.getX();
        personaggioY = personaggioImageView.getY();
        personaggioWidth = personaggioImageView.getWidth();
        personaggioHeight = personaggioImageView.getHeight();
    }

    private void highlightArea(ImageView imageView) {
        imageView.setBackground(getContext().getDrawable(R.drawable.esercizio_highlight_background));
    }

    private boolean isPersonaggioInAreaPrimoEsercizio() {
        setPersonaggioPosition();
        float areaPrimoEsercizioX = posizioneGioco1ImageView.getX();
        float areaPrimoEsercizioY = posizioneGioco1ImageView.getY();
        float areaPrimoEsercizioWidth = posizioneGioco1ImageView.getWidth();
        float areaPrimoEsercizioHeight = posizioneGioco1ImageView.getHeight();

        return personaggioX < (areaPrimoEsercizioX + areaPrimoEsercizioWidth) &&
                (personaggioX + personaggioWidth) > areaPrimoEsercizioX &&
                personaggioY < (areaPrimoEsercizioY + areaPrimoEsercizioHeight) &&
                (personaggioY + personaggioHeight) > areaPrimoEsercizioY;
    }

    private boolean isPersonaggioInAreaSecondaEsercizio() {
        setPersonaggioPosition();
        float areaSecondoEsercizioX = posizioneGioco2ImageView.getX();
        float areaSecondoEsercizioY = posizioneGioco2ImageView.getY();
        float areaSecondoEsercizioWidth = posizioneGioco2ImageView.getWidth();
        float areaSecondoEsercizioHeight = posizioneGioco2ImageView.getHeight();

        return personaggioX < (areaSecondoEsercizioX + areaSecondoEsercizioWidth) &&
                (personaggioX + personaggioWidth) > areaSecondoEsercizioX &&
                personaggioY < (areaSecondoEsercizioY + areaSecondoEsercizioHeight) &&
                (personaggioY + personaggioHeight) > areaSecondoEsercizioY;
    }

    private boolean isPersonaggioInAreaTerzoEsercizio() {
        setPersonaggioPosition();
        float areaTerzoEsercizioX = posizioneGioco3ImageView.getX();
        float areaTerzoEsercizioY = posizioneGioco3ImageView.getY();
        float areaTerzoEsercizioWidth = posizioneGioco3ImageView.getWidth();
        float areaTerzoEsercizioHeight = posizioneGioco3ImageView.getHeight();

        return personaggioX < (areaTerzoEsercizioX + areaTerzoEsercizioWidth) &&
                (personaggioX + personaggioWidth) > areaTerzoEsercizioX &&
                personaggioY < (areaTerzoEsercizioY + areaTerzoEsercizioHeight) &&
                (personaggioY + personaggioHeight) > areaTerzoEsercizioY;
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

                        if(isPersonaggioInAreaPrimoEsercizio()) {
                            highlightArea(posizioneGioco1ImageView);
                            posizioneGioco2ImageView.setBackground(null);
                            posizioneGioco3ImageView.setBackground(null);
                        }
                        else if(isPersonaggioInAreaSecondaEsercizio()){
                            highlightArea(posizioneGioco2ImageView);
                            posizioneGioco1ImageView.setBackground(null);
                            posizioneGioco3ImageView.setBackground(null);
                        }
                        else if(isPersonaggioInAreaTerzoEsercizio()) {
                            highlightArea(posizioneGioco3ImageView);
                            posizioneGioco1ImageView.setBackground(null);
                            posizioneGioco2ImageView.setBackground(null);
                        }
                        else{
                            posizioneGioco1ImageView.setBackground(null);
                            posizioneGioco2ImageView.setBackground(null);
                            posizioneGioco3ImageView.setBackground(null);
                        }

                        // Verifica che la ImageView non esca dalla schermata
                        if (newX > 0 && newX < (getScreenWidth() - v.getWidth())) {
                            v.setX(newX);
                        }

                        if (newY > 0 && newY < getScreenHeight() - v.getHeight()-bottomHeight) {
                            v.setY(newY);
                        }

                        break;

                    case MotionEvent.ACTION_UP:
                        //controlla dove sta il personaggio e naviga al gioco corrispondente
                        if(isPersonaggioInAreaPrimoEsercizio()) {
                            Log.d("Personaggio", "in area primo esercizio in esecuzione");
                            navigateTo(R.id.action_scenarioFragment_to_esercizioDenominazioneImmagineFragment2);
                        }
                        else if(isPersonaggioInAreaSecondaEsercizio()) {
                            navigateTo(R.id.action_scenarioFragment_to_esercizioCoppiaImmagini2);
                        }
                        else if(isPersonaggioInAreaTerzoEsercizio()) {
                            navigateTo(R.id.action_scenarioFragment_to_esercizioSequenzaParole);
                        }
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
