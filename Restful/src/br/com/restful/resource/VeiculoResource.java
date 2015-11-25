package br.com.restful.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	@GET
	@Path("/cadastrarVeiculo/"
			+ "{carro}/"
			+ "{placa}/"
			+ "{apelido}/"
			+ "{idUsuario}")
	@Produces("application/json")

	
	public Veiculo cadastrarVeiculo(@PathParam("carro") String carro, 
			@PathParam("placa") String placa, 
			@PathParam("apelido") String apelido,
			@PathParam("idUsuario") String idUsuario) 
			{
		System.out.println("Novo cadastro de veiculo, id: " + idUsuario);
		return new VeiculoController().cadastrarVeiculo(carro, placa, apelido, idUsuario);
	}
	
	@GET
	@Path("/listarMeusVeiculos/"
			+ "{idUsuario}")
	@Produces("application/json")

	
	public ArrayList<Veiculo> listarMeusVeiculos(@PathParam("idUsuario") String idUsuario) 
			{
		System.out.println("Solicitação de lista de veiculo");
		return new VeiculoController().listarMeusVeiculos(idUsuario);
	}
	
	@GET
	@Path("/apagarVeiculo/"
			+ "{idVeiculo}")
	@Produces("application/json")

	
	public String apagarVeiculo(@PathParam("idVeiculo") String idVeiculo) 
			{
		System.out.println("Solicitação de lista de veiculo");
		return new VeiculoController().apagarVeiculo(idVeiculo);
	}
	
	@GET
	@Path("/estacionar/"
			+ "{placa}/"
			+ "{horasDeUso}")
	@Produces("application/json")

	
	public String estacionar(@PathParam("placa") String placa, @PathParam("horasDeUso") String horasDeUso) 
			{
		System.out.println("Solicitação para estacionar veiculo da placa: " + placa + "por " + horasDeUso + "horas");
		return new VeiculoController().estacionar(placa, horasDeUso); 
	}
	
	@GET
	@Path("/pararCobranca/"
			+ "{placa}")
	@Produces("application/json")

	
	public String pararCobranca(@PathParam("placa") String placa) 
			{
		System.out.println("Parar cobrança do veiculo: " + placa);
		return new VeiculoController().pararCobranca(placa); 
	}
	
}
