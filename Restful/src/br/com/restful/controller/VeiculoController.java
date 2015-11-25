package br.com.restful.controller;

import java.util.ArrayList;

import br.com.restful.dao.VeiculoDAO;
import br.com.restful.model.Veiculo;

/**
 * Classe responsavel por ser o controlador entre o resource e a camada DAO
 * 
 * 
 * 
 * @author Admin
 *
 */
public class VeiculoController {
	public ArrayList<Veiculo> listarTodos(){
		return VeiculoDAO.getInstance().listarTodos();
	}
	
	public Veiculo cadastrarVeiculo(String carro, String placa, String apelido, String idUsuario){
		return VeiculoDAO.getInstance().cadastrarVeiculo(carro, placa, apelido, idUsuario);
	}
	
	public ArrayList<Veiculo> listarMeusVeiculos(String idUsuario) {
		return VeiculoDAO.getInstance().listarMeusVeiculos(idUsuario);
	}
	
	public String apagarVeiculo(String idVeiculo){
		return VeiculoDAO.getInstance().apagarVeiculo(idVeiculo);
	}

	public String estacionar(String placa, String horasDeUso){
		return VeiculoDAO.getInstance().estacionar(placa, horasDeUso);
	}
	public String pararCobranca(String placa){
		return VeiculoDAO.getInstance().pararCobranca(placa);
	}
	
}
