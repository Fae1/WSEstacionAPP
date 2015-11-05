package br.com.restful.model;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * Classe responsável por conter os atributos de nossa entidade veiculo
 * 
 * @author Rafael Jesus
 * @since 09/09/2015 17:25:32
 * @version 1.0
 *
 */
@XmlRootElement
public final class Veiculo {
	
	
	private Integer idVeiculo;
	private String modelo;
	public String getModelo() {
		return modelo;
	}



	public void setModelo(String modelo) {
		this.modelo = modelo;
	}



	private String placa;
	private String apelido;
	
	
	
	public Integer getIdVeiculo() {
		return idVeiculo;
	}



	public void setIdVeiculo(Integer idVeiculo) {
		this.idVeiculo = idVeiculo;
	}



	public String getPlaca() {
		return placa;
	}



	public void setPlaca(String placa) {
		this.placa = placa;
	}



	public String getApelido() {
		return apelido;
	}



	public void setApelido(String apelido) {
		this.apelido = apelido;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apelido == null) ? 0 : apelido.hashCode());
		result = prime * result
				+ ((idVeiculo == null) ? 0 : idVeiculo.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		if (apelido == null) {
			if (other.apelido != null)
				return false;
		} else if (!apelido.equals(other.apelido))
			return false;
		if (idVeiculo == null) {
			if (other.idVeiculo != null)
				return false;
		} else if (!idVeiculo.equals(other.idVeiculo))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Veiculo [idVeiculo=" + idVeiculo + ", placa=" + placa
				+ ", apelido=" + apelido + "]";
	}
	
}
