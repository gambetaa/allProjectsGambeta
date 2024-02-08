package br.com.fintech.system.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fintech.system.enumerator.TipoTelefone;
import br.com.fintech.system.model.Usuario;

public class EnderecoDAO {

	private static final String SQL_SELECT_USUARIO = "SELECT * FROM t_usuario WHERE cd_usuario=?";

	public Usuario getEndereco(int idUsuario) {
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


//	public boolean cadastrarEndereco(int cdUsuario, String estado, String cidade, String bairro, String rua, int nrEndereco, long cep) {
//	    Connection conn = null;
//	    PreparedStatement stmt = null;
//	    
//	    try {
//	        conn = ConexaoDAO.conectar();
//
//	                // Inserir dados na tabela t_estado
//	                    String sqlTEstado = "INSERT INTO t_estado(cd_estado, nm_estado) VALUES (seq_estado.nextval, ?)";
//	                    stmt = conn.prepareStatement(sqlTEstado);
//	                    stmt.setString(1, estado);
//	                    stmt.executeUpdate();
//
//	                    // Inserir dados na tabela t_cidade
//	                    String sqlTCidade = "INSERT INTO t_cidade (cd_cidade, t_estado_cd_estado, nm_cidade) VALUES (seq_cidade.nextval, seq_estado.CURRVAL, ?)";
//	                    stmt = conn.prepareStatement(sqlTCidade);
//	                    stmt.setString(1, cidade);
//	                    stmt.executeUpdate();
//
//	                    // Inserir dados na tabela t_bairro
//	                    String sqlTBairro = "INSERT INTO t_bairro (cd_bairro, t_cidade_cd_cidade, nm_bairro) VALUES (seq_bairro.nextval, seq_cidade.CURRVAL, ?)";
//	                    stmt = conn.prepareStatement(sqlTBairro);
//	                    stmt.setString(1, bairro);
//	                    stmt.executeUpdate();
//
//	                    // Inserir dados na tabela t_endereco
//	                    String sqlTEndereco = "INSERT INTO t_endereco (cd_endereco, t_usuario_cd_usuario, t_bairro_cd_bairro, nr_cep, nm_rua, nr_endereco, ds_complemento, ds_referencia) VALUES (seq_endereco.nextval, ?, seq_bairro.CURRVAL, ?, ?, ?)";
//	                    stmt = conn.prepareStatement(sqlTEndereco);
//	                    stmt.setInt(1, cdUsuario);
//	                    stmt.setLong(2, cep);
//	                    stmt.setString(3, rua);
//	                    stmt.setInt(4, nrEndereco);
//	                    stmt.executeUpdate();
//
//	                    // Efetivar a transação
//	                    conn.commit();
//
//	    } catch (SQLException e) {
//	        // Rollback em caso de erro
//	        try {
//	            if (conn != null) {
//	                conn.rollback();
//	            }
//	        } catch (SQLException rollbackException) {
//	            rollbackException.printStackTrace();
//	        }
//	        System.err.println("Erro ao inserir cadastro: " + e.getMessage());
//	    } finally {
//	        // Desconectar e fechar recursos
//	        ConexaoDAO.desconectar(conn);
//	    }
//		return false;
//	}
	
	public boolean cadastrarEndereco(int cdUsuario, String estado, String cidade, String bairro, String rua, int nrEndereco, long cep) {
	    String sqlTEstado = "INSERT INTO t_estado(cd_estado, nm_estado) VALUES (seq_estado.nextval, ?)";
	    String sqlTCidade = "INSERT INTO t_cidade (cd_cidade, t_estado_cd_estado, nm_cidade) VALUES (seq_cidade.nextval, seq_estado.CURRVAL, ?)";
	    String sqlTBairro = "INSERT INTO t_bairro (cd_bairro, t_cidade_cd_cidade, nm_bairro) VALUES (seq_bairro.nextval, seq_cidade.CURRVAL, ?)";
	    String sqlTEndereco = "INSERT INTO t_endereco (cd_endereco, t_usuario_cd_usuario, t_bairro_cd_bairro, nr_cep, nm_rua, nr_endereco, ds_complemento, ds_referencia) VALUES (seq_endereco.nextval, ?, seq_bairro.CURRVAL, ?, ?, ?, ?, ?)";

	    try (Connection conn = ConexaoDAO.conectar()) {
	        conn.setAutoCommit(false);

	        try (PreparedStatement stmtEstado = conn.prepareStatement(sqlTEstado);
	             PreparedStatement stmtCidade = conn.prepareStatement(sqlTCidade);
	             PreparedStatement stmtBairro = conn.prepareStatement(sqlTBairro);
	             PreparedStatement stmtEndereco = conn.prepareStatement(sqlTEndereco)) {

	        	stmtEstado.setString(1, estado);
	        	stmtEstado.executeUpdate();

                stmtCidade.setString(1, cidade);
                stmtCidade.executeUpdate();

                stmtBairro.setString(1, bairro);
                stmtBairro.executeUpdate();

                stmtEndereco.setInt(1, cdUsuario);
                stmtEndereco.setLong(2, cep);
                stmtEndereco.setString(3, rua);
                stmtEndereco.setInt(4, nrEndereco);
                stmtEndereco.executeUpdate();

	            conn.commit();
	            
	            return true;
	        } catch (SQLException e) {
	            conn.rollback(); // Rollback em caso de exceção
	            System.err.println("Erro durante a transação: " + e.getMessage());
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
	    }
	    return false;
	}
}

//
//// Inserir dados na tabela t_estado
//if (rowsInsertedLogin > 0) {
//    String sqlTEstado = "INSERT INTO t_estado(cd_estado, nm_estado) VALUES (seq_estado.nextval, ?)";
//    stmt = conn.prepareStatement(sqlTEstado);
//    stmt.setString(1, novoUsuario.getEstado());
//    stmt.executeUpdate();
//
//    // Inserir dados na tabela t_cidade
//    String sqlTCidade = "INSERT INTO t_cidade (cd_cidade, t_estado_cd_estado, nm_cidade) VALUES (seq_cidade.nextval, seq_estado.CURRVAL, ?)";
//    stmt = conn.prepareStatement(sqlTCidade);
//    stmt.setString(1, novoUsuario.getCidade());
//    stmt.executeUpdate();
//
//    // Inserir dados na tabela t_bairro
//    String sqlTBairro = "INSERT INTO t_bairro (cd_bairro, t_cidade_cd_cidade, nm_bairro) VALUES (seq_bairro.nextval, seq_cidade.CURRVAL, ?)";
//    stmt = conn.prepareStatement(sqlTBairro);
//    stmt.setString(1, novoUsuario.getBairro());
//    stmt.executeUpdate();
//
//    // Inserir dados na tabela t_endereco
//    String sqlTEndereco = "INSERT INTO t_endereco (cd_endereco, t_usuario_cd_usuario, t_bairro_cd_bairro, nr_cep, nm_rua, nr_endereco, ds_complemento, ds_referencia) VALUES (seq_endereco.nextval, ?, seq_bairro.CURRVAL, ?, ?, ?, ?, ?)";
//    stmt = conn.prepareStatement(sqlTEndereco);
//    stmt.setInt(1, cdUsuario);
//    stmt.setInt(2, novoUsuario.getCep().intValue());
//    stmt.setString(3, novoUsuario.getRua());
//    stmt.setInt(4, novoUsuario.getNumeroCasa().intValue());
//    stmt.setString(5, novoUsuario.getComplemento());
//    stmt.setString(6, novoUsuario.getReferencia());
//    stmt.executeUpdate();
//

