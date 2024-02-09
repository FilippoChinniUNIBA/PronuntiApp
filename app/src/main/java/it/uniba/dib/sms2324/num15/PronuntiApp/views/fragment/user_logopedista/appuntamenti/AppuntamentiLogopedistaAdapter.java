package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.appuntamenti;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class AppuntamentiLogopedistaAdapter extends RecyclerView.Adapter<AppuntamentiLogopedistaAdapter.AppuntamentiLogopedistaViewHolder> {

    private List<AppuntamentoCustom> appuntamentiFull;
    private List<AppuntamentoCustom> appuntamenti;

    public AppuntamentiLogopedistaAdapter(List<AppuntamentoCustom> appuntamentiFull) {
        this.appuntamentiFull = appuntamentiFull;
        appuntamenti = new ArrayList<>(appuntamentiFull);
    }

    @Override
    public AppuntamentiLogopedistaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paziente_in_appuntamenti_logopedista, parent, false);
        return new AppuntamentiLogopedistaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppuntamentiLogopedistaViewHolder holder, int position) {
        AppuntamentoCustom appuntamento = appuntamenti.get(position);

        holder.textViewNomePaziente.setText(appuntamento.getNomePaziente());
        holder.textViewCognomePaziente.setText(appuntamento.getCognomePaziente());
        holder.textViewDataAppuntamento.setText(appuntamento.getDataAppuntamento().format(DateTimeFormatter.ofPattern("dd MMMM"))); // Modifica in base al tipo di dato previsto per data appuntamento
        holder.textViewOraAppuntamento.setText(appuntamento.getOraAppuntamento().format(DateTimeFormatter.ofPattern("HH:mm"))); // Modifica in base al tipo di dato previsto per orario appuntamento
    }

    @Override
    public int getItemCount() {
        return appuntamenti.size();
    }
    public AppuntamentoCustom getItem(int position) {
        return appuntamenti.get(position);
    }

    public static class AppuntamentiLogopedistaViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNomePaziente;
        TextView textViewCognomePaziente;
        TextView textViewDataAppuntamento;
        TextView textViewOraAppuntamento;

        public AppuntamentiLogopedistaViewHolder(View itemView) {
            super(itemView);

            textViewNomePaziente = itemView.findViewById(R.id.textViewNomePaziente);
            textViewCognomePaziente = itemView.findViewById(R.id.textViewCognomePaziente);
            textViewDataAppuntamento = itemView.findViewById(R.id.textViewDataNascitaPaziente);
            textViewOraAppuntamento = itemView.findViewById(R.id.textViewSessoPaziente);
        }
    }

    public Filter getFilter() {
        return new AppuntamentiLogopedistaAdapter.AppuntamentiFilter();
    }


    private class AppuntamentiFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<AppuntamentoCustom> filteredApppuntamenti = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredApppuntamenti.addAll(appuntamentiFull);
            } else {
                String query = charSequence.toString().toLowerCase();
                for (AppuntamentoCustom appuntamento : appuntamentiFull) {
                    String nome = appuntamento.getNomePaziente().toLowerCase();
                    String cognome = appuntamento.getCognomePaziente().toLowerCase();
                    if (nome.contains(query) || cognome.contains(query)) {
                        filteredApppuntamenti.add(appuntamento);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredApppuntamenti;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            appuntamenti.clear();
            appuntamenti.addAll((List<AppuntamentoCustom>) filterResults.values);
            notifyDataSetChanged();
        }
    }
}