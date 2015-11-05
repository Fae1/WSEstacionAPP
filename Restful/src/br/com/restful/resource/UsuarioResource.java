package br.com.restful.resource;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import br.com.restful.controller.UsuarioController;
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
	@Context
	private HttpServletRequest request;
	private HttpSession session = null;
	/**
	 * 
	 * Metodo responsavel por fazer chamada ao controller
	 * 
	 * @return ArrayList<Usuario>
	 */
	
	public boolean verificarSessao(){
		return false;
		
	}
	
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
	    	session = request.getSession(true);
	    	session.setAttribute("email", usuarioLogado.getEmail());
	    	
	    }
	    else{
	    	System.out.println("Senha ou email de usuario incorretos.");
	    }
	    return usuarioLogado;
	}
	
	
	
	@POST
	@Path("/cadastrarUsuario")
	@Produces("aplication/json")
	@Consumes(value = "aplication/json")
	
	public Usuario cadastrarUsuario(@FormParam("nome") String nome, @FormParam("sobrenome") String sobrenome, @FormParam("email") String email,
			@FormParam("cpf") String cpf, @FormParam("cnh") String cnh,@FormParam("dataNasc") String dataNasc, @FormParam("celular") String celular,
			@FormParam("sexo") String sexo, @FormParam("senha")String senha){
		
		return new UsuarioController().cadastrarUsuario(nome, sobrenome, email, cpf, cnh, dataNasc, celular, sexo, senha);
	}
	
	@GET
	@Path("/fornecerInfo")
	@Produces("application/json")
	
	public Usuario fornecerInfo(){
		String emailLogado = null;
		Usuario usuario = null;
		if(session.equals(true)){
			emailLogado = (String) session.getAttribute("email");
			usuario = new UsuarioController().fornecerInfo(emailLogado);
		}
		return usuario;
	}
	
	@GET
	@Path("/fornecerInfo")
	@Produces("application/json")
	
	public Usuario fazerLogoff(){
		Usuario usuario = null;
		if(session.equals(true)){
			session.invalidate();;
		}
		return usuario;
	}
	
}
