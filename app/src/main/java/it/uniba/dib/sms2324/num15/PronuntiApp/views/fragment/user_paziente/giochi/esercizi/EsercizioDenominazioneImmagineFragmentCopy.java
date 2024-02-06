package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class EsercizioDenominazioneImmagineFragmentCopy extends Fragment {
    private ImageView aiutiImageView;
    private ImageView completaEsercizioImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla il layout per questo fragment
        View view = inflater.inflate(R.layout.fragment_esercizio_denominazione_immagine_copy, container, false);

        aiutiImageView = view.findViewById(R.id.buttonAiuti);
        completaEsercizioImageView = view.findViewById(R.id.buttonCompletaEsercizio);

        // Aggiungi il listener per il clic prolungato sui pulsanti Aiuti e CompletaEsercizio
        aiutiImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showTooltip(getString(R.string.showHelp));
                return true;
            }
        });

        completaEsercizioImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showTooltip(getString(R.string.checkAnswer));
                return true;
            }
        });

        return view;
    }

    // Metodo per mostrare i tooltip
    private void showTooltip(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("Denominazione immagine");
        }
    }
}