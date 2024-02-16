package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.Autenticazione;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiNodiDB;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.database_utils.ComandiFirebaseStorage;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.LogopedistaDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.PazienteDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioEseguibile;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioCoppiaImmagini;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.risultato.RisultatoEsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.classifica.Classifica;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.utils_fragments.DatePickerCustom;

public class TestInserimentoDatiDBFragment extends Fragment {
    private TextInputEditText dataInizioTerapia;
    private TextInputEditText dataFineTerapia;
    private TextInputEditText immagineSfondoScenario;
    private TextInputEditText immagineTappa1;
    private TextInputEditText immagineTappa2;
    private TextInputEditText immagineTappa3;
    private TextInputEditText dataInizioScenario;
    private TextInputEditText ricompensaFinale;
    private TextInputEditText idTemplateScenarioGioco;
    private TextInputEditText ricompensaCorretto;
    private TextInputEditText ricompensaErrato;
    private TextInputEditText idTemplateEsercizio;
    private TextInputEditText immagineEsercizio;
    private TextInputEditText parolaEsercizio;
    private TextInputEditText audioAiuto;
    private TextInputEditText audioEsercizio;
    private TextInputEditText parola1;
    private TextInputEditText parola2;
    private TextInputEditText parola3;
    private TextInputEditText audioEsercizioImmagine;
    private TextInputEditText immagineEsercizioCorretta;
    private TextInputEditText immagineEsercizioErrata;
    private CheckBox esercizioCorretto;
    private TextInputEditText countAiuti;
    private TextInputEditText audioRegistrato;

    private Button bottoneFilePicker_immagineSfondo;
    private Button bottoneFilePicker_immagineTappa1;
    private Button bottoneFilePicker_immagineTappa2;
    private Button bottoneFilePicker_immagineTappa3;
    private Button bottoneFilePicker_immagineEsercizio;
    private Button bottoneFilePicker_audioAiuto;
    private Button bottoneFilePicker_audioEsercizio;
    private Button bottoneFilePicker_audioEsercizioImmagine;
    private Button bottoneFilePicker_immagineEsercizioCorretta;
    private Button bottoneFilePicker_immagineEsercizioErrata;
    private Button bottoneFilePicker_audioRegistrato;


    private static final int PICK_FILE_REQUEST_1 = 1;
    private static final int PICK_FILE_REQUEST_9 = 9;
    private static final int PICK_FILE_REQUEST_10 = 10;
    private static final int PICK_FILE_REQUEST_11 = 11;
    private static final int PICK_FILE_REQUEST_2 = 2;
    private static final int PICK_FILE_REQUEST_3 = 3;
    private static final int PICK_FILE_REQUEST_4 = 4;
    private static final int PICK_FILE_REQUEST_5 = 5;
    private static final int PICK_FILE_REQUEST_6 = 6;
    private static final int PICK_FILE_REQUEST_7 = 7;
    private static final int PICK_FILE_REQUEST_8 = 8;

    private Button bottoneInserisciDati;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment_inserimento_dati_db, container, false);

        this.dataInizioTerapia = view.findViewById(R.id.textInputEditTextDataInizioTerapia);
        this.dataFineTerapia = view.findViewById(R.id.textInputEditTextDataFineTerapia);

        this.immagineSfondoScenario = view.findViewById(R.id.textInputEditTextImmagineSfondo);
        this.immagineTappa1 = view.findViewById(R.id.textInputEditTextImmagineTappa1);
        this.immagineTappa2 = view.findViewById(R.id.textInputEditTextImmagineTappa2);
        this.immagineTappa3 = view.findViewById(R.id.textInputEditTextImmagineTappa3);
        this.dataInizioScenario = view.findViewById(R.id.textInputEditTextDataInizioScenario);
        this.ricompensaFinale = view.findViewById(R.id.textInputEditTextRicompensaFinaleScenario);
        this.idTemplateScenarioGioco = view.findViewById(R.id.textInputEditTextIdTemplateScenarioGioco);

        this.ricompensaCorretto = view.findViewById(R.id.textInputEditTextRicompensaCorretto);
        this.ricompensaErrato = view.findViewById(R.id.textInputEditTextRicompensaErrato);
        this.idTemplateEsercizio = view.findViewById(R.id.textInputEditTextIdTemplateEsercizio);
        this.immagineEsercizio = view.findViewById(R.id.textInputEditTextImmagineEsercizio);
        this.parolaEsercizio = view.findViewById(R.id.textInputEditTextParoleEsercizi);
        this.audioAiuto = view.findViewById(R.id.textInputEditTextAudioAiuto);
        this.audioEsercizio = view.findViewById(R.id.textInputEditTextAudioEsercizio);
        this.parola1 = view.findViewById(R.id.textInputEditTextParola1);
        this.parola2 = view.findViewById(R.id.textInputEditTextParola2);
        this.parola3 = view.findViewById(R.id.textInputEditTextParola3);
        this.audioEsercizioImmagine = view.findViewById(R.id.textInputEditTextAudioEsercizioImmagine);
        this.immagineEsercizioCorretta = view.findViewById(R.id.textInputEditTextImmagineEsercizioCorretta);
        this.immagineEsercizioErrata = view.findViewById(R.id.textInputEditTextImmagineEsercizioErrata);

        this.esercizioCorretto = view.findViewById(R.id.checkBoxEsitoCorretto);
        this.countAiuti = view.findViewById(R.id.textInputEditTextCountAiuti);
        this.audioRegistrato = view.findViewById(R.id.textInputEditTextAudioRegistrati);


        this.bottoneFilePicker_immagineSfondo = view.findViewById(R.id.buttonOpenFilePickerImmagineSfondo);
        this.bottoneFilePicker_immagineTappa1 = view.findViewById(R.id.buttonOpenFilePickerImmagineTappa1);
        this.bottoneFilePicker_immagineTappa2 = view.findViewById(R.id.buttonOpenFilePickerImmagineTappa2);
        this.bottoneFilePicker_immagineTappa3 = view.findViewById(R.id.buttonOpenFilePickerImmagineTappa3);
        this.bottoneFilePicker_immagineEsercizio = view.findViewById(R.id.buttonOpenFilePickerImmagineEsercizio);
        this.bottoneFilePicker_audioAiuto = view.findViewById(R.id.buttonOpenFilePickerAudioAiuto);
        this.bottoneFilePicker_audioEsercizio = view.findViewById(R.id.buttonOpenFilePickerAudioEsercizio);
        this.bottoneFilePicker_audioEsercizioImmagine = view.findViewById(R.id.buttonOpenFilePickerAudioEsercizioImmagine);
        this.bottoneFilePicker_immagineEsercizioCorretta = view.findViewById(R.id.buttonOpenFilePickerImmagineEserizioCorretta);
        this.bottoneFilePicker_immagineEsercizioErrata = view.findViewById(R.id.buttonOpenFilePickerImmagineEserizioErrata);
        this.bottoneFilePicker_audioRegistrato = view.findViewById(R.id.buttonOpenFilePickerAudioRegistrati);

        bottoneFilePicker_immagineSfondo.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_1));
        bottoneFilePicker_immagineTappa1.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_9));
        bottoneFilePicker_immagineTappa2.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_10));
        bottoneFilePicker_immagineTappa3.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_11));
        bottoneFilePicker_immagineEsercizio.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_2));
        bottoneFilePicker_audioAiuto.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_3));
        bottoneFilePicker_audioEsercizio.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_4));
        bottoneFilePicker_audioEsercizioImmagine.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_5));
        bottoneFilePicker_immagineEsercizioCorretta.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_6));
        bottoneFilePicker_immagineEsercizioErrata.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_7));
        bottoneFilePicker_audioRegistrato.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_8));

        dataInizioTerapia.setOnClickListener(v -> DatePickerCustom.showDatePickerDialog(getContext(), dataInizioTerapia));
        dataFineTerapia.setOnClickListener(v -> DatePickerCustom.showDatePickerDialog(getContext(), dataFineTerapia));
        dataInizioScenario.setOnClickListener(v -> DatePickerCustom.showDatePickerDialog(getContext(), dataInizioScenario));


        this.bottoneInserisciDati = view.findViewById(R.id.buttonSalvaTerapia);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottoneInserisciDati.setOnClickListener(v -> tempWrapper());
    }

    private void tempWrapper() {
		/*Autenticazione mAutenticazione = new Autenticazione();

		mAutenticazione.registrazione("email@provaREGISTRAZIONE.it", "password").thenAccept(aVoid -> {
			Log.d("TEST AUTH", "Registrazione avvenuta con successo");
			mAutenticazione.login("email@provaREGISTRAZIONE.it", "password").thenAccept(aVoid1 -> {
				Log.d("TEST AUTH", "Login avvenuto con successo");
				salvaInDB();
			});
		});*/

        salvaInDB();
    }

    private void salvaInDB() {

        //Terapia
        LocalDate dataInizioT = LocalDate.parse(dataInizioTerapia.getText().toString());
        LocalDate dataFineT = LocalDate.parse(dataFineTerapia.getText().toString());

        //ScenarioGioco
        String immagineSfondoS = immagineSfondoScenario.getText().toString();
        String immagineTappa1S = immagineTappa1.getText().toString();
        String immagineTappa2S = immagineTappa2.getText().toString();
        String immagineTappa3S = immagineTappa3.getText().toString();
        LocalDate dataInizioS = LocalDate.parse(dataInizioScenario.getText().toString());
        int ricompensaFinaleS = Integer.parseInt(ricompensaFinale.getText().toString());
        String idTemplateScenarioGiocoS = idTemplateScenarioGioco.getText().toString();

        //Esercizio
        int ricompensaCorrettoE = Integer.parseInt(ricompensaCorretto.getText().toString());
        int ricompensaErratoE = Integer.parseInt(ricompensaErrato.getText().toString());
        String idTemplateEsercizioE = idTemplateEsercizio.getText().toString();

        String immagineEsercizioE = immagineEsercizio.getText().toString();
        String parolaEsercizioE = parolaEsercizio.getText().toString();
        String audioAiutoE = audioAiuto.getText().toString();

        String audioEsercizioE = audioEsercizio.getText().toString();
        String parola1E = parola1.getText().toString();
        String parola2E = parola2.getText().toString();
        String parola3E = parola3.getText().toString();

        String audioEsercizioImmagineE = audioEsercizioImmagine.getText().toString();
        String immagineEsercizioCorrettaE = immagineEsercizioCorretta.getText().toString();
        String immagineEsercizioErrataE = immagineEsercizioErrata.getText().toString();

        //RisultatoEsercizio
        boolean esercizioCorrettoE = esercizioCorretto.isChecked();
        int countAiutiE = Integer.parseInt(countAiuti.getText().toString());
        String audioRegistratoE = audioRegistrato.getText().toString();


        RisultatoEsercizioDenominazioneImmagine risultatoDEN = new RisultatoEsercizioDenominazioneImmagine(esercizioCorrettoE, audioRegistratoE, countAiutiE);
        RisultatoEsercizioSequenzaParole risultatoSEQ = new RisultatoEsercizioSequenzaParole(esercizioCorrettoE, audioRegistratoE);
        RisultatoEsercizioCoppiaImmagini risultatoCOP = new RisultatoEsercizioCoppiaImmagini(esercizioCorrettoE);

        EsercizioEseguibile esercizioDEN = new EsercizioDenominazioneImmagine(ricompensaCorrettoE, ricompensaErratoE, immagineEsercizioE, parolaEsercizioE, audioAiutoE, idTemplateEsercizioE, risultatoDEN);
        EsercizioEseguibile esercizioSEQ = new EsercizioSequenzaParole(ricompensaCorrettoE, ricompensaErratoE, audioEsercizioE, parola1E, parola2E, parola3E, idTemplateEsercizioE, risultatoSEQ);
        EsercizioEseguibile esercizioCOP = new EsercizioCoppiaImmagini(ricompensaCorrettoE, ricompensaErratoE, audioEsercizioImmagineE, immagineEsercizioCorrettaE, immagineEsercizioErrataE, idTemplateEsercizioE, risultatoCOP);

        ScenarioGioco scenarioGioco = new ScenarioGioco(immagineSfondoS, immagineTappa1S, immagineTappa2S, immagineTappa3S, dataInizioS, ricompensaFinaleS, Arrays.asList(esercizioDEN, esercizioSEQ, esercizioCOP), idTemplateScenarioGiocoS);

        Terapia terapia = new Terapia(dataInizioT, dataFineT, Arrays.asList(scenarioGioco));

		/*Map<String, Integer> personaggiSbloccati = new HashMap<String, Integer>() {{
			put("personaggio1", 1);
			put("personaggio2", 0);
		}};
		Paziente paziente = new Paziente("prova123456", "nome", "cognome", "username", "email", "password", 5, LocalDate.parse("2021-01-01"), 'M', 120, 180, personaggiSbloccati, Arrays.asList(terapia));

		Classifica classifica = new Classifica(new HashMap<String, Integer>() {{
			put("ssss", 180);
			put("fsrdhs", 150);
			put("svss", 200);
		}});
		Logopedista logopedista = new Logopedista(FirebaseAuth.getInstance().getCurrentUser().getUid(),"nome", "cognome", "username", "email", "password", "333333333", "via", classifica, Arrays.asList(paziente));

		//Test get
		LogopedistaDAO logopedistaDAO = new LogopedistaDAO();
		logopedistaDAO.getAll().thenAccept(logopedisti -> {
			Log.d("TEST PROVA MATTA", "Logopedisti: " + logopedisti);
			Log.d("TEST PROVA MATTA", "Pazienti: " + logopedisti.get(0).getPazienti());
			Log.d("TEST PROVA MATTA", "Terapie: " + logopedisti.get(0).getPazienti().get(0).getTerapie());
			Log.d("TEST PROVA MATTA", "ScenariGioco: " + logopedisti.get(0).getPazienti().get(0).getTerapie().get(0).getScenariGioco());
			Log.d("TEST PROVA MATTA", "Esercizi: " + logopedisti.get(0).getPazienti().get(0).getTerapie().get(0).getScenariGioco().get(0).getEsercizi());
			Log.d("TEST PROVA MATTA", "Risultato Esercizio: " + logopedisti.get(0).getPazienti().get(0).getTerapie().get(0).getScenariGioco().get(0).getEsercizi().get(0).getRisultatoEsercizio());
		});
		*/

        //Test save
        PazienteDAO pazienteDAO = new PazienteDAO();
        pazienteDAO.getById("JVE3VGm5IZTnK83DzthuAeW46842").thenAccept(paziente -> {
            paziente.addTerapia(terapia);
            pazienteDAO.update(paziente);
            Log.d("TEST PROVA MATTA", "Terapia salvata: " + terapia.toString());
        });

        //Test get
        PazienteDAO pazienteDAO2 = new PazienteDAO();
        pazienteDAO2.getById("JVE3VGm5IZTnK83DzthuAeW46842").thenAccept(paziente -> {
            Log.d("TEST PROVA MATTA", "Paziente: " + paziente);
            Log.d("TEST PROVA MATTA", "Terapie: " + paziente.getTerapie());
            Log.d("TEST PROVA MATTA", "ScenariGioco: " + paziente.getTerapie().get(0).getScenariGioco());
            Log.d("TEST PROVA MATTA", "Esercizi: " + paziente.getTerapie().get(0).getScenariGioco().get(0).getEsercizi());
            Log.d("TEST PROVA MATTA", "Risultato Esercizio: " + paziente.getTerapie().get(0).getScenariGioco().get(0).getEsercizi().get(0).getRisultatoEsercizio());
        });

    }


    private void startFilePicker(int requestCode) {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[] {"image/*", "audio/*"});

        //ActivityResultLauncher<String> filePickerLauncher = createFilePickerLauncher(requestCode);
        //filePickerLauncher.launch(intent.getType());

        startActivityForResult(Intent.createChooser(intent, "Select Picture or Audio"), requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            ComandiFirebaseStorage comandiFirebaseStorage = new ComandiFirebaseStorage();
            comandiFirebaseStorage.uploadFileAndGetLink(uri, "TEST/TERPIA_TEST").thenAccept(link -> {

                switch (requestCode) {
                    case PICK_FILE_REQUEST_1:
                        immagineSfondoScenario.setText(link);
                        break;
                    case PICK_FILE_REQUEST_2:
                        immagineEsercizio.setText(link);
                        break;
                    case PICK_FILE_REQUEST_3:
                        audioAiuto.setText(link);
                        break;
                    case PICK_FILE_REQUEST_4:
                        audioEsercizio.setText(link);
                        break;
                    case PICK_FILE_REQUEST_5:
                        audioEsercizioImmagine.setText(link);
                        break;
                    case PICK_FILE_REQUEST_6:
                        immagineEsercizioCorretta.setText(link);
                        break;
                    case PICK_FILE_REQUEST_7:
                        immagineEsercizioErrata.setText(link);
                        break;
                    case PICK_FILE_REQUEST_8:
                        audioRegistrato.setText(link);
                        break;
                    case PICK_FILE_REQUEST_9:
                        immagineTappa1.setText(link);
                        break;
                    case PICK_FILE_REQUEST_10:
                        immagineTappa2.setText(link);
                        break;
                    case PICK_FILE_REQUEST_11:
                        immagineTappa3.setText(link);
                        break;
                    default:
                        break;
                }
            });
        }
    }

	/*private ActivityResultLauncher<String> createFilePickerLauncher(int requestCode) {
		return registerForActivityResult(
				new ActivityResultContracts.GetContent(),
				uri -> {

					ComandiFirebaseStorage comandiFirebaseStorage = new ComandiFirebaseStorage();
					comandiFirebaseStorage.uploadFileAndGetLink(uri, "TEST/TERPIA_TEST").thenAccept(link -> {

						switch (requestCode) {
							case PICK_FILE_REQUEST_1:
								immagineSfondoScenario.setText(link);
								break;
							case PICK_FILE_REQUEST_2:
								immagineEsercizio.setText(link);
								break;
							case PICK_FILE_REQUEST_3:
								audioAiuto.setText(link);
								break;
							case PICK_FILE_REQUEST_4:
								audioEsercizio.setText(link);
								break;
							case PICK_FILE_REQUEST_5:
								audioEsercizioImmagine.setText(link);
								break;
							case PICK_FILE_REQUEST_6:
								immagineEsercizioCorretta.setText(link);
								break;
							case PICK_FILE_REQUEST_7:
								immagineEsercizioErrata.setText(link);
								break;
							case PICK_FILE_REQUEST_8:
								audioRegistrato.setText(link);
								break;
							default:
								Log.d("TestInserimentoDatiDBFragment", "Errore nel file picker");
								break;
						}
					});
				}
		);
	}*/

}
