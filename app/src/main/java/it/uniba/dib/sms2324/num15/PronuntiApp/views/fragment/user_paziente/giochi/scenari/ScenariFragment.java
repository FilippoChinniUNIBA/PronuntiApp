package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.scenari;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class ScenariFragment extends AbstractFragmentWithNavigation {
    private PazienteViewModel mPazienteViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_scenari,container,false);
        this.mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPazienteViewModel.getPazienteLiveData().observe(getViewLifecycleOwner(), Void -> {
            List<Integer> scenariGiocoValidi = mPazienteViewModel.getScenariPaziente();
            if(scenariGiocoValidi!=null) {
                int terapiaIndex = mPazienteViewModel.getTerapiaPazienteIndex();
                Bundle bundle = getArguments();
                Log.d("Bundle", "onViewCreated SCENARI fine scenario: " + bundle);

                //se c'è checkFineScenario, allora è stato completato uno scenario e si deve mostrare la schermata di fine esercizio
                if(bundle != null) {
                    if(bundle.containsKey("checkFineScenario") && bundle.getBoolean("checkFineScenario")) {
                        bundle.putBoolean("checkFineScenario", true);
                    }
                }
                else {
                    bundle = new Bundle();
                }
                ScenarioFragment scenarioFragment = new ScenarioFragment();
                bundle.putInt("indiceScenarioCorrente", scenariGiocoValidi.get(scenariGiocoValidi.size() - 1));
                bundle.putInt("indiceTerapia",terapiaIndex);
                if(bundle.containsKey("indiceScenarioGioco")){
                    bundle.putInt("indiceScenarioCorrente",bundle.getInt("indiceScenarioGioco"));
                }
                scenarioFragment.setArguments(bundle);
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_scenari_singolo, scenarioFragment).commit();
            }else{
                AssenzaScenariFragment assenzaScenariFragment = new AssenzaScenariFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_scenari_singolo, assenzaScenariFragment).commit();
            }
        });
    }


}
