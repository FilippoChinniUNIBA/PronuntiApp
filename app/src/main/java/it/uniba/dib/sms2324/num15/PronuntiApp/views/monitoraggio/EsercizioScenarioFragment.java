package it.uniba.dib.sms2324.num15.PronuntiApp.views.monitoraggio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.Esercizio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioEseguibile;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.AbstractRisultatoEsercizio;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi.EsercizioCoppiaImmaginiController;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class EsercizioScenarioFragment extends AbstractFragmentWithNavigation {


    private EsercizioEseguibile esercizio;
    private TextView textViewTipoEsercizio;
    private TextView textViewNumeroEsercizio;
    private ImageView imageViewCorrect;
    private ImageView imageViewWrong;
    private ImageView imageViewNonEseguito;
    private int counterEsercizi;

    public EsercizioScenarioFragment(EsercizioEseguibile esercizio, int counterEsercizi) {
        super();
        this.esercizio = esercizio;
        this.counterEsercizi = counterEsercizi;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate del layout
        View view = inflater.inflate(R.layout.item_esercizio, container, false);

        textViewNumeroEsercizio = view.findViewById(R.id.textViewNumeroEsercizio);
        textViewTipoEsercizio = view.findViewById(R.id.textViewTipoEsercizio);
        imageViewCorrect = view.findViewById(R.id.imageViewCheckEsercizio);
        imageViewWrong = view.findViewById(R.id.imageViewWrongEsercizio);
        imageViewNonEseguito = view.findViewById(R.id.imageViewNonEseguito);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewNumeroEsercizio.setText(counterEsercizi +"°");

        if(esercizio instanceof EsercizioDenominazioneImmagine) {
            textViewTipoEsercizio.setText("Denominazione immagine");
        }
        else if(esercizio instanceof EsercizioCoppiaImmagini) {
            textViewTipoEsercizio.setText("Coppia immagini");
        }
        else if(esercizio instanceof EsercizioSequenzaParole) {
            textViewTipoEsercizio.setText("Sequenza parole");
        }

        checkRisultatoEsercizio();
    }

    private void checkRisultatoEsercizio(){
        if(esercizio.getRisultatoEsercizio()==null){
            visibleNonEseguito();
        }
        else{
            visibleEsito();
        }
    }

    private void visibleNonEseguito() {
        imageViewNonEseguito.setVisibility(View.VISIBLE);
        imageViewCorrect.setVisibility(View.GONE);
        imageViewWrong.setVisibility(View.GONE);
    }

    private void visibleEsito(){
        if(esercizio.getRisultatoEsercizio().isEsitoCorretto())
            visibleCorretto();
        else
            visibleSbagliato();
    }

    private void visibleCorretto(){
        imageViewCorrect.setVisibility(View.VISIBLE);
        imageViewWrong.setVisibility(View.GONE);
    }

    private void visibleSbagliato(){
        imageViewCorrect.setVisibility(View.GONE);
        imageViewWrong.setVisibility(View.VISIBLE);
    }
}
