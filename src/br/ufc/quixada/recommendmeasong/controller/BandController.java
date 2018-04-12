package br.ufc.quixada.recommendmeasong.controller;

import java.util.List;
import java.util.Scanner;

import br.ufc.quixada.recommendmeasong.dao.BandDAO;
import br.ufc.quixada.recommendmeasong.pojo.Band;

public class BandController {
	Scanner scan = new Scanner(System.in);
	
	private BandDAO bandDAO;
	
	public BandController() {
		this.bandDAO = new BandDAO();
	}
	
	public String insert() {
		System.out.println("Digite o nome da banda: ");
		String name = scan.next();
		Band band = new Band(0, name);
		if(bandDAO.addBand(band)) return "Inserido com sucesso.\n";
		else return "Erro ao inserir.\n";
	}
	
	public String update() {
		this.listAll();
		System.out.println("Digite o ID do que deseja atualizar: ");
		int id = scan.nextInt();
		System.out.println("Digite o novo nome da banda: ");
		String name = scan.next();
		if(bandDAO.upBand(id, name)) return "Atualizado com sucesso.\n";
		else return "Erro ao atualizar.\n";
	}
	
	public String remove(){
		this.listAll();
		System.out.println("Digite o ID do que deseja remover: ");
		int id = scan.nextInt();
		if(bandDAO.rmBand(id)) return "Removido com sucesso.\n";
		else return "Erro ao remover.\n";
	}
	
	public void listAll(){
		List<Band> bands = bandDAO.getAllBands();
		for(Band b : bands){
			System.out.println(b.toString());
		}
		System.out.println();
	}

}
