package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.scenari;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import java.time.format.DateTimeFormatter;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.InfoDialog;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class NavScenariFragment extends AbstractFragmentWithNavigation {
    private TextView textViewDataScenario;
    private ImageButton buttonIndietroScenario;
    private ImageButton buttonAvantiScenario;
    private PazienteViewModel mPazienteViewModel;
    private LinearLayout linearLayout;
    private int currentScenarioIndex = 0;
    private int maxSize;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_nav_scenari, container, false);
        mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);
        textViewDataScenario = view.findViewById(R.id.textViewDataScenario);
        buttonIndietroScenario = view.findViewById(R.id.buttonIndietroScenario);
        buttonAvantiScenario = view.findViewById(R.id.buttonAvantiScenario);
        linearLayout = view.findViewById(R.id.myLinearLayout);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPazienteViewModel.getPazienteLiveData().observe(getViewLifecycleOwner(), paziente -> {
            List<Integer> scenariIndexListmPazienteViewModel = mPazienteViewModel.getScenariPaziente();
            if(scenariIndexListmPazienteViewModel!= null) {
                maxSize = scenariIndexListmPazienteViewModel.size() - 1;
                currentScenarioIndex = maxSize;

                List<Terapia> terapie = paziente.getTerapie();
                int sizeTerapie = terapie.size();
                textViewDataScenario.setText(terapie.get(sizeTerapie - 1).getScenariGioco().get(currentScenarioIndex).getDataInizio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                buttonIndietroScenario.setOnClickListener(v -> navigaScenarioPrecedente(scenariIndexListmPazienteViewModel));
                buttonAvantiScenario.setOnClickListener(v -> navigaScenarioSuccessivo(scenariIndexListmPazienteViewModel));
            }else{
                linearLayout.setVisibility(View.GONE);
            }
        });
    }

    private void navigaScenarioSuccessivo(List<Integer> listaIndici) {
        if(currentScenarioIndex+1<=maxSize) {

            Bundle bundle = new Bundle();
            ScenarioFragment scenarioFragment = new ScenarioFragment();
            currentScenarioIndex +=1;
            bundle.putInt("indiceScenarioCorrente", listaIndici.get(currentScenarioIndex));
            scenarioFragment.setArguments(bundle);
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_scenari_singolo, scenarioFragment).commit();
        }else{
            showInfoDialog(true);
        }
    }

    private void navigaScenarioPrecedente(List<Integer> listaIndici) {
        if(currentScenarioIndex-1>=0) {

            Bundle bundle = new Bundle();
            ScenarioFragment scenarioFragment = new ScenarioFragment();
            currentScenarioIndex-=1;
            bundle.putInt("indiceScenarioCorrente", listaIndici.get(currentScenarioIndex));
            scenarioFragment.setArguments(bundle);
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_scenari_singolo, scenarioFragment).commit();
        }else{
            showInfoDialog(false);
        }
    }

    private void showInfoDialog(boolean error){
        if(error){
            InfoDialog infoDialog = new InfoDialog(requireActivity(),getString(R.string.navScenariNoticeForward),getString(R.string.ok));
            infoDialog.setOnConfermaButtonClickListener(() -> {});
            infoDialog.show();
        }else{
            InfoDialog infoDialog = new InfoDialog(requireActivity(),getString(R.string.navScenariNoticeBackward),getString(R.string.ok));
            infoDialog.setOnConfermaButtonClickListener(() -> {});
            infoDialog.show();
        }
    }

}
