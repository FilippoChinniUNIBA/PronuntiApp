package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.personaggio.PersonaggioDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class TestDBFragment extends Fragment {
	private TextView textViewTestDB;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.test_fragment_test_db, container, false);

		View bottoneTestDB = view.findViewById(R.id.buttonAvviaTestDB);

		this.textViewTestDB = view.findViewById(R.id.textRisultatoTestDB);

		bottoneTestDB.setOnClickListener(v -> testDAOPersonaggio());

		return view;
	}

	private void testDAOPersonaggio() {
		// PER FAR FUNZIONARE AGGIUNGERE UN METODO getDb() ALLA CLASSE PersonaggioDAO

//		PersonaggioDAO daoP = new PersonaggioDAO();
//		Personaggio p = new Personaggio("1", "abcde", 500, "texture");
//		DatabaseReference ref = daoP.getDb().getReference("personaggi");
//
//		String chiave = ref.push().getKey();
//
//		ref.child(chiave).setValue(p);
//
//		textViewTestDB.setText(chiave);
	}


}
