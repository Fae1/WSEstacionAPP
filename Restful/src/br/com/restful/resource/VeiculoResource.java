package br.com.restful.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.restful.controller.VeiculoController;
import br.com.restful.model.Veiculo;

/**
 * 
 * Classe responsavel po conter os metodos rest de acesso ao webservice
 * 
 * @author Admin
 *
 */

@Path("/veiculo")

public class VeiculoResource {
	
	/**
	 * 
	 * Metodo responsavel por fazer chamada ao controller
	 * 
	 * @return ArrayList<Veiculo>
	 */
	@GET
	@Path("/listarTodos")
	@Produces("application/json")
	
	public ArrayList<Veiculo> listarTodos(){
		return new VeiculoController().listarTodos();
	}
	
}
