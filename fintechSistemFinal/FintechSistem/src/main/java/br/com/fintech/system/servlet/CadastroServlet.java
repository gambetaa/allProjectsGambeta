package br.com.fintech.system.servlet;

import java.io.IOException;

import br.com.fintech.system.dao.EnderecoDAO;
import br.com.fintech.system.dao.UsuarioDAO;
import br.com.fintech.system.enumerator.TipoTelefone;
import br.com.fintech.system.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CadastroServlet() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Devolver JSP com as informações de cadastro
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String nomeSocial = request.getParameter("nomeSocial");
//		int cpf = Integer.parseInt(request.getParameter("cpf"));
		long cpf = Long.parseLong(request.getParameter("cpf"));
        int tipoTelefoneId = Integer.parseInt(request.getParameter("tipoTel"));
//		int telefone = Integer.parseInt(request.getParameter("telefone"));
        long telefone = Long.parseLong(request.getParameter("telefone"));

		String estado = request.getParameter("estado");
		String cidade = request.getParameter("cidade");
		String bairro = request.getParameter("bairro");
		String rua = request.getParameter("rua");
		int nrEndereco = Integer.parseInt(request.getParameter("nrEndereco"));
//		int cep = Integer.parseInt(request.getParameter("cep"));
		long cep = Long.parseLong(request.getParameter("cep"));

		System.out.println("Dados recebidos: ");
		System.out.println("Email: " + email);
		System.out.println("Senha: " + senha);
		System.out.println("Nome: " + nome);
		System.out.println("Nome Social: " + nomeSocial);
		System.out.println("CPF: " + cpf);
		System.out.println("Tipo de Telefone ID: " + tipoTelefoneId);
		System.out.println("Telefone: " + telefone);

		
		// Cadastrar usuário
        int cdUsuario = usuarioDAO.cadastrarUsuario(email, senha, nome, nomeSocial, cpf, tipoTelefoneId, telefone);

        System.out.println("Resultado do cadastro de usuário: " + cdUsuario);
        
        // Cadastrar endereço
        enderecoDAO.cadastrarEndereco(cdUsuario, estado, cidade, bairro, rua, nrEndereco, cep);
       
        System.out.println("Cadastro de endereço concluído para o usuário com ID: " + cdUsuario);

		
		// Devolver para o usuário - tela de login
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}
