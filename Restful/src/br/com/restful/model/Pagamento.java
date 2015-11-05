package br.com.restful.model;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * Classe responsável por conter os atributos de nossa entidade pagamento
 * 
 * @author Rafael Jesus
 * @since 09/09/2015 17:25:32
 * @version 1.0
 *
 */
@XmlRootElement
public final class Pagamento {
	
	
	private Integer idPagamento;
	private Integer tipoPagamento;
	private float valor;
	
	public Integer getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}
	public Integer getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(Integer tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idPagamento == null) ? 0 : idPagamento.hashCode());
		result = prime * result
				+ ((tipoPagamento == null) ? 0 : tipoPagamento.hashCode());
		result = prime * result + Float.floatToIntBits(valor);
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
		Pagamento other = (Pagamento) obj;
		if (idPagamento == null) {
			if (other.idPagamento != null)
				return false;
		} else if (!idPagamento.equals(other.idPagamento))
			return false;
		if (tipoPagamento == null) {
			if (other.tipoPagamento != null)
				return false;
		} else if (!tipoPagamento.equals(other.tipoPagamento))
			return false;
		if (Float.floatToIntBits(valor) != Float.floatToIntBits(other.valor))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Pagamento [idPagamento=" + idPagamento + ", tipoPagamento="
				+ tipoPagamento + ", valor=" + valor + "]";
	}
}
