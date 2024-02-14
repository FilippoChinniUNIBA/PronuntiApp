package it.uniba.dib.sms2324.num15.PronuntiApp.views.monitoraggio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class MonitoraggioEsercizioView extends AbstractFragmentWithNavigation {

    private TextView textNumber;
    private TextView textDenomination;
    private ImageView imageCheck;
    private ImageView imageWrong;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.item_esercizio, container, false);

        // Find views
        textNumber = view.findViewById(R.id.textViewNumeroEsercizio);
        textDenomination = view.findViewById(R.id.textViewTipoEsercizio);
        imageCheck = view.findViewById(R.id.imageViewCheckEsercizio);
        imageWrong = view.findViewById(R.id.imageViewWrongEsercizio);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO set textNumber and textDenomination da viewmodel
        textNumber.setText("1°");
        textDenomination.setText("Denominazione immagine");

        //TODO if correct setCorrectVisibility(); else setWrongVisibility();
        setCorrectVisibility();
    }

    private void setCorrectVisibility( ) {
        imageWrong.setVisibility(View.GONE);
        imageCheck.setVisibility(View.VISIBLE);
    }

    private void setWrongVisibility() {
        imageCheck.setVisibility(View.GONE);
        imageWrong.setVisibility(View.VISIBLE);
    }

}
