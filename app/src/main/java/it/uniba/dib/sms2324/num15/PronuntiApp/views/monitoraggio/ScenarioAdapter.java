package it.uniba.dib.sms2324.num15.PronuntiApp.views.monitoraggio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;

public class ScenarioAdapter extends RecyclerView.Adapter<ScenarioAdapter.ScenarioViewHolder> {
    private List<ScenarioGioco> listaScenari;
    private FragmentManager fragmentManager;
    private Context context;
    private int counterEsercizi=0;

    public ScenarioAdapter(List<ScenarioGioco> listaScenari, FragmentManager fragmentManager, Context context) {
        this.listaScenari = listaScenari;
        this.fragmentManager = fragmentManager;
        this.context = context;
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
        holder.textViewDataScenario.setText(holder.textViewDataScenario.getText() + " " + scenario.getDataInizio().toString());

        RecyclerView recyclerViewEsercizi = holder.recyclerViewEsercizi;
        recyclerViewEsercizi.setLayoutManager(new LinearLayoutManager(context));
        EsercizioAdapter esercizioAdapter = new EsercizioAdapter(scenario.getEsercizi());
        recyclerViewEsercizi.setAdapter(esercizioAdapter);

    }

    @Override
    public int getItemCount() {
        return listaScenari.size();
    }

    public class ScenarioViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView textViewDataScenario;
        private ImageView imageViewArrowDown;
        private RecyclerView recyclerViewEsercizi;

        public ScenarioViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardViewScenario);
            textViewDataScenario = itemView.findViewById(R.id.textViewDataScenario);
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
