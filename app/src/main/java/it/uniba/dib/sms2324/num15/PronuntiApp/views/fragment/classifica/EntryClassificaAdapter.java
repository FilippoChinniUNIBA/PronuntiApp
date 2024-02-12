package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.classifica;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class EntryClassificaAdapter extends RecyclerView.Adapter<EntryClassificaAdapter.PazienteViewHolder> {
    private List<EntryClassifica> pazienti;
    private String pazienteAttuale;

    public EntryClassificaAdapter(List<EntryClassifica> pazienti, String pazienteAttuale) {
        this.pazienti = pazienti;
        this.pazienteAttuale = pazienteAttuale;
    }

    @Override
    public PazienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classifica, parent, false);
        return new PazienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PazienteViewHolder holder, int position) {
        EntryClassifica paziente = pazienti.get(position);

        if (position == 0) {
            holder.imageViewCorona.setVisibility(View.VISIBLE);
            holder.imageViewCorona.setImageResource(R.drawable.crown_gold);
        } else if (position == 1) {
            holder.imageViewCorona.setVisibility(View.VISIBLE);
            holder.imageViewCorona.setImageResource(R.drawable.crown_silver);
        } else if (position == 2) {
            holder.imageViewCorona.setVisibility(View.VISIBLE);
            holder.imageViewCorona.setImageResource(R.drawable.crown_bronze);
        } else {
            holder.imageViewCorona.setVisibility(View.INVISIBLE);
        }

        if (pazienteAttuale.equals(paziente.getUsername())) {
            holder.linearLayoutClassificaItem.setBackground(holder.itemView.getContext().getDrawable(R.drawable.stroke_selector));
        }

        holder.textViewPosizione.setText(String.valueOf(position + 1));
        holder.textViewUsernamePaziente.setText(paziente.getUsername());
        holder.textViewPunteggio.setText(String.valueOf(paziente.getPunteggio()));
        Picasso.get().load(paziente.getUrlImg()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return pazienti.size();
    }


    public class PazienteViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewPosizione;
        public ImageView imageView;
        public TextView textViewUsernamePaziente;
        public TextView textViewPunteggio;
        public ImageView imageViewCorona;
        public LinearLayout linearLayoutClassificaItem;

        public PazienteViewHolder(View itemView) {
            super(itemView);
            textViewPosizione = itemView.findViewById(R.id.textViewPosizione);
            imageView = itemView.findViewById(R.id.imageViewPaziente);
            textViewUsernamePaziente = itemView.findViewById(R.id.textViewUsernamePaziente);
            textViewPunteggio = itemView.findViewById(R.id.textViewPunteggio);
            imageViewCorona = itemView.findViewById(R.id.imageViewCorona);
            linearLayoutClassificaItem = itemView.findViewById(R.id.linearLayoutClassificaItem);
        }
    }

}


