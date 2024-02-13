package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi;

import java.util.Random;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioCoppiaImmagini;

public class EsercizioCoppiaImmaginiController {
	private EsercizioCoppiaImmagini mEsercizioCoppiaImmagini;

	public void setEsercizioCoppiaImmagini(EsercizioCoppiaImmagini mEsercizioCoppiaImmagini) {
		this.mEsercizioCoppiaImmagini = mEsercizioCoppiaImmagini;
	}


	public int generaPosizioneImmagineCorretta() {
		return new Random().nextInt(2) + 1;
	}

	public boolean verificaSceltaImmagine(int immagineScelta, int immagineCorretta) {
		return (immagineScelta == immagineCorretta);
	}

}
