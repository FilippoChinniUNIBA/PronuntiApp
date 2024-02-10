package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.database_utils.ComandiFirebaseStorage;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.esercizio.TemplateEsercizioDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.personaggio.PersonaggioDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.Esercizio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.TemplateEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.TemplateEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.TemplateEsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.TemplateScenarioGioco;

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

		/*for (Personaggio personaggio : listaPersonaggi) {
			personaggioDAO.save(personaggio);
			testoInserito.append(personaggio.toString());
		}*/

		personaggioDAO.getAll().thenAccept(personaggi -> {
			for (Personaggio personaggio : personaggi) {
				Log.d("TestNuovoTest1Fragment", personaggio.getIdPersonaggio() + "\t" + personaggio.getNomePersonaggio());
			}
		});


	}

	private static final List<Personaggio> LISTA_PERSONAGGI_PRONUNTIAPP = new ArrayList<Personaggio>() {{
		add(new Personaggio("-NqIFkOcaDJKFU_BhuH2", "Batman", 500, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FBatman.png?alt=media&token=b892a5f7-8cd5-4081-976e-34925b20740f"));
		add(new Personaggio("-NqIFkOqJ7eznb2BIubm", "Cane", 0, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FCane.png?alt=media&token=ca263bc9-634d-4b34-a498-4c87f5a791e2"));
		add(new Personaggio("-NqIFkOuix-wiNRaxtlo", "Capitan America", 200, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FCapitan%20America.png?alt=media&token=0c9d2613-9d93-4fb0-a8a7-40d5e770c143"));
		add(new Personaggio("-NqIFkP0yNyXQFp5VveX", "Cavaliere", 100, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FCavaliera.png?alt=media&token=83bdcc54-4730-4c83-ac13-6551faf3b836"));
		add(new Personaggio("-NqIFkP7u9RENb6CfQlG", "Cavaliera", 100, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FCavaliere.png?alt=media&token=0f5d97fb-1a3c-41ed-ba63-93f52b41f202"));
		add(new Personaggio("-NqIFkPCmPM-2sOY6O0e", "Coniglio", 0, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FConiglio.png?alt=media&token=de33b79e-fe74-4191-bfdf-bb5d15550ff1"));
		add(new Personaggio("-NqIFkPIJoJlQZ1iwgfj", "Draghetta", 300, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FDraghetta.png?alt=media&token=500b4653-fb12-4a59-9a50-a5baee091f9d"));
		add(new Personaggio("-NqIFkPTTupPgYkYc74E", "Drago", 1000, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FDrago.png?alt=media&token=cd56a567-567f-4945-b8de-7a182168a7a0"));
		add(new Personaggio("-NqIFkPYD_JMBxlV-dIx", "Elefante", 250, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FElefante.png?alt=media&token=8fc69ac1-97f2-460c-9d57-ef8ad9f4c571"));
		add(new Personaggio("-NqIFkPchAFVCOm0Uffi", "Gattina", 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FGattina.png?alt=media&token=c61ff666-8ef6-4f8f-91ec-cba90fac9da8"));
		add(new Personaggio("-NqIFkPgCrXO8lqN56jo", "Gatto", 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FGatto.png?alt=media&token=f43b4cc9-7885-43e3-bd98-8378a6cffd6c"));
		add(new Personaggio("-NqIFkPlPjvZDY2TskvO", "Mucca", 100, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FMucca.png?alt=media&token=7d6f6304-fa94-472f-bda4-c295fc50fdcb"));
		add(new Personaggio("-NqIFkPpkXllw9SXtFY2", "Pecora", 100, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FPecora.png?alt=media&token=a479a636-f152-4406-a605-f221bfb5decc"));
		add(new Personaggio("-NqIFkPtcD7NHKpKOL0X", "Spiderman", 800, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FSpiderman.png?alt=media&token=1e93debf-a5d0-49ad-a918-05b8b3aeca96"));
		add(new Personaggio("-NqIFkPxWAuHV9o40Idc", "Wonderwoman", 200, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FWonderwoman.png?alt=media&token=46260ed5-0447-445f-bf95-9a9342ed87ab"));
	}};

	/*private static final List<Personaggio> LISTA_PERSONAGGI_PRONUNTIAPP = new ArrayList<Personaggio>() {{
		add(new Personaggio("Batman", 500, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FBatman.png?alt=media&token=b892a5f7-8cd5-4081-976e-34925b20740f"));
		add(new Personaggio("Cane", 0, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FCane.png?alt=media&token=ca263bc9-634d-4b34-a498-4c87f5a791e2"));
		add(new Personaggio("Capitan America", 200, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FCapitan%20America.png?alt=media&token=0c9d2613-9d93-4fb0-a8a7-40d5e770c143"));
		add(new Personaggio("Cavaliere", 100, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FCavaliera.png?alt=media&token=83bdcc54-4730-4c83-ac13-6551faf3b836"));
		add(new Personaggio("Cavaliera", 100, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FCavaliere.png?alt=media&token=0f5d97fb-1a3c-41ed-ba63-93f52b41f202"));
		add(new Personaggio("Coniglio", 0, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FConiglio.png?alt=media&token=de33b79e-fe74-4191-bfdf-bb5d15550ff1"));
		add(new Personaggio("Draghetta", 300, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FDraghetta.png?alt=media&token=500b4653-fb12-4a59-9a50-a5baee091f9d"));
		add(new Personaggio("Drago", 1000, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FDrago.png?alt=media&token=cd56a567-567f-4945-b8de-7a182168a7a0"));
		add(new Personaggio("Elefante", 250, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FElefante.png?alt=media&token=8fc69ac1-97f2-460c-9d57-ef8ad9f4c571"));
		add(new Personaggio("Gattina", 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FGattina.png?alt=media&token=c61ff666-8ef6-4f8f-91ec-cba90fac9da8"));
		add(new Personaggio("Gatto", 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FGatto.png?alt=media&token=f43b4cc9-7885-43e3-bd98-8378a6cffd6c"));
		add(new Personaggio("Mucca", 100, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FMucca.png?alt=media&token=7d6f6304-fa94-472f-bda4-c295fc50fdcb"));
		add(new Personaggio("Pecora", 100, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FPecora.png?alt=media&token=a479a636-f152-4406-a605-f221bfb5decc"));
		add(new Personaggio("Spiderman", 800, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FSpiderman.png?alt=media&token=1e93debf-a5d0-49ad-a918-05b8b3aeca96"));
		add(new Personaggio("Wonderwoman", 200, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FWonderwoman.png?alt=media&token=46260ed5-0447-445f-bf95-9a9342ed87ab"));
	}};*/


	private static final List<TemplateScenarioGioco> LISTA_TEMPLATE_SCENARIGIOCO = new ArrayList<TemplateScenarioGioco>() {{
		add(new TemplateScenarioGioco("https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FSfondoScenario_CandyTheme.jpg?alt=media&token=9d207974-6524-45a4-abd2-996fa4a483f9", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FCandyTheme_pink.png?alt=media&token=9995fb55-93c9-44d1-a2e0-f4a4bbf00517", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FCandyTheme_yellow.png?alt=media&token=07a1a331-58c2-4ecb-a36f-a385054be968", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FCandyTheme_red.png?alt=media&token=87511017-3be4-4036-9bdf-3c86eef8de77"));
		add(new TemplateScenarioGioco("", "", "", ""));
		add(new TemplateScenarioGioco("", "", "", ""));
	}};

	private static final List<Esercizio> LISTA_TEMPLATE_ESERCIZI = new ArrayList<Esercizio>() {{
		add(new TemplateEsercizioDenominazioneImmagine(100, 10, "", "parola", ""));
		add(new TemplateEsercizioDenominazioneImmagine(100, 10, "", "parola", ""));
		add(new TemplateEsercizioDenominazioneImmagine(100, 10, "", "parola", ""));
		add(new TemplateEsercizioDenominazioneImmagine(100, 10, "", "parola", ""));
		add(new TemplateEsercizioDenominazioneImmagine(100, 10, "", "parola", ""));

		add(new TemplateEsercizioSequenzaParole(100, 10, "", "p1", "p2", "p3"));
		add(new TemplateEsercizioSequenzaParole(100, 10, "", "p1", "p2", "p3"));
		add(new TemplateEsercizioSequenzaParole(100, 10, "", "p1", "p2", "p3"));
		add(new TemplateEsercizioSequenzaParole(100, 10, "", "p1", "p2", "p3"));
		add(new TemplateEsercizioSequenzaParole(100, 10, "", "p1", "p2", "p3"));

		add(new TemplateEsercizioCoppiaImmagini(100, 10, "", "", ""));
		add(new TemplateEsercizioCoppiaImmagini(100, 10, "", "", ""));
		add(new TemplateEsercizioCoppiaImmagini(100, 10, "", "", ""));
		add(new TemplateEsercizioCoppiaImmagini(100, 10, "", "", ""));
		add(new TemplateEsercizioCoppiaImmagini(100, 10, "", "", ""));
	}};


}
