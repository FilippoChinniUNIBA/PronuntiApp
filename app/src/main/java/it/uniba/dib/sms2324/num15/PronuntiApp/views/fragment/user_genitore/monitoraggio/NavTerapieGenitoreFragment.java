package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.monitoraggio;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.InfoDialog;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class NavTerapieGenitoreFragment extends AbstractFragmentWithNavigation {

    private int indiceTerapia;
    private ImageButton imageButtonProssimaTerapia;
    private ImageButton imageButtonTerapiaPrecedente;
    private GenitoreViewModel mGenitoreViewModel;
    private LinearLayout linearLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_nav_terapie, container, false);

        imageButtonProssimaTerapia = view.findViewById(R.id.buttonAvantiTerapia);
        imageButtonTerapiaPrecedente = view.findViewById(R.id.buttonIndietroTerapia);

        mGenitoreViewModel = new ViewModelProvider(requireActivity()).get(GenitoreViewModel.class);

        mGenitoreViewModel.getPazienteLiveData().observe(getViewLifecycleOwner(), paziente -> {
            if(mGenitoreViewModel.getPazienteLiveData().getValue().getTerapie()!=null) {
                this.indiceTerapia = mGenitoreViewModel.getPazienteLiveData().getValue().getTerapie().size() - 1;
            }else {
                this.indiceTerapia = -1;
            }
        });

        Log.d("Boh",""+indiceTerapia);

        linearLayout = view.findViewById(R.id.myLinearLayout);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Bundle bundle = new Bundle();

        if(indiceTerapia != -1){
            imageButtonProssimaTerapia.setOnClickListener(v -> {
                if (indiceTerapia != mGenitoreViewModel.getIndiceUltimaTerapia() && indiceTerapia != -1) {
                    indiceTerapia++;
                    Log.d("NavTerapieGenitore",""+indiceTerapia);
                    bundle.putInt("indiceTerapiaScelta",indiceTerapia);
                    MonitoraggioGenitoreFragment nuovoFragmentMonitoraggio = new MonitoraggioGenitoreFragment();
                    nuovoFragmentMonitoraggio.setArguments(bundle);
                    getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMonitoraggio,nuovoFragmentMonitoraggio).commit();
                }else{
                    creaDialogErroreCampi(1);
                }
            });

            imageButtonTerapiaPrecedente.setOnClickListener(v -> {
                if (indiceTerapia != 0 && indiceTerapia != -1) {
                    indiceTerapia--;
                    Log.d("NavTerapieGenitore",""+indiceTerapia);
                    bundle.putInt("indiceTerapiaScelta",indiceTerapia);
                    MonitoraggioGenitoreFragment nuovoFragmentMonitoraggio = new MonitoraggioGenitoreFragment();
                    nuovoFragmentMonitoraggio.setArguments(bundle);
                    getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMonitoraggio,nuovoFragmentMonitoraggio).commit();
                }else{
                    creaDialogErroreCampi(2);
                }

            });

        }else{
            linearLayout.setVisibility(View.GONE);
        }

    }

    public void creaDialogErroreCampi(int tipoErrore) {
        String messaggioErrore = "";
        switch (tipoErrore) {
            case 1:
                messaggioErrore = getString(R.string.firstTherapy);
                break;
            case 2:
                messaggioErrore = getString(R.string.lastTherapy);
                break;
        }
        InfoDialog infoDialog = new InfoDialog(getContext(), messaggioErrore, getString(R.string.tastoRiprova));
        infoDialog.show();
        infoDialog.setOnConfermaButtonClickListener(null);
    }


}
