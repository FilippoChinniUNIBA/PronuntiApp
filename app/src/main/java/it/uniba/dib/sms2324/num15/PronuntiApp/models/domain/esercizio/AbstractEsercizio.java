package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBEsercizioAbstract;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioAbstract;

public abstract class AbstractEsercizio implements Esercizio {
	protected String idEsercizio;
	protected int ricompensaCorretto;
	protected int ricompensaErrato;

	public AbstractEsercizio(String idEsercizio, int ricompensaCorretto, int ricompensaErrato) {
		this.idEsercizio = idEsercizio;
		this.ricompensaCorretto = ricompensaCorretto;
		this.ricompensaErrato = ricompensaErrato;
	}

	public AbstractEsercizio(int ricompensaCorretto, int ricompensaErrato) {
		this.ricompensaCorretto = ricompensaCorretto;
		this.ricompensaErrato = ricompensaErrato;
	}

	public AbstractEsercizio(Map<String,Object> fromDatabaseMap){}

	public String getIdEsercizio() {
		return idEsercizio;
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

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = new HashMap<>();
		entityMap.put(CostantiDBEsercizioAbstract.RICOMPENSA_CORRETTO, this.ricompensaCorretto);
		entityMap.put(CostantiDBEsercizioAbstract.RICOMPENSA_ERRATO, this.ricompensaErrato);
		return entityMap;
	}

	@Override
	public Esercizio fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
	}

}
