package br.ufc.quixada.recommendmeasong.pojo;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int id_user;
	private String name_user;
	private String email_user;
	private String password_user;
	private List<Genre> favorite_genres;
	private List<Artist> favorite_artists;
	private List<Song> already_heard;
	
	public User(int id_user, String name_user, String email_user, String password_user){
		this.id_user = id_user;
		this.name_user = name_user;
		this.email_user = email_user;
		this.password_user = password_user;
		this.favorite_genres = new ArrayList<Genre>();
		this.favorite_artists = new ArrayList<Artist>();
		this.already_heard = new ArrayList<Song>();
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getName_user() {
		return name_user;
	}

	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	public String getEmail_user() {
		return email_user;
	}

	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}

	public String getPassword_user() {
		return password_user;
	}

	public void setPassword_user(String password_user) {
		this.password_user = password_user;
	}

	public List<Genre> getFavorite_genres() {
		return favorite_genres;
	}

	public void setFavorite_genres(List<Genre> favorite_genres) {
		this.favorite_genres = favorite_genres;
	}

	public List<Artist> getFavorite_artists() {
		return favorite_artists;
	}

	public void setFavorite_artists(List<Artist> favorite_artists) {
		this.favorite_artists = favorite_artists;
	}
	
	public List<Song> getAlready_heard() {
		return already_heard;
	}

	public void setAlready_heard(List<Song> already_heard) {
		this.already_heard = already_heard;
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", name_user=" + name_user
				+ ", email_user=" + email_user
				+ ", password_user=" + password_user + ", favorite_genres="
				+ favorite_genres + ", favorite_artists=" + favorite_artists
				+ ", already_heard=" + already_heard + "]";
	}
}