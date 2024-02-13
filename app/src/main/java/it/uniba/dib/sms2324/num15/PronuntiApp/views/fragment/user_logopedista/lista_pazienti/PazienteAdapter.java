package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;


public class PazienteAdapter extends RecyclerView.Adapter<PazienteAdapter.PazienteViewHolder> {
    private List<Paziente> pazienti;
    private List<Paziente> pazientiFull;
    private CardView lastClickedCardView=null;

    public PazienteAdapter(List<Paziente> pazienti) {
        this.pazienti = pazienti;

        if (pazienti == null) {
            this.pazienti = new ArrayList<>();
            this.pazientiFull = new ArrayList<>();
        } else {
            this.pazientiFull = new ArrayList<>(pazienti);
        }//TODO per Nicola: qusto è il check che ti avevo detot se pazienti è null
    }

    @NonNull
    @Override
    public PazienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paziente_in_lista_logopedista, parent, false);
        return new PazienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PazienteViewHolder holder, int position) {
        Paziente paziente = pazienti.get(position);
        holder.textViewNomePaziente.setText(paziente.getNome());
        holder.textViewCognomePaziente.setText(paziente.getCognome());
        holder.textViewDataNascitaPaziente.setText(paziente.getDataNascita().toString());
        holder.textViewSessoPaziente.setText(Character.toString(paziente.getSesso()));

        holder.textViewNomePaziente.setOnClickListener(v ->setCardViewColor(holder));
        holder.textViewCognomePaziente.setOnClickListener(v ->setCardViewColor(holder));
        holder.textViewDataNascitaPaziente.setOnClickListener(v ->setCardViewColor(holder));
        holder.textViewSessoPaziente.setOnClickListener(v ->setCardViewColor(holder));
        holder.cardViewPazienteInListaLogopedista.setOnClickListener(v ->setCardViewColor(holder));
    }

    private void setCardViewColor(PazienteViewHolder holder) {
        holder.cardViewPazienteInListaLogopedista.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.primaryColorSoft));
        if(lastClickedCardView != null) {
            lastClickedCardView.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.colorPrimary));
        }
        lastClickedCardView = holder.cardViewPazienteInListaLogopedista;
        //TODO per Nicola: questo è il metodo che ho aggiunto per gestire il click su un paziente
    }

    @Override
    public int getItemCount() {
        return pazienti.size();
    }

    public Paziente getItem(int position) {
        return pazienti.get(position);
    }

    public static class PazienteViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNomePaziente;
        TextView textViewCognomePaziente;
        TextView textViewDataNascitaPaziente;
        TextView textViewSessoPaziente;
        CardView cardViewPazienteInListaLogopedista;

        public PazienteViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewPazienteInListaLogopedista = itemView.findViewById(R.id.cardViewPazienteInListaLogopedista);
            textViewNomePaziente = itemView.findViewById(R.id.textViewNomePaziente);
            textViewCognomePaziente = itemView.findViewById(R.id.textViewCognomePaziente);
            textViewDataNascitaPaziente = itemView.findViewById(R.id.textViewDataNascitaPaziente);
            textViewSessoPaziente = itemView.findViewById(R.id.textViewSessoPaziente);
        }
    }

    public Filter getFilter() {
        return new PazienteFilter();
    }

    private class PazienteFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Paziente> filteredPazienti = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredPazienti.addAll(pazientiFull);
            } else {
                String query = charSequence.toString().toLowerCase();
                for (Paziente paziente : pazientiFull) {
                    String nome = paziente.getNome().toLowerCase();
                    String cognome = paziente.getCognome().toLowerCase();
                    if (nome.contains(query) || cognome.contains(query)) {
                        filteredPazienti.add(paziente);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredPazienti;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            pazienti.clear();
            pazienti.addAll((List<Paziente>) filterResults.values);
            notifyDataSetChanged();
        }
    }

}
