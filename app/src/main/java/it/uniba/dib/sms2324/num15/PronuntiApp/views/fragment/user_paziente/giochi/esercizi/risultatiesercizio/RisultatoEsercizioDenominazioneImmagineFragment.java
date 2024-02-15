package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi.risultatiesercizio;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class RisultatoEsercizioDenominazioneImmagineFragment extends AbstractFragmentWithNavigation {
    private ImageView imageViewCheck;
    private ImageView imageViewWrong;
    private TextView textAiutiUtilizzati;
    private ImageButton playButton;
    private ImageButton pauseButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_risultato_es_denominazione_immagine, container, false);

        //TODO come si ottiene il risultato?
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