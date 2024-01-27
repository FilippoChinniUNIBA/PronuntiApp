package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Appuntamento {
	private int idAppuntamento;
	private int refIdGenitore;
	private int refIdLogopedista;
	private LocalDate data;
	private LocalTime time;
	private String luogo;

	public Appuntamento(int idAppuntamento, int refIdGenitore, int refIdLogopedista, LocalDate data, LocalTime time, String luogo) {
		this.idAppuntamento = idAppuntamento;
		this.refIdGenitore = refIdGenitore;
		this.refIdLogopedista = refIdLogopedista;
		this.data = data;
		this.time = time;
		this.luogo = luogo;
	}

	public int getIdAppuntamento() {
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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Appuntamento that = (Appuntamento) o;
		return idAppuntamento == that.idAppuntamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAppuntamento);
	}
}
