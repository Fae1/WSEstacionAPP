package br.com.restful.controller;

import br.com.restful.dao.FiscalizadorDAO;
import br.com.restful.model.Fiscalizador;

/**
 * Classe responsavel por ser o controlador entre o resource e a camada DAO
 * 
 * 
 * 
 * @author Admin
 *
 */
public class FiscalizadorController {
	public Fiscalizador logarFiscalizador(String email, String senha){
		return FiscalizadorDAO.getInstance().logarFiscalizador(email, senha);
	}
	
	public String fiscalizarVeiculo(String placa){
		return FiscalizadorDAO.getInstance().fiscalizarVeiculo(placa);
	}
}
