package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.monitoraggio;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioEseguibile;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioSequenzaParole;

public class EsercizioAdapter extends RecyclerView.Adapter<EsercizioAdapter.EsercizioViewHolder> {

    private List<EsercizioEseguibile> listaEsercizi;
    private NavigateTo navigateTo;

    private int idNavToEsercizioDenominazioneImmagine;
    private int idNavToEsercizioCoppiaImmagini;
    private int idNavToEsercizioSequenzaParole;

    public EsercizioAdapter(List<EsercizioEseguibile> listaEsercizi, NavigateTo navigateTo, int idNavToEsercizioDenominazioneImmagine, int idNavToEsercizioCoppiaImmagini, int idNavToEsercizioSequenzaParole) {
        this.listaEsercizi = listaEsercizi;
        this.navigateTo = navigateTo;
        this.idNavToEsercizioDenominazioneImmagine = idNavToEsercizioDenominazioneImmagine;
        this.idNavToEsercizioCoppiaImmagini = idNavToEsercizioCoppiaImmagini;
        this.idNavToEsercizioSequenzaParole = idNavToEsercizioSequenzaParole;
    }

    @NonNull
    @Override
    public EsercizioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_esercizio, parent, false);
        return new EsercizioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EsercizioViewHolder holder, int position) {
        EsercizioEseguibile esercizio = listaEsercizi.get(position);

        holder.textViewNumeroEsercizio.setText(position+1 +"°"); // Imposta il numero dell'esercizio

        if(esercizio instanceof EsercizioDenominazioneImmagine) {
            holder.textViewTipoEsercizio.setText("Denominazione immagine");
        }
        else if(esercizio instanceof EsercizioCoppiaImmagini) {
            holder.textViewTipoEsercizio.setText("Coppia immagini");
        }
        else if(esercizio instanceof EsercizioSequenzaParole) {
            holder.textViewTipoEsercizio.setText("Sequenza parole");
        }

        // Gestisci le icone di stato in base allo stato dell'esercizio
        if (esercizio.getRisultatoEsercizio()==null) {
            holder.imageViewCheckEsercizio.setVisibility(View.GONE);
            holder.imageViewWrongEsercizio.setVisibility(View.GONE);
            holder.imageViewNonEseguito.setVisibility(View.VISIBLE);
            holder.imageViewSeeMoreEsercizio.setVisibility(View.GONE);
            holder.imageViewSeeMoreEsercizio.setVisibility(View.INVISIBLE);
            holder.itemView.setOnClickListener(null);
        } else if (esercizio.getRisultatoEsercizio().isEsitoCorretto()) {
            holder.imageViewCheckEsercizio.setVisibility(View.VISIBLE);
            holder.imageViewWrongEsercizio.setVisibility(View.GONE);
            holder.imageViewNonEseguito.setVisibility(View.GONE);
            setUpOnClickCardView(holder, esercizio, position);
        } else {
            holder.imageViewCheckEsercizio.setVisibility(View.GONE);
            holder.imageViewWrongEsercizio.setVisibility(View.VISIBLE);
            holder.imageViewNonEseguito.setVisibility(View.GONE);
            setUpOnClickCardView(holder, esercizio, position);
        }


    }

    private void setUpOnClickCardView(EsercizioViewHolder holder, EsercizioEseguibile esercizio, int position) {
        holder.itemView.setOnClickListener(v->{
            if(esercizio instanceof EsercizioDenominazioneImmagine) {
                //((EsercizioDenominazioneImmagine) esercizio).setRisultatoEsercizio(new RisultatoEsercizioDenominazioneImmagine(true, "risposta data", 1));
                navigateToEsercizio(idNavToEsercizioDenominazioneImmagine,"indiceEsercizioDenominazioneImmagine", position);
            }
            else if(esercizio instanceof EsercizioCoppiaImmagini) {
                //((EsercizioCoppiaImmagini) esercizio).setRisultatoEsercizio(new RisultatoEsercizioCoppiaImmagini(true));
                navigateToEsercizio(idNavToEsercizioCoppiaImmagini,"indiceEsercizioCoppiaImmagini", position);
            }
            else if(esercizio instanceof EsercizioSequenzaParole) {
                //((EsercizioSequenzaParole) esercizio).setRisultatoEsercizio(new RisultatoEsercizioSequenzaParole(true, "risposta data"));
                navigateToEsercizio(idNavToEsercizioSequenzaParole,"indiceEsercizioSequenzaParole", position);
            }
        });
    }

    private void navigateToEsercizio(int id,String stringSerializable, int posizioneInLista) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(stringSerializable, posizioneInLista);
        Log.d("EsercizioAdapter", "onBindViewHolder bundle: "+bundle);
        navigateTo.navigateToEsercizio(id, bundle);
    }

    @Override
    public int getItemCount() {
        return listaEsercizi.size();
    }

    public class EsercizioViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNumeroEsercizio;
        private TextView textViewTipoEsercizio;
        private ImageView imageViewCheckEsercizio;
        private ImageView imageViewWrongEsercizio;
        private ImageView imageViewNonEseguito;
        private ImageView imageViewSeeMoreEsercizio;

        public EsercizioViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumeroEsercizio = itemView.findViewById(R.id.textViewNumeroEsercizio);
            textViewTipoEsercizio = itemView.findViewById(R.id.textViewTipoEsercizio);
            imageViewCheckEsercizio = itemView.findViewById(R.id.imageViewCheckEsercizio);
            imageViewWrongEsercizio = itemView.findViewById(R.id.imageViewWrongEsercizio);
            imageViewNonEseguito = itemView.findViewById(R.id.imageViewNonEseguito);
            imageViewSeeMoreEsercizio = itemView.findViewById(R.id.imageViewSeeMoreEsercizio);
        }
    }



}