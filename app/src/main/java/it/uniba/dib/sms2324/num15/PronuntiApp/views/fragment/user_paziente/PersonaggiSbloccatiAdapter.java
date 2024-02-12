package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class PersonaggiSbloccatiAdapter extends RecyclerView.Adapter<PersonaggiSbloccatiAdapter.ViewHolder> {
    private Context context;
    private List<String> urls;
    private List<String> nomiPersonaggi; // Aggiungi una lista per memorizzare i nomi dei personaggi

    public PersonaggiSbloccatiAdapter(Context context, List<String> urls, List<String> nomiPersonaggi) {
        this.context = context;
        this.urls = urls;
        this.nomiPersonaggi = nomiPersonaggi; // Inizializza la lista dei nomi dei personaggi
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_view_personaggio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = urls.get(position);
        String nomePersonaggio = nomiPersonaggi.get(position); // Ottieni il nome del personaggio corrispondente
        holder.textViewNomePersonaggio.setText(nomePersonaggio); // Imposta il nome del personaggio nel TextView
        Picasso.get().load(url).into(holder.imageViewPersonaggio);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPersonaggio;
        TextView textViewNomePersonaggio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPersonaggio = itemView.findViewById(R.id.imageViewPersonaggio);
            textViewNomePersonaggio = itemView.findViewById(R.id.textViewNomePersonaggio);
        }
    }
}
