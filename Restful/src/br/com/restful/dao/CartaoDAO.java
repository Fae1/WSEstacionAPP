package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Cartao;
/**
 * Classe responsavel por conter os metodos CRUD
 * @author Admin
 *
 */

public class CartaoDAO extends ConnectionFactory {
	
	private static CartaoDAO instance;
	
	/**
	 * Método responsável por criar uma instancia da classe Usuario DAO (Singleton)
	 * 
	 * 
	 * 
	 * @return
	 */
	
	public static CartaoDAO getInstance(){
		if(instance == null)
			instance = new CartaoDAO();
		return instance;
	}
	
	/**
	 * Metodos responsavel por listar todos os usuarios do banco.
	 * 
	 * 
	 * @return ArrayList<Cartao> Cartoes
	 */
	public ArrayList<Cartao> listarTodos(){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Cartao> cartoes = null;
		
		conexao = criarConexao();
		cartoes = new ArrayList<Cartao>();
		
		try {
			pstmt = conexao.prepareStatement("select * from usuario order by nome");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Cartao cartao = new Cartao();
				
				cartao.setIdCartao(rs.getInt("idCartao"));
				cartao.setCodSeg(rs.getString("Cod_Seg"));
				cartao.setNomeTitular(rs.getString("Nome_Titular"));
				cartao.setNumCartao(rs.getString("Num_Cartao"));
				cartao.setValidadeMes(rs.getInt("Validade_Mes"));
				cartao.setValidadeAno(rs.getInt("Validade_Ano"));
				
				cartoes.add(cartao);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os cartoes: " + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		
		return cartoes;
	}
	
}
