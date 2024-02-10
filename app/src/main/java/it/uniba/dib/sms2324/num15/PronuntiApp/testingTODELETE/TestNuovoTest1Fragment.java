package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.personaggio.PersonaggioDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class TestNuovoTest1Fragment extends Fragment {
	private View bottoneInserisci;
	private TextView testoInserito;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.test_fragment_test_nuovo_test_1, container, false);

		this.bottoneInserisci = view.findViewById(R.id.buttonTestInserimentoPersonaggi);
		this.testoInserito = view.findViewById(R.id.textViewTetsInserimentoPersonaggi);

		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		this.bottoneInserisci.setOnClickListener(v -> funzioneMaster(LISTA_PERSONAGGI_PRONUNTIAPP)); }

	public void funzioneMaster(List<Personaggio> listaPersonaggi) {
		PersonaggioDAO personaggioDAO = new PersonaggioDAO();

		for (Personaggio personaggio : listaPersonaggi) {
			personaggioDAO.save(personaggio);
			testoInserito.append(personaggio.toString());
		}


	}

	private static final List<Personaggio> LISTA_PERSONAGGI_PRONUNTIAPP = new ArrayList<Personaggio>() {{
		add(new Personaggio("personaggio1", 0, "texture1"));
		add(new Personaggio("personaggio2", 0, "texture2"));
		add(new Personaggio("personaggio3", 0, "texture3"));
		add(new Personaggio("personaggio4", 0, "texture4"));
		add(new Personaggio("personaggio5", 0, "texture5"));
		add(new Personaggio("personaggio6", 0, "texture6"));
		add(new Personaggio("personaggio7", 0, "texture7"));
		add(new Personaggio("personaggio8", 0, "texture8"));
		add(new Personaggio("personaggio9", 0, "texture9"));
		add(new Personaggio("personaggio10", 0, "texture10"));
	}};


}
