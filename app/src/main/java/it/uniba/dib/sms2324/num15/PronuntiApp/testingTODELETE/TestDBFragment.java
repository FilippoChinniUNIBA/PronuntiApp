package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class TestDBFragment extends Fragment {
	private TextView textViewTestDB;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.test_fragment_test_db, container, false);

		View bottoneTestDB = view.findViewById(R.id.buttonAvviaTestDB);

		this.textViewTestDB = view.findViewById(R.id.textRisultatoTestDB);

		bottoneTestDB.setOnClickListener(v -> azioneBottoneTestDB());

		return view;
	}

	private void azioneBottoneTestDB() {
		this.textViewTestDB.setText("TestDBFunzionante");
	}


}
