package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.classifica;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class PazienteClassificaAdapter extends RecyclerView.Adapter<PazienteClassificaAdapter.PazienteViewHolder> {

    private List<PazienteClassifica> pazienti;

    public PazienteClassificaAdapter(List<PazienteClassifica> pazienti) {
        this.pazienti = pazienti;
    }

    @Override
    public PazienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classifica, parent, false);
        return new PazienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PazienteViewHolder holder, int position) {
        PazienteClassifica paziente = pazienti.get(position);

        holder.textViewPosizione.setText(String.valueOf(position + 1));
        holder.textViewUsernamePaziente.setText(paziente.getUsername());
        holder.textViewPunteggio.setText(String.valueOf(paziente.getPunteggio()));
        Glide.with(holder.imageView.getContext()).load(paziente.getUrl_img()).into(holder.imageView);
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

        public PazienteViewHolder(View itemView) {
            super(itemView);
            textViewPosizione = itemView.findViewById(R.id.textViewPosizione);
            imageView = itemView.findViewById(R.id.imageViewPaziente);
            textViewUsernamePaziente = itemView.findViewById(R.id.textViewUsernamePaziente);
            textViewPunteggio = itemView.findViewById(R.id.textViewPunteggio);
        }
    }
}


