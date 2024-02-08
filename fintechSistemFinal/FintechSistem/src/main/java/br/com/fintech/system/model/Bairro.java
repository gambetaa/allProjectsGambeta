package br.com.fintech.system.model;

public class Bairro {
	private int cdBairro;
	private Cidade cidade;
	private String nmBairro;
	
	public int getCdBairro() {
		return cdBairro;
	}
	public void setCdBairro(int cdBairro) {
		this.cdBairro = cdBairro;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public String getNmBairro() {
		return nmBairro;
	}
	public void setNmBairro(String nmBairro) {
		this.nmBairro = nmBairro;
	}
	
	
}
