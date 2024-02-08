package br.com.fintech.system.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Despesa extends Transacao {

	private int idDespesa;
	private String descricaoDespesa;
	private String nomeCategoria;
	private String nomeSubCategoria;
	
	public Despesa(int idUsuario, BigDecimal valor, LocalDate data) {
		super(idUsuario, valor, data);
	}
	
	public Despesa(int idTransacao, int idUsuario, BigDecimal valor, LocalDate data) {
		super(idTransacao, idUsuario, valor, data);
	}

	public int getIdDespesa() {
		return idDespesa;
	}

	public void setIdDespesa(int idDespesa) {
		this.idDespesa = idDespesa;
	}

	public String getDescricaoDespesa() {
		return descricaoDespesa;
	}

	public void setDescricaoDespesa(String descricaoDespesa) {
		this.descricaoDespesa = descricaoDespesa;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getNomeSubCategoria() {
		return nomeSubCategoria;
	}

	public void setNomeSubCategoria(String nomeSubCategoria) {
		this.nomeSubCategoria = nomeSubCategoria;
	}

    public BigDecimal getValorDespesa() {
        return getValor();
    }

    public LocalDate getDataDespesa() {
        return getData();
    }

}
