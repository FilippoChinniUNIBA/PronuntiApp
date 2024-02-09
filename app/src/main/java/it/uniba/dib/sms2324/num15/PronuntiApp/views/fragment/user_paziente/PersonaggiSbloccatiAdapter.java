package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente;

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
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class PersonaggiSbloccatiAdapter extends RecyclerView.Adapter<PersonaggiSbloccatiAdapter.ViewHolder> {

    private Context context;
    private List<Personaggio> listaPersonaggi;

    public PersonaggiSbloccatiAdapter(Context context, List<Personaggio> listaPersonaggi) {
        this.context = context;
        this.listaPersonaggi = listaPersonaggi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_view_personaggio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Personaggio currentItem = listaPersonaggi.get(position);

        // Configura la vista del ViewHolder con i dati appropriati
        //holder.textViewNomePersonaggio.setText(currentItem.getNome());

        // Puoi gestire clic o altre interazioni qui se necessario
    }

    @Override
    public int getItemCount() {
        return listaPersonaggi.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNomePersonaggio;
        ImageView imageViewPersonaggio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomePersonaggio = itemView.findViewById(R.id.textViewNomePersonaggio);
            imageViewPersonaggio = itemView.findViewById(R.id.imageViewPersonaggio);
            // Altri findViewById per altre viste...

            // Puoi impostare clic e altre interazioni qui se necessario
        }
    }
}
