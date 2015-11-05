package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Pagamento;
/**
 * Classe responsavel por conter os metodos CRUD
 * @author Admin
 *
 */

public class PagamentoDAO extends ConnectionFactory {
	
	private static PagamentoDAO instance;
	
	/**
	 * Método responsável por criar uma instancia da classe Usuario DAO (Singleton)
	 * 
	 * 
	 * 
	 * @return
	 */
	
	public static PagamentoDAO getInstance(){
		if(instance == null)
			instance = new PagamentoDAO();
		return instance;
	}
	
	/**
	 * Metodos responsavel por listar todos os usuarios do banco.
	 * 
	 * 
	 * @return ArrayList<Pagamento> Usuarios
	 */
	public ArrayList<Pagamento> listarTodos(){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Pagamento> pagamentos = null;
		
		conexao = criarConexao();
		pagamentos = new ArrayList<Pagamento>();
		
		try {
			pstmt = conexao.prepareStatement("select * from usuario order by nome");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Pagamento pagamento = new Pagamento();
				
				pagamento.setIdPagamento(rs.getInt("idPagamento"));
				pagamento.setTipoPagamento(rs.getInt("Tipo_Pgto"));
				pagamento.setValor(rs.getFloat("Valor"));
				pagamentos.add(pagamento);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os pagamentos: " + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		
		return pagamentos;
	}
	
}
