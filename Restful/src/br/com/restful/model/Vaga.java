package br.com.restful.model;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * Classe responsável por conter os atributos de nossa entidade vaga
 * 
 * @author Rafael Jesus
 * @since 09/09/2015 17:25:32
 * @version 1.0
 *
 */
@XmlRootElement
public final class Vaga {
	
	
	private Integer idVaga;
	private String local;
	private Integer qtdVagas;
	private float valorHora;
	private float tempoUtiliza;
	private Date inicioUtiliza;
	private Date fimUtiliza;
	public Integer getIdVaga() {
		return idVaga;
	}
	public void setIdVaga(Integer idVaga) {
		this.idVaga = idVaga;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public Integer getQtdVagas() {
		return qtdVagas;
	}
	public void setQtdVagas(Integer qtdVagas) {
		this.qtdVagas = qtdVagas;
	}
	public float getValorHora() {
		return valorHora;
	}
	public void setValorHora(float f) {
		this.valorHora = f;
	}
	public float getTempoUtiliza() {
		return tempoUtiliza;
	}
	public void setTempoUtiliza(float tempoUtiliza) {
		this.tempoUtiliza = tempoUtiliza;
	}
	public Date getInicioUtiliza() {
		return inicioUtiliza;
	}
	public void setInicioUtiliza(Date inicioUtiliza) {
		this.inicioUtiliza = inicioUtiliza;
	}
	public Date getFimUtiliza() {
		return fimUtiliza;
	}
	public void setFimUtiliza(Date fimUtiliza) {
		this.fimUtiliza = fimUtiliza;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fimUtiliza == null) ? 0 : fimUtiliza.hashCode());
		result = prime * result + ((idVaga == null) ? 0 : idVaga.hashCode());
		result = prime * result
				+ ((inicioUtiliza == null) ? 0 : inicioUtiliza.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result
				+ ((qtdVagas == null) ? 0 : qtdVagas.hashCode());
		result = prime * result + Float.floatToIntBits(tempoUtiliza);
		result = prime * result + Float.floatToIntBits(valorHora);
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
		Vaga other = (Vaga) obj;
		if (fimUtiliza == null) {
			if (other.fimUtiliza != null)
				return false;
		} else if (!fimUtiliza.equals(other.fimUtiliza))
			return false;
		if (idVaga == null) {
			if (other.idVaga != null)
				return false;
		} else if (!idVaga.equals(other.idVaga))
			return false;
		if (inicioUtiliza == null) {
			if (other.inicioUtiliza != null)
				return false;
		} else if (!inicioUtiliza.equals(other.inicioUtiliza))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (qtdVagas == null) {
			if (other.qtdVagas != null)
				return false;
		} else if (!qtdVagas.equals(other.qtdVagas))
			return false;
		if (Float.floatToIntBits(tempoUtiliza) != Float
				.floatToIntBits(other.tempoUtiliza))
			return false;
		if (Float.floatToIntBits(valorHora) != Float
				.floatToIntBits(other.valorHora))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Vaga [idVaga=" + idVaga + ", local=" + local + ", qtdVagas="
				+ qtdVagas + ", valorHora=" + valorHora + ", tempoUtiliza="
				+ tempoUtiliza + ", inicioUtiliza=" + inicioUtiliza
				+ ", fimUtiliza=" + fimUtiliza + "]";
	}
	
	
}
