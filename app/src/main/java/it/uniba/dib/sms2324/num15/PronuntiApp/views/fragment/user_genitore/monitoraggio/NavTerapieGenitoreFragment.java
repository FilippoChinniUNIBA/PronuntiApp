package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.monitoraggio;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class NavTerapieGenitoreFragment extends AbstractFragmentWithNavigation {

    private int indiceTerapia;
    private ImageButton imageButtonProssimaTerapia;
    private ImageButton imageButtonTerapiaPrecedente;
    private GenitoreViewModel mGenitoreViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_nav_terapie, container, false);

        imageButtonProssimaTerapia = view.findViewById(R.id.buttonAvantiTerapia);
        imageButtonTerapiaPrecedente = view.findViewById(R.id.buttonIndietroTerapia);

        mGenitoreViewModel = new ViewModelProvider(requireActivity()).get(GenitoreViewModel.class);

        mGenitoreViewModel.getPazienteLiveData().observe(getViewLifecycleOwner(), paziente -> {
            indiceTerapia = mGenitoreViewModel.getIndiceUltimaTerapia();
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("NavTerapieGenitore",""+indiceTerapia);

        Bundle bundle = new Bundle();

        imageButtonProssimaTerapia.setOnClickListener(v -> {
            if (indiceTerapia != mGenitoreViewModel.getIndiceUltimaTerapia() && indiceTerapia != -1) {
                indiceTerapia++;
                Log.d("NavTerapieGenitore",""+indiceTerapia);
                bundle.putInt("indiceTerapiaScelta",indiceTerapia);
                MonitoraggioGenitoreFragment nuovoFragmentMonitoraggio = new MonitoraggioGenitoreFragment();
                nuovoFragmentMonitoraggio.setArguments(bundle);
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMonitoraggio,nuovoFragmentMonitoraggio).commit();
            }
            //TODO dialog
        });

        imageButtonTerapiaPrecedente.setOnClickListener(v -> {
            if (indiceTerapia != 0 && indiceTerapia != -1) {
                indiceTerapia--;
                Log.d("NavTerapieGenitore",""+indiceTerapia);
                bundle.putInt("indiceTerapiaScelta",indiceTerapia);
                MonitoraggioGenitoreFragment nuovoFragmentMonitoraggio = new MonitoraggioGenitoreFragment();
                nuovoFragmentMonitoraggio.setArguments(bundle);
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMonitoraggio,nuovoFragmentMonitoraggio).commit();
            }
        });

    }

}
