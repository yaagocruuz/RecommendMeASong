package br.ufc.quixada.recommendmeasong.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ufc.quixada.recommendmeasong.dao.ArtistDAO;
import br.ufc.quixada.recommendmeasong.dao.GenreDAO;
import br.ufc.quixada.recommendmeasong.dao.UserDAO;
import br.ufc.quixada.recommendmeasong.dao.UserHasArtistDAO;
import br.ufc.quixada.recommendmeasong.dao.UserHasGenreDAO;
import br.ufc.quixada.recommendmeasong.pojo.Artist;
import br.ufc.quixada.recommendmeasong.pojo.Genre;
import br.ufc.quixada.recommendmeasong.pojo.User;

public class UserController {
	Scanner scan = new Scanner(System.in);
	
	private UserDAO userDAO = new UserDAO();
	private GenreDAO genreDAO = new GenreDAO();
	private ArtistDAO artistDAO = new ArtistDAO();
	private UserHasGenreDAO userHasGenreDAO = new UserHasGenreDAO();
	private UserHasArtistDAO userHasArtistDAO = new UserHasArtistDAO();
	
	public UserController(){
		this.userDAO = new UserDAO();
	}
	
	public String insert(){
		System.out.println("Digite seu nome: ");
		String name = scan.next();
		System.out.println("Digite seu email: ");
		String email = scan.next();
		System.out.println("Digite sua senha: ");
		String password = scan.next();
		User u_new = new User(0, name, email, password);
		// Adicionar os gêneros favoritos
		System.out.println();
		boolean teste = true;
		List<Integer> id_gs = new ArrayList<Integer>();
		while(teste) {
			for(Genre g : genreDAO.getAllGenres()){
				System.out.println(g.toString());
			}
			System.out.println("\nDigite a ID do gênero: ");
			int id_g = scan.nextInt();
			if(genreDAO.getGenreById(id_g) != null) id_gs.add(id_g);
			System.out.println("Deseja adicionar outra? (s|n)");
			String answer = scan.next();
			if(answer.equals("s")) teste = true;
			else teste = false;
		}
		for(Integer id : id_gs){
			u_new.getFavorite_genres().add(genreDAO.getGenreById(id));		
		}
		// Adicionar os artistas favoritos
		System.out.println();
		boolean teste2 = true;
		List<Integer> id_as = new ArrayList<Integer>();
		while(teste2) {
			for(Artist a : artistDAO.getAllArtists()){
				System.out.println(a.toString());
			}
			System.out.println("\nDigite a ID do artista: ");
			int id_a = scan.nextInt();
			if(artistDAO.getArtistById(id_a) != null) id_as.add(id_a);
			System.out.println("Deseja adicionar outra? (s|n)");
			String answer = scan.next();
			if(answer.equals("s")) teste2 = true;
			else teste2 = false;
		}
		for(Integer id : id_as){
			u_new.getFavorite_artists().add(artistDAO.getArtistById(id));		
		}
		// Adicionar no banco
		if(userDAO.addUser(u_new)){
			u_new.setId_user(userDAO.getIdByEmail(email));
			if(userHasGenreDAO.addUserHasGenre(u_new)){
				if(userHasArtistDAO.addUserHasArtist(u_new)){
					return "Inserido com sucesso";
				}
			}
		}
		return "Erro ao inserir";
	}
	
	public String update(){
		return "";
	}
	
	public String remove(){
		this.listAllUsers();
		System.out.println("Digite a ID do que deseja remover: ");
		int id_u = scan.nextInt();
		if(userDAO.rmUser(id_u)) return "Removido com sucesso";
		else return "Erro ao remover";
	}
	
	public void listAllUsers(){
		List<User> users = userDAO.getAllUsers();
		for(User u : users){
			System.out.println(u.toString());
		}
		System.out.println();
	}

}
