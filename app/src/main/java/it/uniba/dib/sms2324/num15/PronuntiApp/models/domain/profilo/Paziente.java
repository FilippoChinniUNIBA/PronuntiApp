package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import android.util.Log;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBPaziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;

public class Paziente extends AbstractProfilo {
	private int eta;
	private LocalDate dataNascita;
	private char sesso;
	private int valuta;
	private int punteggioTot;
	private Map<String, Integer> personaggiSbloccati;
	private List<Terapia> terapie;
	private String refIdLogopedista;

	public Paziente(String idProfilo, String nome, String cognome, String username, String email, String password, int eta, LocalDate dataNascita, char sesso, int valuta, int punteggioTot, Map<String, Integer> personaggiSbloccati, List<Terapia> terapie, String refIdLogopedista) {
		super(idProfilo, nome, cognome, username, email, password);
		this.eta = eta;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
		this.valuta = valuta;
		this.punteggioTot = punteggioTot;
		this.personaggiSbloccati = personaggiSbloccati;
		this.terapie = terapie;
		this.refIdLogopedista = refIdLogopedista;
	}

	public Paziente(String nome, String cognome, String username, String email, String password, int eta, LocalDate dataNascita, char sesso, int valuta, int punteggioTot, Map<String, Integer> personaggiSbloccati, List<Terapia> terapie, String refIdLogopedista) {
		super(nome, cognome, username, email, password);
		this.eta = eta;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
		this.valuta = valuta;
		this.punteggioTot = punteggioTot;
		this.personaggiSbloccati = personaggiSbloccati;
		this.terapie = terapie;
		this.refIdLogopedista = refIdLogopedista;
	}

	public Paziente(String nome, String cognome, String username, String email, String password, int eta, LocalDate dataNascita, char sesso, int valuta, int punteggioTot, Map<String, Integer> personaggiSbloccati, List<Terapia> terapie) {
		super(nome, cognome, username, email, password);
		this.eta = eta;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
		this.valuta = valuta;
		this.punteggioTot = punteggioTot;
		this.personaggiSbloccati = personaggiSbloccati;
		this.terapie = terapie;
	}

	public Paziente(Map<String,Object> fromDatabaseMap, String fromDatabaseKey){
		Paziente p = this.fromMap(fromDatabaseMap);

		this.idProfilo = fromDatabaseKey;
		this.nome = p.getNome();
		this.cognome = p.getCognome();
		this.username = p.getUsername();
		this.email = p.getEmail();
		this.password = p.getPassword();
		this.eta = p.getEta();
		this.dataNascita = p.getDataNascita();
		this.sesso = p.getSesso();
		this.valuta = p.getValuta();
		this.punteggioTot = p.getPunteggioTot();
		this.personaggiSbloccati = p.getPersonaggiSbloccati();
		this.terapie = p.getTerapie();
		this.refIdLogopedista = p.getRefIdLogopedista();

	}

	public int getEta() {
		return eta;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public char getSesso() {
		return sesso;
	}

	public int getValuta() {
		return valuta;
	}

	public int getPunteggioTot() {
		return punteggioTot;
	}

	public Map<String, Integer> getPersonaggiSbloccati() {
		return personaggiSbloccati;
	}

	public List<Terapia> getTerapie() {
		return terapie;
	}

	public String getRefIdLogopedista() {
		return refIdLogopedista;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
	}

	public void setValuta(int valuta) {
		this.valuta = valuta;
	}

	public void setPunteggioTot(int punteggioTot) {
		this.punteggioTot = punteggioTot;
	}

	public void setPersonaggiSbloccati(Map<String, Integer> personaggiSbloccati) {
		this.personaggiSbloccati = personaggiSbloccati;
	}

	public void setTerapie(List<Terapia> terapie) {
		this.terapie = terapie;
	}

	public void setRefIdLogopedista(String refIdLogopedista) {
		this.refIdLogopedista = refIdLogopedista;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> entityMap = super.toMap();

		entityMap.put(CostantiDBPaziente.ETA, this.eta);
		entityMap.put(CostantiDBPaziente.DATA_NASCITA, this.dataNascita.toString());
		entityMap.put(CostantiDBPaziente.SESSO, Character.toString(this.sesso));
		entityMap.put(CostantiDBPaziente.VALUTA, this.valuta);
		entityMap.put(CostantiDBPaziente.PUNTEGGIO_TOT, this.punteggioTot);
		entityMap.put(CostantiDBPaziente.MAPPA_PERSONAGGI_SBLOCCATI, this.personaggiSbloccati);
		entityMap.put(CostantiDBPaziente.LISTA_TERAPIE, this.terapie.stream().map(Terapia::getIdTerapia).collect(Collectors.toList()));
		entityMap.put(CostantiDBPaziente.ID_LOGOPEDISTA, this.refIdLogopedista);
		return entityMap;
	}

	@Override
	public Paziente fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("Paziente.fromMap()", fromDatabaseMap.toString());
		return new Paziente(
				(String) fromDatabaseMap.get(CostantiDBPaziente.NOME),
				(String) fromDatabaseMap.get(CostantiDBPaziente.COGNOME),
				(String) fromDatabaseMap.get(CostantiDBPaziente.USERNAME),
				(String) fromDatabaseMap.get(CostantiDBPaziente.EMAIL),
				(String) fromDatabaseMap.get(CostantiDBPaziente.PASSWORD),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBPaziente.ETA)),
				LocalDate.parse((String) fromDatabaseMap.get(CostantiDBPaziente.DATA_NASCITA)),
				((String) fromDatabaseMap.get(CostantiDBPaziente.SESSO)).charAt(0),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBPaziente.VALUTA)),
				Math.toIntExact((long) fromDatabaseMap.get(CostantiDBPaziente.PUNTEGGIO_TOT)),
				//TODO: lanceranno eccezione, bisogna fare una funzione che faccia la get di ogni terapia e pesonaggio
				(Map<String, Integer>) fromDatabaseMap.get(CostantiDBPaziente.MAPPA_PERSONAGGI_SBLOCCATI),
				(List<Terapia>) fromDatabaseMap.get(CostantiDBPaziente.LISTA_TERAPIE),
				(String) fromDatabaseMap.get(CostantiDBPaziente.ID_LOGOPEDISTA)
		);
	}

	@Override
	public String toString() {
		return "Paziente{" +
				"idProfilo='" + idProfilo + '\'' +
				", nome='" + nome + '\'' +
				", cognome='" + cognome + '\'' +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", eta=" + eta +
				", dataNascita=" + dataNascita +
				", sesso=" + sesso +
				", valuta=" + valuta +
				", punteggioTot=" + punteggioTot +
				", personaggiSbloccati=" + personaggiSbloccati +
				", terapie=" + terapie +
				", refIdLogopedista='" + refIdLogopedista + '\'' +
				'}';
	}

}
