package it.uniba.dib.sms2324.num15.PronuntiApp.models.external_api.google_cloud_speech_to_text_api;

import android.content.Context;
import android.util.Log;

import com.google.auth.oauth2.GoogleCredentials;

import java.io.IOException;
import java.io.InputStream;

public final class GoogleCredentialsProvider {
	private static final String GOOGLE_CREDENTIALS_FILE_NAME = "google-cloud-credentials.json";
	private static GoogleCredentials credentials = null;
	private static GoogleCredentialsProvider instance = null;

	private GoogleCredentialsProvider(Context context) {
		try {
			InputStream inputStream = context.getAssets().open(GOOGLE_CREDENTIALS_FILE_NAME);
			if (inputStream == null) {
				Log.e("GoogleCredentialsProvider", "Credentials file not found in assets: " + GOOGLE_CREDENTIALS_FILE_NAME);
				return;
			}
			credentials = GoogleCredentials.fromStream(inputStream);
		} catch (IOException e) {
			Log.e("GoogleCredentialsProvider", "Error reading credentials from file", e);
		}
	}

	public static GoogleCredentialsProvider getInstance(Context context) {
		if (instance == null) {
			instance = new GoogleCredentialsProvider(context);
		}
		return instance;
	}

	public GoogleCredentials getCredentials() {
		return credentials;
	}

}