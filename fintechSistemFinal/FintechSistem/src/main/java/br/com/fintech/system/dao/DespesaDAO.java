package br.com.fintech.system.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.system.enumerator.TipoLancamento;
import br.com.fintech.system.model.Despesa;
import br.com.fintech.system.model.Usuario;

public class DespesaDAO {
	private static final String SQL_SELECT_DESPESAS = "SELECT * FROM t_lancamento l inner join t_gasto g on g.t_lancamento_cd_lancamento = l.cd_lancamento WHERE l.t_usuario_cd_usuario = ?";
	private static final String SQL_INSERT_DESPESA = "INSERT INTO t_gasto (t_lancamento_cd_lancamento, ds_gasto, nm_categoria, nm_subcategoria) VALUES (?,?,?,?)";
	private static final String SQL_DELETE_DESPESA = "DELETE FROM t_gasto WHERE t_lancamento_cd_lancamento = ?";
	private static final String SQL_UPDATE_DESPESA = "UPDATE t_gasto set ds_gasto=?, nm_categoria=?, nm_subcategoria=? WHERE t_lancamento_cd_lancamento = ?";
	
	public List<Despesa> listarDespesas(Usuario usuario) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Despesa> despesas = null;
		try {
			conn = ConexaoDAO.conectar();
			stmt = conn.prepareStatement(SQL_SELECT_DESPESAS);
			stmt.setInt(1, usuario.getIdUsuario());
			rs = stmt.executeQuery();
			despesas = new ArrayList<>();
			while (rs.next()) {
				final BigDecimal valorLancamento = rs.getBigDecimal("vl_lancamento");
				final LocalDate dataLancamento =  rs.getDate("dt_lancamento").toLocalDate();
				final int idUsuario = usuario.getIdUsuario();
				final Despesa despesa = new Despesa(idUsuario, valorLancamento, dataLancamento);
				despesa.setIdDespesa(rs.getInt("cd_gasto"));
				despesa.setDescricaoDespesa(rs.getString("ds_gasto"));
				despesa.setNomeCategoria(rs.getString("nm_categoria"));
				despesa.setNomeSubCategoria(rs.getString("nm_subcategoria"));
				despesas.add(despesa);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar despesas: " + e.getMessage());
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
		return despesas;
	}

	public void incluirDespesa(Despesa despesa) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			final TransacaoDAO transacaoDAO = new TransacaoDAO();
			final int idTransacao = transacaoDAO.incluirTransacao(despesa, TipoLancamento.DESPESA);
			conn = ConexaoDAO.conectar();
			stmt = conn.prepareStatement(SQL_INSERT_DESPESA);
			stmt.setInt(1, idTransacao);
			stmt.setString(2, despesa.getDescricaoDespesa());
			stmt.setString(3, despesa.getNomeCategoria());
			stmt.setString(4, despesa.getNomeSubCategoria());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao incluir despesa: " + e.getMessage());
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

	public void excluirDespesa(int idTransacao) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			final TransacaoDAO transacaoDAO = new TransacaoDAO();
			transacaoDAO.excluirTransacao(idTransacao);
			conn = ConexaoDAO.conectar();
			stmt = conn.prepareStatement(SQL_DELETE_DESPESA);
			stmt.setInt(1, idTransacao);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao excluir despesa: " + e.getMessage());
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

	public void alterarDespesa(Despesa despesa) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			final TransacaoDAO transacaoDAO = new TransacaoDAO();
			transacaoDAO.alterarTransacao(despesa, TipoLancamento.DESPESA);
			conn = ConexaoDAO.conectar();
			stmt = conn.prepareStatement(SQL_UPDATE_DESPESA);
			stmt.setString(1, despesa.getDescricaoDespesa());
			stmt.setString(2, despesa.getNomeCategoria());
			stmt.setString(3, despesa.getNomeSubCategoria());
			stmt.setInt(4, despesa.getIdTransacao());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao alterar despesa: " + e.getMessage());
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
