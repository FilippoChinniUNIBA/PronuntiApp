package it.uniba.dib.sms2324.num15.PronuntiApp.models.restapi.cloudspeechtotextapi.Test;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.File;
import java.io.IOException;

public class AudioTest {

    private MediaPlayer mediaPlayer;
    private File audioFile;
    private Context context;

    public AudioTest(MediaPlayer mediaPlayer,File audioFile,Context context){
        this.audioFile=audioFile;
        this.context=context;
        this.mediaPlayer=mediaPlayer;
    }

    public void playAudio() {
        if (mediaPlayer == null) {
            throw new RuntimeException();
        } else {
            mediaPlayer.reset();
        }

        try {
            // Imposta la sorgente audio da file
            mediaPlayer.setDataSource(context, Uri.fromFile(audioFile));

            // Prepara il MediaPlayer
            mediaPlayer.prepare();

            // Avvia la riproduzione
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
