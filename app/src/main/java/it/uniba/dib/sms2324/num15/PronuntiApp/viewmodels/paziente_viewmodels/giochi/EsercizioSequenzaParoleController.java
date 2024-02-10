package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi;

import android.content.Context;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.external_api.ffmpegkit_api.AudioConverter;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.external_api.google_cloud_speech_to_text_api.SpeechToTextAPI;

public class EsercizioSequenzaParoleController {
	private EsercizioSequenzaParole mEsercizioSequenzaParole;

	public void setEsercizioSequenzaParole(EsercizioSequenzaParole mEsercizioSequenzaParole) {
		this.mEsercizioSequenzaParole = mEsercizioSequenzaParole;
	}


	public boolean verificaAudio(File audioRegistrato, Context context) {
		List<String> paroleCorrette = Arrays.asList(
				mEsercizioSequenzaParole.getParola1().toLowerCase(),
				mEsercizioSequenzaParole.getParola2().toLowerCase(),
				mEsercizioSequenzaParole.getParola3().toLowerCase());

		List<String> paroleRegistrate = SpeechToTextAPI.callAPI(context, audioRegistrato);

		if (paroleRegistrate == null || paroleCorrette.size() != paroleRegistrate.size()) {
			return false;
		}

		for (int i = 0; i < paroleCorrette.size(); i++) {
			if (!paroleCorrette.get(i).equals(paroleRegistrate.get(i).toLowerCase())) {
				return false;
			}
		}

		return true;
	}

	public File convertiAudio(File audioRegistrato, File outputFile) {
		return AudioConverter.convertiAudio(audioRegistrato, outputFile);
	}

}
