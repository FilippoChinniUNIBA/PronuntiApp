package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.personaggi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;

public class PersonaggiSbloccatiAdapter extends RecyclerView.Adapter<PersonaggiSbloccatiAdapter.ViewHolder> {
    private Context context;
    private List<Personaggio> personaggiSbloccati;

    PazienteViewModel mPazienteViewModel;

    public PersonaggiSbloccatiAdapter(Context context, List<Personaggio> personaggiSbloccati, PazienteViewModel mPazienteViewModel) {
        this.context = context;
        this.personaggiSbloccati = personaggiSbloccati;
        this.mPazienteViewModel = mPazienteViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_personaggio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Personaggio personaggio = personaggiSbloccati.get(position);
        String urlPersonaggio = personaggio.getTexturePersonaggio();
        String nomePersonaggio = personaggio.getNomePersonaggio();
        String idPersonaggio = personaggio.getIdPersonaggio();

        if(position==0) {
            holder.buttonPossiediPersonaggio.setVisibility(View.GONE);
            holder.llPersonaggioSelezionato.setVisibility(View.VISIBLE);
        }
        else {
            holder.llPersonaggioSelezionato.setVisibility(View.GONE);
            holder.buttonPossiediPersonaggio.setVisibility(View.VISIBLE);
        }

        holder.textViewNomePersonaggio.setText(nomePersonaggio);
        Glide.with(context).asBitmap().apply(new RequestOptions().override(150, 150)).load(urlPersonaggio).into(holder.imageViewPersonaggio);


        holder.buttonPossiediPersonaggio.setOnClickListener(v -> {
            refreshPersonaggioSelezionato(personaggio,position);
            updatePersonaggiPaziente(idPersonaggio);
        });
    }
    private void updatePersonaggiPaziente(String idPersonaggio){
        Map<String, Integer> personaggi = mPazienteViewModel.getPazienteLiveData().getValue().getPersonaggiSbloccati();
        Map<String, Integer> personaggiModificati = eliminaPersonaggioSelezionato(personaggi);
        personaggiModificati.put(idPersonaggio, 2);
        mPazienteViewModel.getPazienteLiveData().getValue().setPersonaggiSbloccati(personaggiModificati);
        mPazienteViewModel.aggiornaPazienteRemoto();
    }

    public Map<String, Integer> eliminaPersonaggioSelezionato(Map<String, Integer> mappa) {

        Map<String, Integer> nuovaMappa = new HashMap<>();
        for (Map.Entry<String, Integer> entry : mappa.entrySet()) {
            String chiave = entry.getKey();
            int valore = Integer.parseInt(String.valueOf(entry.getValue()));
            int nuovoValore = (valore == 2) ? 1 : valore;
            nuovaMappa.put(chiave, nuovoValore);
        }
        return nuovaMappa;
    }
    private void refreshPersonaggioSelezionato(Personaggio personaggio,int position){
        Collections.swap(personaggiSbloccati,0,position);
        notifyDataSetChanged();
    }

    public void addPersonaggisbloccato(Personaggio personaggio){
        personaggiSbloccati.add(0,personaggio);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return personaggiSbloccati.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPersonaggio;
        TextView textViewNomePersonaggio;

        LinearLayout llAcquistaPersonaggio; // DA NON VISUALIZZARE QUI


        LinearLayout llPersonaggioSelezionato;
        Button buttonPossiediPersonaggio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPersonaggio = itemView.findViewById(R.id.imageViewPersonaggio);
            textViewNomePersonaggio = itemView.findViewById(R.id.textViewNomePersonaggio);

            llAcquistaPersonaggio = itemView.findViewById(R.id.llAcquistaPersonaggio);
            llAcquistaPersonaggio.setVisibility(View.GONE);

            llPersonaggioSelezionato = itemView.findViewById(R.id.llPersonaggioSelezionato);
            buttonPossiediPersonaggio  = itemView.findViewById(R.id.buttonPossiediPersonaggio);

        }
    }

}
