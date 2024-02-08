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
import br.com.fintech.system.model.Receita;
import br.com.fintech.system.model.Usuario;

public class ReceitaDAO {
	private static final String SQL_SELECT_RECEITAS = "SELECT * FROM t_lancamento l inner join t_receita r on r.t_lancamento_cd_lancamento = l.cd_lancamento WHERE l.t_usuario_cd_usuario = ?";
	private static final String SQL_INSERT_RECEITA = "INSERT INTO t_receita (t_lancamento_cd_lancamento, ds_receita, nm_categoria, nm_subcategoria) VALUES (?,?,?,?)";
	private static final String SQL_DELETE_RECEITA = "DELETE FROM t_receita WHERE t_lancamento_cd_lancamento = ?";
	private static final String SQL_UPDATE_RECEITA = "UPDATE t_receita set ds_receita=?, nm_categoria=?, nm_subcategoria=? WHERE t_lancamento_cd_lancamento = ?";
	
	// Método Listar Receita
	public List<Receita> listarReceitas(Usuario usuario) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Receita> receitas = null;
		try {
			conn = ConexaoDAO.conectar();
			stmt = conn.prepareStatement(SQL_SELECT_RECEITAS);
			stmt.setInt(1, usuario.getIdUsuario());
			rs = stmt.executeQuery();
			receitas = new ArrayList<>();
			while (rs.next()) {
				final BigDecimal valorLancamento = rs.getBigDecimal("vl_lancamento");
				final LocalDate dataLancamento =  rs.getDate("dt_lancamento").toLocalDate();
				final int idUsuario = usuario.getIdUsuario();
				final Receita receita = new Receita(idUsuario, valorLancamento, dataLancamento);
				receita.setIdReceita(rs.getInt("cd_receita"));
				receita.setDescricaoReceita(rs.getString("ds_receita"));
				receita.setNomeCategoria(rs.getString("nm_categoria"));
				receita.setNomeSubCategoria(rs.getString("nm_subcategoria"));
				receitas.add(receita);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar receitas: " + e.getMessage());
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
		return receitas;
	}
	
	// Método incluir Receita
	public void incluirReceita(Receita receita) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			final TransacaoDAO transacaoDAO = new TransacaoDAO();
			final int idTransacao = transacaoDAO.incluirTransacao(receita, TipoLancamento.RECEITA);
			conn = ConexaoDAO.conectar();
			stmt = conn.prepareStatement(SQL_INSERT_RECEITA);
			stmt.setInt(1, idTransacao);
			stmt.setString(2, receita.getDescricaoReceita());
			stmt.setString(3, receita.getNomeCategoria());
			stmt.setString(4, receita.getNomeSubCategoria());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao incluir receita: " + e.getMessage());
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
	
	// Método excluir Receita
	public void excluirReceita(int idTransacao) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			final TransacaoDAO transacaoDAO = new TransacaoDAO();
			transacaoDAO.excluirTransacao(idTransacao);
			conn = ConexaoDAO.conectar();
			stmt = conn.prepareStatement(SQL_DELETE_RECEITA);
			stmt.setInt(1, idTransacao);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao excluir receita: " + e.getMessage());
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
	
	// Método alterar Receita
	public void alterarReceita(Receita receita) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			final TransacaoDAO transacaoDAO = new TransacaoDAO();
			transacaoDAO.alterarTransacao(receita, TipoLancamento.RECEITA);
			conn = ConexaoDAO.conectar();
			stmt = conn.prepareStatement(SQL_UPDATE_RECEITA);
			stmt.setString(1, receita.getDescricaoReceita());
			stmt.setString(2, receita.getNomeCategoria());
			stmt.setString(3, receita.getNomeSubCategoria());
			stmt.setInt(4, receita.getIdTransacao());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao alterar receita: " + e.getMessage());
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
