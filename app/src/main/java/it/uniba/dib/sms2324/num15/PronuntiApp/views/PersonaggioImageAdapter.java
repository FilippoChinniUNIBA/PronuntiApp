package it.uniba.dib.sms2324.num15.PronuntiApp.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class PersonaggioImageAdapter extends RecyclerView.Adapter<PersonaggioImageAdapter.ImageViewHolder> {

    private final Context context;
    private final List<Integer> imageList;
    private final List<String> characterNames;
    private final List<String> characterCosts;

    public PersonaggioImageAdapter(Context context, List<Integer> imageList, List<String> characterNames, List<String> characterCosts) {
        this.context = context;
        this.imageList = imageList;
        this.characterNames = characterNames;
        this.characterCosts = characterCosts;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewPersonaggio;
        public TextView textViewCostoPersonaggio;
        public ImageView imageViewCoins;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewPersonaggio);
            textViewPersonaggio = itemView.findViewById(R.id.textViewNomePersonaggio);
            textViewCostoPersonaggio = itemView.findViewById(R.id.textViewCostoPersonaggio);
            imageViewCoins = itemView.findViewById(R.id.imageViewCoins);
        }
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_view_personaggio, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        int imageResource = imageList.get(position);
        String characterName = characterNames.get(position);
        String characterCost = characterCosts.get(position);

        holder.imageView.setImageResource(imageResource);
        holder.textViewPersonaggio.setText(characterName);
        holder.textViewCostoPersonaggio.setText( characterCost );
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}


