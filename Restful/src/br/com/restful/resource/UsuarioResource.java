package br.com.restful.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.restful.controller.UsuarioController;
import br.com.restful.model.Log;
import br.com.restful.model.Usuario;

/**
 * 
 * Classe responsavel po conter os metodos rest de acesso ao webservice
 * 
 * @author Admin
 *
 */

@Path("/usuario")

public class UsuarioResource {
	/**
	 * 
	 * Metodo responsavel por fazer chamada ao controller
	 * 
	 * @return ArrayList<Usuario>
	 */

	
	@GET
	@Path("/listarTodos")
	@Produces("application/json")
	
	public ArrayList<Usuario> listarTodos(){
		return new UsuarioController().listarTodos();
	}
	
	@GET
	@Path("/logarUsuario/{email}/{senha}")
	@Produces("application/json")
	
	public Usuario logarUsuario(@PathParam("email") String email, @PathParam("senha") String senha){
		System.out.println("App requisitou.");
		System.out.println(email + senha);
		Usuario usuarioLogado = null;
		usuarioLogado = new UsuarioController().logarUsuario(email, senha);
		
	    if (usuarioLogado != null){
	    	System.out.println("Bichão memo.");
	    }
	    else{
	    	System.out.println("Senha ou email de usuario incorretos.");
	    }
	    return usuarioLogado;
	}
	
	@GET
	@Path("/inserirCredito/{id}/{valor}")
	@Produces("application/json")
	
	public String inserirCredito(@PathParam("id") String id, @PathParam("valor") String valor){
		String retorno;
		retorno = new UsuarioController().inserirCredito(id, valor);
	    return retorno;
	}
	
	@GET
	@Path("/cadastrarUsuario/"
			+ "{nome}/"
			+ "{sobrenome}/"
			+ "{email}/"
			+ "{cpf}/"
			+ "{cnh}/"
			+ "{dataNasc}/"
			+ "{celular}/"
			+ "{sexo}/"
			+ "{senha}")
	@Produces("application/json")

	
	public Usuario cadastrarUsuario(@PathParam("nome") String nome, 
			@PathParam("sobrenome") String sobrenome, 
			@PathParam("email") String email,
			@PathParam("cpf") String cpf, 
			@PathParam("cnh") String cnh,
			@PathParam("dataNasc") String dataNasc, 
			@PathParam("celular") String celular,
			@PathParam("sexo") String sexo,
			@PathParam("senha") String senha) 
			{
		System.out.println("Novo cadastro de user");
		return new UsuarioController().cadastrarUsuario(nome, sobrenome, email, cpf, cnh, dataNasc, celular, sexo, senha);
	}
	
	@GET
	@Path("/fornecerInfo/{email}")
	@Produces("application/json")
	
	public Usuario fornecerInfo(@PathParam("email") String email){
		Usuario usuario = null;
		usuario = new UsuarioController().fornecerInfo(email);
		return usuario;
	}
	
	@GET
	@Path("/consultarHistorico/"
			+ "{idUsuario}")
	@Produces("application/json")
	
	public ArrayList<Log> consultarHistorico(@PathParam("idUsuario") String idUsuario) 
			{
		System.out.println("Solicitação de historico.");
		return new UsuarioController().consultarHistorico(idUsuario);
	}
	
	
	/*@GET
	@Path("/fornecerInfo")
	@Produces("application/json")
	
	public Usuario fazerLogoff(){
		Usuario usuario = null;
		if(session.equals(true)){
			session.invalidate();;
		}
		return usuario;
	}*/
	
}
