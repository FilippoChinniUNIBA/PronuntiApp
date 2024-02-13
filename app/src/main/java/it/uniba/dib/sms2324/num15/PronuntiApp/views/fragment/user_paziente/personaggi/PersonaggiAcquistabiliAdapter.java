package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.personaggi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class PersonaggiAcquistabiliAdapter extends RecyclerView.Adapter<PersonaggiAcquistabiliAdapter.ViewHolder> {
    private Context context;
    private List<String> urls;
    private List<String> nomiPersonaggi;

    public PersonaggiAcquistabiliAdapter(Context context, List<String> urls, List<String> nomiPersonaggi) {
        this.context = context;
        this.urls = urls;
        this.nomiPersonaggi = nomiPersonaggi;
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
        String nomePersonaggio = nomiPersonaggi.get(position);
        holder.textViewNomePersonaggio.setText(nomePersonaggio);
        //Picasso.get().load(url).into(holder.imageViewPersonaggio);
        Glide.with(context).asBitmap().apply(new RequestOptions().override(150, 150)).load(url).into(holder.imageViewPersonaggio);
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