package br.com.restful.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.restful.controller.PagamentoController;
import br.com.restful.model.Pagamento;

/**
 * 
 * Classe responsavel po conter os metodos rest de acesso ao webservice
 * 
 * @author Admin
 *
 */

@Path("/pagamento")

public class PagamentoResource {
	
	/**
	 * 
	 * Metodo responsavel por fazer chamada ao controller
	 * 
	 * @return ArrayList<Pagamento>
	 */
	@GET
	@Path("/listarTodos")
	@Produces("application/json")
	
	public ArrayList<Pagamento> listarTodos(){
		return new PagamentoController().listarTodos();
	}
}
