package br.ufc.quixada.recommendmeasong.controller;

import java.util.List;
import java.util.Scanner;

import br.ufc.quixada.recommendmeasong.dao.BandDAO;
import br.ufc.quixada.recommendmeasong.dao.GenreDAO;
import br.ufc.quixada.recommendmeasong.dao.SongDAO;
import br.ufc.quixada.recommendmeasong.pojo.Band;
import br.ufc.quixada.recommendmeasong.pojo.Genre;
import br.ufc.quixada.recommendmeasong.pojo.Song;

public class SongController {
	Scanner scan = new Scanner(System.in);
	
	private SongDAO songDAO = new SongDAO();
	private GenreDAO genreDAO = new GenreDAO();
	private BandDAO bandDAO = new BandDAO();
	
	public String insert(){
		System.out.println("Digite o nome da música: ");
		String name = scan.next();
		
		System.out.println();
		for(Genre g : genreDAO.getAllGenres()){
			System.out.println(g.toString());
		}
		System.out.println("\nDigite a ID do Tipo de música: ");
		int id_g = scan.nextInt();
		
		System.out.println();
		for(Band b : bandDAO.getAllBands()){
			System.out.println(b.toString());
		}
		System.out.println("\nDigite a ID da banda: ");
		int id_b = scan.nextInt();
		
		Song song = new Song(0, name, genreDAO.getGenreById(id_g), 
				bandDAO.getBandById(id_b));
		if(songDAO.addSong(song)) return "Inserido com sucesso";
		else return "Erro ao inserir";
	}
	
	public String update(){
		this.listAll();
		System.out.println("Digite a ID do que deseja atualizar: ");
		int id_s = scan.nextInt();
		System.out.println("Digite o nome da música: ");
		String name = scan.next();
		System.out.println();
		for(Genre g : genreDAO.getAllGenres()){
			System.out.println(g.toString());
		}
		System.out.println("\nDigite a ID do Tipo de música: ");
		int id_g = scan.nextInt();
		System.out.println();
		for(Band b : bandDAO.getAllBands()){
			System.out.println(b.toString());
		}
		System.out.println("\nDigite a ID da banda: ");
		int id_b = scan.nextInt();
		Song song = new Song(id_s, name, genreDAO.getGenreById(id_g), 
				bandDAO.getBandById(id_b));
		
		if(songDAO.upSong(song)) return "Atualizado com sucesso";
		else return "Erro ao atualizar";
	}
	
	public String remove(){
		this.listAll();
		System.out.println("Digite a ID do que deseja remover: ");
		int id_s = scan.nextInt();
		if(songDAO.rmSong(id_s)) return "Removido com sucesso";
		else return "Erro ao remover";
	}
	
	public void listAll(){
		List<Song> songs = songDAO.getAllSongs();
		for(Song s : songs){
			System.out.println(s.toString());
		}
		System.out.println();
	}
}
