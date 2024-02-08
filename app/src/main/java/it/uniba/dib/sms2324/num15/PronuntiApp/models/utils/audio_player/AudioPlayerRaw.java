package it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_player;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayerRaw extends AbstractAudioPlayer {
	private int rawAudio;

	public AudioPlayerRaw(Context context, int rawAudio) {
		this.mediaPlayer = MediaPlayer.create(context, rawAudio);
		this.rawAudio = rawAudio;
	}

	public int getRawAudio() {
		return rawAudio;
	}

	public void playAudio() {
		mediaPlayer.start();
	}

}
