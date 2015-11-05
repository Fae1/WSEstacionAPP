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
}
