package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.monitoraggio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;

public class ScenarioAdapter extends RecyclerView.Adapter<ScenarioAdapter.ScenarioViewHolder> {
    private List<ScenarioGioco> listaScenari;
    private NavigateTo navigateTo;
    private int idNavToEsercizioDenominazioneImmagine;
    private int idNavToEsercizioCoppiaImmagini;
    private int idNavToEsercizioSequenzaParole;


    public ScenarioAdapter(List<ScenarioGioco> listaScenari, NavigateTo navigateTo, int idNavToEsercizioDenominazioneImmagine, int idNavToEsercizioCoppiaImmagini, int idNavToEsercizioSequenzaParole) {
        this.listaScenari = listaScenari;
        this.navigateTo = navigateTo;
    }

    @NonNull
    @Override
    public ScenarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scenario, parent, false);
        return new ScenarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScenarioViewHolder holder, int position) {
        ScenarioGioco scenario = listaScenari.get(position);
        holder.textViewGiornoScenario.setText(DateTimeFormatter.ofPattern("dd").format(scenario.getDataInizio()));
        holder.textViewMeseAnnoScenario.setText(DateTimeFormatter.ofPattern("MMMM yyyy").format(scenario.getDataInizio()));

        RecyclerView recyclerViewEsercizi = holder.recyclerViewEsercizi;
        recyclerViewEsercizi.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        EsercizioAdapter esercizioAdapter = new EsercizioAdapter(scenario.getEsercizi(), navigateTo, idNavToEsercizioDenominazioneImmagine, idNavToEsercizioCoppiaImmagini, idNavToEsercizioSequenzaParole);
        recyclerViewEsercizi.setAdapter(esercizioAdapter);

    }

    @Override
    public int getItemCount() {
        return listaScenari.size();
    }

    public class ScenarioViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView textViewGiornoScenario;
        private TextView textViewMeseAnnoScenario;
        private ImageView imageViewArrowDown;
        private RecyclerView recyclerViewEsercizi;

        public ScenarioViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardViewScenario);
            textViewGiornoScenario = itemView.findViewById(R.id.textViewGiornoScenario);
            textViewMeseAnnoScenario = itemView.findViewById(R.id.textViewMeseAnnoScenario);
            imageViewArrowDown = itemView.findViewById(R.id.imageViewArrowDown);
            imageViewArrowDown.setImageResource(R.drawable.ic_arrow_down_white);
            recyclerViewEsercizi = itemView.findViewById(R.id.recyclerViewEsercizi);
            recyclerViewEsercizi.setVisibility(View.GONE);
            cardView.setOnClickListener(v -> toggleVisibility());
        }


        private void toggleVisibility() {
            if (recyclerViewEsercizi.getVisibility() == View.VISIBLE) {
                recyclerViewEsercizi.setVisibility(View.GONE);
                imageViewArrowDown.setRotation(0);
            } else {
                recyclerViewEsercizi.setVisibility(View.VISIBLE);
                imageViewArrowDown.setRotation(180);
            }
        }
    }
}
