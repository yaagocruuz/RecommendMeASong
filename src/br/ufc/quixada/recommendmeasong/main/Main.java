package br.ufc.quixada.recommendmeasong.main;

import java.util.Scanner;

import br.ufc.quixada.recommendmeasong.controller.ArtistController;
import br.ufc.quixada.recommendmeasong.controller.BandController;
import br.ufc.quixada.recommendmeasong.controller.GenreController;
import br.ufc.quixada.recommendmeasong.controller.LoginController;
import br.ufc.quixada.recommendmeasong.controller.SongController;
import br.ufc.quixada.recommendmeasong.controller.UserController;
import br.ufc.quixada.recommendmeasong.pojo.Song;
import br.ufc.quixada.recommendmeasong.pojo.User;


public class Main {
	
	public static void main(String[] args) {
		int op;
		boolean end = false;
		
		//GenreDAO genreDAO = new GenreDAO();
		GenreController gControl = new GenreController();
		//BandDAO bandDAO = new BandDAO();
		BandController bControl = new BandController();
		//ArtistDAO artistDAO = new ArtistDAO();
		ArtistController aControl = new ArtistController();
		//SongDAO songDAO = new SongDAO();
		SongController sControl = new SongController();
		//UserDAO userDAO = new UserDAO();
		UserController uControl = new UserController();
		LoginController lCOntrol = new LoginController();
		
		while(!end){
			System.out.println("------ Manda música aí! ------");
			System.out.println("1 - Cadastrar Tipos de Música");
			System.out.println("2 - Cadastrar Bandas");
			System.out.println("3 - Cadastrar Artistas");
			System.out.println("4 - Cadastrar Músicas");
			System.out.println("5 - Cadastrar Usuários");
			System.out.println("6 - Login");
			System.out.println("0 - Sair");
			System.out.println("\nDigite a opção desejada: ");
			op = new Scanner(System.in).nextInt();
			
			switch(op){
			case 0:{
				System.out.println("Até logo... <3");
				end = true;
				break;
			}case 1:{// Genres
				int op1;
				boolean end1 = false;
				
				while(!end1){
					System.out.println();
					System.out.println("Cadastrar Tipos de Música");
					System.out.println("1 - Inserir");
					System.out.println("2 - Atualizar");
					System.out.println("3 - Deletar");
					System.out.println("4 - Listar todos");
					System.out.println("0 - Voltar à  Tela Inicial");
					System.out.println("\nDigite a opção desejada: ");
					op1 = new Scanner(System.in).nextInt();
					
					switch(op1){
					case 0:{
						end1 = true;
						break;
					}case 1:{
						System.out.println(gControl.insert());
						break;
					}case 2:{
						System.out.println(gControl.update());
						break;
					}case 3:{
						System.out.println(gControl.remove());
						break;
					}case 4:{
						gControl.listAll();
						break;
					}default:
						System.out.println("Opção digitada incorretamente. Tente novamente.\n");
						break;
					}
				}
			}case 2:{ //Bands
				int op1;
				boolean end1 = false;
				
				while(!end1){
					System.out.println();
					System.out.println("Cadastrar Bandas");
					System.out.println("1 - Inserir");
					System.out.println("2 - Atualizar");
					System.out.println("3 - Deletar");
					System.out.println("4 - Listar todos");
					System.out.println("0 - Voltar à  Tela Inicial");
					System.out.println("\nDigite a opção desejada: ");
					op1 = new Scanner(System.in).nextInt();
					
					switch(op1){
					case 0:{
						end1 = true;
						break;
					}case 1:{
						System.out.println(bControl.insert());
						break;
					}case 2:{
						System.out.println(bControl.update());
						break;
					}case 3:{
						System.out.println(bControl.remove());
						break;
					}case 4:{
						bControl.listAll();
						break;
					}default:
						System.out.println("Opção digitada incorretamente. Tente novamente.\n");
						break;
					}
				}
			}case 3:{ // Artists
				int op1;
				boolean end1 = false;
				while(!end1){
					System.out.println();
					System.out.println("Cadastrar Artistas");
					System.out.println("1 - Inserir");
					System.out.println("2 - Atualizar");
					System.out.println("3 - Deletar");
					System.out.println("4 - Listar todos");
					System.out.println("0 - Voltar à  Tela Inicial");
					System.out.println("\nDigite a opção desejada: ");
					op1 = new Scanner(System.in).nextInt();
					
					switch(op1){
					case 0:{
						end1 = true;
						break;
					}case 1:{
						System.out.println(aControl.insert());
						break;
					}case 2:{
						System.out.println(aControl.update());
						break;
					}case 3:{
						System.out.println(aControl.remove());
						break;
					}case 4:{
						aControl.listAll();
						break;
					}default:
						System.out.println("Opção digitada incorretamente. Tente novamente.\n");
						break;
					}
				}
			}case 4:{ //Songs
				int op1;
				boolean end1 = false;
				while(!end1){
					System.out.println();
					System.out.println("Cadastrar Músicas");
					System.out.println("1 - Inserir");
					System.out.println("2 - Atualizar");
					System.out.println("3 - Deletar");
					System.out.println("4 - Listar todos");
					System.out.println("0 - Voltar à  Tela Inicial");
					System.out.println("\nDigite a opção desejada: ");
					op1 = new Scanner(System.in).nextInt();
					
					switch(op1){
					case 0:{
						end1 = true;
						break;
					}case 1:{
						System.out.println(sControl.insert());
						break;
					}case 2:{
						System.out.println(sControl.update());
						break;
					}case 3:{
						System.out.println(sControl.remove());
						break;
					}case 4:{
						sControl.listAll();
						break;
					}default:
						System.out.println("Opção digitada incorretamente. Tente novamente.\n");
						break;
					}
				}
			}case 5:{ //Users
				int op1;
				boolean end1 = false;
				while(!end1){
					System.out.println();
					System.out.println("Cadastrar Usuários");
					System.out.println("1 - Inserir");
					System.out.println("2 - Atualizar");
					System.out.println("3 - Deletar");
					System.out.println("4 - Listar todos");
					System.out.println("0 - Voltar à  Tela Inicial");
					System.out.println("\nDigite a opção desejada: ");
					op1 = new Scanner(System.in).nextInt();
					
					switch(op1){
					case 0:{
						end1 = true;
						break;
					}case 1:{
						System.out.println(uControl.insert());
						break;
					}case 2:{
						System.out.println(uControl.update());
						break;
					}case 3:{
						System.out.println(uControl.remove());
						break;
					}case 4:{
						uControl.listAllUsers();
						break;
					}default:
						System.out.println("Opção digitada incorretamente. Tente novamente.\n");
						break;
					}
				}
			}case 6:{ // Login
				System.out.println("------ Efetue seu Login ------");
				System.out.println("Digite seu email: ");
				String email = new Scanner(System.in).next();
				System.out.println("Digite sua senha: ");
				String password = new Scanner(System.in).next();
				
				User user = lCOntrol.login(email, password);
				
				if(user == null){
					System.out.println("Email ou senha incorretos.");
					break;
				}
				
				int op1;
				boolean end1 = false;
				while(!end1){
					System.out.println();
					System.out.println("Bem Vindo, " + user.getName_user());
					System.out.println("1 - Veja nossas recomendações");
					System.out.println("2 - Adicione as que já escutou");
					System.out.println("3 - Listar músicas já escutadas");
					System.out.println("0 - Voltar à  Tela Inicial");
					System.out.println("\nDigite a opção desejada: ");
					op1 = new Scanner(System.in).nextInt();
					
					switch(op1){
					case 0:{
						end1 = true;
						break;
					}case 1:{
						System.out.println("------ Nossa lista para você ------");
						//System.out.println(lCOntrol.recomendations(user.getId_user()).size());
						for(Song s : lCOntrol.recomendations(user.getId_user())){
							System.out.println(s.toString());
						}
						break;
					}case 2:{
						System.out.println("------ Nossa lista para você ------");
						for(Song s : lCOntrol.recomendations(user.getId_user())){
							System.out.println(s.toString());
						}
						System.out.println("\nDigite a id da música que você quer adicionar " +
								"as já escutadas: ");
						int song = new Scanner(System.in).nextInt();
						if(lCOntrol.addAlreadyHeard(user.getId_user(), song)) System.out.println("Adicionado com sucesso.");
						else System.out.println("Erro na inserção.");
						break;
					}case 3:{
						System.out.println("------ Sua lista de já escutadas ------");
						//System.out.println(lCOntrol.recomendations(user.getId_user()).size());
						for(Song s : lCOntrol.listAlreadyHeard(user.getId_user())){
							System.out.println(s.toString());
						}
						break;
					}default:
						System.out.println("Opção digitada incorretamente. Tente novamente.\n");
						break;
					}
				}
			}default:
				System.out.println("Opção digitada está incorreta. Digite novamente.");
				break;
			}
		}
	}
}
