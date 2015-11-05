package br.com.restful.controller;

import java.util.ArrayList;

import br.com.restful.dao.CartaoDAO;
import br.com.restful.model.Cartao;

/**
 * Classe responsavel por ser o controlador entre o resource e a camada DAO
 * 
 * 
 * 
 * @author Admin
 *
 */
public class CartaoController {
	public ArrayList<Cartao> listarTodos(){
		return CartaoDAO.getInstance().listarTodos();
	}
}
