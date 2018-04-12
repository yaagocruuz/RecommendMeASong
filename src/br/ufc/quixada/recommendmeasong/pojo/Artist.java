package br.ufc.quixada.recommendmeasong.pojo;

public class Artist {
	private int id_artist;
	private String name_artist;
	private Band band;
	
	public Artist(int id_artist, String name_artist, Band band){
		this.id_artist = id_artist;
		this.name_artist = name_artist;
		this.band = band;
	}

	public int getId_artist() {
		return id_artist;
	}

	public void setId_artist(int id_artist) {
		this.id_artist = id_artist;
	}

	public String getName_artist() {
		return name_artist;
	}

	public void setName_artist(String name_artist) {
		this.name_artist = name_artist;
	}

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	@Override
	public String toString() {
		return "Artist [id_artist=" + id_artist + ", name_artist="
				+ name_artist + ", band=" + band.toString() + "]";
	}
}
