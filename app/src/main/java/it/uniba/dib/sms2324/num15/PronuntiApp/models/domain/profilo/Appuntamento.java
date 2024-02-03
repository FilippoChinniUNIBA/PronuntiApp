package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo;

import android.util.Log;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBAppuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBPersonaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;

public class Appuntamento implements Persistente<Appuntamento> {
	private String idAppuntamento;
	private LocalDate data;
	private LocalTime ora;
	private String luogo;

	public Appuntamento(String idAppuntamento, LocalDate data, LocalTime ora, String luogo) {
		this.idAppuntamento = idAppuntamento;
		this.data = data;
		this.ora = ora;
		this.luogo = luogo;
	}

	public Appuntamento(LocalDate data, LocalTime ora, String luogo) {
		this.data = data;
		this.ora = ora;
		this.luogo = luogo;
	}

	public Appuntamento(Map<String, Object> fromDatabaseMap, String fromDatabaseKey) {
		Appuntamento a = this.fromMap(fromDatabaseMap);

		this.idAppuntamento = fromDatabaseKey;
		this.data = a.getData();
		this.ora = a.getOra();
		this.luogo = a.getLuogo();
	}

	public String getIdAppuntamento() {
		return idAppuntamento;
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
		Map<String, Object> entityMap = new HashMap<>();

		//entityMap.put(CostantiDBAppuntamento.ID_APPUNTAMENTO, this.idAppuntamento);
		entityMap.put(CostantiDBAppuntamento.DATA, this.data.toString());
		entityMap.put(CostantiDBAppuntamento.ORA, this.ora.toString());
		entityMap.put(CostantiDBAppuntamento.LUOGO, this.luogo);
		return entityMap;
	}

	@Override
	public Appuntamento fromMap(Map<String, Object> fromDatabaseMap) {
		Log.d("Appuntamento.fromMap()", fromDatabaseMap.toString());
		return new Appuntamento(
				LocalDate.parse((String) fromDatabaseMap.get(CostantiDBAppuntamento.DATA)),
				LocalTime.parse((String) fromDatabaseMap.get(CostantiDBAppuntamento.ORA)),
				(String) fromDatabaseMap.get(CostantiDBAppuntamento.LUOGO)
		);
	}

	@Override
	public String toString() {
		return "Appuntamento{" +
				"idAppuntamento='" + idAppuntamento + '\'' +
				", data=" + data +
				", ora=" + ora +
				", luogo='" + luogo + '\'' +
				'}';
	}

/*	public static List<String> fromAppuntamentoListToAppuntamentoIdList(List<Appuntamento> appuntamenti) {
		List<String> listaIdAppuntamenti = new ArrayList<>();

		for (Appuntamento a : appuntamenti) {
			listaIdAppuntamenti.add(a.getIdAppuntamento());
		}

		return listaIdAppuntamenti;
	}*/

}
