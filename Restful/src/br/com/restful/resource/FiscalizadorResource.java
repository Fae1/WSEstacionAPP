package br.com.restful.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.restful.controller.FiscalizadorController;
import br.com.restful.model.Fiscalizador;

/**
 * 
 * Classe responsavel po conter os metodos rest de acesso ao webservice
 * 
 * @author Admin
 *
 */

@Path("/fiscalizador")


public class FiscalizadorResource {
	
	@GET
	@Path("/logarFiscal/{email}/{senha}")
	@Produces("application/json")
	
	public Fiscalizador logarFiscal(@PathParam("email") String email, @PathParam("senha") String senha){
		System.out.println("App requisitou.");
		System.out.println(email + senha);
		Fiscalizador fiscal = null;
		fiscal = new FiscalizadorController().logarFiscalizador(email, senha);
		
	    if (fiscal != null){
	    	System.out.println("Fiscal logado.");
	    }
	    else{
	    	System.out.println("Senha ou email de usuario incorretos.");
	    }
	    return fiscal;
	}
	
	@GET
	@Path("/fiscalizarVeiculo/"
			+ "{placa}")
	@Produces("application/json")
	
	public String fiscalizarVeiculo(@PathParam("placa") String placa) 
			{
		System.out.println("Fiscalizar Veiculo: " + placa);
		return new FiscalizadorController().fiscalizarVeiculo(placa);
	}
	
}
