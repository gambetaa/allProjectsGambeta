package br.com.fintech.system.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import br.com.fintech.system.dao.ReceitaDAO;
import br.com.fintech.system.model.Receita;
import br.com.fintech.system.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/receita")
public class ReceitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReceitaDAO receitaDAO;
	
	public void init() {
		this.receitaDAO = new ReceitaDAO();
	}

	// Método GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		final Usuario usuario = new Usuario();
		usuario.setIdUsuario(idUsuario);
		final List<Receita> receitas = receitaDAO.listarReceitas(usuario);
		if (receitas.isEmpty()) {
			response.getWriter().append("Não existe receitas cadastradas");	
		} else {			
			response.getWriter().append("Receita: ").append(receitas.get(0).getDescricaoReceita());
		}
	}
	
	// Método POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Receita receita = getReceitaFromRequest(request);
		receitaDAO.incluirReceita(receita);
		response.getWriter().append("Receita incluida com sucesso... ");
	}
	
	// Método PUT
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Receita receita = getReceitaFromRequest(request);
		receitaDAO.alterarReceita(receita);
		response.getWriter().append("Receita alterada com sucesso... ");
	}
	
	// Método DELETE
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int idTransacao = Integer.parseInt(request.getParameter("idTransacao"));
		receitaDAO.excluirReceita(idTransacao);
		response.getWriter().append("Receita excluída com sucesso... ");
	}
	
	private Receita getReceitaFromRequest(HttpServletRequest request) {
		final int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		final BigDecimal valorReceita = new BigDecimal(request.getParameter("valor"));
		final LocalDate dataReceita = Date.valueOf(request.getParameter("data")).toLocalDate();
		final String descricaoReceita = request.getParameter("descricaoReceita");
		final String nomeCategoria = request.getParameter("nomeCategoria");
		final String nomeSubCategoria = request.getParameter("nomeSubCategoria");
		Receita receita;
		if (request.getParameter("idTransacao") != null) {
			final int idTransacao = Integer.parseInt(request.getParameter("idTransacao"));
			receita= new Receita(idTransacao, idUsuario, valorReceita, dataReceita);
		} else { 
			receita= new Receita(idUsuario, valorReceita, dataReceita);
		}
		receita.setDescricaoReceita(descricaoReceita);
		receita.setNomeCategoria(nomeCategoria);
		receita.setNomeSubCategoria(nomeSubCategoria);
		return receita;
	}
	
}