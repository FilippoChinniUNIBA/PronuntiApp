package it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.speech.v1.SpeechSettings;
import com.google.cloud.speech.v1.WordInfo;
import com.google.protobuf.ByteString;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;

public class AudioRecognizer {
    private static final int SAMPLE_RATE = 16000;
    private static final int CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;
    private static final int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;

    private AudioRecord audioRecord;
    private boolean isRecording = false;
    private File audioFile;
    private Context context;

    public AudioRecognizer(File audioFile, Context context) {
        this.audioFile = audioFile;
        this.context=context;
    }
    public File getAudioFile() {
        return audioFile;
    }
    @SuppressLint("MissingPermission")
    public void startRecording(Context context) {

        int bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT, bufferSize);
        byte[] audioBuffer = new byte[bufferSize];

        audioRecord.startRecording();
        isRecording = true;

        new Thread(() -> {
            try {
                FileOutputStream outputStream = new FileOutputStream(audioFile);
                while (isRecording) {
                    int bytesRead = audioRecord.read(audioBuffer, 0, bufferSize);
                    outputStream.write(audioBuffer, 0, bytesRead);
                }
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void stopRecording() {
        if (audioRecord != null && isRecording) {
            isRecording = false;
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
        }
    }
    //L'AUDIO DEVE ESSERE AL MASSIMO DI 60 SECONDI ALTRIMENTI GOOGLE LO DEVE FARE IN MODO ASINCRONO
    public  List<String> getText() throws Exception {
        if(audioFile.exists()) {
            InputStream inputStream = context.getAssets().open("google-cloud-credentials.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);

            try (SpeechClient speech = SpeechClient.create(SpeechSettings.newBuilder().setCredentialsProvider(() -> credentials).build())) {
                byte[] data = Files.readAllBytes(audioFile.toPath());
                ByteString audioBytes = ByteString.copyFrom(data);

                //CAPIRE COME SETTARE LE IMPOSTAZIONI
                RecognitionConfig config = RecognitionConfig.newBuilder()
                        .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                        .setLanguageCode("it-IT")
                        .setSampleRateHertz(16000)
                        .build();

                RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();
                RecognizeResponse response = speech.recognize(config, audio);
                List<SpeechRecognitionResult> results = response.getResultsList();
                List<String> words = new ArrayList<>();
                for (SpeechRecognitionResult result : results) {
                    List<WordInfo> listofwords = result.getAlternativesList().get(0).getWordsList();
                    for(WordInfo word: listofwords){
                        words.add(word.getWord());
                    }
                }
                return words;
            }
        }else{
            throw new RuntimeException();
        }
    }
}
