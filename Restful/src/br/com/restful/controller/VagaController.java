package br.com.restful.controller;

import java.util.ArrayList;

import br.com.restful.dao.VagaDAO;
import br.com.restful.model.Vaga;

/**
 * Classe responsavel por ser o controlador entre o resource e a camada DAO
 * 
 * 
 * 
 * @author Admin
 *
 */
public class VagaController {
	public ArrayList<Vaga> listarTodos(){
		return VagaDAO.getInstance().listarTodos();
	}
}
