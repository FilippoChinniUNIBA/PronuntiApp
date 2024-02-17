package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti.terapie;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.database_utils.ComandiFirebaseStorage;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.Esercizio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioEseguibile;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_recorder.AudioRecorder;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.InfoDialog;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.PermessiDialog;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class CreazioneEsercizioFragment extends AbstractFragmentWithNavigation {

    private ScaleAnimation animazioneButtonMic;
    private AudioRecorder audioRecorder;
    private static final int PICK_FILE_REQUEST_1 = 1;
    private static final int PICK_FILE_REQUEST_2 = 2;
    private static final int PICK_FILE_REQUEST_3 = 3;
    private static int countEsercizio = 1;


    //titolo
    private TextView textViewTitleEsercizio;
    private ImageView imageViewArrowDown;
    private LinearLayout linearLayoutTitleEsercizio;

    //aggiungi esercizio
    private LinearLayout linearLayoutAggiungiEsercizio;

    //tipo esercizio
    private LinearLayout linearLayoutTipoEsercizio;
    private Button buttonEsercizioDenominazioneImmagine;
    private Button buttonEsercizioSequenzaParole;
    private Button buttonEsercizioCoppiaImmagini;

    //scelta template o creazione
    private LinearLayout linearLayoutSceltaTemplateEsercizio;
    private Button buttonTemplateEsercizio;
    private Button buttonCreaEsercizio;

    //template
    private LinearLayout linearLayoutTemplateEsercizio;

    //template denominazione
    private CardView cardViewTemplateEsercizioDenominazioneImmagine;
    private ImageButton buttonTemplateDenominazioneImmagineBack;
    private TextView textViewParoleTemplateDenominazioneImmagine;
    private ImageButton buttonTemplateDenominazioneImmagineNext;

    //template sequenza
    private CardView cardViewTemplateEsercizioSequenzaParole;
    private ImageButton buttonTemplateSequenzaParoleBack;
    private TextView textViewParoleTemplateSequenzaParole1, textViewParoleTemplateSequenzaParole2, textViewParoleTemplateSequenzaParole3;
    private ImageButton buttonTemplateSequenzaParoleNext;

    //template coppia
    private CardView cardViewTemplateEsercizioCoppiaImmagini;
    private ImageButton buttonTemplateCoppiaImmagineBack;
    private ImageView templateCoppiaImmagine;
    private ImageButton buttonTemplateCoppiaImmagineNext;

    //creazione
    private LinearLayout linearLayoutCreazioneEsercizio;

    //creazione denominazione
    private CardView cardViewCreazioneEsercizioDenominazioneImmagine;
    private ImageButton buttonAudioDenominazione;
    private View viewAnimationAudioDenominazione, viewStopAudioDenominazione;
    private TextInputEditText textInputEditTextRispostaEsercizioDenominaizone;
    private ImageView buttonOpenFilePickerImmagineEsercizioDenominazione;
            private String immagineEsercizioDenominazione, audioDenominazione;
    private TextInputEditText textInputEditTextRicompensaCorrettoDenominazione, textInputEditTextRicompensaErratoDenominazione;
    private Button buttonConfermaEsercizioDenominazione;

    //creazione sequenza parole
    private CardView cardViewCreazioneEsercizioSequenzaParole;
    private ImageButton buttonAudioSequenzaParole;
    private View viewAnimationAudioSequenzaParole, viewStopAudioSequenzaParole;
            private String audioSequenzaParole;
    private TextInputEditText textInputEditTextParola1, textInputEditTextParola2, textInputEditTextParola3;
    private TextInputEditText textInputEditTextRicompensaCorrettoSequenzaParole, textInputEditTextRicompensaErratoSequenzaParole;
    private Button buttonConfermaEsercizioSequenzaParole;

    //creazione coppia immagini
    private CardView cardViewCreazioneEsercizioCoppiaImmagini;
    private ImageButton buttonAudioCoppia;
    private View viewAnimationAudioCoppia, viewStopAudioCoppia;
    private ImageView buttonOpenFilePickerImmagineCoppiaCorretta, buttonOpenFilePickerImmagineCoppiaErrata;
            private String immagineCoppiaCorretta, immagineCoppiaErrata, audioCoppia;
    private TextInputEditText textInputEditTextRicompensaCorrettoCoppiaImmagine, textInputEditTextRicompensaErratoCoppiaImmagine;
    private Button buttonConfermaEsercizioCoppiaImmagine;


    //indici per scorrere le liste dei template
    private int indexTemplateDenominazioneImmagine = 0;
    private int indexTemplateSequenzaParole = 0;
    private int indexTemplateCoppiaImmagini = 0;


    //esercizio che creo
    private EsercizioEseguibile esercizio;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_creazione_esercizio, container, false);

        //titolo
        textViewTitleEsercizio = view.findViewById(R.id.textViewTitleEsercizio);
        imageViewArrowDown = view.findViewById(R.id.imageViewArrowDown);
        linearLayoutTitleEsercizio = view.findViewById(R.id.linearLayoutTitleEsercizio);

        //aggiungi esercizio
        linearLayoutAggiungiEsercizio = view.findViewById(R.id.linearLayoutAggiungiEsercizio);

        //tipo esercizio
        linearLayoutTipoEsercizio = view.findViewById(R.id.linearLayoutTipoEsercizio);
        buttonEsercizioDenominazioneImmagine = view.findViewById(R.id.buttonEsercizioDenominazioneImmagine);
        buttonEsercizioSequenzaParole = view.findViewById(R.id.buttonEsercizioSequenzaParole);
        buttonEsercizioCoppiaImmagini = view.findViewById(R.id.buttonEsercizioCoppiaImmagini);

        //scelta template o creazione
        linearLayoutSceltaTemplateEsercizio = view.findViewById(R.id.linearLayoutSceltaTemplateEsercizio);
        buttonTemplateEsercizio = view.findViewById(R.id.buttonTemplateEsercizio);
        buttonCreaEsercizio = view.findViewById(R.id.buttonCreaEsercizio);

        //template
        linearLayoutTemplateEsercizio = view.findViewById(R.id.linearLayoutTemplateEsercizio);

        //template denominazione
        cardViewTemplateEsercizioDenominazioneImmagine = view.findViewById(R.id.cardViewTemplateEsercizioDenominazioneImmagine);
        buttonTemplateDenominazioneImmagineBack = view.findViewById(R.id.buttonTemplateDenominazioneImmagineBack);
        textViewParoleTemplateDenominazioneImmagine = view.findViewById(R.id.textViewParoleTemplateDenominazioneImmagine);
        buttonTemplateDenominazioneImmagineNext = view.findViewById(R.id.buttonTemplateDenominazioneImmagineNext);

        //template sequenza
        cardViewTemplateEsercizioSequenzaParole = view.findViewById(R.id.cardViewTemplateEsercizioSequenzaParole);
        buttonTemplateSequenzaParoleBack = view.findViewById(R.id.buttonTemplateSequenzaParoleBack);
        textViewParoleTemplateSequenzaParole1 = view.findViewById(R.id.textViewParoleTemplateSequenzaParole1);
        textViewParoleTemplateSequenzaParole2 = view.findViewById(R.id.textViewParoleTemplateSequenzaParole2);
        textViewParoleTemplateSequenzaParole3 = view.findViewById(R.id.textViewParoleTemplateSequenzaParole3);
        buttonTemplateSequenzaParoleNext = view.findViewById(R.id.buttonTemplateSequenzaParoleNext);

        //template coppia
        cardViewTemplateEsercizioCoppiaImmagini = view.findViewById(R.id.cardViewTemplateEsercizioCoppiaImmagini);
        buttonTemplateCoppiaImmagineBack = view.findViewById(R.id.buttonTemplateCoppiaImmagineBack);
        templateCoppiaImmagine = view.findViewById(R.id.templateCoppiaImmagine);
        buttonTemplateCoppiaImmagineNext = view.findViewById(R.id.buttonTemplateCoppiaImmagineNext);

        //creazione
        linearLayoutCreazioneEsercizio = view.findViewById(R.id.linearLayoutCreazioneEsercizio);

        //creazione denominazione
        cardViewCreazioneEsercizioDenominazioneImmagine = view.findViewById(R.id.cardViewCreazioneEsercizioDenominazioneImmagine);
        buttonAudioDenominazione = view.findViewById(R.id.buttonAudioDenominazione);
        viewAnimationAudioDenominazione = view.findViewById(R.id.viewAnimationAudioDenominazione);
        viewStopAudioDenominazione = view.findViewById(R.id.viewStopAudioDenominazione);
        textInputEditTextRispostaEsercizioDenominaizone = view.findViewById(R.id.textInputEditTextRispostaEsercizioDenominaizone);
        textInputEditTextRicompensaCorrettoDenominazione = view.findViewById(R.id.textInputEditTextRicompensaCorrettoDenominazione);
        textInputEditTextRicompensaErratoDenominazione = view.findViewById(R.id.textInputEditTextRicompensaErratoDenominazione);
        buttonConfermaEsercizioDenominazione = view.findViewById(R.id.buttonConfermaEsercizioDenominazione);
        buttonOpenFilePickerImmagineEsercizioDenominazione = view.findViewById(R.id.buttonOpenFilePickerImmagineEsercizioDenominazione);

        //creazione sequenza parole
        cardViewCreazioneEsercizioSequenzaParole = view.findViewById(R.id.cardViewCreazioneEsercizioSequenzaParole);
        buttonAudioSequenzaParole = view.findViewById(R.id.buttonAudioSequenzaParole);
        viewAnimationAudioSequenzaParole = view.findViewById(R.id.viewAnimationAudioSequenzaParole);
        viewStopAudioSequenzaParole = view.findViewById(R.id.viewStopAudioSequenzaParole);
        textInputEditTextParola1 = view.findViewById(R.id.textInputEditTextParola1);
        textInputEditTextParola2 = view.findViewById(R.id.textInputEditTextParola2);
        textInputEditTextParola3 = view.findViewById(R.id.textInputEditTextParola3);
        textInputEditTextRicompensaCorrettoSequenzaParole = view.findViewById(R.id.textInputEditTextRicompensaCorrettoSequenzaParole);
        textInputEditTextRicompensaErratoSequenzaParole = view.findViewById(R.id.textInputEditTextRicompensaErratoSequenzaParole);
        buttonConfermaEsercizioSequenzaParole = view.findViewById(R.id.buttonConfermaEsercizioSequenzaParole);

        //creazione coppia immagini
        cardViewCreazioneEsercizioCoppiaImmagini = view.findViewById(R.id.cardViewCreazioneEsercizioCoppiaImmagini);
        buttonAudioCoppia = view.findViewById(R.id.buttonAudioCoppia);
        viewAnimationAudioCoppia = view.findViewById(R.id.viewAnimationAudioCoppia);
        viewStopAudioCoppia = view.findViewById(R.id.viewStopAudioCoppia);
        buttonOpenFilePickerImmagineCoppiaCorretta = view.findViewById(R.id.buttonOpenFilePickerImmagineCoppiaCorretta);
        buttonOpenFilePickerImmagineCoppiaErrata = view.findViewById(R.id.buttonOpenFilePickerImmagineCoppiaErrata);
        textInputEditTextRicompensaCorrettoCoppiaImmagine = view.findViewById(R.id.textInputEditTextRicompensaCorrettoCoppiaImmagine);
        textInputEditTextRicompensaErratoCoppiaImmagine = view.findViewById(R.id.textInputEditTextRicompensaErratoCoppiaImmagine);
        buttonConfermaEsercizioCoppiaImmagine = view.findViewById(R.id.buttonConfermaEsercizioCoppiaImmagini);


        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewTitleEsercizio.setText(textViewTitleEsercizio.getText().toString() +" "+ countEsercizio);

        if(countEsercizio==3)
            countEsercizio=1;
        else countEsercizio++;


        linearLayoutAggiungiEsercizio.setVisibility(View.GONE);
        linearLayoutTipoEsercizio.setVisibility(View.VISIBLE);
        linearLayoutSceltaTemplateEsercizio.setVisibility(View.GONE);
        viewStopAudioDenominazione.setVisibility(View.GONE);
        viewStopAudioSequenzaParole.setVisibility(View.GONE);
        viewStopAudioCoppia.setVisibility(View.GONE);
        viewAnimationAudioDenominazione.setVisibility(View.GONE);
        viewAnimationAudioSequenzaParole.setVisibility(View.GONE);
        viewAnimationAudioCoppia.setVisibility(View.GONE);

        //set onclick su button picker immagine
        buttonOpenFilePickerImmagineEsercizioDenominazione.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_1));
        buttonOpenFilePickerImmagineCoppiaCorretta.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_2));
        buttonOpenFilePickerImmagineCoppiaErrata.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_3));

        //set onclik su button conferma esercizio
        buttonConfermaEsercizioDenominazione.setOnClickListener(v -> createEsercizioDenominazioneImmagine());
        buttonConfermaEsercizioSequenzaParole.setOnClickListener(v -> createEsercizioSequenzaParole());
        buttonConfermaEsercizioCoppiaImmagine.setOnClickListener(v -> createEsercizioCoppiaImmagini());

        //set onclick su microfono
        this.audioRecorder = initAudioRecorder();
        setAnimazioneRegistrazione();
        buttonAudioDenominazione.setOnClickListener(v -> avviaPrimaRegistrazione(buttonAudioDenominazione, viewStopAudioDenominazione, viewAnimationAudioDenominazione));
        viewStopAudioDenominazione.setOnClickListener(v -> audioDenominazione=stoppaRegistrazione(buttonAudioDenominazione, viewStopAudioDenominazione, viewAnimationAudioDenominazione));
        buttonAudioSequenzaParole.setOnClickListener(v -> avviaPrimaRegistrazione(buttonAudioSequenzaParole, viewStopAudioSequenzaParole, viewAnimationAudioSequenzaParole));
        viewStopAudioSequenzaParole.setOnClickListener(v -> audioSequenzaParole=stoppaRegistrazione(buttonAudioSequenzaParole, viewStopAudioSequenzaParole, viewAnimationAudioSequenzaParole));
        buttonAudioCoppia.setOnClickListener(v -> avviaPrimaRegistrazione(buttonAudioCoppia, viewStopAudioCoppia, viewAnimationAudioCoppia));
        viewStopAudioCoppia.setOnClickListener(v -> audioCoppia=stoppaRegistrazione(buttonAudioCoppia, viewStopAudioCoppia, viewAnimationAudioCoppia));

        //set frecce avanti e indietro tra i template

        //denominazione
        buttonTemplateDenominazioneImmagineBack.setOnClickListener(v -> {
            indexTemplateDenominazioneImmagine--;
            if(indexTemplateDenominazioneImmagine < 0) indexTemplateDenominazioneImmagine = 0;
            mostraTemplateDenominazioneImmagine();
        });
        buttonTemplateDenominazioneImmagineNext.setOnClickListener(v -> {
            indexTemplateDenominazioneImmagine++;
            if(indexTemplateDenominazioneImmagine > 0) indexTemplateDenominazioneImmagine = 0;
            mostraTemplateDenominazioneImmagine();
        });

        //sequenza
        buttonTemplateSequenzaParoleBack.setOnClickListener(v -> {
            indexTemplateSequenzaParole--;
            if(indexTemplateSequenzaParole < 0) indexTemplateSequenzaParole = 0;
            mostraTemplateSequenzaParole();
        });
        buttonTemplateSequenzaParoleNext.setOnClickListener(v -> {
            indexTemplateSequenzaParole++;
            if(indexTemplateSequenzaParole > 0) indexTemplateSequenzaParole = 0;
            mostraTemplateSequenzaParole();
        });

        //coppia
        buttonTemplateCoppiaImmagineBack.setOnClickListener(v -> {
            indexTemplateCoppiaImmagini--;
            if(indexTemplateCoppiaImmagini < 0) indexTemplateCoppiaImmagini = 0;
            mostraTemplateCoppiaImmagini();
        });
        buttonTemplateCoppiaImmagineNext.setOnClickListener(v -> {
            indexTemplateCoppiaImmagini++;
            if(indexTemplateCoppiaImmagini > 0) indexTemplateCoppiaImmagini = 0;
            mostraTemplateCoppiaImmagini();
        });

        //mostra nascondi esercizio
        linearLayoutTitleEsercizio.setOnClickListener(v -> {
            if (linearLayoutAggiungiEsercizio.getVisibility() == View.VISIBLE) {
                linearLayoutAggiungiEsercizio.setVisibility(View.GONE);
                imageViewArrowDown.setRotation(0);
            } else {
                linearLayoutAggiungiEsercizio.setVisibility(View.VISIBLE);
                imageViewArrowDown.setRotation(180);
            }
        });

        buttonEsercizioDenominazioneImmagine.setOnClickListener(v -> scegliTipoEsercizio(1));
        buttonEsercizioSequenzaParole.setOnClickListener(v -> scegliTipoEsercizio(2));
        buttonEsercizioCoppiaImmagini.setOnClickListener(v -> scegliTipoEsercizio(3));

    }

    private void startFilePicker(int requestCode) {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[] {"image/*"});

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), requestCode);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            ComandiFirebaseStorage comandiFirebaseStorage = new ComandiFirebaseStorage();
            comandiFirebaseStorage.uploadFileAndGetLink(uri, "TEST/TERPIA_TEST").thenAccept(link -> {

                switch (requestCode) {
                    case PICK_FILE_REQUEST_1:
                        immagineEsercizioDenominazione = link;
                        Glide.with(getContext()).load(uri).into(buttonOpenFilePickerImmagineEsercizioDenominazione);
                        break;
                    case PICK_FILE_REQUEST_2:
                        immagineCoppiaCorretta = link;
                        Glide.with(getContext()).load(uri).into(buttonOpenFilePickerImmagineCoppiaCorretta);
                        break;
                    case PICK_FILE_REQUEST_3:
                        immagineCoppiaErrata = link;
                        Glide.with(getContext()).load(uri).into(buttonOpenFilePickerImmagineCoppiaErrata);
                        break;
                    default:
                        break;
                }
            });
        }
    }

    private void scegliTipoEsercizio(int tipoEsercizio) {
        linearLayoutTipoEsercizio.setVisibility(View.GONE);
        linearLayoutSceltaTemplateEsercizio.setVisibility(View.VISIBLE);

        if(tipoEsercizio == 1) {
            buttonTemplateEsercizio.setOnClickListener(v -> mostraTemplateEsercizio(1));
            buttonCreaEsercizio.setOnClickListener(v -> mostraCreazioneEsercizio(1));
        } else if(tipoEsercizio == 2) {
            buttonTemplateEsercizio.setOnClickListener(v -> mostraTemplateEsercizio(2));
            buttonCreaEsercizio.setOnClickListener(v -> mostraCreazioneEsercizio(2));
        } else if(tipoEsercizio == 3) {
            buttonTemplateEsercizio.setOnClickListener(v -> mostraTemplateEsercizio(3));
            buttonCreaEsercizio.setOnClickListener(v -> mostraCreazioneEsercizio(3));
        }

    }

    private void mostraCreazioneEsercizio(int tipoEsercizio){
        linearLayoutSceltaTemplateEsercizio.setVisibility(View.GONE);
        linearLayoutCreazioneEsercizio.setVisibility(View.VISIBLE);

        if(tipoEsercizio == 1){
            cardViewCreazioneEsercizioDenominazioneImmagine.setVisibility(View.VISIBLE);
            cardViewCreazioneEsercizioSequenzaParole.setVisibility(View.GONE);
            cardViewCreazioneEsercizioCoppiaImmagini.setVisibility(View.GONE);
        } else if(tipoEsercizio == 2){
            cardViewCreazioneEsercizioDenominazioneImmagine.setVisibility(View.GONE);
            cardViewCreazioneEsercizioSequenzaParole.setVisibility(View.VISIBLE);
            cardViewCreazioneEsercizioCoppiaImmagini.setVisibility(View.GONE);
        } else if(tipoEsercizio == 3){
            cardViewCreazioneEsercizioDenominazioneImmagine.setVisibility(View.GONE);
            cardViewCreazioneEsercizioSequenzaParole.setVisibility(View.GONE);
            cardViewCreazioneEsercizioCoppiaImmagini.setVisibility(View.VISIBLE);
        }
    }

    private void mostraTemplateEsercizio(int tipoEsercizio) {
        linearLayoutSceltaTemplateEsercizio.setVisibility(View.GONE);
        linearLayoutTemplateEsercizio.setVisibility(View.VISIBLE);

        if(tipoEsercizio == 1) {
            cardViewTemplateEsercizioDenominazioneImmagine.setVisibility(View.VISIBLE);
            cardViewTemplateEsercizioSequenzaParole.setVisibility(View.GONE);
            cardViewTemplateEsercizioCoppiaImmagini.setVisibility(View.GONE);
            mostraTemplateDenominazioneImmagine();
        } else if(tipoEsercizio == 2) {
            cardViewTemplateEsercizioDenominazioneImmagine.setVisibility(View.GONE);
            cardViewTemplateEsercizioSequenzaParole.setVisibility(View.VISIBLE);
            cardViewTemplateEsercizioCoppiaImmagini.setVisibility(View.GONE);
            mostraTemplateSequenzaParole();
        } else if(tipoEsercizio == 3) {
            cardViewTemplateEsercizioDenominazioneImmagine.setVisibility(View.GONE);
            cardViewTemplateEsercizioSequenzaParole.setVisibility(View.GONE);
            cardViewTemplateEsercizioCoppiaImmagini.setVisibility(View.VISIBLE);
            mostraTemplateCoppiaImmagini();
        }
    }

    private void mostraTemplateDenominazioneImmagine() {
        //TODO prendere la lista di teplate denominazione dal db o viewModel non lo so com è
        // fare il get della lista di template denominazione immagine con indice=indexTemplateDenominazioneImmagine
        // ottenere i dati dal teplate e passarli al costruttore di EsercizioDenominazioneImmagine

        //esercizio= new EsercizioDenominazioneImmagine();

        //TODO mettere qui dentro la parola presa dal template
        textViewParoleTemplateDenominazioneImmagine.setText("parola1");

    }

    private void mostraTemplateSequenzaParole() {
        //TODO prendere la lista di teplate sequenza dal db o viewModel non lo so com è
        // fare il get della lista di template sequenza parole con indice=indexTemplateSequenzaParole
        // ottenere i dati dal teplate e passarli al costruttore di EsercizioSequenzaParole

        //esercizio= new EsercizioSequenzaParole();


        //TODO mettere qui dentro le parole presa dal template
        textViewParoleTemplateSequenzaParole1.setText("parola1");
        textViewParoleTemplateSequenzaParole2.setText("parola2");
        textViewParoleTemplateSequenzaParole3.setText("parola3");

    }

    private void mostraTemplateCoppiaImmagini() {
        //TODO prendere la lista di teplate coppia dal db o viewModel non lo so com è
        // fare il get della lista di template coppia immagini con indice=indexTemplateCoppiaImmagini
        // ottenere i dati dal teplate e passarli al costruttore di EsercizioCoppiaImmagini

        //esercizio= new EsercizioCoppiaImmagini();

        //TODO mettere qui dentro l'immagine prese dal template
        Uri uri= Uri.parse("https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/struzzo.jpg?alt=media&token=50abcf18-c404-48c1-bb3a-b37436898b8d");
        Glide.with(this).load(uri).into(templateCoppiaImmagine);

    }

    private void avviaPrimaRegistrazione(ImageButton buttonAvviaRegistrazione, View viewStopMic, View viewAnimationMic) {
        if (richiestaPermessi()) {
            avviaRegistrazione(buttonAvviaRegistrazione, viewStopMic, viewAnimationMic);

            viewStopMic.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), getContext().getString(R.string.startedRecording), Toast.LENGTH_SHORT).show();
        }
    }

    private void avviaRegistrazione(ImageButton buttonAvviaRegistrazione, View viewStopMic, View viewAnimationMic) {
        buttonAvviaRegistrazione.setBackground(null);
        viewAnimationMic.setVisibility(View.VISIBLE);
        viewAnimationMic.startAnimation(animazioneButtonMic);
        viewStopMic.setVisibility(View.VISIBLE);

        audioRecorder.startRecording();

    }

    private String stoppaRegistrazione(ImageButton buttonAvviaRegistrazione, View viewStopMic, View viewAnimationMic) {
        viewAnimationMic.clearAnimation();
        buttonAvviaRegistrazione.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.circle_green_background));
        viewStopMic.setVisibility(View.GONE);
        viewAnimationMic.setVisibility(View.GONE);

        audioRecorder.stopRecording();

        //TODO salvare il file temp su Storage e ottenere link (pasquale lo sa fare credo)
        //File temp = mController.convertiAudio(audioRecorder.getAudioFile(), new File(getContext().getFilesDir(), "tempAudioConvertito.mp3"));
       String audioRegistrato = "linkAudioRegistrato";

        Toast.makeText(getContext(), getContext().getString(R.string.stopedRecording), Toast.LENGTH_SHORT).show();

        return audioRegistrato;
    }

    private void setAnimazioneRegistrazione() {
        animazioneButtonMic = new ScaleAnimation(1f, 0.8f, 1f, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animazioneButtonMic.setDuration(500);
        animazioneButtonMic.setRepeatMode(Animation.REVERSE);
        animazioneButtonMic.setRepeatCount(Animation.INFINITE);
    }

    private boolean richiestaPermessi() {
        if (!checkPermissions(requireActivity())) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.RECORD_AUDIO)) {
                setPermissionDialog();
            } else {
                requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO);
            }
            return false;
        } else {
            return true;
        }
    }
    private boolean checkPermissions(Activity currentactivity) {
        int recordAudioPermission = ContextCompat.checkSelfPermission(currentactivity, Manifest.permission.RECORD_AUDIO);
        return recordAudioPermission == PackageManager.PERMISSION_GRANTED;
    }

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {

                } else {
                    InfoDialog infoDialog = new InfoDialog(getContext(), getString(R.string.permissionDeniedInstructions), getString(R.string.infoOk));
                    infoDialog.show();
                    infoDialog.setOnConfermaButtonClickListener(() -> navigateTo(R.id.action_esercizioDenominazioneImmagineFragment_to_scenarioFragment));
                }
            });

    private void setPermissionDialog() {
        PermessiDialog permessiDialog = new PermessiDialog(getContext(), getString(R.string.permissionDeniedDescription));
        permessiDialog.show();
        permessiDialog.setOnConfermaButtonClickListener(() -> requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO));
        permessiDialog.setOnAnnullaButtonClickListener(() -> navigateTo(R.id.action_esercizioDenominazioneImmagineFragment_to_scenarioFragment));
    }

    private AudioRecorder initAudioRecorder() {
        File cartellaApp = getContext().getFilesDir();
        File audioRegistrazione = new File(cartellaApp, "tempAudioRegistrato");

        return new AudioRecorder(audioRegistrazione);
    }

    private void createEsercizioDenominazioneImmagine() {

        if (immagineEsercizioDenominazione==null || audioDenominazione==null || textInputEditTextRicompensaCorrettoDenominazione.getText().toString().isEmpty() || textInputEditTextRicompensaErratoDenominazione.getText().toString().isEmpty() || textInputEditTextRispostaEsercizioDenominaizone.getText().toString().isEmpty()) {
            showErrorDialog();
        }else {
            esercizio = new EsercizioDenominazioneImmagine(Integer.parseInt(textInputEditTextRicompensaCorrettoDenominazione.getText().toString()), Integer.parseInt(textInputEditTextRicompensaErratoDenominazione.getText().toString()), audioDenominazione, immagineEsercizioDenominazione, textInputEditTextRispostaEsercizioDenominaizone.getText().toString());
            Log.d("Esercizio", esercizio.toString());
            linearLayoutAggiungiEsercizio.setVisibility(View.GONE);
            imageViewArrowDown.setRotation(0);
            Toast.makeText(getContext(), getString(R.string.esercizio) + " " + getString(R.string.confermato), Toast.LENGTH_SHORT).show();
        }
    }

    private void createEsercizioSequenzaParole() {

        if (audioSequenzaParole==null || textInputEditTextRicompensaCorrettoSequenzaParole.getText().toString().isEmpty() || textInputEditTextRicompensaErratoSequenzaParole.getText().toString().isEmpty() || textInputEditTextParola1.getText().toString().isEmpty() || textInputEditTextParola2.getText().toString().isEmpty() || textInputEditTextParola3.getText().toString().isEmpty()) {
            showErrorDialog();
        }else {
            esercizio = new EsercizioSequenzaParole(Integer.parseInt(textInputEditTextRicompensaCorrettoSequenzaParole.getText().toString()), Integer.parseInt(textInputEditTextRicompensaErratoSequenzaParole.getText().toString()), audioSequenzaParole, textInputEditTextParola1.getText().toString(), textInputEditTextParola2.getText().toString(), textInputEditTextParola3.getText().toString());
            Log.d("Esercizio", esercizio.toString());
            linearLayoutAggiungiEsercizio.setVisibility(View.GONE);
            imageViewArrowDown.setRotation(0);
            Toast.makeText(getContext(), getString(R.string.esercizio) + " " + getString(R.string.confermato), Toast.LENGTH_SHORT).show();
        }
    }

    private void createEsercizioCoppiaImmagini() {
        if(immagineCoppiaCorretta==null || immagineCoppiaErrata==null || audioCoppia==null || textInputEditTextRicompensaCorrettoCoppiaImmagine.getText().toString().isEmpty() || textInputEditTextRicompensaErratoCoppiaImmagine.getText().toString().isEmpty()){
            Log.d("Esercizio", (immagineCoppiaCorretta==null) +" " +(immagineCoppiaErrata==null) +" " +(audioCoppia==null) +" " +(textInputEditTextRicompensaCorrettoCoppiaImmagine.getText().toString().isEmpty()) +" " +(textInputEditTextRicompensaErratoCoppiaImmagine.getText().toString().isEmpty()));
            showErrorDialog();
        }else {
            esercizio = new EsercizioCoppiaImmagini(Integer.parseInt(textInputEditTextRicompensaCorrettoCoppiaImmagine.getText().toString()), Integer.parseInt(textInputEditTextRicompensaErratoCoppiaImmagine.getText().toString()), audioCoppia, immagineCoppiaCorretta, immagineCoppiaErrata);
            Log.d("Esercizio", esercizio.toString());
            linearLayoutAggiungiEsercizio.setVisibility(View.GONE);
            imageViewArrowDown.setRotation(0);
            Toast.makeText(getContext(), getString(R.string.esercizio) + " " + getString(R.string.confermato), Toast.LENGTH_SHORT).show();
        }
    }

    EsercizioEseguibile getEsercizio() {
        return esercizio;
    }

    private void showErrorDialog(){
        InfoDialog infoDialog = new InfoDialog(getContext(), getString(R.string.compilaPrimaTutto), getString(R.string.tastoRiprova));
        infoDialog.setOnConfermaButtonClickListener(null);
        infoDialog.show();
    }

}
