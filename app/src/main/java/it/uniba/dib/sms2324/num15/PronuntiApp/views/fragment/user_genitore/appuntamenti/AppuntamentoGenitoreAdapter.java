package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.appuntamenti;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;

public class AppuntamentoGenitoreAdapter extends RecyclerView.Adapter<AppuntamentoGenitoreAdapter.ViewHolder> {

    private List<Appuntamento> appuntamenti;

    public AppuntamentoGenitoreAdapter(List<Appuntamento> appuntamenti) {
        this.appuntamenti = appuntamenti;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appuntamento_in_genitore, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Appuntamento appuntamento = appuntamenti.get(position);

        if(appuntamento.getData().isBefore(LocalDate.now()) && appuntamento.getOra().isBefore(LocalTime.now())) {
            holder.cardViewAppuntamentoGenitore.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.hintTextColorDisabled));
        }
        else {
            holder.cardViewAppuntamentoGenitore.setCardBackgroundColor(holder.itemView.getContext().getColor(R.color.colorPrimary));
        }
        holder.textViewGiornoAppuntamentoGenitore.setText(DateTimeFormatter.ofPattern("dd").format(appuntamento.getData()));
        holder.textViewMeseAppuntamentoGenitore.setText(DateTimeFormatter.ofPattern("MMMM yyyy").format(appuntamento.getData()));
        holder.textViewLuogoAppuntamentoGenitore.setText(appuntamento.getLuogo());
        holder.textViewOraAppuntamentoGenitore.setText(DateTimeFormatter.ofPattern("HH:mm").format(appuntamento.getOra()));
    }

    @Override
    public int getItemCount() {
        return appuntamenti.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewGiornoAppuntamentoGenitore;
        private TextView textViewMeseAppuntamentoGenitore;
        private TextView textViewLuogoAppuntamentoGenitore;
        private TextView textViewOraAppuntamentoGenitore;
        private CardView cardViewAppuntamentoGenitore;
        public ViewHolder(View itemView) {
            super(itemView);
            cardViewAppuntamentoGenitore = itemView.findViewById(R.id.cardViewAppuntamentoGenitore);
            textViewGiornoAppuntamentoGenitore = itemView.findViewById(R.id.textViewGiornoAppuntamentoGenitore);
            textViewMeseAppuntamentoGenitore = itemView.findViewById(R.id.textViewMeseAppuntamentoGenitore);
            textViewLuogoAppuntamentoGenitore = itemView.findViewById(R.id.textViewLuogoAppuntamentoGenitore);
            textViewOraAppuntamentoGenitore = itemView.findViewById(R.id.textViewOraAppuntamentoGenitore);
        }
    }
}
