package br.com.restful.controller;

import java.util.ArrayList;

import br.com.restful.dao.UsuarioDAO;
import br.com.restful.model.Usuario;

/**
 * Classe responsavel por ser o controlador entre o resource e a camada DAO
 * 
 * 
 * 
 * @author Admin
 *
 */
public class UsuarioController {
	public ArrayList<Usuario> listarTodos(){
		return UsuarioDAO.getInstance().listarTodos();
	}
	
	public Usuario logarUsuario(String email, String senha){
		return UsuarioDAO.getInstance().logarUsuario(email, senha);
	}
	
	public Usuario cadastrarUsuario(String nome, String sobrenome, String email, String cpf, String cnh, 
			String dataNasc, String celular, String sexo, String senha){
		return UsuarioDAO.getInstance().cadastrarUsuario(nome, sobrenome, email, cpf, cnh, dataNasc, celular, sexo, senha);
	}
	
	public Usuario fornecerInfo(String email){
		return UsuarioDAO.getInstance().fornecerInfo(email);
	}
}
