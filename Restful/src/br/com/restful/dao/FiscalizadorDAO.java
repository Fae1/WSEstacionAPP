package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Fiscalizador;
/**
 * Classe responsavel por conter os metodos CRUD
 * @author Admin
 *
 */

public class FiscalizadorDAO extends ConnectionFactory {
	
	private static FiscalizadorDAO instance;
	
	/**
	 * Método responsável por criar uma instancia da classe Usuario DAO (Singleton)
	 * 
	 * 
	 * 
	 * @return
	 */
	
	public static FiscalizadorDAO getInstance(){
		if(instance == null)
			instance = new FiscalizadorDAO();
		return instance;
	}
	
	/**
	 * Metodos responsavel por listar todos os fiscalizadores do banco.
	 * 
	 * 
	 * @return ArrayList<Fiscalizador> Usuarios
	 */
	public ArrayList<Fiscalizador> listarTodos(){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Fiscalizador> fiscalizadores = null;
		
		conexao = criarConexao();
		fiscalizadores = new ArrayList<Fiscalizador>();
		
		try {
			pstmt = conexao.prepareStatement("select * from usuario order by nome");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Fiscalizador fiscalizador = new Fiscalizador();
				
				fiscalizador.setIdFiscalizador(rs.getInt("idFiscalizador"));
				fiscalizador.setNome(rs.getString("nome"));
				fiscalizador.setSobreNome(rs.getString("sobrenome"));
				fiscalizador.setEmail(rs.getString("email"));
				fiscalizador.setEmpresa(rs.getString("empresa"));
				fiscalizador.setCpf(rs.getString("CPF"));
				fiscalizador.setDataNasc(rs.getDate("Data_Nasc"));
				fiscalizador.setSexo(rs.getBoolean("sexo"));
				fiscalizadores.add(fiscalizador);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os usuarios: " + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		
		return fiscalizadores;
	}
	
	public Fiscalizador logarFiscalizador(String email, String senha){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Fiscalizador fiscalizador = null;
		
		conexao = criarConexao();
		
		try {
			pstmt = conexao.prepareStatement("select * from fiscalizador where email = '" + email + "'");
			rs = pstmt.executeQuery();
			rs.next();
			fiscalizador = new Fiscalizador();
			fiscalizador.setEmail(rs.getString("email"));
			fiscalizador.setSenha(rs.getString("senha"));
			fiscalizador.setIdFiscalizador(rs.getInt("idFiscalizador"));
			fiscalizador.setNome(rs.getString("nome"));
			fiscalizador.setSobreNome(rs.getString("sobrenome"));
			fiscalizador.setEmpresa(rs.getString("empresa"));
			if(!fiscalizador.getSenha().equals(senha)){
				fiscalizador = null;
			}
			
		} catch (SQLException e) {
			System.out.println("Email não cadastrado, erro: " + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		return fiscalizador;
	}
	
	public String fiscalizarVeiculo(String placa){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String situacao = null;
		
		conexao = criarConexao();
		
		try {
			pstmt = conexao.prepareStatement("select Estacionado from veiculo where Placa = '" + placa + "'");
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getBoolean("Estacionado")){
					situacao = "Veiculo legalmente estacionado.";
					System.out.println(situacao);
				}
				else{
					situacao = "Veiculo em situação irregular.";
					System.out.println(situacao);
				}
			}
			else{
				situacao = "Veiculo não encontrado, favor verificar o preenchimento da placa.";
			}
		} catch (SQLException e) {
			System.out.println("Email não cadastrado, erro: " + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		return situacao;
	}
	
}
