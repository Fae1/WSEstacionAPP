package br.com.restful.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe responsavel por conter os mediotos de criar e fechar o banco de dados.
 * 
 * 
 * @author Admin
 *
 */


public class ConnectionFactory {
	//Caminho do banco de dados
	private static final String DRIVER = "com.mysql.jdbc.Driver"; 
	private static final String URL = "jdbc:mysql://localhost:3306/estacionapp"; 
	private static final String USUARIO = "root"; 
	private static final String SENHA = "";
	
	
	/**
	 * Método responsavel por criar uma conexão com o banco
	 * 
	 * 
	 * 
	 * 
	 * @return
	 */
	
	public Connection criarConexao(){
		
		Connection conexao = null;
		
		try {
			
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			
		} catch (Exception e) {
			System.out.println("Erro ao criar conexão com o banco: " + URL);
			e.printStackTrace();
		}
		return conexao;
	}
	
	public void fecharConexao(Connection conexao, PreparedStatement pstmt, ResultSet rs){
		try {
			if (conexao != null){
				conexao.close();
			}
			if (pstmt != null){
				pstmt.close();
			}
			if (rs != null){
				rs.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void fecharConexao(Connection conexao, PreparedStatement pstmt){
		try {
			if (conexao != null){
				conexao.close();
			}
			if (pstmt != null){
				pstmt.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
