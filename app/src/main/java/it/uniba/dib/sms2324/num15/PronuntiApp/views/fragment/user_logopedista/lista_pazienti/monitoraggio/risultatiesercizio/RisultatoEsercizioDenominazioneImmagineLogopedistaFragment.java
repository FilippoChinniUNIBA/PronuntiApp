package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti.monitoraggio.risultatiesercizio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class RisultatoEsercizioDenominazioneImmagineLogopedistaFragment extends AbstractFragmentWithNavigation {
    private ImageView imageViewCheck;
    private ImageView imageViewWrong;
    private TextView textAiutiUtilizzati;
    private ImageButton playButton;
    private ImageButton pauseButton;
    private ImageView immagineEsercizioDenominazioneImageView;
    private EsercizioDenominazioneImmagine mEsercizioDenominazioneImmagine;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_risultato_es_denominazione_immagine, container, false);

        setToolBar(view, getString(R.string.risultatoEsercizio));


        //TODO prendere esercizio da id passato da fragment chiamante
        immagineEsercizioDenominazioneImageView = view.findViewById(R.id.imageViewImmagineEsercizioDenominazione);
        imageViewCheck = view.findViewById(R.id.imageViewCheckEsercizio);
        imageViewWrong = view.findViewById(R.id.imageViewWrongEsercizio);
        textAiutiUtilizzati = view.findViewById(R.id.textAiutiUtilizzati);
        playButton = view.findViewById(R.id.imageButtonAvviaAudioRegistrato);
        pauseButton = view.findViewById(R.id.imageButtonPausaAudioRegistrato);
        pauseButton.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mEsercizioDenominazioneImmagine = new EsercizioDenominazioneImmagine(2500, 200, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/pinguino.jpg?alt=media&token=8792af2e-2a3d-4366-9d86-56746a42d2be", "pinguino", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/help.mp3?alt=media&token=89cbfacf-2a02-46c5-986d-29b2d7e2fcdd");
        Picasso.get().load(mEsercizioDenominazioneImmagine.getImmagineEsercizio()).into(immagineEsercizioDenominazioneImageView);

        if (isCorrect()) {
            imageViewCheck.setVisibility(View.VISIBLE);
            imageViewWrong.setVisibility(View.GONE);
        } else {
            imageViewCheck.setVisibility(View.GONE);
            imageViewWrong.setVisibility(View.VISIBLE);
        }

        //TODO prendere aiuti da viewmodel
        textAiutiUtilizzati.setText("Aiuti utilizzati: " + 2);

        playButton.setOnClickListener(v -> playAudio());
        pauseButton.setOnClickListener(v -> stopAudio());

    }

    private void playAudio() {
        playButton.setVisibility(View.GONE);
        pauseButton.setVisibility(View.VISIBLE);
        //TODO riproduzione audio
    }

    private void stopAudio() {
        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.GONE);
        //TODO ferma riproduzione audio
    }

    private boolean isCorrect() {
        //TODO prendere esito risultato da viewmodel
        return false;
    }

}