package br.com.fintech.system.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import br.com.fintech.system.dao.DespesaDAO;
import br.com.fintech.system.model.Despesa;
import br.com.fintech.system.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/despesa")
public class DespesaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DespesaDAO despesaDAO;
	
	public void init() {
		this.despesaDAO = new DespesaDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//final int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        
		//final int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		//final Usuario usuario = new Usuario();
		//usuario.setIdUsuario(idUsuario);
		final List<Despesa> despesas = despesaDAO.listarDespesas(usuario);
		if (despesas.isEmpty()) {
			response.getWriter().append("Não existe despesas cadastradas");	
		} else {			
			response.getWriter().append("Despesa: ").append(despesas.get(0).getDescricaoDespesa());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Despesa despesa = getDespesaFromRequest(request);
		despesaDAO.incluirDespesa(despesa);
		response.getWriter().append("Despesa incluida com sucesso... ");
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Despesa despesa = getDespesaFromRequest(request);
		despesaDAO.alterarDespesa(despesa);
		response.getWriter().append("Despesa alterada com sucesso... ");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int idTransacao = Integer.parseInt(request.getParameter("idTransacao"));
		despesaDAO.excluirDespesa(idTransacao);
		response.getWriter().append("Despesa excluída com sucesso... ");
	}
	
	private Despesa getDespesaFromRequest(HttpServletRequest request) {
		final int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		final BigDecimal valorDespesa = new BigDecimal(request.getParameter("valor"));
		final LocalDate dataDespesa = Date.valueOf(request.getParameter("data")).toLocalDate();
		final String descricaoDespesa = request.getParameter("descricaoDespesa");
		final String nomeCategoria = request.getParameter("nomeCategoria");
		final String nomeSubCategoria = request.getParameter("nomeSubCategoria");
		Despesa despesa;
		if (request.getParameter("idTransacao") != null) {
			final int idTransacao = Integer.parseInt(request.getParameter("idTransacao"));
			despesa= new Despesa(idTransacao, idUsuario, valorDespesa, dataDespesa);
		} else { 
			despesa= new Despesa(idUsuario, valorDespesa, dataDespesa);
		}
		despesa.setDescricaoDespesa(descricaoDespesa);
		despesa.setNomeCategoria(nomeCategoria);
		despesa.setNomeSubCategoria(nomeSubCategoria);
		return despesa;
	}
}
