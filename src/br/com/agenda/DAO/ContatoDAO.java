package br.com.agenda.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {
	
	/*
	 * CRUD
	 * c: CREATE
	 * r: READ
	 * u: UPDATE
	 * d: DELETE
	 */
	
	public void save(Contato contato) {
		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criamos uma PreparedStatement para executar uma query
			pstm = conn.prepareStatement(sql);
			
			//Adicionar os valores que são esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			java.sql.Date dataCadastro = new java.sql.Date(contato.getDataCadastro().getTime());
			pstm.setDate(3, dataCadastro);

			
			//Executar a query
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			//Fechar as conexões se estiverem abertas
			
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}



public List<Contato> getContatos() {
    String sql = "SELECT * FROM contatos";

    List<Contato> contatos = new ArrayList<Contato>();

    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rset = null;

    try {
        conn = ConnectionFactory.createConnectionToMySQL();
        pstm = conn.prepareStatement(sql);
        rset = pstm.executeQuery();

        while (rset.next()) {
            Contato contato = new Contato();

            contato.setId(rset.getInt("id"));
            contato.setNome(rset.getString("nome"));
            contato.setIdade(rset.getInt("idade"));
            contato.setDataCadastro(rset.getDate("dataCadastro"));

            contatos.add(contato);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rset != null) {
                rset.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    return contatos;
}


public void update(Contato contato) {
    String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? WHERE id = ?";
    
    Connection conn = null;
    PreparedStatement pstm = null;
    
    try {
    	
    	//Criar conexão com o banco
        conn = ConnectionFactory.createConnectionToMySQL();
        //Criar a classe para executar a query
        pstm = conn.prepareStatement(sql);
        
     
     // Adicionar os valores para atualizar 
        pstm.setString(1, contato.getNome());
        pstm.setInt(2, contato.getIdade());
        java.sql.Date dataCadastro = new java.sql.Date(contato.getDataCadastro().getTime());
        pstm.setDate(3, dataCadastro);
        
     // Qual ID do registro precisa atualizar
        pstm.setInt(4, contato.getId());
        
      //Executar a querry
        pstm.execute();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


public void deleteByID(int id) {
    String sql = "DELETE FROM contatos WHERE id = ?";
    
    Connection conn = null;
    PreparedStatement pstm = null;
    
    try {
        conn = ConnectionFactory.createConnectionToMySQL();
        pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, id);
        
        pstm.execute();
        
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (pstm != null) {
                pstm.close();
            }
            
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
}
	