package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import java.io.File;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_player.AudioPlayerLink;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_recorder.AudioRecorder;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioDenominazioneImmagineController;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.RichiestaConfermaDialog;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class EsercizioDenominazioneImmagineFragment extends AbstractFragmentWithNavigation {
    private ImageButton buttonAiutiImageView;
    private ImageButton buttonCompletaEsercizioImageView;
    private ImageButton buttonAvviaRegistrazione;
    private View viewOverlayBackground;
    private ImageView immagineEsercizioDenominazioneImageView;
    private View viewSuggestionMic;
    private ScaleAnimation animazioneButtonMic;
    private View viewAnimationMic, viewConfirmMic, viewStopMic;
    private ImageView imageViewConfermaRegistrazione;
    private ConstraintLayout constraintLayoutEsercizioDenominazione;
    private FineEsercizioView fineEsercizioView;


    private AudioRecorder audioRecorder;
    private int countAiuti = 3;
    private PazienteViewModel mPazienteViewModel;
    private EsercizioDenominazioneImmagineController mController;
    private EsercizioDenominazioneImmagine mEsercizioDenominazioneImmagine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_esercizio_denominazione_immagine, container, false);

        this.mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);
        this.mController = mPazienteViewModel.getEsercizioDenominazioneImmagineController();

        constraintLayoutEsercizioDenominazione = view.findViewById(R.id.constraintLayoutEsercizioDenominazioneImmagine);
        constraintLayoutEsercizioDenominazione.setVisibility(View.VISIBLE);

        fineEsercizioView = view.findViewById(R.id.fineEsercizioView);

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

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO: in sto fragment l'esercizio dovrebbe essere passato dalla classe chiamante
        this.mEsercizioDenominazioneImmagine = new EsercizioDenominazioneImmagine(2500, 200, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/pinguino.jpg?alt=media&token=8792af2e-2a3d-4366-9d86-56746a42d2be", "pinguino", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/help.mp3?alt=media&token=89cbfacf-2a02-46c5-986d-29b2d7e2fcdd");

        this.mController.setEsercizioDenominazioneImmagine(mEsercizioDenominazioneImmagine);


        this.audioRecorder = initAudioRecorder();
        Picasso.get().load(mEsercizioDenominazioneImmagine.getImmagineEsercizio()).into(immagineEsercizioDenominazioneImageView);


        buttonAvviaRegistrazione.setOnClickListener(v -> avviaPrimaRegistrazione());
        viewStopMic.setOnClickListener(v -> stoppaRegistrazione());
        viewConfirmMic.setOnClickListener(v -> sovrascriviAudio());

        buttonAiutiImageView.setOnClickListener(v -> riproduciAiuti(mEsercizioDenominazioneImmagine.getAudioAiuto()));
        viewOverlayBackground.setOnClickListener(v -> mostraSuggerimento());

        buttonCompletaEsercizioImageView.setOnClickListener(v -> invalidConferma());
    }

    private void avviaPrimaRegistrazione() {
        if (!checkPermissions(requireActivity())) {
            requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO);
            return;
        }

        avviaRegistrazione();

        imageViewConfermaRegistrazione.setVisibility(View.INVISIBLE);
        viewStopMic.setVisibility(View.VISIBLE);
        viewConfirmMic.setVisibility(View.GONE);

        Toast.makeText(getContext(), getContext().getString(R.string.startedRecording), Toast.LENGTH_SHORT).show();
    }

    private void avviaRegistrazione() {
        buttonAvviaRegistrazione.setBackground(null);
        viewAnimationMic.startAnimation(animazioneButtonMic);
        imageViewConfermaRegistrazione.setVisibility(View.INVISIBLE);
        viewStopMic.setVisibility(View.VISIBLE);
        viewConfirmMic.setVisibility(View.GONE);

        audioRecorder.startRecording();

        buttonCompletaEsercizioImageView.setOnClickListener(v -> invalidConferma());
    }

    private void stoppaRegistrazione() {
        viewAnimationMic.clearAnimation();
        buttonAvviaRegistrazione.setBackground(getContext().getDrawable(R.drawable.circle_button_mic_background));
        imageViewConfermaRegistrazione.setVisibility(View.VISIBLE);
        viewStopMic.setVisibility(View.GONE);
        viewConfirmMic.setVisibility(View.VISIBLE);

        audioRecorder.stopRecording();

        Toast.makeText(getContext(), getContext().getString(R.string.stopedRecording), Toast.LENGTH_SHORT).show();

        buttonCompletaEsercizioImageView.setOnClickListener(v -> completaEsercizio());
    }

    private void riproduciAiuti(String audioAiuto) {
        String aiutiRimasti = null;
        AudioPlayerLink audioPlayerLink = new AudioPlayerLink(audioAiuto);

        switch (countAiuti) {
            case 3:
                audioPlayerLink.playAudio();
                aiutiRimasti = String.valueOf(--countAiuti);
                Toast.makeText(getContext(), (aiutiRimasti + " " + getContext().getString(R.string.aidRemaining)), Toast.LENGTH_SHORT).show();
                break;
            case 2:
                audioPlayerLink.playAudio();
                aiutiRimasti = String.valueOf(--countAiuti);
                Toast.makeText(getContext(), (aiutiRimasti + " " + getContext().getString(R.string.aidRemaining)), Toast.LENGTH_SHORT).show();
                break;
            case 1:
                audioPlayerLink.playAudio();
                --countAiuti;
                Toast.makeText(getContext(), getContext().getString(R.string.noAidRemaining), Toast.LENGTH_SHORT).show();
                break;
            case 0:
                Toast.makeText(getContext(), getContext().getString(R.string.noAidRemaining), Toast.LENGTH_SHORT).show();
        }
    }

    private void invalidConferma() {
        Toast.makeText(getContext(), getContext().getString(R.string.recordBeforeCheck), Toast.LENGTH_SHORT).show();
    }

    private void completaEsercizio() {
        boolean esito;

        if (mController.verificaAudio(audioRecorder.getAudioFile(), getContext())) {
            esito = true;
            fineEsercizioView.setEsercizioCorretto(mEsercizioDenominazioneImmagine.getRicompensaCorretto());
        }
        else {
            esito = false;
            fineEsercizioView.setEsercizioSbagliato(mEsercizioDenominazioneImmagine.getRicompensaErrato());
        }

        constraintLayoutEsercizioDenominazione.setVisibility(View.GONE);

        File temp = mController.convertiAudio(audioRecorder.getAudioFile(), new File(getContext().getFilesDir(), "tempAudioConvertito.mp3"));
        //TODO salvare il file temp su Storage e ottenere link
        String audioRegistrato = "";

        Log.d("EsercizioDenominazioneImmagineFragment.completaEsercizio()", "Esercizio completato: " + mPazienteViewModel.getPaziente());
        mEsercizioDenominazioneImmagine.setRisultatoEsercizio(new RisultatoEsercizioDenominazioneImmagine(esito, audioRegistrato, countAiuti));
        Log.d("EsercizioDenominazioneImmagineFragment.completaEsercizio()", "Esercizio completato: " + mEsercizioDenominazioneImmagine);
        Log.d("EsercizioDenominazioneImmagineFragment.completaEsercizio()", "Esercizio completato: " + mPazienteViewModel.getPaziente());

        //TODO aggiornamento del paziente con l'esito dell'esercizio

        //TODO per Nicola: hai 2 opzioni: o devi settare un listener su fineEsercizioView a fine animazione
        //e fare il navigateTo() in quel listener,
        //oppure devi mettere un onClick sulla schemata finale a fine animazione e fare il navigateTo() in quel onClick
        navigateTo(R.id.action_esercizioDenominazioneImmagineFragment_to_scenarioFragment);
    }

    private void sovrascriviAudio(){
        RichiestaConfermaDialog richiestaConfermaDialog = new RichiestaConfermaDialog(getContext(), getContext().getString(R.string.overwriteAudio), getContext().getString(R.string.overwriteAudioDescription));
        richiestaConfermaDialog.setOnConfermaButtonClickListener(this::avviaRegistrazione);
        richiestaConfermaDialog.setOnAnnullaButtonClickListener(() -> {});
        richiestaConfermaDialog.show();
    }

    private void setAnimazioneRegistrazione() {
        animazioneButtonMic = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animazioneButtonMic.setDuration(500);
        animazioneButtonMic.setRepeatMode(Animation.REVERSE);
        animazioneButtonMic.setRepeatCount(Animation.INFINITE);
    }

    private void mostraSuggerimento() {
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(500);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                viewSuggestionMic.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        viewSuggestionMic.startAnimation(fadeIn);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
                fadeOut.setDuration(500);
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        viewSuggestionMic.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });

                viewSuggestionMic.startAnimation(fadeOut);
            }
        }, 10000);
    }


    private boolean checkPermissions(Activity currentactivity) {
        int recordAudioPermission = ContextCompat.checkSelfPermission(currentactivity, Manifest.permission.RECORD_AUDIO);
        return recordAudioPermission == PackageManager.PERMISSION_GRANTED;
    }

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    this.audioRecorder = initAudioRecorder();
                    avviaPrimaRegistrazione();
                } else {
                    navigateTo(R.id.action_esercizioDenominazioneImmagineFragment_to_scenarioFragment);
                }
            });

    private AudioRecorder initAudioRecorder() {
        File cartellaApp = getContext().getFilesDir();
        File audioRegistrazione = new File(cartellaApp, "tempAudioRegistrato");

        return new AudioRecorder(audioRegistrazione);
    }

}