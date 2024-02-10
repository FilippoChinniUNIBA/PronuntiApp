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
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.scenariogioco.TemplateScenarioGiocoDAO;
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

		//this.bottoneInserisci.setOnClickListener(v -> funzioneMaster1(LISTA_PERSONAGGI_PRONUNTIAPP));
		//this.bottoneInserisci.setOnClickListener(v -> funzioneMaster2(LISTA_TEMPLATE_SCENARIGIOCO));
		this.bottoneInserisci.setOnClickListener(v -> funzioneMaster3(LISTA_TEMPLATE_ESERCIZI));
	}

	public void funzioneMaster1(List<Personaggio> listaPersonaggi) {
		PersonaggioDAO personaggioDAO = new PersonaggioDAO();
		TemplateScenarioGiocoDAO templateScenarioGiocoDAO = new TemplateScenarioGiocoDAO();
		TemplateEsercizioDAO templateEsercizioDAO = new TemplateEsercizioDAO();

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

	public void funzioneMaster2(List<TemplateScenarioGioco> listaTemplateScenarioGioco) {
		TemplateScenarioGiocoDAO templateScenarioGiocoDAO = new TemplateScenarioGiocoDAO();

		/*for (TemplateScenarioGioco templateScenarioGioco : listaTemplateScenarioGioco) {
			templateScenarioGiocoDAO.save(templateScenarioGioco);
			testoInserito.append(templateScenarioGioco.toString());
		}*/

		templateScenarioGiocoDAO.getAll().thenAccept(templateScenariGioco -> {
			for (TemplateScenarioGioco templateScenarioGioco : templateScenariGioco) {
				Log.d("TestNuovoTest1Fragment", templateScenarioGioco.toString());
			}
		});
	}

	public void funzioneMaster3(List<Esercizio> listaTemplateEsercizi) {
		TemplateEsercizioDAO templateEsercizioDAO = new TemplateEsercizioDAO();

		for (Esercizio esercizio : listaTemplateEsercizi) {
			templateEsercizioDAO.save(esercizio);
			testoInserito.append(esercizio.toString());
		}

		/*templateEsercizioDAO.getAll().thenAccept(templateEsercizi -> {
			for (Esercizio esercizio : templateEsercizi) {
				Log.d("TestNuovoTest1Fragment", esercizio.toString());
			}
		});*/
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
		add(new TemplateScenarioGioco("-NqJ3ZUPycWc4KkmJPLA", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FSfondoScenario_CandyTheme.jpg?alt=media&token=9d207974-6524-45a4-abd2-996fa4a483f9", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FCandyTheme_pink.png?alt=media&token=9995fb55-93c9-44d1-a2e0-f4a4bbf00517", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FCandyTheme_yellow.png?alt=media&token=07a1a331-58c2-4ecb-a36f-a385054be968", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FCandyTheme_red.png?alt=media&token=87511017-3be4-4036-9bdf-3c86eef8de77"));
		add(new TemplateScenarioGioco("-NqJ3ZUtC06JNUi_ie1U", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FSfondoScenario_IceTheme.jpg?alt=media&token=da1acae8-776b-4081-8294-6c4c6e4055ca", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FIceTheme_igloo.png?alt=media&token=b173a74a-0962-4eaf-90b9-b04a693a21e9", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FIceTheme_lake.png?alt=media&token=29f404ad-6a41-4a9e-b7d0-ee3990944d60", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FIceTheme_house.png?alt=media&token=a6881757-a7b0-4966-af05-fbbb6014dc98"));
		add(new TemplateScenarioGioco("-NqJ3ZVI6vfRmUB-y6eC", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FSfondoScenario_SpaceTheme.jpg?alt=media&token=7cf8a6d6-795b-493d-99cf-c4847a76fbcf", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FSpaceTheme_asteroid.png?alt=media&token=3d34282a-a4eb-4440-b935-9609166af63f", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FSpaceTheme_jupiter.png?alt=media&token=70f6d65b-2cab-4e56-87b8-9921f5a5535b", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FSpaceTheme_earth.png?alt=media&token=1af1e347-1669-4df8-a5e8-3a7527db9b71"));
	}};

	/*private static final List<TemplateScenarioGioco> LISTA_TEMPLATE_SCENARIGIOCO = new ArrayList<TemplateScenarioGioco>() {{
		add(new TemplateScenarioGioco("https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FSfondoScenario_CandyTheme.jpg?alt=media&token=9d207974-6524-45a4-abd2-996fa4a483f9", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FCandyTheme_pink.png?alt=media&token=9995fb55-93c9-44d1-a2e0-f4a4bbf00517", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FCandyTheme_yellow.png?alt=media&token=07a1a331-58c2-4ecb-a36f-a385054be968", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FCandyTheme_red.png?alt=media&token=87511017-3be4-4036-9bdf-3c86eef8de77"));
		add(new TemplateScenarioGioco("https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FSfondoScenario_IceTheme.jpg?alt=media&token=da1acae8-776b-4081-8294-6c4c6e4055ca", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FIceTheme_igloo.png?alt=media&token=b173a74a-0962-4eaf-90b9-b04a693a21e9", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FIceTheme_lake.png?alt=media&token=29f404ad-6a41-4a9e-b7d0-ee3990944d60", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FIceTheme_house.png?alt=media&token=a6881757-a7b0-4966-af05-fbbb6014dc98"));
		add(new TemplateScenarioGioco("https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FSfondoScenario_SpaceTheme.jpg?alt=media&token=7cf8a6d6-795b-493d-99cf-c4847a76fbcf", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FSpaceTheme_asteroid.png?alt=media&token=3d34282a-a4eb-4440-b935-9609166af63f", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FSpaceTheme_jupiter.png?alt=media&token=70f6d65b-2cab-4e56-87b8-9921f5a5535b", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_scenarigioco%2FSpaceTheme_earth.png?alt=media&token=1af1e347-1669-4df8-a5e8-3a7527db9b71"));
	}};*/

	private static final List<Esercizio> LISTA_TEMPLATE_ESERCIZI = new ArrayList<Esercizio>() {{
		add(new TemplateEsercizioDenominazioneImmagine("-NqJ5r2wOiZ5Ebck7Lkj", 50, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio1_immagineEsercizio.png?alt=media&token=d2cc6092-8b9f-4276-98c8-67c8605b5fc5", "Trattore", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio1_audioAiuto.mp3?alt=media&token=4910d1b4-0820-4c28-8c28-a2138e7f85ef"));
		add(new TemplateEsercizioDenominazioneImmagine("-NqJ5r3OVBPPduPv4dZL", 30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio2_immagineEsercizio.jpg?alt=media&token=12b1a453-3be0-4eaf-813b-1b65fcc688fd", "Aereo", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio2_audioAiuto.mp3?alt=media&token=cf0c3145-09a2-484d-b61c-eaa30043b2e2"));
		add(new TemplateEsercizioDenominazioneImmagine("-NqJ5r3fG7a4aCiOHd_O", 30, 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio3_immagineEsercizio.png?alt=media&token=29b81fe3-7da2-473e-91e4-93daf35d75c3", "Joker", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio3_audioAiuto.mp3?alt=media&token=ac4e0848-bfdc-44c8-9d21-b184985b0f82"));
		add(new TemplateEsercizioDenominazioneImmagine("-NqJ5r42A2kCBlPXPXCV", 50, 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio4_immagineEsercizio.png?alt=media&token=3746dbb7-6b9b-4b9b-a97f-51e72c201a90", "Squalo", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio4_audioAiuto.mp3?alt=media&token=f9e3962a-bc38-411c-8fb3-b795bf2db260"));
		add(new TemplateEsercizioDenominazioneImmagine("-NqJ5r4TuDnUvQh76izw", 30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio5_immagineEsercizio.png?alt=media&token=669fb8d2-a818-4fe1-9b8b-75565c7648f8", "Giraffa", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio5_audioAiuto.mp3?alt=media&token=7086598f-2010-4149-a1db-be981ac97545"));

		add(new TemplateEsercizioSequenzaParole("-NqJ5r4vJ3fXbNQXvYLf", 30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_sequenza_parole%2FtemplateEsercizio1_audioAiuto.mp3?alt=media&token=111203b9-a2ed-4bd7-bc77-d7baffdce41d", "Topo", "Carota", "Sasso"));
		add(new TemplateEsercizioSequenzaParole("-NqJ5r5M4g0V0-Kc7j0r", 30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_sequenza_parole%2FtemplateEsercizio2_audioAiuto.mp3?alt=media&token=548e82b4-65f1-43b4-b98c-718749ad663f", "Maglione", "Letto", "Coperta"));
		add(new TemplateEsercizioSequenzaParole("-NqJ5r5qxjCX7vC1XHAa", 50, 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_sequenza_parole%2FtemplateEsercizio3_audioAiuto.mp3?alt=media&token=b32a588d-1b19-4c83-a827-2d4fe6b7f02c", "Biblioteca", "Libreria", "Tavolo"));
		add(new TemplateEsercizioSequenzaParole("-NqJ5r6J0LQS7QEHl9eZ", 50, 30, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_sequenza_parole%2FtemplateEsercizio4_audioAiuto.mp3?alt=media&token=02cae36b-dee1-4766-96fb-1a5089fc8450", "Quel", "Ramarro", "Marrone"));
		add(new TemplateEsercizioSequenzaParole("-NqJ5r6p8fd5x-X47OqR", 30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_sequenza_parole%2FtemplateEsercizio5_audioAiuto.mp3?alt=media&token=972a5982-d2b4-487e-a2a6-8d4f31979ff0", "Ciao", "Come", "Stai"));

		add(new TemplateEsercizioCoppiaImmagini("-NqJ5r7MnhxQRsHCE-GR", 50, 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio1_audioAiuto.mp3?alt=media&token=5a17cb26-d249-48d9-b1b8-d8388d4a28c2", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio1_immagineEsercizioCorretta.png?alt=media&token=620d98df-e564-4520-83c2-e42d05de379b", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio1_immagineEsercizioSbagliata.png?alt=media&token=a26db668-c8e4-47ea-9552-1f0a51c4c66c"));
		add(new TemplateEsercizioCoppiaImmagini("-NqJ5r840UXa0nroZfzj", 30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio2_audioAiuto.mp3?alt=media&token=af68ade4-0c68-4d83-9765-c98c7cc10418", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio2_immagineEsercizioCorretta.png?alt=media&token=c8440237-2b10-4968-af24-badcb7c2c7cf", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio2_immagineEsercizioSbagliata.png?alt=media&token=6a9275ed-f2b2-4c98-aae5-8eb9d10af327"));
		add(new TemplateEsercizioCoppiaImmagini("-NqJ5r8q6lqSnOExwJ6c", 50, 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio3_audioAiuto.mp3?alt=media&token=33c0efd2-be85-4ce7-a42e-9b1bb9d461af", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio3_immagineEsercizioCorretta.png?alt=media&token=a6a81428-cc42-404b-a49b-1255e9c6bd4e", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio3_immagineEsercizioSbagliata.png?alt=media&token=53d1879e-c016-4f5a-b06f-ea98a8dacfd7"));
		add(new TemplateEsercizioCoppiaImmagini("-NqJ5r9gdqxDMFDXi7WV", 30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio4_audioAiuto.mp3?alt=media&token=ba6014a6-b09b-43b8-83ba-1b4de4f53539", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio4_immagineEsercizioCorretta.png?alt=media&token=76c495d1-e3ad-4bc5-a2fc-58d64787fe2b", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio4_immagineEsercizioSbagliata.png?alt=media&token=2dbef1f1-36e0-4b9b-9afc-a2418244fd73"));
		add(new TemplateEsercizioCoppiaImmagini("-NqJ5rAapfEB1bghNK5T", 30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio5_audioAiuto.mp3?alt=media&token=6c9e97d4-b7ac-441d-98b7-67f56ee75b59", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio5_immagineEsercizioCorretta.png?alt=media&token=7b540765-2bcb-4df6-9494-466d00c43875", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio5_immagineEsercizioSbagliata.png?alt=media&token=41c96c07-dfe0-4a78-9588-21b3f6dc48ee"));
	}};

	/*private static final List<Esercizio> LISTA_TEMPLATE_ESERCIZI = new ArrayList<Esercizio>() {{
		add(new TemplateEsercizioDenominazioneImmagine(50, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio1_immagineEsercizio.png?alt=media&token=d2cc6092-8b9f-4276-98c8-67c8605b5fc5", "Trattore", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio1_audioAiuto.mp3?alt=media&token=4910d1b4-0820-4c28-8c28-a2138e7f85ef"));
		add(new TemplateEsercizioDenominazioneImmagine(30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio2_immagineEsercizio.jpg?alt=media&token=12b1a453-3be0-4eaf-813b-1b65fcc688fd", "Aereo", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio2_audioAiuto.mp3?alt=media&token=cf0c3145-09a2-484d-b61c-eaa30043b2e2"));
		add(new TemplateEsercizioDenominazioneImmagine(30, 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio3_immagineEsercizio.png?alt=media&token=29b81fe3-7da2-473e-91e4-93daf35d75c3", "Joker", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio3_audioAiuto.mp3?alt=media&token=ac4e0848-bfdc-44c8-9d21-b184985b0f82"));
		add(new TemplateEsercizioDenominazioneImmagine(50, 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio4_immagineEsercizio.png?alt=media&token=3746dbb7-6b9b-4b9b-a97f-51e72c201a90", "Squalo", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio4_audioAiuto.mp3?alt=media&token=f9e3962a-bc38-411c-8fb3-b795bf2db260"));
		add(new TemplateEsercizioDenominazioneImmagine(30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio5_immagineEsercizio.png?alt=media&token=669fb8d2-a818-4fe1-9b8b-75565c7648f8", "Giraffa", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_denominazione_immagine%2FtemplateEsercizio5_audioAiuto.mp3?alt=media&token=7086598f-2010-4149-a1db-be981ac97545"));

		add(new TemplateEsercizioSequenzaParole(30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_sequenza_parole%2FtemplateEsercizio1_audioAiuto.mp3?alt=media&token=111203b9-a2ed-4bd7-bc77-d7baffdce41d", "Topo", "Carota", "Sasso"));
		add(new TemplateEsercizioSequenzaParole(30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_sequenza_parole%2FtemplateEsercizio2_audioAiuto.mp3?alt=media&token=548e82b4-65f1-43b4-b98c-718749ad663f", "Maglione", "Letto", "Coperta"));
		add(new TemplateEsercizioSequenzaParole(50, 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_sequenza_parole%2FtemplateEsercizio3_audioAiuto.mp3?alt=media&token=b32a588d-1b19-4c83-a827-2d4fe6b7f02c", "Biblioteca", "Libreria", "Tavolo"));
		add(new TemplateEsercizioSequenzaParole(50, 30, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_sequenza_parole%2FtemplateEsercizio4_audioAiuto.mp3?alt=media&token=02cae36b-dee1-4766-96fb-1a5089fc8450", "Quel", "Ramarro", "Marrone"));
		add(new TemplateEsercizioSequenzaParole(30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_sequenza_parole%2FtemplateEsercizio5_audioAiuto.mp3?alt=media&token=972a5982-d2b4-487e-a2a6-8d4f31979ff0", "Ciao", "Come", "Stai"));

		add(new TemplateEsercizioCoppiaImmagini(50, 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio1_audioAiuto.mp3?alt=media&token=5a17cb26-d249-48d9-b1b8-d8388d4a28c2", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio1_immagineEsercizioCorretta.png?alt=media&token=620d98df-e564-4520-83c2-e42d05de379b", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio1_immagineEsercizioSbagliata.png?alt=media&token=a26db668-c8e4-47ea-9552-1f0a51c4c66c"));
		add(new TemplateEsercizioCoppiaImmagini(30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio2_audioAiuto.mp3?alt=media&token=af68ade4-0c68-4d83-9765-c98c7cc10418", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio2_immagineEsercizioCorretta.png?alt=media&token=c8440237-2b10-4968-af24-badcb7c2c7cf", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio2_immagineEsercizioSbagliata.png?alt=media&token=6a9275ed-f2b2-4c98-aae5-8eb9d10af327"));
		add(new TemplateEsercizioCoppiaImmagini(50, 20, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio3_audioAiuto.mp3?alt=media&token=33c0efd2-be85-4ce7-a42e-9b1bb9d461af", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio3_immagineEsercizioCorretta.png?alt=media&token=a6a81428-cc42-404b-a49b-1255e9c6bd4e", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio3_immagineEsercizioSbagliata.png?alt=media&token=53d1879e-c016-4f5a-b06f-ea98a8dacfd7"));
		add(new TemplateEsercizioCoppiaImmagini(30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio4_audioAiuto.mp3?alt=media&token=ba6014a6-b09b-43b8-83ba-1b4de4f53539", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio4_immagineEsercizioCorretta.png?alt=media&token=76c495d1-e3ad-4bc5-a2fc-58d64787fe2b", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio4_immagineEsercizioSbagliata.png?alt=media&token=2dbef1f1-36e0-4b9b-9afc-a2418244fd73"));
		add(new TemplateEsercizioCoppiaImmagini(30, 10, "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio5_audioAiuto.mp3?alt=media&token=6c9e97d4-b7ac-441d-98b7-67f56ee75b59", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio5_immagineEsercizioCorretta.png?alt=media&token=7b540765-2bcb-4df6-9494-466d00c43875", "https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/template_esercizi%2Ftemplate_esercizi_coppia_immagini%2FtemplateEsercizio5_immagineEsercizioSbagliata.png?alt=media&token=41c96c07-dfe0-4a78-9588-21b3f6dc48ee"));
	}};*/


}
