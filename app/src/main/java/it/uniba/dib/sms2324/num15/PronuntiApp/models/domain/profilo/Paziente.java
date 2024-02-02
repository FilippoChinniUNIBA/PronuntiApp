package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;

public class Paziente extends AbstractProfilo {
	private int eta;
	private LocalDate dataNascita;
	private char sesso;
	private int valuta = 0;
	private int punteggioTot = 0;
	private Map<String, Boolean> personaggiSbloccati;
	private List<Terapia> terapie;
	private String refIdLogopedista;

	public Paziente(String idProfilo, String nome, String cognome, String username, String email, String password, int eta, LocalDate dataNascita, char sesso, int valuta, int punteggioTot, Map<String, Boolean> personaggiSbloccati, List<Terapia> terapie, String refIdLogopedista) {
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

	public Paziente(String nome, String cognome, String username, String email, String password, int eta, LocalDate dataNascita, char sesso, int valuta, int punteggioTot, Map<String, Boolean> personaggiSbloccati, List<Terapia> terapie, String refIdLogopedista) {
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

	public Paziente(String nome, String cognome, String username, String email, String password, int eta, LocalDate dataNascita, char sesso, int valuta, int punteggioTot, Map<String, Boolean> personaggiSbloccati, List<Terapia> terapie) {
		super(nome, cognome, username, email, password);
		this.eta = eta;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
		this.valuta = valuta;
		this.punteggioTot = punteggioTot;
		this.personaggiSbloccati = personaggiSbloccati;
		this.terapie = terapie;
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

	public Map<String, Boolean> getPersonaggiSbloccati() {
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

	public void setPersonaggiSbloccati(Map<String, Boolean> personaggiSbloccati) {
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
		return null;
	}

	@Override
	public Paziente fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
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
