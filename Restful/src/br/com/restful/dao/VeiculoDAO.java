package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Veiculo;
import br.com.restful.util.DebitControl;
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
	
	private DebitControl debitControl = null;
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
	
	public Veiculo cadastrarVeiculo(String carro, String placa, String apelido, String idUsuario){
			Connection conexao = null;
			PreparedStatement pstmt = null;
			
			int Estacionado = 0;
			conexao = criarConexao();
			Veiculo veiculo = new Veiculo();
			
			
			
			/*try {//CODE que pode ser útil para consultar meus veiculos
				pstmt = conexao.prepareStatement("select idUsuario from usuario where CPF = " + cpf + ";");
				rs = pstmt.executeQuery();
			} catch (SQLException e1) {
				System.out.println("Erro na consulta do cpf");
				e1.printStackTrace();
			}
			if(rs.next()){
			*/
			try {
					pstmt = conexao.prepareStatement("INSERT INTO veiculo (Nome, Placa, Apelido, idUsuarioFK, Estacionado) "
					           +"VALUES('"  
					           + carro + "', '" 
					           + placa + "', '"  
					           + apelido + "', '"
					           + idUsuario + "', '"
					           + Estacionado + "');");
					pstmt.executeUpdate();
					
					veiculo.setModelo(carro);
					veiculo.setPlaca(placa);
					veiculo.setApelido(apelido);
			} catch (SQLException e) {
				System.out.println("Erro ao inserir o novo veículo: " + e);
				veiculo = null;
				e.printStackTrace();
			} finally{
				fecharConexao(conexao, pstmt);
			}
			
			return veiculo;
		}

	public ArrayList<Veiculo> listarMeusVeiculos(String idUsuario) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Veiculo> veiculos = null;
		
		conexao = criarConexao();
		veiculos = new ArrayList<Veiculo>();
		
		try {
			pstmt = conexao.prepareStatement("select * from veiculo WHERE idUsuarioFK = " + idUsuario + " order by nome;");
			rs = pstmt.executeQuery();
			Veiculo veiculo = null;
			while(rs.next()){
				veiculo = new Veiculo();
				veiculo.setIdVeiculo(rs.getInt("idVeiculo"));
				veiculo.setModelo(rs.getString("Nome"));
				veiculo.setApelido(rs.getString("Apelido"));
				veiculo.setPlaca(rs.getString("Placa"));
				veiculos.add(veiculo);
			}
			veiculo = new Veiculo();
			veiculo.setIdVeiculo(0);
			veiculo.setModelo("0");
			veiculo.setApelido("0");
			veiculo.setPlaca("0");
			veiculos.add(veiculo);
		} catch (Exception e) {
			System.out.println("Erro ao listar seus veiculos: " + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		
		return veiculos;
		
	}
	
	public String apagarVeiculo(String idVeiculo){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		String retorno ="";
		conexao = criarConexao();
		
			try {
				pstmt = conexao.prepareStatement("delete from veiculo WHERE idVeiculo = " + idVeiculo + ";");
				pstmt.executeUpdate();
				retorno = "Apagado";
				
			} catch (SQLException e) {
				retorno = "Não foi possível apagar. /nPor favor, tente novamente.";
				e.printStackTrace();
			}finally{
				fecharConexao(conexao, pstmt);
			}
			
		return retorno;
		
	}
	
	public String estacionar(String placa, String horasDeUso){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		String retorno = "", 
				descricao = "Utilização do zona azul";
		float saldo = new UsuarioDAO().consultarSaldo(placa);
		float valorHora = new VagaDAO().consultarValorHora();
		int idUsuario, idVeiculo;
		ResultSet rs = null;
		//TODO Passa a identificação da vaga, latitude longitude/bairro/cidade/estado
		if(saldo > valorHora * Float.parseFloat(horasDeUso)){
			conexao = criarConexao();
			try {
				pstmt = conexao.prepareStatement("update veiculo SET estacionado =  1 where Placa = '" + placa + "';");
				pstmt.executeUpdate();
				retorno = "Seu carro está devidamente estacionado.";
				
				pstmt = conexao.prepareStatement("select idUsuarioFK, idVeiculo from veiculo where Placa = '" + placa + "'");
				rs = pstmt.executeQuery();
				rs.next();
				idUsuario = rs.getInt("idUsuarioFK");
				idVeiculo = rs.getInt("idVeiculo");
				
				pstmt = conexao.prepareStatement("INSERT INTO log (data, idUsuarioFK, descricao, valorRecarga, idVeiculo) "
				           +"VALUES("  
				           + "NOW(), '" 
				           + idUsuario + "', '"  
				           + descricao + "', "
				           + 0 + ", '"
				           + idVeiculo +"');");
				pstmt.executeUpdate();
				debitControl = new DebitControl(placa, Integer.parseInt(horasDeUso));
				debitControl.start();
			} catch (SQLException e) {
				retorno = "Veiculo não encotrado, favor contactar o suporte.";
				System.out.println(retorno + e);
				e.printStackTrace();
			} finally{
				fecharConexao(conexao, pstmt);
			}
		}
		else{
			retorno = "Seu saldo é insuficiente: " + saldo;
		}
		return retorno;
	}
	
	public String pararCobranca(String placa){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String retorno = "", descricao = "Utilização do zona azul";
		double valorHora = new VagaDAO().consultarValorHora();
		boolean estacionado;
		conexao = criarConexao();
			try {
				pstmt = conexao.prepareStatement("select estacionado from veiculo where Placa = '" + placa + "'");
				rs = pstmt.executeQuery();
				rs.next();
				estacionado = rs.getBoolean("estacionado");
				if(estacionado){
					pstmt = conexao.prepareStatement("update veiculo SET estacionado =  0 where Placa = '" + placa + "';");//***
					pstmt.executeUpdate();
					
					pstmt = conexao.prepareStatement("select idUsuarioFK from veiculo where Placa = '" + placa + "'");
					rs = pstmt.executeQuery();
					rs.next();
					int idUsuario = rs.getInt("idUsuarioFK");
					
					pstmt = conexao.prepareStatement("select data from log where idUsuarioFK = '" + idUsuario + "'order by id desc");
					rs = pstmt.executeQuery();
					rs.next();
					
					Calendar calEstacionado = Calendar.getInstance();
					Timestamp ts = rs.getTimestamp("data");
					
					calEstacionado.setTimeInMillis(ts.getTime());
					long diferencaMili = calEstacionado.getTimeInMillis();
							
					Calendar horaAtual = Calendar.getInstance();
					horaAtual.setTime(horaAtual.getTime());
					System.out.println("Hora que o carro foi estacionado: " + diferencaMili
							+ "Time atual: " + horaAtual.getTimeInMillis());
					diferencaMili = horaAtual.getTimeInMillis() - diferencaMili;
					
					System.out.println("Diferença em milisegundos: " + diferencaMili);
					double tempoEstacionado = diferencaMili/3600000;
					
					System.out.println("Minutos estacionado: " + tempoEstacionado);
					valorHora = (Math.floor(tempoEstacionado * 100) / 100) * valorHora;
					pstmt = conexao.prepareStatement("update usuario SET saldo = saldo - '" +  valorHora +  "' where idUsuario = '" + idUsuario + "'");
					pstmt.executeUpdate();
					debitControl.stop();
					retorno = "Carro saiu da vaga.";
				}
				
			} catch (SQLException e) {
				retorno = "Veiculo não encotra-se estacionado.";
				System.out.println(retorno + e);
				e.printStackTrace();
			} finally{
				fecharConexao(conexao, pstmt);
			}
		return retorno;
	}
}
