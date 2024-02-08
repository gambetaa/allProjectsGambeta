package br.com.fintech.system.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Receita extends Transacao {	
	
	// Declarando os atributos
	private int idReceita;
	private String descricaoReceita;
	private String nomeCategoria;
	private String nomeSubCategoria;
	
	public Receita(int idUsuario, BigDecimal valor, LocalDate data) {
		super(idUsuario, valor, data);
	}
	
	public Receita(int idTransacao, int idUsuario, BigDecimal valorReceita, LocalDate dataReceita) {
		super(idTransacao, idUsuario, valorReceita, dataReceita);
	}

	// Chamando os métodos getters e setters
	public int getIdReceita() {
		return idReceita;
	}
	
	public void setIdReceita(int idReceita) {
		this.idReceita = idReceita;
	}
	
	public String getDescricaoReceita() {
		return descricaoReceita;
	}
	
	public void setDescricaoReceita(String descricaoReceita) {
		this.descricaoReceita = descricaoReceita;
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

    public BigDecimal getValorReceita() {
        return getValor();
    }

    public LocalDate getDataReceita() {
        return getData();
    }
    
}
		

