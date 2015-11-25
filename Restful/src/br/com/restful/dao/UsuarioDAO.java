package br.com.restful.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Log;
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
				usuario.setSaldo(rs.getFloat("Saldo"));
			if(!usuario.getSenha().equals(senha)){
				usuario = null;
			}
			
		} catch (SQLException e) {
			System.out.println("Email não cadastrado, erro: " + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		
		return usuario;
		
	}
	
	public Usuario cadastrarUsuario(String nome, String sobrenome, String email, String cpf, String cnh, 
		String dataNasc, String celular, String sexo,  String senha){
		
		Connection conexao = null;
		PreparedStatement pstmt = null;
		
		Usuario usuario = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		Date date = null;
		
		try {
			date = new java.sql.Date(formatter.parse(dataNasc).getTime());
		} catch (ParseException e1) {
			System.out.println("Erro no converter de data" + e1);
			e1.printStackTrace();
		}
		
		conexao = criarConexao();
		
		//" insert into users (first_name, last_name, date_created, is_admin, num_points)"
        //+ " values (?, ?, ?, ?, ?)";
		
		try {
			pstmt = conexao.prepareStatement("INSERT INTO usuario (Nome, Sobrenome, Email, CPF, CNH, Data_Nasc, Celular, Sexo, Senha, Saldo) "
			           +"VALUES('"  
			           + nome + "', '" 
			           + sobrenome + "', '"  
			           + email + "', '" 
			           + cpf + "', '"  
			           + cnh + "', '"
			           + date + "', '"
			           + celular + "', '" 
			           + sexo + "', '"
			           + senha + "', '"
			           + "0')");//Saldo
			pstmt.executeUpdate();
			usuario = new Usuario();
			usuario.setNome(nome);
			usuario.setSobreNome(sobrenome);
			usuario.setEmail(email);
			usuario.setEmail(cpf);
			usuario.setEmail(cnh);
			usuario.setEmail(dataNasc);
			usuario.setEmail(celular);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt);
		}

		return usuario;
	}
	
	public Usuario fornecerInfo(String email){
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
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("Nome"));
				usuario.setSobreNome(rs.getString("Sobrenome"));
				usuario.setCelular(rs.getString("Celular"));
				usuario.setCnh(rs.getString("CNH"));
				usuario.setDataNasc(rs.getDate("Data_Nasc"));
				usuario.setCpf(rs.getString("CPF"));
				usuario.setSaldo(rs.getFloat("Saldo"));
				usuario.setSexo(rs.getBoolean("Sexo"));
			
		} catch (SQLException e) {
			System.out.println("Erro ao fornecer as informações, error: " + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		
		return usuario;
	}
	
	public String inserirCredito(String id, String valor){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		String retorno = "",
				descricao = "Inserção de crédito";
		
		conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("update usuario SET saldo = saldo + '" + valor + "' where idUsuario = '" + id + "';");
			pstmt.executeUpdate();
			pstmt = conexao.prepareStatement("INSERT INTO log (data, idUsuarioFK, descricao, valorRecarga, idVeiculo) "
			           +"VALUES("  
			           + "NOW(), '" 
			           + id + "', '"  
			           + descricao + "', '"
			           + valor + "', '"
			           + 0 +"');");
			pstmt.executeUpdate();
			retorno = "Recarga realizada com sucesso.";
		} catch (SQLException e) {
			retorno = "Não foi possível realizar a recarga.";
			System.out.println(retorno + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt);
		}
		
		return retorno;
	}
	
	public float consultarSaldo(String placa){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		float saldo = 0;
		int idUsuario = 0;
		
		conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("select idUsuarioFK from veiculo where Placa = '" + placa + "'");
			rs = pstmt.executeQuery();
			rs.next();
			idUsuario = rs.getInt("idUsuarioFK");
			pstmt = conexao.prepareStatement("select Saldo from usuario where idUsuario = '" + idUsuario + "'");
			rs = pstmt.executeQuery();
			rs.next();
			saldo = rs.getFloat("Saldo");
		} catch (SQLException e) {
			System.out.println("ERROR: " + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		
		return saldo;
	}
	
	public ArrayList<Log> consultarHistorico(String idUsuario){
		ArrayList<Log> logs = new ArrayList<Log>();
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("select * from log where idUsuarioFK = '" + idUsuario + "'");
			rs = pstmt.executeQuery();
			Log log = null;
			while(rs.next()){
				log = new Log();
				ResultSet rs2 = null;
				log.setData(rs.getString("data"));
				log.setIdUsuario(rs.getInt("idUsuarioFK"));
				log.setDescricao(rs.getString("descricao"));
				log.setValorRecarga(rs.getFloat("valorRecarga"));
				log.setIdVeiculo(rs.getInt("idVeiculo"));
				pstmt = conexao.prepareStatement("select Placa from veiculo where idVeiculo = '" + log.getIdVeiculo() + "'");
				rs2 = pstmt.executeQuery();
				if(rs2.next())
					log.setPlaca(rs2.getString("Placa"));
				logs.add(log);
				
				System.out.println("Conteudo do item: " + log.getData()
									+ "\n" + log.getIdUsuario() 
									+ "\n" + log.getDescricao()
									+ "\n" + log.getValorRecarga()
									+ "\n" + log.getIdVeiculo()
									+ "\n" + log.getPlaca());
			}
			log = new Log();
			log.setData("0");
			log.setIdUsuario(0);
			log.setDescricao("0");
			log.setValorRecarga(0);
			log.setIdVeiculo(0);
			log.setPlaca("0");
			logs.add(log);
			System.out.println("Add outro.");
		} catch (SQLException e) {
			System.out.println("ERROR: " + e);
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		return logs;
	}
	
	
}
