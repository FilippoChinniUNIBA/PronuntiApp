package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.giochi;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioCoppiaImmagini;

public class EsercizioCoppiaImmaginiController {
	private EsercizioCoppiaImmagini mEsercizioCoppiaImmagini;

	public void setEsercizioCoppiaImmagini(EsercizioCoppiaImmagini mEsercizioCoppiaImmagini) {
		this.mEsercizioCoppiaImmagini = mEsercizioCoppiaImmagini;
	}


	public boolean verificaSceltaImmagine(int immagineScelta, int immagineCorretta) {
		return (immagineScelta == immagineCorretta);
	}

}
