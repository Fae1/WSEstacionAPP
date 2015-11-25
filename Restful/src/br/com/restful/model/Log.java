package br.com.restful.model;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * Classe responsável por conter os atributos de nossa entidade log
 * 
 * @author Rafael Jesus
 * @since 09/09/2015 17:25:32
 * @version 1.0
 *
 */
@XmlRootElement
public final class Log {
	
	/*
	 * log.setData(rs.getString("data"));
				log.setIdUsuario(rs.getInt("idUsuarioFK"));
				log.setDescricao(rs.getString("descricao"));
				log.setValorRecarga(rs.getFloat("valorRecarga"));
				log.setIdVeiculo(rs.getInt("idVeiculo"));*/
	private String data;
	private int idUsuario;
	private String descricao;
	private float valorRecarga;
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	private int idVeiculo;
	private String placa;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + idUsuario;
		result = prime * result + idVeiculo;
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result + Float.floatToIntBits(valorRecarga);
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
		Log other = (Log) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idUsuario != other.idUsuario)
			return false;
		if (idVeiculo != other.idVeiculo)
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (Float.floatToIntBits(valorRecarga) != Float
				.floatToIntBits(other.valorRecarga))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Log [data=" + data + ", idUsuario=" + idUsuario
				+ ", descricao=" + descricao + ", valorRecarga=" + valorRecarga
				+ ", idVeiculo=" + idVeiculo + ", placa=" + placa + "]";
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getValorRecarga() {
		return valorRecarga;
	}
	public void setValorRecarga(float valorRecarga) {
		this.valorRecarga = valorRecarga;
	}
	public int getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdVeiculo(int idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	
}
