package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class TopBarFragment extends Fragment {
    private LinearLayout topBarLayout;
    private ImageView imageViewPaziente;
    private TextView textViewUsernamePaziente;
    private TextView textViewPunteggio;
    private TextView coinsTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_top_bar_paziente, container, false);

        topBarLayout = view.findViewById(R.id.topBarPaziente);
        imageViewPaziente = view.findViewById(R.id.imageViewPaziente);
        textViewUsernamePaziente = view.findViewById(R.id.textViewUsernamePaziente);
        textViewPunteggio = view.findViewById(R.id.textViewPunteggio);
        coinsTextView = view.findViewById(R.id.coinsTextView);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO set monete punteggio e username
        textViewPunteggio.setText("25000");
        coinsTextView.setText("2500");
        textViewUsernamePaziente.setText("Your username");
    }

    //TODO funzioni per aggiornare i punteggi e le monete

}
