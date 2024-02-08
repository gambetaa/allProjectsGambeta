package br.com.fintech.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fintech.system.model.Login;

public class LoginDAO {
	private static final String SQL_SELECT_LOGIN = "SELECT * FROM t_login WHERE email_login=? AND senha_login=?";
	
	public int doLogin(Login login) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int idUsuario = 0;
		try {
			conn = ConexaoDAO.conectar();
			stmt = conn.prepareStatement(SQL_SELECT_LOGIN);
            stmt.setString(1, login.getEmail());
            stmt.setString(2, login.getSenha());
			rs = stmt.executeQuery();
			while (rs.next()) {
				idUsuario = rs.getInt("t_usuario_cd_usuario");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar login: " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			ConexaoDAO.desconectar(conn);
		}
		return idUsuario;
	}

}
