package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi;

import android.content.Context;

import java.io.File;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.external_api.ffmpegkit.AudioConverter;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.external_api.google_cloud_speech_to_text_api.SpeechToTextAPI;

public class EsercizioDenominazioneImmagineController {
	private EsercizioDenominazioneImmagine mEsercizioDenominazioneImmagine;

	public EsercizioDenominazioneImmagine getEsercizioDenominazioneImmagine() {
		return mEsercizioDenominazioneImmagine;
	}

	public void setEsercizioDenominazioneImmagine(EsercizioDenominazioneImmagine esercizioDenominazioneImmagine) {
		this.mEsercizioDenominazioneImmagine = esercizioDenominazioneImmagine;
	}


	public boolean verificaAudio(File audioRegistrato, Context context) {
		List<String> words = SpeechToTextAPI.callAPI(context, audioRegistrato);

		if (words == null || words.isEmpty()) {
			return false;
		}
		else {
			boolean check = true;
			for (String word : words) {
				if (!(word.toLowerCase().equals(mEsercizioDenominazioneImmagine.getParolaEsercizio().toLowerCase()))) {
					check = false;
				}

			}
			return check;
		}
	}

	public void convertiAudio(File audioRegistrato) {
		AudioConverter.convertiAudio(audioRegistrato);
	}

}
