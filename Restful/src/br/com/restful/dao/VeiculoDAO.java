package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Usuario;
import br.com.restful.model.Veiculo;
/**
 * Classe responsavel por conter os metodos CRUD
 * @author Admin
 *
 */

public class VeiculoDAO extends ConnectionFactory {
	
	private static VeiculoDAO instance;
	
	/**
	 * Método responsável por criar uma instancia da classe Veiculo DAO (Singleton)
	 * 
	 * 
	 * 
	 * @return
	 */
	
	public static VeiculoDAO getInstance(){
		if(instance == null)
			instance = new VeiculoDAO();
		return instance;
	}
	
	/**
	 * Metodos responsavel por listar todos os veiculos do banco.
	 * 
	 * 
	 * @return ArrayList<Veiculo> Veiculos
	 */
	public ArrayList<Veiculo> listarTodos(){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Veiculo> veiculos = null;
		
		conexao = criarConexao();
		veiculos = new ArrayList<Veiculo>();
		
		try {
			pstmt = conexao.prepareStatement("select * from veiculo order by nome");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Veiculo veiculo = new Veiculo();
				
				veiculo.setIdVeiculo(rs.getInt("idVeiculo"));
				veiculo.setApelido(rs.getString("Apelido"));
				veiculo.setPlaca(rs.getString("Placa"));
				veiculos.add(veiculo);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os veiculos: " + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		
		return veiculos;
	}
	public Veiculo cadastrarVeiculo(String modelo, String placa, String apelido){
			Connection conexao = null;
			PreparedStatement pstmt = null;
			
			conexao = criarConexao();
			Veiculo veiculo = new Veiculo();
			
			veiculo.setModelo(modelo);
			veiculo.setPlaca(placa);
			veiculo.setApelido(apelido);

			try {
				pstmt.executeUpdate("INSERT INTO Veiculo VALUES('"  
				           + veiculo.getModelo() + "', '" + veiculo.getPlaca() + "',"  
				           + veiculo.getApelido() + "')");
			} catch (SQLException e) {
				System.out.println("Erro ao inserir o novo usuário: " + e);
				veiculo = null;
				e.printStackTrace();
			}
		     
		         System.out.println("Inserida!");  
			
			return veiculo;
		}
}
