package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.DAO.ContatoDAO;
import br.com.agenda.model.Contato;

public class main {

	public static void main(String[] args) {
		
		Contato contato = new Contato();
		ContatoDAO contatoDao = new ContatoDAO();
		
		contato.setNome("Robson");
		contato.setIdade(28);
		contato.setDataCadastro(new Date());
		
		contatoDao.save(contato);
		
		//Atualizar contato
		
		Contato c1 = new Contato();
		
		c1.setNome("Robson Ferreira da Silva");
		c1.setIdade(30);
		c1.setDataCadastro(new Date());
		c1.setId(1);
		
		contatoDao.update(c1);
		
		//Remover o contato pelo o numero do id
		
		contatoDao.deleteByID(1);
		
		//Visualização dos registros do banco de dados
		
		for(Contato c : contatoDao.getContatos()) {
			System.out.println("Nome: " + c.getNome());
			System.out.println("Idade: " + c.getIdade());
			System.out.println("Data: " + c.getDataCadastro());
		}

	}

}
