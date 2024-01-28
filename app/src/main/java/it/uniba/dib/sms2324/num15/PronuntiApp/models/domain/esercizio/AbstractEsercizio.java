package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

public abstract class AbstractEsercizio implements Esercizio {
	protected int ricompensaCorretto;
	protected int ricompensaErrato;

	public AbstractEsercizio(int ricompensaCorretto, int ricompensaErrato) {
		this.ricompensaCorretto = ricompensaCorretto;
		this.ricompensaErrato = ricompensaErrato;
	}

	public int getRicompensaCorretto() {
		return ricompensaCorretto;
	}

	public int getRicompensaErrato() {
		return ricompensaErrato;
	}

	public void setRicompensaCorretto(int ricompensaCorretto) {
		this.ricompensaCorretto = ricompensaCorretto;
	}

	public void setRicompensaErrato(int ricompensaErrato) {
		this.ricompensaErrato = ricompensaErrato;
	}
}
