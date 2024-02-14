package it.uniba.dib.sms2324.num15.PronuntiApp.views.monitoraggio;

import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.Esercizio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioEseguibile;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

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
        holder.textViewDataScenario.setText(holder.textViewDataScenario.getText() + " "+scenario.getDataInizio().toString());

        if(counterEsercizi>3) {
            counterEsercizi = 0;
        }

        for(int i=0; i<scenario.getEsercizi().size(); i++) {
            EsercizioEseguibile esercizio = scenario.getEsercizi().get(i);


            Log.d("ScenarioAdapter", "onBindViewHolder esercizio: " +i+ " "+esercizio.getIdEsercizio());
            int id = 0;
            switch (i) {
                case 0:
                    id = R.id.fragmentContainerViewMonitoraggioEsercizio1 + i;
                    break;
                case 1:
                    id = R.id.fragmentContainerViewMonitoraggioEsercizio2 +i ;
                    break;
                case 2:
                    id = R.id.fragmentContainerViewMonitoraggioEsercizio3 +i;
                    break;
            }

            // Crea un nuovo FragmentContainerView per ogni esercizio
            FragmentContainerView fragmentContainerView = new FragmentContainerView(context);
            fragmentContainerView.setId(id);
            fragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            holder.linearLayoutInfoScenario.addView(fragmentContainerView);
            fragmentManager.beginTransaction().add(id, new EsercizioScenarioFragment(esercizio, ++counterEsercizi)).commit();
        }
    }

    @Override
    public int getItemCount() {
        return listaScenari.size();
    }

    public class ScenarioViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView textViewDataScenario;
        private ImageView imageViewArrowDown;
        private LinearLayout linearLayoutInfoScenario;

        public ScenarioViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardViewScenario);
            textViewDataScenario = itemView.findViewById(R.id.textViewDataScenario);
            imageViewArrowDown = itemView.findViewById(R.id.imageViewArrowDown);
            imageViewArrowDown.setImageResource(R.drawable.ic_arrow_down_white);
            linearLayoutInfoScenario = itemView.findViewById(R.id.linearLayoutInfoScenario);
            //linearLayoutInfoScenario.setVisibility(View.GONE);
            cardView.setOnClickListener(v -> toggleVisibility());
        }


        private void toggleVisibility() {
            if (linearLayoutInfoScenario.getVisibility() == View.VISIBLE) {
                linearLayoutInfoScenario.setVisibility(View.GONE);
                imageViewArrowDown.setRotation(0);
            } else {
                linearLayoutInfoScenario.setVisibility(View.VISIBLE);
                imageViewArrowDown.setRotation(180);
            }
        }
    }
}
