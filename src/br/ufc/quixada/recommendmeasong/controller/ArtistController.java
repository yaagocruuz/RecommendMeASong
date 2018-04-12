package br.ufc.quixada.recommendmeasong.controller;

import java.util.List;
import java.util.Scanner;

import br.ufc.quixada.recommendmeasong.dao.ArtistDAO;
import br.ufc.quixada.recommendmeasong.dao.BandDAO;
import br.ufc.quixada.recommendmeasong.pojo.Artist;
import br.ufc.quixada.recommendmeasong.pojo.Band;

public class ArtistController {
	Scanner scan = new Scanner(System.in);
	
	private ArtistDAO artistDAO;
	private BandDAO bandDAO;
	
	public ArtistController(){
		this.artistDAO = new ArtistDAO();
		this.bandDAO = new BandDAO();
	}
	
	public String insert(){
		System.out.println("Digite o nome do artista: ");
		String name = scan.next();
		System.out.println();
		List<Band> bands = bandDAO.getAllBands();
		for(Band b : bands){
			System.out.println(b.toString());
		}
		System.out.println("\nDigite a ID da banda: ");
		int id_b = scan.nextInt();
		
		Artist artist = new Artist(0, name, bandDAO.getBandById(id_b));
		if(artistDAO.addArtist(artist)) return "Inserido com sucesso.\n";
		else return "Erro ao inserir.\n";
	}

	public String update(){
		this.listAll();
		System.out.println("Digite o ID do que deseja atualizar: ");
		int id = scan.nextInt();
		System.out.println("Digite o novo nome do artista: ");
		String name = scan.next();
		System.out.println();
		List<Band> bands = bandDAO.getAllBands();
		for(Band b : bands){
			System.out.println(b.toString());
		}
		System.out.println("\nDigite a ID da banda: ");
		int id_b = scan.nextInt();
		if(artistDAO.upArtist(id, name, bandDAO.getBandById(id_b))) 
			return "Atualizado com sucesso.\n";
		else return "Erro ao atualizar.\n";
	}
	
	public String remove(){
		this.listAll();
		System.out.println("\nDigite o ID do que deseja remover: ");
		int id = scan.nextInt();
		if(artistDAO.rmArtist(id)) return "Removido com sucesso.\n";
		else return "Erro ao remover.\n";
	}
	
	public void listAll(){
		List<Artist> artists = artistDAO.getAllArtists();
		for(Artist a : artists){
			System.out.println(a.toString());
		}
		System.out.println();
	}
}
