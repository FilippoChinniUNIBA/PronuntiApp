package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE.test_database.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE.test_database.PersonaggioDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.AudioConverter;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.AudioRecognizer;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.Test.AudioTest;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.CloudTask;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.cloud_actions.DownloadAction;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.cloud_actions.UploadAction;

public class TestApiFragment extends Fragment {
	private Button buttonAvviaRegistrazione;
	private Button buttonStopRegistrazione;
	private Button buttonUploadFile;
	private Button buttonUploadFileFirebase;
	private Button buttonDownloadFile;
	private Button buttonAvviaVocale;
	private Button buttonStoppaVocale;
	private TextView textViewSpeechToTextView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.test_fragment_test_api, container, false);

		buttonAvviaRegistrazione = view.findViewById(R.id.buttonAvviaRegistrazione);
		buttonStopRegistrazione = view.findViewById(R.id.buttonStopRegistrazione);
		buttonUploadFile = view.findViewById(R.id.buttonUploadFile);
		buttonDownloadFile = view.findViewById(R.id.buttonDownloadFile);
		buttonUploadFileFirebase = view.findViewById(R.id.buttonUpdloadinfirebase);
		buttonAvviaVocale = view.findViewById(R.id.buttonAvviaVocale);
		buttonStoppaVocale= view.findViewById(R.id.buttonStoppaVocale);
		textViewSpeechToTextView = view.findViewById(R.id.textViewSpeechToText);


		Activity curretactivity = requireActivity();

		File directoryMusic = curretactivity.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
		File directoryDownloads = curretactivity.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);

		File fileRegistrazione = new File(directoryMusic,"test");
		File fileConvertito = new File(directoryMusic,"test.mp3");
		File downloadFileConvertito = new File(directoryDownloads,"test.mp3");

		String nomeFileUploadDownload = "test.mp3";

		UploadAction uploadAction = new UploadAction();
		DownloadAction downloadAction = new DownloadAction();
		MediaPlayer mediaPlayer = new MediaPlayer();

		List<Personaggio> personaggioList = new ArrayList<Personaggio>();

		Personaggio pers1 = new Personaggio("1","nome1",5,R.drawable.personaggio_bambina_rossa);
		Personaggio pers2 = new Personaggio("2","nome2",5,R.drawable.personaggio_bambina_disegna);
		Personaggio pers3 = new Personaggio("3","nome3",5,R.drawable.personaggio_bambino_no_zaino);
		Personaggio pers4 = new Personaggio("4","nome4",5,R.drawable.personaggio_batman);
		Personaggio pers5 = new Personaggio("5","nome5",5,R.drawable.personaggio_pecora);
		Personaggio pers6 = new Personaggio("6","nome6",5,R.drawable.personaggio_mucca);

		PersonaggioDAO dao = new PersonaggioDAO ();
		dao.save(pers1);
		dao.save(pers2);
		dao.save(pers3);
		dao.save(pers4);
		dao.save(pers5);
		dao.save(pers6);

		AudioRecognizer audioRecognizer = new AudioRecognizer(fileRegistrazione,curretactivity);
		CloudTask cloudTaskUpload = new CloudTask(fileConvertito,curretactivity,nomeFileUploadDownload,uploadAction);
		CloudTask cloudTaskDownload = new CloudTask(downloadFileConvertito,curretactivity,nomeFileUploadDownload, downloadAction);
		AudioTest audioTest = new AudioTest(mediaPlayer,fileConvertito,curretactivity);

		if (checkPermissions(curretactivity)) {
			setupButtons(audioRecognizer,curretactivity,cloudTaskUpload,cloudTaskDownload,audioTest,dao);
		} else {
			requestPermissions(curretactivity);
			setupButtons(audioRecognizer,curretactivity,cloudTaskUpload,cloudTaskDownload,audioTest,dao);
		}
		return view;
	}

	private void setupButtons(AudioRecognizer audioRecognizer, Activity currentactivity, CloudTask cloudTaskUpload ,CloudTask cloudTaskDownload,AudioTest audioTest,PersonaggioDAO dao) {

		buttonAvviaRegistrazione.setOnClickListener(v -> {
			startRecording(audioRecognizer,currentactivity);
        });

		buttonStopRegistrazione.setOnClickListener(v -> {
			stopRecording(audioRecognizer,currentactivity);
		});

		buttonUploadFile.setOnClickListener(v -> {
			uploadFile(cloudTaskUpload,currentactivity);
		});

		buttonDownloadFile.setOnClickListener(v -> {
			downloadFile(dao);
		});

		buttonUploadFileFirebase.setOnClickListener(v -> {
			uploadInFirebase(audioRecognizer);
		});

		buttonAvviaVocale.setOnClickListener(v -> {
			startVocal(audioTest);
		});

		buttonStoppaVocale.setOnClickListener(v -> {
			stopVocal(audioTest);
		});

	}


	private void startRecording(AudioRecognizer audioRecognizer, Activity currentactivity){
		try{
		audioRecognizer.startRecording();
			textViewSpeechToTextView.setText("Registrazione in corso . . .");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void stopRecording(AudioRecognizer audioRecognizer, Activity currentactivity){
		try {
			textViewSpeechToTextView.setText("Salvataggio in corso . . .");
			audioRecognizer.stopRecording();
			List<String> words = audioRecognizer.getText();
			textViewSpeechToTextView.setText("parole dette: " + words.toString());
			AudioConverter.convertFile(audioRecognizer.getAudioFile(),new File(currentactivity.getExternalFilesDir(Environment.DIRECTORY_MUSIC),"test.mp3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void uploadFile(CloudTask uploadTask,Activity currentactivity){
		try {
			//do nothing
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private void downloadFile(PersonaggioDAO dao){
		try {
			printcharacters(dao);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private void uploadInFirebase(AudioRecognizer audioRecognizer){
		FirebaseStorage storage = FirebaseStorage.getInstance();
		StorageReference mountainImagesRef = storage.getReference("test");
		InputStream stream = null;
		UploadTask uploadTask =null;
		try {
			stream = new FileInputStream(audioRecognizer.getAudioFile());
			uploadTask = mountainImagesRef.putStream(stream);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		uploadTask.addOnCompleteListener(task -> {
			if (task.isSuccessful()) {
				Log.d("Success","Upload done");
			} else {
				Exception exception = task.getException();
				Log.d("Fail", Objects.requireNonNull(exception.getMessage()));
			}
		});
	}

	private void startVocal(AudioTest audioTest){
		try {
			audioTest.playAudio();
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	private void stopVocal(AudioTest audioTest){
		try {
			audioTest.stopAudio();
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	private void printcharacters(PersonaggioDAO dao){
		CompletableFuture<List<Personaggio>> futurePersonaggi = dao.getPersonaggi();

		futurePersonaggi.thenAccept(personaggi -> {
			// Elabora la lista di personaggi ottenuta
			for (Personaggio personaggio : personaggi) {
				System.out.println(personaggio); // Esempio: Stampa i dettagli del personaggio
			}
		}).exceptionally(exception -> {
			// Gestisci eventuali eccezioni
			System.err.println("Errore durante il recupero dei personaggi: " + exception.getMessage());
			return null;
		});
	}


	private boolean checkPermissions(Activity currentactivity) {
		int readStoragePermission = ContextCompat.checkSelfPermission(currentactivity, Manifest.permission.READ_EXTERNAL_STORAGE);
		int writeStoragePermission = ContextCompat.checkSelfPermission(currentactivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		int recordAudioPermission = ContextCompat.checkSelfPermission(currentactivity, Manifest.permission.RECORD_AUDIO);

		return readStoragePermission == PackageManager.PERMISSION_GRANTED &&
				writeStoragePermission == PackageManager.PERMISSION_GRANTED &&
				recordAudioPermission == PackageManager.PERMISSION_GRANTED;
	}

	private void requestPermissions(Activity currentactivity) {
		ActivityCompat.requestPermissions(currentactivity, new String[]{
				Manifest.permission.READ_EXTERNAL_STORAGE,
				Manifest.permission.WRITE_EXTERNAL_STORAGE,
				Manifest.permission.RECORD_AUDIO
		}, 1000);
	}

}
