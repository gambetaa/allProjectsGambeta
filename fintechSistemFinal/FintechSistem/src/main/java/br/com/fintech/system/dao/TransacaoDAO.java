package br.com.fintech.system.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import br.com.fintech.system.enumerator.TipoLancamento;
import br.com.fintech.system.model.Transacao;

public class TransacaoDAO {
	private static final String SQL_INSERT_TRANSACAO = "INSERT INTO t_lancamento (t_usuario_cd_usuario, vl_lancamento, dt_lancamento, tp_lancamento) VALUES (?,?,?,?)";
	private static final String SQL_SELECT_MAX_TRANSACAO = "SELECT * FROM t_lancamento WHERE cd_lancamento IN (SELECT DISTINCT MAX(cd_lancamento) FROM t_lancamento WHERE t_usuario_cd_usuario=?)";
	private static final String SQL_DELETE_TRANSACAO = "DELETE FROM t_lancamento WHERE cd_lancamento = ?";
	private static final String SQL_UPDATE_TRANSACAO = "UPDATE t_lancamento set vl_lancamento=?, dt_lancamento=?, tp_lancamento=? WHERE cd_lancamento = ?";
			
	public int incluirTransacao(Transacao transacao, TipoLancamento tipoLancamento) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int idTransacao = 0;
		try {
			conn = ConexaoDAO.conectar();
			stmt = conn.prepareStatement(SQL_INSERT_TRANSACAO);
			stmt.setInt(1, transacao.getIdUsuario());
			stmt.setBigDecimal(2, transacao.getValor());
			stmt.setDate(3, Date.valueOf(transacao.getData()));
			stmt.setString(4, tipoLancamento.name());
			stmt.executeUpdate();
			idTransacao = this.listarUltimaTransacao(transacao.getIdUsuario()).getIdTransacao();
		} catch (SQLException e) {
			System.err.println("Erro ao incluir transacao: " + e.getMessage());
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
		return idTransacao;
	}
	
	public Transacao listarUltimaTransacao(int idUsuario) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Transacao transacao = null;
		try {
			conn = ConexaoDAO.conectar();
			stmt = conn.prepareStatement(SQL_SELECT_MAX_TRANSACAO);
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();
			while (rs.next()) {
				final int idTransacao = rs.getInt("cd_lancamento");
				final BigDecimal valor = rs.getBigDecimal("vl_lancamento");
				final LocalDate data = rs.getDate("dt_lancamento").toLocalDate();
				transacao = new Transacao(idTransacao, idUsuario, valor, data);
			}			
		} catch (SQLException e) {
			System.err.println("Erro ao listar ultima transacao: " + e.getMessage());
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
		return transacao;
	}

	public void excluirTransacao(int idTransacao) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConexaoDAO.conectar();
			stmt = conn.prepareStatement(SQL_DELETE_TRANSACAO);
			stmt.setInt(1, idTransacao);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao excluir transacao: " + e.getMessage());
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

	public void alterarTransacao(Transacao transacao, TipoLancamento tipoLancamento) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConexaoDAO.conectar();
			stmt = conn.prepareStatement(SQL_UPDATE_TRANSACAO);
			stmt.setBigDecimal(1, transacao.getValor());
			stmt.setDate(2, Date.valueOf(transacao.getData()));
			stmt.setString(3, tipoLancamento.name());
			stmt.setInt(4, transacao.getIdTransacao());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao alterar transacao: " + e.getMessage());
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

}
