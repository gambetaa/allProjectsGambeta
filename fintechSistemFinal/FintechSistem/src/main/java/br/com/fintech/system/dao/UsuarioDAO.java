package br.com.fintech.system.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fintech.system.enumerator.TipoTelefone;
import br.com.fintech.system.model.Usuario;

public class UsuarioDAO {
	private static final String SQL_SELECT_USUARIO = "SELECT * FROM t_usuario WHERE cd_usuario=?";
	private static final String SQL_SELECT_LOGIN_SENHA = "SELECT * FROM t_login WHERE email_login=? AND senha_login=?";

	public Usuario getUsuario(int idUsuario) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Usuario usuario = null;
		try {
			conn = ConexaoDAO.conectar();
			stmt = conn.prepareStatement(SQL_SELECT_USUARIO);
            stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("cd_usuario"));
				usuario.setTipoTelefone(TipoTelefone.fromId(rs.getInt("t_tipo_tel_cd_tipo_tel")));
				usuario.setTelefone(rs.getString("nr_telefone"));
				usuario.setNomeUsuario(rs.getString("nm_usuario"));
				usuario.setNomeSocial(rs.getString("nm_social"));
				usuario.setCpf(rs.getString("nr_cpf"));
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
		return usuario;
	}

	public boolean verificarCredenciais(String login, String senha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoDAO.conectar();
            stmt = conn.prepareStatement(SQL_SELECT_LOGIN_SENHA);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            // Se o ResultSet tiver alguma linha, significa que já existe um usuário com essas credenciais
            return rs.next();

        } catch (SQLException e) {
            System.err.println("Erro ao verificar credenciais: " + e.getMessage());
            return false;
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
    }

	public int cadastrarUsuario(String login, String senha, String nome, String nomeSocial, long cpf, int tipoTelefoneId, long telefone) {
	    Connection conn = null;
	    PreparedStatement stmtUsuario = null;
	    PreparedStatement stmtLogin = null;

	    try {
	        conn = ConexaoDAO.conectar();

	        // Inserir dados na tabela t_usuario
	        String sqlTUsuario = "INSERT INTO t_usuario (cd_usuario, t_tipo_tel_cd_tipo_tel, nr_telefone, nm_usuario, nm_social, nr_cpf) VALUES (seq_usuario.nextval, ?, ?, ?, ?, ?)";

	        stmtUsuario = conn.prepareStatement(sqlTUsuario, new String[] { "cd_usuario" });

	        // Preencher os valores necessários, exceto o cd_usuario, que será gerado automaticamente
	        stmtUsuario.setInt(1, tipoTelefoneId);
	        stmtUsuario.setBigDecimal(2, new BigDecimal(telefone));
	        stmtUsuario.setString(3, nome);
	        stmtUsuario.setString(4, nomeSocial);
	        stmtUsuario.setLong(5, cpf);

	        // Executar a inserção na tabela t_usuario
	        int rowsInsertedUsuario = stmtUsuario.executeUpdate();

	        if (rowsInsertedUsuario > 0) {
	            // Obtemos o valor gerado (cd_usuario) usando a sequência seq_usuario
	            String sqlSelectCdUsuario = "SELECT seq_usuario.CURRVAL FROM DUAL";
	            stmtLogin = conn.prepareStatement(sqlSelectCdUsuario);
	            ResultSet generatedKeys = stmtLogin.executeQuery();

	            if (generatedKeys.next()) {
	                int cdUsuario = generatedKeys.getInt(1);

	                // Usamos o valor gerado como t_usuario_cd_usuario para a tabela t_login
	                String sqlTLogin = "INSERT INTO t_login (t_usuario_cd_usuario, email_login, senha_login) VALUES (?, ?, ?)";
	                stmtLogin = conn.prepareStatement(sqlTLogin);
	                stmtLogin.setInt(1, cdUsuario);
	                stmtLogin.setString(2, login);
	                stmtLogin.setString(3, senha);
	                int rowsInsertedLogin = stmtLogin.executeUpdate();

	                // Retorna true se a inserção na tabela t_login foi bem-sucedida
//	                return rowsInsertedLogin > 0;
	                return cdUsuario;
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
	    } finally {
	        if (stmtUsuario != null) {
	            try {
	                stmtUsuario.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (stmtLogin != null) {
	            try {
	                stmtLogin.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        ConexaoDAO.desconectar(conn);
	    }
	    return 0;
	}
//	public boolean cadastrarUsuario(String login, String senha, String nome, String nomeSocial, String cpf, int tipoTelefoneId, String telefone) {	    
//		Connection conn = null;
//		PreparedStatement stmtUsuario = null;
//	    PreparedStatement stmtLogin = null;
//	    
//	    Usuario novoUsuario = new Usuario();
//
//	    try {
//	        conn = ConexaoDAO.conectar();
//	        
//	        // Inserir dados na tabela t_usuario
//			String sqlTUsuario = "INSERT INTO t_usuario (cd_usuario, t_tipo_tel_cd_tipo_tel, nr_telefone, nm_usuario, nm_social, nr_cpf) VALUES (seq_usuario.nextval, ?, ?, ?, ?, ?)";
//
//	        stmtUsuario = conn.prepareStatement(sqlTUsuario, new String[] { "cd_usuario" });
//	        
//	        // Preencher os valores necessários, exceto o cd_usuario, que será gerado automaticamente
//	        stmtUsuario.setInt(1, novoUsuario.getTipoTelefone().getId());
//	        stmtUsuario.setBigDecimal(2, new BigDecimal(novoUsuario.getTelefone()));
//	        stmtUsuario.setString(3, novoUsuario.getNomeUsuario());
//	        stmtUsuario.setString(4, novoUsuario.getNomeSocial());
//	        stmtUsuario.setString(5, novoUsuario.getCpf());
//
//	        // Executar a inserção na tabela t_usuario
//	        int rowsInsertedUsuario = stmtUsuario.executeUpdate();
//	        
//	        if (rowsInsertedUsuario > 0) {
//            // Obtemos o valor gerado (cd_usuario) usando a sequência seq_usuario
//            String sqlSelectCdUsuario = "SELECT seq_usuario.CURRVAL FROM DUAL";
//            stmtLogin = conn.prepareStatement(sqlSelectCdUsuario);
//            ResultSet generatedKeys = stmtLogin.executeQuery();
//
//            if (generatedKeys.next()) {
//                int cdUsuario = generatedKeys.getInt(1);
//
//                // Usamos o valor gerado como t_usuario_cd_usuario para a tabela t_login
//                String sqlTLogin = "INSERT INTO t_login (t_usuario_cd_usuario, email_login, senha_login) VALUES (?, ?, ?)";
//                stmtLogin = conn.prepareStatement(sqlTLogin);
//                stmtLogin.setInt(1, cdUsuario);
//                stmtLogin.setString(2, login); // Supondo que você tenha um método getEmail() em Cadastro
//                stmtLogin.setString(3, senha); // Supondo que você tenha um método getSenha() em Cadastro
//                stmtLogin.executeUpdate();
//            }
//        }
//	    } catch (SQLException e) {
//	        System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
//	        return false;
//	    } finally {
//	        if (stmtUsuario != null) {
//	            try {
//	                stmtUsuario.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	        if (stmtLogin != null) {
//	            try {
//	                stmtLogin.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	        ConexaoDAO.desconectar(conn);
//	    }
//	    return false;
//	}
}
