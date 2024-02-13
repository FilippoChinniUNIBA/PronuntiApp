package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.personaggi;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.checkerframework.checker.units.qual.N;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;

public class PersonaggiAcquistabiliAdapter extends RecyclerView.Adapter<PersonaggiAcquistabiliAdapter.ViewHolder> {
    private Context context;
    private List<Personaggio> personaggiAcquistabili;
    PersonaggiSbloccatiAdapter personaggiSbloccatiAdapter;
    NestedScrollView nestedScrollView;
    PazienteViewModel mPazienteViewModel;

    public PersonaggiAcquistabiliAdapter(Context context, List<Personaggio> personaggiAcquistabili, PersonaggiSbloccatiAdapter personaggiSbloccatiAdapter, NestedScrollView nestedScrollView, PazienteViewModel mPazienteViewModel) {
        this.context = context;
        this.personaggiAcquistabili = personaggiAcquistabili;
        this.personaggiSbloccatiAdapter = personaggiSbloccatiAdapter;
        this.nestedScrollView = nestedScrollView;
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
        Personaggio personaggio = personaggiAcquistabili.get(position);
        String idPersonaggio = personaggio.getIdPersonaggio();
        String urlPersonaggio = personaggio.getTexturePersonaggio();
        String nomePersonaggio = personaggio.getNomePersonaggio();
        int costoPersonaggio = personaggio.getCostoSblocco();
        holder.textViewNomePersonaggio.setText(nomePersonaggio);
        Glide.with(context).asBitmap().apply(new RequestOptions().override(150, 150)).load(urlPersonaggio).into(holder.imageViewPersonaggio);
        holder.textViewCostoPersonaggio.setText(String.valueOf(costoPersonaggio));

        holder.llAcquistaPersonaggio.setOnClickListener(v -> {
            refreshPersonaggi(personaggio);
            getAnimator().start();
            notifyDataSetChanged();
            updatePersonaggiPaziente(idPersonaggio);
        });
    }

    private void updatePersonaggiPaziente(String idPersonaggio){
        Map<String, Integer> personaggi = mPazienteViewModel.getPazienteLiveData().getValue().getPersonaggiSbloccati();
        Map<String, Integer> personaggiModificati = eliminaPersonaggioSelezionato(personaggi);
        personaggiModificati.put(idPersonaggio, 2);
        mPazienteViewModel.getPazienteLiveData().getValue().setPersonaggiSbloccati(personaggiModificati);
        mPazienteViewModel.aggiornaTexturePersonaggioSelezionatoLiveData();
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
    private void refreshPersonaggi(Personaggio personaggio){
        personaggiAcquistabili.remove(personaggio);
        personaggiSbloccatiAdapter.addPersonaggisbloccato(personaggio);
    }

    @Override
    public int getItemCount() {
        return personaggiAcquistabili.size();
    }

    private Animator getAnimator(){
        ValueAnimator animator = ValueAnimator.ofInt(nestedScrollView.getScrollY(), 0); // L'ultimo parametro è la destinazione dello scroll
        animator.setDuration(1250); // Durata dell'animazione in millisecondi (ad esempio 1000ms = 1 secondo)
        animator.addUpdateListener(animation -> {
            int value = (Integer) animation.getAnimatedValue();
            nestedScrollView.scrollTo(0, value); // Imposta la posizione dello scroll durante l'animazione
        });
        return animator;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPersonaggio;
        TextView textViewNomePersonaggio;

        LinearLayout llAcquistaPersonaggio;

        TextView textViewCostoPersonaggio;




        LinearLayout llPersonaggioSelezionato; //DA NON VISUALIZZARE QUI
        Button buttonPossiediPersonaggio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPersonaggio = itemView.findViewById(R.id.imageViewPersonaggio);
            textViewNomePersonaggio = itemView.findViewById(R.id.textViewNomePersonaggio);

            llPersonaggioSelezionato = itemView.findViewById(R.id.llPersonaggioSelezionato);
            buttonPossiediPersonaggio = itemView.findViewById(R.id.buttonPossiediPersonaggio);
            llAcquistaPersonaggio = itemView.findViewById(R.id.llAcquistaPersonaggio);
            textViewCostoPersonaggio = itemView.findViewById(R.id.textViewCostoPersonaggio);

            llPersonaggioSelezionato.setVisibility(View.GONE);
            buttonPossiediPersonaggio.setVisibility(View.GONE);
        }
    }

}