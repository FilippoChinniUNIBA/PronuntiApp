package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class TestApiFragment extends Fragment {
    private Button buttonAvviaRegistrazione;
    private Button buttonStopRegistrazione;
    private TextView textViewSpeechToTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.test_fragment_test_api, container, false);

        buttonAvviaRegistrazione = view.findViewById(R.id.buttonAvviaRegistrazione);
        buttonStopRegistrazione = view.findViewById(R.id.buttonStopRegistrazione);
        textViewSpeechToTextView = view.findViewById(R.id.textViewSpeechToText);
        setupButtons();

        return view;
    }

    private void setupButtons() {
        buttonAvviaRegistrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Azione per il bottone 1
            }
        });

        buttonStopRegistrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewSpeechToTextView.setText("Prova");
            }
        });

    }

}
