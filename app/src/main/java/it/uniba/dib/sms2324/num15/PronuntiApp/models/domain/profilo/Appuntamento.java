package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class Appuntamento implements Persistente<Appuntamento> {
	private String idAppuntamento;
	private int refIdGenitore;
	private int refIdLogopedista;
	private LocalDate data;
	private LocalTime time;
	private String luogo;

	public Appuntamento(String idAppuntamento, int refIdGenitore, int refIdLogopedista, LocalDate data, LocalTime time, String luogo) {
		this.idAppuntamento = idAppuntamento;
		this.refIdGenitore = refIdGenitore;
		this.refIdLogopedista = refIdLogopedista;
		this.data = data;
		this.time = time;
		this.luogo = luogo;
	}

	public Appuntamento(int refIdGenitore, int refIdLogopedista, LocalDate data, LocalTime time, String luogo) {
		this.refIdGenitore = refIdGenitore;
		this.refIdLogopedista = refIdLogopedista;
		this.data = data;
		this.time = time;
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

	public int getRefIdGenitore() {
		return refIdGenitore;
	}

	public int getRefIdLogopedista() {
		return refIdLogopedista;
	}

	public LocalDate getData() {
		return data;
	}

	public LocalTime getTime() {
		return time;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setIdAppuntamento(String idAppuntamento) {
		this.idAppuntamento = idAppuntamento;
	}

	public void setRefIdGenitore(int refIdGenitore) {
		this.refIdGenitore = refIdGenitore;
	}

	public void setRefIdLogopedista(int refIdLogopedista) {
		this.refIdLogopedista = refIdLogopedista;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setTime(LocalTime time) {
		this.time = time;
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
				", time=" + time +
				", luogo='" + luogo + '\'' +
				'}';
	}

}
