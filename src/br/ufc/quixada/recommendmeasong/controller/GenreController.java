package br.ufc.quixada.recommendmeasong.controller;

import java.util.List;
import java.util.Scanner;

import br.ufc.quixada.recommendmeasong.dao.GenreDAO;
import br.ufc.quixada.recommendmeasong.pojo.Genre;

public class GenreController {
	Scanner scan = new Scanner(System.in);
	private GenreDAO genreDAO;
	
	public GenreController() {
		this.genreDAO = new GenreDAO();
	}
	
	public String insert(){
		System.out.println("Digite o nome do tipo:");
		String name = scan.next();
		Genre genre = new Genre(0, name);
		if(genreDAO.addGenre(genre)) return "Inserido com sucesso.\n";
		else return "Erro ao remover.\n";
	}
	
	public String update(){
		this.listAll();
		System.out.println("Digite o ID do que deseja atualizar: ");
		int id = scan.nextInt();
		System.out.println("Digite o novo nome de tipo: ");
		String name = scan.next();
		if(genreDAO.upGenre(id, name)) return "Atualizado com sucesso.\n";
		else return "Erro ao atualizar.\n";
	}
	
	public String remove(){
		this.listAll();
		System.out.println("Digite o ID do que deseja remover: ");
		int id = scan.nextInt();
		if(genreDAO.rmGenre(id)) return "Removido com sucesso.\n";
		else return "Erro ao remover.\n";
	}
	
	public void listAll(){
		System.out.println("----- Tipos de Música -----");
		List<Genre> genres = genreDAO.getAllGenres();
		for(Genre g : genres){
			//System.out.println("r");
			System.out.println(g.toString());
		}
		System.out.println();
	}
}
