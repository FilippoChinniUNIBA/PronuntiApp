package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import java.io.File;
import java.util.List;
import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.AudioRecognizer;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.CloudTask;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.cloud_actions.DownloadAction;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.cloud_actions.UploadAction;

public class TestApiFragment extends Fragment {
	private Button buttonAvviaRegistrazione;
	private Button buttonStopRegistrazione;
	private Button buttonUploadFile;

	private Button buttonDownloadFile;
	private TextView textViewSpeechToTextView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.test_fragment_test_api, container, false);

		buttonAvviaRegistrazione = view.findViewById(R.id.buttonAvviaRegistrazione);
		buttonStopRegistrazione = view.findViewById(R.id.buttonStopRegistrazione);
		buttonUploadFile = view.findViewById(R.id.buttonUploadFile);
		buttonDownloadFile = view.findViewById(R.id.buttonDownloadFile);
		textViewSpeechToTextView = view.findViewById(R.id.textViewSpeechToText);


		Activity curretactivity = requireActivity();
		AudioRecognizer audioRecognizer = new AudioRecognizer(new File(curretactivity.getExternalFilesDir(Environment.DIRECTORY_MUSIC),"test.wav"),getContext());
		CloudTask cloudTaskUpload = new CloudTask(audioRecognizer.getAudioFile(),curretactivity,"test.wav",new UploadAction());
		CloudTask cloudTaskDownload = new CloudTask(new File(curretactivity.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"test.wav"),curretactivity,"test.wav", new DownloadAction());

		if (checkPermissions(curretactivity)) {
			setupButtons(audioRecognizer,curretactivity,cloudTaskUpload,cloudTaskDownload);
		} else {
			requestPermissions(curretactivity);
			setupButtons(audioRecognizer,curretactivity,cloudTaskUpload,cloudTaskDownload);
		}
		return view;
	}

	private void setupButtons(AudioRecognizer audioRecognizer, Activity currentactivity, CloudTask cloudTaskUpload ,CloudTask cloudTaskDownload) {

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
			downloadFile(cloudTaskDownload, currentactivity);
		});

	}


	private void startRecording(AudioRecognizer audioRecognizer, Activity currentactivity){
		try{
		audioRecognizer.startRecording();
		Toast.makeText(currentactivity, "Registrazione avviata", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void stopRecording(AudioRecognizer audioRecognizer, Activity currentactivity){
		try {
			audioRecognizer.stopRecording();
			Toast.makeText(currentactivity, "Registrazione interrotta", Toast.LENGTH_SHORT).show();
			List<String> words = audioRecognizer.getText();
			textViewSpeechToTextView.setText(words.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void uploadFile(CloudTask uploadTask,Activity currentactivity){
		try {
			uploadTask.execute();
			Toast.makeText(currentactivity, "Upload riuscito", Toast.LENGTH_SHORT).show();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private void downloadFile(CloudTask downloadTask,Activity currentactivity){
		try {
			downloadTask.execute();
			Toast.makeText(currentactivity, "Download riuscito", Toast.LENGTH_SHORT).show();
		}catch (Exception e){
			e.printStackTrace();
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
	}
}
