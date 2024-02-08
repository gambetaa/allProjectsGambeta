package br.com.fintech.system.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dashboard {
	// Atributos
	
	private List<Receita> receitas; // Lista de Receitas
    private List<Despesa> despesas; // Lista de Despesas

	// Método construtor
    
    public Dashboard() {
        this.receitas = new ArrayList<>();
        this.despesas = new ArrayList<>();
    }


	// Metodos get - não preciso
	
	// Métodos set
	
    public void registrarReceita(Receita receita) {
        receitas.add(receita); // Adiciona uma Receita à lista
    }

    public void registrarDespesa(Despesa despesa) {
        despesas.add(despesa); // Adiciona uma Despesa à lista
    }
    
	// Método da lógica da Classe
    
    public void mostrarResumo(LocalDate dataInicio, LocalDate dataFim) {
        System.out.println("Construindo o Dash");
    }
}

