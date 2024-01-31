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

		buttonTestDB_1.setOnClickListener(v -> testDAOPersonaggioSave());
		buttonTestDB_2.setOnClickListener(v -> testDAOPersonaggioGet());
		buttonTestDB_3.setOnClickListener(v -> testDAOPersonaggioUpdate());
		buttonTestDB_4.setOnClickListener(v -> testDAOPersonaggioDelete());

		return view;
	}

	private void testDAOPersonaggioSave() {
		String idPersonaggio = "1234567890";
		String nomePersonaggio = "nome prova 1";
		int costoSblocco = 600;
		File texturePersonaggio = new File("folder/texture/texture.png");

		Personaggio p = new Personaggio(nomePersonaggio, costoSblocco, texturePersonaggio);

		PersonaggioDAO daoP = new PersonaggioDAO();
		daoP.save(p);

		//List<Personaggio> list = daoP.get(CostantiDBPersonaggio.NOME_PERSONAGGIO, "nome prova 1");
		Log.d("Test DAO - Save", p.toString());
		textViewTestDB_1.setText(p.toString());
	}

	private void testDAOPersonaggioUpdate() {
		String idPersonaggio = "-NpTuTGI9QUg0y5XoeJO";
		String nomePersonaggio = "abcde";
		int costoSblocco = 500;
		File texturePersonaggio = new File("folder/cambioProva/texture.png");

		Personaggio p = new Personaggio(idPersonaggio, nomePersonaggio, costoSblocco, texturePersonaggio);

		PersonaggioDAO daoP = new PersonaggioDAO();
		daoP.update(p);

		Log.d("Test DAO - Update", p.toString());
		textViewTestDB_3.setText(p.toString());
	}

	private void testDAOPersonaggioDelete() {
		String idPersonaggio = "-NpWKFqa3yzinrW3acKT";
		String nomePersonaggio = "weggrrgrg";
		int costoSblocco = 1000;
		File texturePersonaggio = new File("folder/cambioSbaglio/texture.png");

		Personaggio p = new Personaggio(idPersonaggio, nomePersonaggio, costoSblocco, texturePersonaggio);

		PersonaggioDAO daoP = new PersonaggioDAO();
		daoP.delete(p);

		Log.d("Test DAO - Delete", p.toString());
		textViewTestDB_4.setText(p.toString());
	}

	private void testDAOPersonaggioGet() {
		PersonaggioDAO daoP = new PersonaggioDAO();

		List<Personaggio> lista = new ArrayList<>();
		lista = daoP.get(CostantiDBPersonaggio.NOME_PERSONAGGIO, "erger");

		Log.d("Test DAO - Get", (lista == null) ? "lista null" : lista.toString());
		textViewTestDB_2.setText(lista.toString());
	}


}
