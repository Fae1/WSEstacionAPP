package br.com.restful.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.restful.controller.VagaController;
import br.com.restful.model.Vaga;

/**
 * 
 * Classe responsavel po conter os metodos rest de acesso ao webservice
 * 
 * @author Admin
 *
 */

@Path("/vaga")

public class VagaResource {
	
	/**
	 * 
	 * Metodo responsavel por fazer chamada ao controller
	 * 
	 * @return ArrayList<Vaga>
	 */
	@GET
	@Path("/listarTodos")
	@Produces("application/json")
	
	public ArrayList<Vaga> listarTodos(){
		return new VagaController().listarTodos();
	}
}
