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

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.external_api.ffmpegkit.AudioConverter;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.external_api.google_cloud_speech_to_text_api.SpeechToTextAPI;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_player.AbstractAudioPlayer;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.personaggio.PersonaggioDAO;

public class TestApiFragment extends Fragment {
	private Button buttonAvviaRegistrazione;
	private Button buttonStopRegistrazione;
	private Button buttonUploadFile;
	private Button buttonUploadFileFirebase;
	private Button buttonDownloadFile;
	private Button buttonAvviaVocale;
	private Button buttonStoppaVocale;
	private TextView textViewSpeechToTextView;

	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.test_fragment_test_api, container, false);

		//buttonAvviaRegistrazione = view.findViewById(R.id.buttonAvviaRegistrazione);
		buttonStopRegistrazione = view.findViewById(R.id.buttonStopRegistrazione);
		//buttonUploadFile = view.findViewById(R.id.buttonUploadFile);
		//buttonDownloadFile = view.findViewById(R.id.buttonDownloadFile);
		//buttonUploadFileFirebase = view.findViewById(R.id.buttonUpdloadinfirebase);
		buttonAvviaVocale = view.findViewById(R.id.buttonAvviaVocale);
		buttonStoppaVocale= view.findViewById(R.id.buttonStoppaVocale);
		//textViewSpeechToTextView = view.findViewById(R.id.textViewSpeechToText);


		Activity curretactivity = requireActivity();

		File directoryMusic = curretactivity.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
		File directoryDownloads = curretactivity.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);

		File fileRegistrazione = new File(directoryMusic,"test");
		File fileConvertito = new File(directoryMusic,"test.mp3");
		File downloadFileConvertito = new File(directoryDownloads,"test.mp3");

		String nomeFileUploadDownload = "test.mp3";

		MediaPlayer mediaPlayer = new MediaPlayer();

		List<Personaggio> personaggioList = new ArrayList<Personaggio>();

		Personaggio pers1 = new Personaggio("1","nome1",5,*//*R.drawable.bambina*//* new File("path"));
		Personaggio pers2 = new Personaggio("2","nome2",5,*//*R.drawable.bambina_disegna*//* new File("path"));
		Personaggio pers3 = new Personaggio("3","nome3",5,*//*R.drawable.bambino*//* new File("path"));
		Personaggio pers4 = new Personaggio("4","nome4",5,*//*R.drawable.batman*//* new File("path"));
		Personaggio pers5 = new Personaggio("5","nome5",5,*//*R.drawable.pecora*//* new File("path"));
		Personaggio pers6 = new Personaggio("6","nome6",5,*//*R.drawable.mucca*//* new File("path"));

		PersonaggioDAO dao = new PersonaggioDAO ();
		dao.save(pers1);
		dao.save(pers2);
		dao.save(pers3);
		dao.save(pers4);
		dao.save(pers5);
		dao.save(pers6);

		SpeechToTextAPI speechToTextAPI = new SpeechToTextAPI(fileRegistrazione,curretactivity);
		AbstractAudioPlayer abstractAudioPlayer = new AbstractAudioPlayer(mediaPlayer,fileConvertito,curretactivity);

		if (checkPermissions(curretactivity)) {
			setupButtons(speechToTextAPI,curretactivity, abstractAudioPlayer,dao);
		} else {
			requestPermissions(curretactivity);
			setupButtons(speechToTextAPI,curretactivity, abstractAudioPlayer,dao);
		}
		return view;
	}

	private void setupButtons(SpeechToTextAPI speechToTextAPI, Activity currentactivity, AbstractAudioPlayer abstractAudioPlayer, PersonaggioDAO dao) {

		buttonAvviaRegistrazione.setOnClickListener(v -> {
			startRecording(speechToTextAPI,currentactivity);
        });

		buttonStopRegistrazione.setOnClickListener(v -> {
			stopRecording(speechToTextAPI,currentactivity);
		});

		buttonUploadFileFirebase.setOnClickListener(v -> {
			uploadInFirebase(speechToTextAPI);
		});

		buttonAvviaVocale.setOnClickListener(v -> {
			startVocal(abstractAudioPlayer);
		});

		buttonStoppaVocale.setOnClickListener(v -> {
			stopVocal(abstractAudioPlayer);
		});

	}


	private void startRecording(SpeechToTextAPI speechToTextAPI, Activity currentactivity){
		try{
		speechToTextAPI.startRecording();
			textViewSpeechToTextView.setText("Registrazione in corso . . .");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void stopRecording(SpeechToTextAPI speechToTextAPI, Activity currentactivity){
		try {
			textViewSpeechToTextView.setText("Salvataggio in corso . . .");
			speechToTextAPI.stopRecording();
			List<String> words = speechToTextAPI.callAPI();
			textViewSpeechToTextView.setText("parole dette: " + words.toString());
			AudioConverter.convertiAudio(speechToTextAPI.getAudioFile(),new File(currentactivity.getExternalFilesDir(Environment.DIRECTORY_MUSIC),"test.mp3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void uploadInFirebase(SpeechToTextAPI speechToTextAPI){
		FirebaseStorage storage = FirebaseStorage.getInstance();
		StorageReference mountainImagesRef = storage.getReference("test");
		InputStream stream = null;
		UploadTask uploadTask =null;
		try {
			stream = new FileInputStream(speechToTextAPI.getAudioFile());
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

	private void startVocal(AbstractAudioPlayer abstractAudioPlayer){
		try {
			abstractAudioPlayer.playAudio();
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	private void stopVocal(AbstractAudioPlayer abstractAudioPlayer){
		try {
			abstractAudioPlayer.stopAudio();
		}catch (Exception e){
			throw new RuntimeException(e);
		}
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
	}*/

}
