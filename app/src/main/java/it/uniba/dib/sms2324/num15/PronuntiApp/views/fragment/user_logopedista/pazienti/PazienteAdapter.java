package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.pazienti;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;

public class PazienteAdapter extends RecyclerView.Adapter<PazienteAdapter.PazienteViewHolder> {
    private List<Paziente> pazienti;
    private List<Paziente> pazientiFull;
    public PazienteAdapter(List<Paziente> pazienti) {
        this.pazienti = pazienti;
        pazientiFull = new ArrayList<>(pazienti);
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
        holder.textViewEtaPaziente.setText(String.valueOf(paziente.getEta()));
        holder.textViewSessoPaziente.setText(Character.toString(paziente.getSesso()));
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
        TextView textViewEtaPaziente;
        TextView textViewSessoPaziente;
        LinearLayout linearLayoutPaziente;

        public PazienteViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayoutPaziente = itemView.findViewById(R.id.llPazienteInListaLogopedista);
            textViewNomePaziente = itemView.findViewById(R.id.textViewNomePaziente);
            textViewCognomePaziente = itemView.findViewById(R.id.textViewCognomePaziente);
            textViewEtaPaziente = itemView.findViewById(R.id.textViewEtaPaziente);
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
