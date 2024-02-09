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

        if (!checkPermissions(requireActivity())) {
            requestPermissions(requireActivity());
        }

        File cartellaApp = getContext().getFilesDir();
        File audioRegistrazione = new File(cartellaApp, "tempAudioRegistrato");
        File audioConvertito = new File(cartellaApp, "audioConvertito.mp3");

        this.audioRecorder = new AudioRecorder(audioRegistrazione);

        //TODO: in sto fragment l'esercizio dovrebbe essere passato dalla classe chiamante

        String immagineEsercizio = "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/pinguino.jpg?alt=media&token=8792af2e-2a3d-4366-9d86-56746a42d2be";
        String parolaEsercizio = "pinguino";
        String audioAiuto = "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/help.mp3?alt=media&token=89cbfacf-2a02-46c5-986d-29b2d7e2fcdd";
        this.mEsercizioDenominazioneImmagine = new EsercizioDenominazioneImmagine(2500, 200, new File("fake"), parolaEsercizio, new File("fake"));
        this.mController.setEsercizioDenominazioneImmagine(mEsercizioDenominazioneImmagine);


        Picasso.get().load(immagineEsercizio).into(immagineEsercizioDenominazioneImageView);

        buttonAvviaRegistrazione.setOnClickListener(v -> avviaPrimaRegistrazione());
        viewStopMic.setOnClickListener(v -> stoppaRegistrazione());
        viewConfirmMic.setOnClickListener(v -> sovrascriviAudio());

        buttonAiutiImageView.setOnClickListener(v -> riproduciAiuti(audioAiuto));
        viewOverlayBackground.setOnClickListener(v -> mostraSuggerimento());

        buttonCompletaEsercizioImageView.setOnClickListener(v -> invalidConferma());
    }

    private void avviaPrimaRegistrazione() {
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

    /*private CompletableFuture<String> getUrlFromStorageReference(StorageReference reference) {
        CompletableFuture<String> future = new CompletableFuture<>();

        reference.getDownloadUrl().addOnSuccessListener(uri -> {
            String imageUrl = uri.toString();
            future.complete(imageUrl);
        }).addOnFailureListener(e -> {
            future.completeExceptionally(e);
        });

        return future;
    }*/

    /*private CompletableFuture<Void> uploadFileToStorage(File file, StorageReference storageReference, Activity activity) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        StorageReference fileReference = storageReference.child(file.getName());

        try {
            FileInputStream stream = new FileInputStream(file);
            UploadTask uploadTask = fileReference.putStream(stream);

            uploadTask.addOnSuccessListener(taskSnapshot -> {
                future.complete(null);
            }).addOnFailureListener(exception -> {
                future.completeExceptionally(exception);
            });
        } catch (IOException e) {
            future.completeExceptionally(e);
        }
        return future;
    }*/

    private void completaEsercizio() {
        boolean esito;

        if (mController.verificaAudio(audioRecorder.getAudioFile(), getContext())) {
            esito = true;
            constraintLayoutEsercizioDenominazione.setVisibility(View.GONE);
            fineEsercizioView.setEsercizioCorretto(mEsercizioDenominazioneImmagine.getRicompensaCorretto());
        }
        else {
            esito = false;
            constraintLayoutEsercizioDenominazione.setVisibility(View.GONE);
            fineEsercizioView.setEsercizioSbagliato(mEsercizioDenominazioneImmagine.getRicompensaErrato());
        }

        mController.convertiAudio(audioRecorder.getAudioFile());
        Log.d("EsercizioDenominazioneImmagineFragment.completaEsercizio()", "Esercizio completato: " + mPazienteViewModel.getPaziente());
        mEsercizioDenominazioneImmagine.setRisultatoEsercizio(new RisultatoEsercizioDenominazioneImmagine(esito, audioRecorder.getAudioFile(), countAiuti));
        Log.d("EsercizioDenominazioneImmagineFragment.completaEsercizio()", "Esercizio completato: " + mEsercizioDenominazioneImmagine);
        Log.d("EsercizioDenominazioneImmagineFragment.completaEsercizio()", "Esercizio completato: " + mPazienteViewModel.getPaziente());
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

    private void sovrascriviAudio(){
        RichiestaConfermaDialog richiestaConfermaDialog = new RichiestaConfermaDialog(getContext(), getContext().getString(R.string.overwriteAudio), getContext().getString(R.string.overwriteAudioDescription));
        richiestaConfermaDialog.setOnConfermaButtonClickListener(this::avviaRegistrazione);
        richiestaConfermaDialog.setOnAnnullaButtonClickListener(() -> {});
        richiestaConfermaDialog.show();
    }

    private boolean checkPermissions(Activity currentactivity) {
        int readStoragePermission = ContextCompat.checkSelfPermission(currentactivity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int writeStoragePermission = ContextCompat.checkSelfPermission(currentactivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int recordAudioPermission = ContextCompat.checkSelfPermission(currentactivity, Manifest.permission.RECORD_AUDIO);

        return readStoragePermission == PackageManager.PERMISSION_GRANTED &&
                writeStoragePermission == PackageManager.PERMISSION_GRANTED &&
                recordAudioPermission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions(Activity currentactivity) {
        ActivityCompat.requestPermissions(currentactivity, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, 1000);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("Denominazione immagine");
        }
    }
}