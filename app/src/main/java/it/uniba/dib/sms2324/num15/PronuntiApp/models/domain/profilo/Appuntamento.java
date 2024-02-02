package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class Appuntamento implements Persistente<Appuntamento> {
	private String idAppuntamento;
	private String refIdGenitore;
	private String refIdLogopedista;
	private LocalDate data;
	private LocalTime ora;
	private String luogo;

	public Appuntamento(String idAppuntamento, String refIdGenitore, String refIdLogopedista, LocalDate data, LocalTime ora, String luogo) {
		this.idAppuntamento = idAppuntamento;
		this.refIdGenitore = refIdGenitore;
		this.refIdLogopedista = refIdLogopedista;
		this.data = data;
		this.ora = ora;
		this.luogo = luogo;
	}

	public Appuntamento(String refIdGenitore, String refIdLogopedista, LocalDate data, LocalTime ora, String luogo) {
		this.refIdGenitore = refIdGenitore;
		this.refIdLogopedista = refIdLogopedista;
		this.data = data;
		this.ora = ora;
		this.luogo = luogo;
	}

	public Appuntamento(Map<String,Object> fromDatabaseMap){
		Appuntamento A = fromMap(fromDatabaseMap);
		this.idAppuntamento = A.idAppuntamento;
		this.refIdGenitore = A.refIdGenitore;
		this.refIdLogopedista = A.refIdLogopedista;
		this.data = A.data;
		this.time = A.time;
		this.luogo = A.luogo;
	}

	public String getIdAppuntamento() {
		return idAppuntamento;
	}

	public String getRefIdGenitore() {
		return refIdGenitore;
	}

	public String getRefIdLogopedista() {
		return refIdLogopedista;
	}

	public LocalDate getData() {
		return data;
	}

	public LocalTime getOra() {
		return ora;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setIdAppuntamento(String idAppuntamento) {
		this.idAppuntamento = idAppuntamento;
	}

	public void setRefIdGenitore(String refIdGenitore) {
		this.refIdGenitore = refIdGenitore;
	}

	public void setRefIdLogopedista(String refIdLogopedista) {
		this.refIdLogopedista = refIdLogopedista;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setOra(LocalTime ora) {
		this.ora = ora;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	@Override
	public Map<String, Object> toMap() {
		return null;
	}

	@Override
	public Appuntamento fromMap(Map<String, Object> fromDatabaseMap) {
		return null;
	}

	@Override
	public String toString() {
		return "Appuntamento{" +
				"idAppuntamento='" + idAppuntamento + '\'' +
				", refIdGenitore=" + refIdGenitore +
				", refIdLogopedista=" + refIdLogopedista +
				", data=" + data +
				", ora=" + ora +
				", luogo='" + luogo + '\'' +
				'}';
	}

}
