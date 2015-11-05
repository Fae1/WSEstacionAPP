package br.com.restful.controller;

import java.util.ArrayList;

import br.com.restful.dao.PagamentoDAO;
import br.com.restful.model.Pagamento;

/**
 * Classe responsavel por ser o controlador entre o resource e a camada DAO
 * 
 * 
 * 
 * @author Admin
 *
 */
public class PagamentoController {
	public ArrayList<Pagamento> listarTodos(){
		return PagamentoDAO.getInstance().listarTodos();
	}
}
