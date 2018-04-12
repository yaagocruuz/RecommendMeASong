package br.ufc.quixada.recommendmeasong.pojo;

public class Song {
	private int id_song;
	private String name_song;
	private Genre genre;
	private Band band;
	
	public Song(int id_song, String name_song, Genre genre, Band band){
		this.id_song = id_song;
		this.name_song = name_song;
		this.genre = genre;
		this.band = band;
	}

	public int getId_song() {
		return id_song;
	}

	public void setId_song(int id_song) {
		this.id_song = id_song;
	}

	public String getName_song() {
		return name_song;
	}

	public void setName_song(String name_song) {
		this.name_song = name_song;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	@Override
	public String toString() {
		return "Song [id_song=" + id_song + ", name_song=" + name_song
				+ ", genre=" + genre + ", band=" + band.toString() + "]";
	}
}
