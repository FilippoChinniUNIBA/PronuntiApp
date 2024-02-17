package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.monitoraggio.risultatiesercizio;

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
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_player.AudioPlayerLink;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class RisultatoEsercizioDenominazioneImmagineGenitoreFragment extends AbstractFragmentWithNavigation {
    private ImageView imageViewCheck;
    private ImageView imageViewWrong;
    private TextView textAiutiUtilizzati;
    private ImageButton playButton;
    private ImageButton pauseButton;
    private ImageView immagineEsercizioDenominazioneImageView;
    private EsercizioDenominazioneImmagine mEsercizioDenominazioneImmagine;
    private int indiceEsercizio;
    private int indiceScenario;
    private int indiceTerapia;
    private GenitoreViewModel mGenitoreViewModel;
    private AudioPlayerLink audioPlayerLink;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_risultato_es_denominazione_immagine, container, false);

        setToolBar(view, getString(R.string.risultatoEsercizio));

        savedInstanceState = getArguments();

        if (savedInstanceState != null && savedInstanceState.containsKey("indiceEsercizio") && savedInstanceState.containsKey("indiceScenario") && savedInstanceState.containsKey("indiceTerapia")) {
            indiceEsercizio = savedInstanceState.getInt("indiceEsercizio");
            indiceScenario = savedInstanceState.getInt("indiceScenario");
            indiceTerapia = savedInstanceState.getInt("indiceTerapia");
        } else {
            indiceTerapia = 0;
            indiceEsercizio = 0;
            indiceScenario = 0;
        }

        //TODO prendere esercizio da id passato da fragment chiamante
        immagineEsercizioDenominazioneImageView = view.findViewById(R.id.imageViewImmagineEsercizioDenominazione);
        imageViewCheck = view.findViewById(R.id.imageViewCheckEsercizio);
        imageViewWrong = view.findViewById(R.id.imageViewWrongEsercizio);
        textAiutiUtilizzati = view.findViewById(R.id.textAiutiUtilizzati);
        playButton = view.findViewById(R.id.imageButtonAvviaAudioRegistrato);
        pauseButton = view.findViewById(R.id.imageButtonPausaAudioRegistrato);
        pauseButton.setVisibility(View.GONE);

        mGenitoreViewModel = new ViewModelProvider(requireActivity()).get(GenitoreViewModel.class);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mEsercizioDenominazioneImmagine = getEsercizioDenominazioneFromViewModel(indiceEsercizio,indiceScenario,indiceTerapia);

        Picasso.get().load(mEsercizioDenominazioneImmagine.getImmagineEsercizio()).into(immagineEsercizioDenominazioneImageView);

        if (isCorrect()) {
            imageViewCheck.setVisibility(View.VISIBLE);
            imageViewWrong.setVisibility(View.GONE);
        } else {
            imageViewCheck.setVisibility(View.GONE);
            imageViewWrong.setVisibility(View.VISIBLE);
        }
        int aiuti = mEsercizioDenominazioneImmagine.getRisultatoEsercizio().getCountAiuti();
        textAiutiUtilizzati.setText( textAiutiUtilizzati.getText().toString()+" "+aiuti);

        playButton.setOnClickListener(v -> playAudio());
        pauseButton.setOnClickListener(v -> stopAudio());

    }

    private void playAudio() {
        playButton.setVisibility(View.GONE);
        pauseButton.setVisibility(View.VISIBLE);
        audioPlayerLink = new AudioPlayerLink(mEsercizioDenominazioneImmagine.getRisultatoEsercizio().getAudioRegistrato());
        audioPlayerLink.playAudio();
    }

    private void stopAudio() {
        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.GONE);
        audioPlayerLink.stopAudio();
    }

    private boolean isCorrect() {
        return mEsercizioDenominazioneImmagine.getRisultatoEsercizio().isEsitoCorretto();
    }

    private EsercizioDenominazioneImmagine getEsercizioDenominazioneFromViewModel(int indiceEsercizio, int indiceScenario, int indiceTerapia){
        Log.d("RisultatoDenominazione", ":"+ indiceEsercizio);
        Log.d("RisultatoSequenzaParole", ":"+  mGenitoreViewModel.getPazienteLiveData().getValue().getTerapie().get(indiceTerapia).getScenariGioco().get(indiceScenario).getEsercizi().get(indiceEsercizio).toString());
        return (EsercizioDenominazioneImmagine) mGenitoreViewModel.getPazienteLiveData().getValue().getTerapie().get(indiceTerapia).getScenariGioco().get(indiceScenario).getEsercizi().get(indiceEsercizio);
    }

}