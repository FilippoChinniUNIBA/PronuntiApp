package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBPersonaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.personaggio.PersonaggioDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class TestDBFragment extends Fragment {
	private Button buttonTestDB_1;
	private Button buttonTestDB_2;
	private Button buttonTestDB_3;
	private Button buttonTestDB_4;
	private TextView textViewTestDB_1;
	private TextView textViewTestDB_2;
	private TextView textViewTestDB_3;
	private TextView textViewTestDB_4;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.test_fragment_test_db, container, false);

		buttonTestDB_1 = view.findViewById(R.id.test_DB_Action_Button_1);
		buttonTestDB_2 = view.findViewById(R.id.test_DB_Action_Button_2);
		buttonTestDB_3 = view.findViewById(R.id.test_DB_Action_Button_3);
		buttonTestDB_4 = view.findViewById(R.id.test_DB_Action_Button_4);

		textViewTestDB_1 = view.findViewById(R.id.test_DB_Action_TextView_1);
		textViewTestDB_2 = view.findViewById(R.id.test_DB_Action_TextView_2);
		textViewTestDB_3 = view.findViewById(R.id.test_DB_Action_TextView_3);
		textViewTestDB_4 = view.findViewById(R.id.test_DB_Action_TextView_4);

		//buttonTestDB_1.setOnClickListener(v -> testDAOPersonaggioSave());
		buttonTestDB_2.setOnClickListener(v -> testDAOPersonaggioGet());

		return view;
	}

	private void testDAOPersonaggioSave() {
		// PER FAR FUNZIONARE AGGIUNGERE UN METODO getDb() ALLA CLASSE PersonaggioDAO

		String idPersonaggio = "1234567890";
		String nomePersonaggio = "erger";
		int costoSblocco = 600;
		File texturePersonaggio = new File("folder/texture/texture.png");

		Personaggio p = new Personaggio(nomePersonaggio, costoSblocco, texturePersonaggio);

		PersonaggioDAO daoP = new PersonaggioDAO();
		DatabaseReference ref = daoP.getDb().getReference(CostantiNodiDB.PERSONAGGI);

		String chiave = ref.push().getKey();
		ref.child(chiave).setValue(p.toMap());

		textViewTestDB_1.setText(chiave);
	}

	private void testDAOPersonaggioGet() {
		// PER FAR FUNZIONARE AGGIUNGERE UN METODO getDb() ALLA CLASSE PersonaggioDAO

		PersonaggioDAO daoP = new PersonaggioDAO();
		DatabaseReference ref = daoP.getDb().getReference(CostantiNodiDB.PERSONAGGI);

		List<Personaggio> lista = new ArrayList<>();
		lista = daoP.get(CostantiDBPersonaggio.NOME_PERSONAGGIO, "erger");

		Log.d("PROVA PERSONAGGIO - Dao", (lista == null) ? "lista null" : lista.toString());
		textViewTestDB_2.setText(lista.toString());

	}


}
