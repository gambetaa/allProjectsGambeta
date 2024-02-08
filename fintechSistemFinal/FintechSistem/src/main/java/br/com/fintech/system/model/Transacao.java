package br.com.fintech.system.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transacao {

	private int idTransacao;
	private int idUsuario;
	protected BigDecimal valor;
	protected LocalDate data;
    private BigDecimal saldo;

    
	public Transacao(int idUsuario, BigDecimal valor, LocalDate data) {        
        this.valor = valor;
        this.data = data;
        this.idUsuario = idUsuario;
        this.saldo = BigDecimal.ZERO;
    }
	
	public Transacao(int idTransacao, int idUsuario, BigDecimal valor, LocalDate data) {
		this(idUsuario, valor, data);
		this.idTransacao = idTransacao;
    }
	
	public int getIdTransacao() {
		return this.idTransacao;
	}
	
	public int getIdUsuario() {
		return this.idUsuario;
	}
		
    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }
    
    public BigDecimal getSaldo() {
        return saldo;
    }
 
}

