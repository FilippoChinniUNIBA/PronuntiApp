package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
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
import java.util.Objects;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.AudioRecognizer;

public class TestApiFragment extends Fragment {
	private Button buttonAvviaRegistrazione;
	private Button buttonStopRegistrazione;
	private TextView textViewSpeechToTextView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.test_fragment_test_api, container, false);

		buttonAvviaRegistrazione = view.findViewById(R.id.buttonAvviaRegistrazione);
		buttonStopRegistrazione = view.findViewById(R.id.buttonStopRegistrazione);
		textViewSpeechToTextView = view.findViewById(R.id.textViewSpeechToText);

		Activity curretactivity = requireActivity();
		AudioRecognizer audioRecognizer = new AudioRecognizer(new File(curretactivity.getExternalFilesDir(Environment.DIRECTORY_MUSIC),"test.wav"),getContext());

		if (checkPermissions(curretactivity)) {
			setupButtons(audioRecognizer,curretactivity);
		} else {
			requestPermissions(curretactivity);
			setupButtons(audioRecognizer,curretactivity);
		}
		return view;
	}

	private void setupButtons(AudioRecognizer audioRecognizer, Activity currentactivity) {
		buttonAvviaRegistrazione.setOnClickListener(v -> {
			audioRecognizer.startRecording();
			Toast.makeText(currentactivity, "Registrazione avviata", Toast.LENGTH_SHORT).show();
        });

		buttonStopRegistrazione.setOnClickListener(v -> {
			audioRecognizer.stopRecording();
			Toast.makeText(currentactivity, "Registrazione interrotta", Toast.LENGTH_SHORT).show();
			try {
				List<String> words = audioRecognizer.getText();
				textViewSpeechToTextView.setText(words.toString());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

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
