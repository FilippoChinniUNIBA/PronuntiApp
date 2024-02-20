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
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_player.AudioPlayerLink;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class RisultatoEsercizioDenominazioneImmagineLogopedistaFragment extends AbstractFragmentWithNavigation {
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
    private String idPaziente;
    private LogopedistaViewModel mLogopedistaViewModel;
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
            idPaziente = savedInstanceState.getString("idPaziente");
        } else {
            indiceTerapia = 0;
            indiceEsercizio = 0;
            indiceScenario = 0;
        }

        mLogopedistaViewModel = new ViewModelProvider(requireActivity()).get(LogopedistaViewModel.class);

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
        this.mEsercizioDenominazioneImmagine = getmEsercizioDenominazioneImmagineFromViewModel();
        Picasso.get().load(mEsercizioDenominazioneImmagine.getImmagineEsercizio()).into(immagineEsercizioDenominazioneImageView);

        if (isCorrect()) {
            imageViewCheck.setVisibility(View.VISIBLE);
            imageViewWrong.setVisibility(View.GONE);
        } else {
            imageViewCheck.setVisibility(View.GONE);
            imageViewWrong.setVisibility(View.VISIBLE);
        }

        textAiutiUtilizzati.setText("Aiuti utilizzati: " + mEsercizioDenominazioneImmagine.getRisultatoEsercizio().getCountAiuti());

        playButton.setOnClickListener(v -> playAudio());
        pauseButton.setOnClickListener(v -> stopAudio());

    }

    private void playAudio() {
        playButton.setVisibility(View.GONE);
        pauseButton.setVisibility(View.VISIBLE);
        this.audioPlayerLink = new AudioPlayerLink(mEsercizioDenominazioneImmagine.getRisultatoEsercizio().getAudioRegistrato());
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

    private EsercizioDenominazioneImmagine getmEsercizioDenominazioneImmagineFromViewModel(){

        for (Paziente pazienti : mLogopedistaViewModel.getLogopedistaLiveData().getValue().getPazienti()) {
            if(pazienti.getIdProfilo().equals(idPaziente)){
                return (EsercizioDenominazioneImmagine) pazienti.getTerapie().get(indiceTerapia).getScenariGioco().get(indiceScenario).getEsercizi().get(indiceEsercizio);
            }
        }
        return new EsercizioDenominazioneImmagine(0,0,"","","");
    }




}