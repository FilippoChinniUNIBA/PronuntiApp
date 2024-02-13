package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;

public class TopBarFragment extends Fragment {
    private LinearLayout topBarLayout;
    private ImageView imageViewPaziente;
    private TextView textViewUsernamePaziente;
    private TextView textViewPunteggio;
    private TextView coinsTextView;
    private PazienteViewModel mPazienteViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_top_bar_paziente, container, false);

        this.mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);

        topBarLayout = view.findViewById(R.id.topBarPaziente);
        imageViewPaziente = view.findViewById(R.id.imageViewPaziente);
        textViewUsernamePaziente = view.findViewById(R.id.textViewUsernamePaziente);
        textViewPunteggio = view.findViewById(R.id.textViewPunteggio);
        coinsTextView = view.findViewById(R.id.coinsTextView);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPazienteViewModel.getPazienteLiveData().observe(getViewLifecycleOwner(), paziente -> {

                textViewUsernamePaziente.setText(paziente.getUsername());
                textViewPunteggio.setText(String.valueOf(paziente.getPunteggioTot()));
                coinsTextView.setText(String.valueOf(paziente.getValuta()));

        });
    }



    //TODO funzioni per aggiornare i punteggi e le monete



}
