package it.uniba.dib.sms2324.num15.PronuntiApp.models.utils.audio_player;

import android.media.MediaPlayer;

public abstract class AbstractAudioPlayer {
	protected MediaPlayer mediaPlayer;

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

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

}
