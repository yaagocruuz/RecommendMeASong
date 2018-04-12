package br.ufc.quixada.recommendmeasong.pojo;

import java.util.ArrayList;
import java.util.List;

public class Band {
	private int id_band;
	private String name_band;
	private List<Artist> members;
	
	public Band(int id_band, String name_band){
		this.id_band = id_band;
		this.name_band = name_band;
		members = new ArrayList<Artist>();
	}

	public int getId_band() {
		return id_band;
	}

	public void setId_band(int id_band) {
		this.id_band = id_band;
	}

	public String getName_band() {
		return name_band;
	}

	public void setName_band(String name_band) {
		this.name_band = name_band;
	}

	public List<Artist> getIntegrantes() {
		return members;
	}

	public void setIntegrantes(List<Artist> integrantes) {
		this.members = integrantes;
	}

	@Override
	public String toString() {
		return "Band [id_band=" + id_band + ", name_band=" + name_band
				+ ", members= {" + members + "}]";
	}
}
