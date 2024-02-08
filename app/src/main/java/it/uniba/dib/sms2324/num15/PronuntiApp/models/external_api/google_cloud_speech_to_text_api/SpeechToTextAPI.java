package it.uniba.dib.sms2324.num15.PronuntiApp.models.external_api.google_cloud_speech_to_text_api;

import android.content.Context;
import android.util.Log;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.speech.v1.SpeechSettings;
import com.google.protobuf.ByteString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpeechToTextAPI {
    private static final int SAMPLE_RATE = 16000;
    private static final String LANGUAGE_CODE_ITA = "it-IT";
    private static final String LANGUAGE_CODE_ENG = "en-UK";
    private static final RecognitionConfig CONFIG = RecognitionConfig.newBuilder()
            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
            .setLanguageCode(LANGUAGE_CODE_ITA)
            .setSampleRateHertz(SAMPLE_RATE)
            .build();

    public static List<String> callAPI(Context context, File audioSpeech) {

        GoogleCredentials credentials = GoogleCredentialsProvider.getInstance(context).getCredentials();

        try {
            SpeechClient speech = SpeechClient.create(SpeechSettings.newBuilder().setCredentialsProvider(() -> credentials).build());

            byte[] data = Files.readAllBytes(audioSpeech.toPath());
            ByteString audioBytes = ByteString.copyFrom(data);

            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();
            RecognizeResponse response = speech.recognize(CONFIG, audio);

            List<SpeechRecognitionResult> results = response.getResultsList();
            List<String> words = new ArrayList<>();

            for (SpeechRecognitionResult result : results) {
                String word = result.getAlternativesList().get(0).getTranscript();
                words.addAll(Arrays.asList(word.split("\\s+")));
            }
            return words;

        } catch (IOException e) {
            Log.e("SpeechToTextAPI.callAPI()", "Errore lettura audio file", e);
        }

        return null;
    }

}
