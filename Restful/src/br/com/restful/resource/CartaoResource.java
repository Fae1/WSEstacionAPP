package br.com.restful.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.restful.controller.CartaoController;
import br.com.restful.model.Cartao;

/**
 * 
 * Classe responsavel po conter os metodos rest de acesso ao webservice
 * 
 * @author Admin
 *
 */

@Path("/cartao")

public class CartaoResource {
	
	/**
	 * 
	 * Metodo responsavel por fazer chamada ao controller
	 * 
	 * @return ArrayList<Cartao>
	 */
	@GET
	@Path("/listarTodos")
	@Produces("application/json")
	
	public ArrayList<Cartao> listarTodos(){
		return new CartaoController().listarTodos();
	}
}
