package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Vaga;
/**
 * Classe responsavel por conter os metodos CRUD
 * @author Admin
 *
 */

public class VagaDAO extends ConnectionFactory {
	
	private static VagaDAO instance;
	
	/**
	 * Método responsável por criar uma instancia da classe Usuario DAO (Singleton)
	 * 
	 * 
	 * 
	 * @return
	 */
	
	public static VagaDAO getInstance(){
		if(instance == null)
			instance = new VagaDAO();
		return instance;
	}
	
	/**
	 * Metodos responsavel por listar todos os usuarios do banco.
	 * 
	 * 
	 * @return ArrayList<Vagas> Vagas
	 */
	public ArrayList<Vaga> listarTodos(){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Vaga> vagas = null;
		
		conexao = criarConexao();
		vagas = new ArrayList<Vaga>();
		
		try {
			pstmt = conexao.prepareStatement("select * from usuario order by nome");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Vaga vaga = new Vaga();
				
				vaga.setIdVaga(rs.getInt("idVaga"));
				vaga.setLocal(rs.getString("Local"));
				vaga.setQtdVagas(rs.getInt("Qtd_Vagas"));
				vaga.setValorHora(rs.getFloat("Valor_Hora"));
				vaga.setInicioUtiliza(rs.getDate("Inicio_Utiliza"));
				vaga.setFimUtiliza(rs.getDate("Fim_Utiliza"));
				vaga.setTempoUtiliza(rs.getFloat("Tempo_Utiliza"));
				vagas.add(vaga);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os vagas: " + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		return vagas;
	}
	
	public float consultarValorHora(){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		float valorHora = 0;
		
		conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("select Valor_Hora from vaga where idVaga = 1");
			rs = pstmt.executeQuery();
			rs.next();
			valorHora = rs.getFloat("Valor_Hora");
		} catch (SQLException e) {
			System.out.println("ERROR: " + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		
		return valorHora;
	}
	
}
