package it.uniba.dib.sms2324.num15.PronuntiApp.views.monitoraggio;

import android.os.Bundle;
import android.os.Parcelable;
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
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi.EsercizioCoppiaImmaginiFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi.EsercizioDenominazioneImmagineFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi.EsercizioSequenzaParoleFragment;

public class EsercizioAdapter extends RecyclerView.Adapter<EsercizioAdapter.EsercizioViewHolder> {

    private List<EsercizioEseguibile> listaEsercizi;
    private NavigateTo navigateTo;

    public EsercizioAdapter(List<EsercizioEseguibile> listaEsercizi, NavigateTo navigateTo) {
        this.listaEsercizi = listaEsercizi;
        this.navigateTo = navigateTo;
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
            //action_monitoraggioFragment2_to_esercizioDenominazioneImmagineFragment2
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
        } else if (esercizio.getRisultatoEsercizio().isEsitoCorretto()) {
            holder.imageViewCheckEsercizio.setVisibility(View.VISIBLE);
            holder.imageViewWrongEsercizio.setVisibility(View.GONE);
            holder.imageViewNonEseguito.setVisibility(View.GONE);
        } else {
            holder.imageViewCheckEsercizio.setVisibility(View.GONE);
            holder.imageViewWrongEsercizio.setVisibility(View.VISIBLE);
            holder.imageViewNonEseguito.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v->{
            if(esercizio instanceof EsercizioDenominazioneImmagine) {
                navigateToEsercizio(R.id.action_monitoraggioFragment2_to_esercizioDenominazioneImmagineFragment2,"esercizioDenominazioneImmagine", esercizio);
            }
            else if(esercizio instanceof EsercizioCoppiaImmagini) {
                navigateToEsercizio(R.id.action_monitoraggioFragment2_to_esercizioCoppiaImmaginiFragment,"esercizioCoppiaImmagini", esercizio);
            }
            else if(esercizio instanceof EsercizioSequenzaParole) {
                navigateToEsercizio(R.id.action_monitoraggioFragment2_to_esercizioSequenzaParoleFragment,"esercizioSequenzaParole", esercizio);
            }
        });
    }

    private void navigateToEsercizio(int id,String stringSerializable, EsercizioEseguibile esercizio) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(stringSerializable, esercizio);
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

        public EsercizioViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumeroEsercizio = itemView.findViewById(R.id.textViewNumeroEsercizio);
            textViewTipoEsercizio = itemView.findViewById(R.id.textViewTipoEsercizio);
            imageViewCheckEsercizio = itemView.findViewById(R.id.imageViewCheckEsercizio);
            imageViewWrongEsercizio = itemView.findViewById(R.id.imageViewWrongEsercizio);
            imageViewNonEseguito = itemView.findViewById(R.id.imageViewNonEseguito);
        }
    }



}