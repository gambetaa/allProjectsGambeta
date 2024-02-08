package br.com.fintech.system.model;

public class Cidade {
	
	private int cdCidade;
	private Estado estado;
	private String nmCidade;
	
	public int getCdCidade() {
		return cdCidade;
	}
	public void setCdCidade(int cdCidade) {
		this.cdCidade = cdCidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public String getNmCidade() {
		return nmCidade;
	}
	public void setNmCidade(String nmCidade) {
		this.nmCidade = nmCidade;
	}
}
