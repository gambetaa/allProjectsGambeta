package br.com.fintech.system.servlet;

import java.io.IOException;

import br.com.fintech.system.dao.LoginDAO;
import br.com.fintech.system.dao.UsuarioDAO;
import br.com.fintech.system.model.Login;
import br.com.fintech.system.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginDAO loginDAO;
	private UsuarioDAO usuarioDAO;
	
	public void init() {
		this.loginDAO = new LoginDAO();
		this.usuarioDAO = new UsuarioDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Login login = new Login();
		login.setEmail(request.getParameter("login"));
		login.setSenha(request.getParameter("senha"));
		final int idUsuario = loginDAO.doLogin(login);
		if (idUsuario > 0) {
			final Usuario usuario = usuarioDAO.getUsuario(idUsuario);
			HttpSession session = request.getSession();
			session.setAttribute("usuario", usuario);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user.jsp");
			dispatcher.forward(request, response);		
		} else {
			request.setAttribute("errorLogin", "Credenciais inválidas. Tente novamente.");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//			dispatcher.forward(request, response);	
			response.sendRedirect("index.jsp");	
		}
	}
	
}
