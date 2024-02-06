package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.RichiestaConfermaDialog;

public class EsercizioDenominazioneImmagineFragmentCopy extends Fragment {
    private ImageButton buttonAiutiImageView;
    private ImageButton buttonCompletaEsercizioImageView;
    private ImageButton buttonAvviaRegistrazione;
    private View viewOverlayBackground;
    private ImageView immagineEsercizioDenominazioneImageView;
    private View viewSuggestionMic;
    private ScaleAnimation animazioneButtonMic;
    private View viewAnimationMic, viewConfirmMic, viewStopMic;
    private ImageView imageViewConfermaRegistrazione;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla il layout per questo fragment
        View view = inflater.inflate(R.layout.fragment_esercizio_denominazione_immagine_copy, container, false);

        buttonAiutiImageView = view.findViewById(R.id.buttonAiuti);
        buttonCompletaEsercizioImageView = view.findViewById(R.id.buttonCompletaEsercizio);
        buttonAvviaRegistrazione = view.findViewById(R.id.buttonAvviaRegistrazione);
        immagineEsercizioDenominazioneImageView = view.findViewById(R.id.imageViewImmagineEsercizioDenominazione);
        viewOverlayBackground = view.findViewById(R.id.viewOverlaySfondoEsercizioDenominazione);
        viewSuggestionMic = view.findViewById(R.id.textViewEsercizioMicSuggestion);
        viewAnimationMic = view.findViewById(R.id.viewAnimationMic);
        viewConfirmMic = view.findViewById(R.id.viewConfirmMic);
        viewStopMic = view.findViewById(R.id.viewStopRegMic);
        imageViewConfermaRegistrazione = view.findViewById(R.id.confermaRegistrazioneImageView);
        imageViewConfermaRegistrazione.setVisibility(View.INVISIBLE);


        setAnimazioneRegistrazione();
        buttonAvviaRegistrazione.setOnClickListener(v -> {
            setButtonAvviaRegistrazione();
        });

        viewStopMic.setOnClickListener(v -> {
            stopRecording();
        });

        viewConfirmMic.setOnClickListener(v -> {
            RichiestaConfermaDialog richiestaConfermaDialog = new RichiestaConfermaDialog(getContext(), "Sovrascrivi audio?", "Sei sicuro di voler registrare dinuovo la risposta?");
            richiestaConfermaDialog.setOnConfermaButtonClickListener(this::automateStartRecording);
            richiestaConfermaDialog.setOnAnnullaButtonClickListener(() -> {});
            richiestaConfermaDialog.show();
        });

        viewOverlayBackground.setOnClickListener(v -> {
            // Create a fade-in animation with a duration of 500 milliseconds
            AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
            fadeIn.setDuration(500);
            fadeIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    // Optional: You can add code to be executed when the animation starts here
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    viewSuggestionMic.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            viewSuggestionMic.startAnimation(fadeIn);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
                    fadeOut.setDuration(500);
                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            // Optional: You can add code to be executed when the animation starts here
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            viewSuggestionMic.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    // Start the animation set
                    viewSuggestionMic.startAnimation(fadeOut);
                }
            }, 10000);
        });



        return view;
    }

    private void setAnimazioneRegistrazione(){
        animazioneButtonMic = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animazioneButtonMic.setDuration(500); // Durata dell'animazione in millisecondi
        animazioneButtonMic.setRepeatMode(Animation.REVERSE); // Ripetizione dell'animazione in modalità reverse
        animazioneButtonMic.setRepeatCount(Animation.INFINITE); // Ripetizione dell'animazione infinite volte
    }

    private void stopRecording(){
        viewAnimationMic.clearAnimation();
        buttonAvviaRegistrazione.setBackground(getContext().getDrawable(R.drawable.circle_button_mic_background));
        imageViewConfermaRegistrazione.setVisibility(View.VISIBLE);
        viewStopMic.setVisibility(View.GONE);
        viewConfirmMic.setVisibility(View.VISIBLE);
    }

    private void automateStartRecording(){
        buttonAvviaRegistrazione.setBackground(null);
        viewAnimationMic.startAnimation(animazioneButtonMic);
        imageViewConfermaRegistrazione.setVisibility(View.INVISIBLE);
        viewStopMic.setVisibility(View.VISIBLE);
        viewConfirmMic.setVisibility(View.GONE);
    }

    private void setButtonAvviaRegistrazione(){
        automateStartRecording();
        imageViewConfermaRegistrazione.setVisibility(View.INVISIBLE);
        viewStopMic.setVisibility(View.VISIBLE);
        viewConfirmMic.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("Denominazione immagine");
        }
    }
}