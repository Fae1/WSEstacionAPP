package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Usuario;
/**
 * Classe responsavel por conter os metodos CRUD
 * @author Admin
 *
 */

public class UsuarioDAO extends ConnectionFactory {
	
	private static UsuarioDAO instance;
	
	/**
	 * Método responsável por criar uma instancia da classe Usuario DAO (Singleton)
	 * 
	 * 
	 * 
	 * @return
	 */
	
	public static UsuarioDAO getInstance(){
		if(instance == null)
			instance = new UsuarioDAO();
		return instance;
	}
	
	/**
	 * Metodos responsavel por listar todos os usuarios do banco.
	 * 
	 * 
	 * @return ArrayList<Usuario> Usuarios
	 */
	public ArrayList<Usuario> listarTodos(){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Usuario> usuarios = null;
		
		conexao = criarConexao();
		usuarios = new ArrayList<Usuario>();
		
		try {
			pstmt = conexao.prepareStatement("select * from usuario order by nome");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Usuario usuario = new Usuario();
				
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("Nome"));
				usuario.setSobreNome(rs.getString("Sobrenome"));
				usuario.setEmail(rs.getString("Email"));
				usuario.setCelular(rs.getString("Celular"));
				usuario.setCnh(rs.getString("CNH"));
				usuario.setCpf(rs.getString("CPF"));
				usuario.setDataNasc(rs.getDate("Data_Nasc"));
				usuario.setSexo(rs.getBoolean("Sexo"));
				usuarios.add(usuario);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os usuarios: " + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		
		return usuarios;
	}
	
	public Usuario logarUsuario(String email, String senha){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Usuario usuario = null;
		
		conexao = criarConexao();
		
		try {
			pstmt = conexao.prepareStatement("select * from usuario where Email = '" + email + "'");
			rs = pstmt.executeQuery();
			rs.next();
				usuario = new Usuario();
				usuario.setEmail(rs.getString("Email"));
				usuario.setSenha(rs.getString("Senha"));
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("Nome"));
				usuario.setSobreNome(rs.getString("Sobrenome"));
				
			if(!usuario.getSenha().equals(senha)){
				usuario = null;
			}
			
		} catch (SQLException e) {
			System.out.println("Email não cadastrado, erro: " + e);
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	public Usuario cadastrarUsuario(String nome, String sobrenome, String email, String cpf, String cnh, 
		String dataNasc, String celular, String sexo, String senha){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		
		conexao = criarConexao();
		Usuario usuario = new Usuario();
		
		usuario.setSobreNome(sobrenome);
		

		try {
			pstmt.executeUpdate("INSERT INTO Usuario VALUES('"  
			           + usuario.getNome() + "', '" + usuario.getNome() + "',"  
			           + usuario.getSenha() + ",'" + usuario.getSobreNome() + "','"  
			           + usuario.getIdUsuario() + "')");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o novo usuário: " + e);
			usuario = null;
			e.printStackTrace();
		}
	     
	         System.out.println("Inserida!");  
		
		return usuario;
	}
	
	public Usuario fornecerInfo(String email){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Usuario usuario = null;
		
		conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("select * from usuario where Nome =" + email);
			rs = pstmt.executeQuery();
			rs.next();
				usuario = new Usuario();
				usuario.setEmail(rs.getString("Email"));
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("Nome"));
				usuario.setSobreNome(rs.getString("Sobrenome"));
				usuario.setCelular(rs.getString("Celular"));
				usuario.setCnh(rs.getString("CNH"));
				usuario.setDataNasc(rs.getDate("Data_Nasc"));
				usuario.setCpf(rs.getString("CPF"));
				usuario.setSaldo(rs.getFloat("Saldo"));
				usuario.setSexo(rs.getBoolean("Sex"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	
}
