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

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class PersonaggiAcquistabiliAdapter extends RecyclerView.Adapter<PersonaggiAcquistabiliAdapter.ViewHolder> {
    private Context context;
    private List<Personaggio> personaggiAcquistabili;
    PersonaggiSbloccatiAdapter personaggiSbloccatiAdapter;
    NestedScrollView nestedScrollView;

    public PersonaggiAcquistabiliAdapter(Context context, List<Personaggio> personaggiAcquistabili, PersonaggiSbloccatiAdapter personaggiSbloccatiAdapter, NestedScrollView nestedScrollView) {
        this.context = context;
        this.personaggiAcquistabili = personaggiAcquistabili;
        this.personaggiSbloccatiAdapter = personaggiSbloccatiAdapter;
        this.nestedScrollView = nestedScrollView;
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
        String url = personaggio.getTexturePersonaggio();
        String nomePersonaggio = personaggio.getNomePersonaggio();
        int costoPersonaggio = personaggio.getCostoSblocco();
        holder.textViewNomePersonaggio.setText(nomePersonaggio);
        Glide.with(context).asBitmap().apply(new RequestOptions().override(150, 150)).load(url).into(holder.imageViewPersonaggio);
        holder.textViewCostoPersonaggio.setText(String.valueOf(costoPersonaggio));

        holder.llAcquistaPersonaggio.setOnClickListener(v -> {
            personaggiAcquistabili.remove(personaggio);
            personaggiSbloccatiAdapter.addPersonaggisbloccato(personaggio);
            getAnimator().start();
            notifyDataSetChanged();
        });
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