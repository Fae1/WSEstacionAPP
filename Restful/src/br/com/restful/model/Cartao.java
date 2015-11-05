package br.com.restful.model;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * Classe responsável por conter os atributos de nossa entidade cartao
 * 
 * @author Rafael Jesus
 * @since 09/09/2015 17:25:32
 * @version 1.0
 *
 */
@XmlRootElement
public final class Cartao {
	
	private Integer idCartao;
	private String nomeTitular;
	private String numCartao;
	private String codSeg;
	private Integer validadeMes;
	private Integer validadeAno;
	
	public Integer getIdCartao() {
		return idCartao;
	}
	public void setIdCartao(Integer idCartao) {
		this.idCartao = idCartao;
	}
	public String getNomeTitular() {
		return nomeTitular;
	}
	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}
	public String getNumCartao() {
		return numCartao;
	}
	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}
	public String getCodSeg() {
		return codSeg;
	}
	public void setCodSeg(String codSeg) {
		this.codSeg = codSeg;
	}
	public Integer getValidadeMes() {
		return validadeMes;
	}
	public void setValidadeMes(Integer validadeMes) {
		this.validadeMes = validadeMes;
	}
	public Integer getValidadeAno() {
		return validadeAno;
	}
	public void setValidadeAno(Integer validadeAno) {
		this.validadeAno = validadeAno;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codSeg == null) ? 0 : codSeg.hashCode());
		result = prime * result
				+ ((idCartao == null) ? 0 : idCartao.hashCode());
		result = prime * result
				+ ((nomeTitular == null) ? 0 : nomeTitular.hashCode());
		result = prime * result
				+ ((numCartao == null) ? 0 : numCartao.hashCode());
		result = prime * result
				+ ((validadeAno == null) ? 0 : validadeAno.hashCode());
		result = prime * result
				+ ((validadeMes == null) ? 0 : validadeMes.hashCode());
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
		Cartao other = (Cartao) obj;
		if (codSeg == null) {
			if (other.codSeg != null)
				return false;
		} else if (!codSeg.equals(other.codSeg))
			return false;
		if (idCartao == null) {
			if (other.idCartao != null)
				return false;
		} else if (!idCartao.equals(other.idCartao))
			return false;
		if (nomeTitular == null) {
			if (other.nomeTitular != null)
				return false;
		} else if (!nomeTitular.equals(other.nomeTitular))
			return false;
		if (numCartao == null) {
			if (other.numCartao != null)
				return false;
		} else if (!numCartao.equals(other.numCartao))
			return false;
		if (validadeAno == null) {
			if (other.validadeAno != null)
				return false;
		} else if (!validadeAno.equals(other.validadeAno))
			return false;
		if (validadeMes == null) {
			if (other.validadeMes != null)
				return false;
		} else if (!validadeMes.equals(other.validadeMes))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cartao [idCartao=" + idCartao + ", nomeTitular=" + nomeTitular
				+ ", numCartao=" + numCartao + ", codSeg=" + codSeg
				+ ", validadeMes=" + validadeMes + ", validadeAno="
				+ validadeAno + "]";
	}
}
