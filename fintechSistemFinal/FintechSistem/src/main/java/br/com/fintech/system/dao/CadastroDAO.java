package br.com.fintech.system.dao;

public class CadastroDAO {
//				
//		public ArrayList<Cadastro> getTUsuarios() {
//		    Connection conn = null;
//		    PreparedStatement stmt = null;
//		    ResultSet rs = null;
//
//		    ArrayList<Cadastro> usuarios = new ArrayList<>();
//		    
//		    try {
//		        conn = ConexaoDAO.conectar();
//		        String sql = "SELECT * FROM t_usuario";
//		        stmt = conn.prepareStatement(sql);
//		        rs = stmt.executeQuery();
//
//		        while (rs.next()) {
//		            Cadastro usuario = new Cadastro();
//		            usuario.setCdUsuario(rs.getInt("cd_usuario"));
//		            usuario.setTipoTelefone(rs.getInt("t_tipo_tel_cd_tipo_tel"));
//		            usuario.setTelefone(rs.getString("nr_telefone"));
//		            usuario.setNome(rs.getString("nm_usuario"));
//		            usuario.setNomeSocial(rs.getString("nm_social"));
//		            usuario.setCpf(rs.getString("nr_cpf"));
//		            usuarios.add(usuario);
//		        }
//		    } catch (SQLException e) {
//		        System.err.println("Erro ao listar usuários: " + e.getMessage());
//		    } finally {
//		        if (rs != null) {
//		            try {
//		                rs.close();
//		            } catch (SQLException e) {
//		                e.printStackTrace();
//		            }
//		        }
//		        if (stmt != null) {
//		            try {
//		                stmt.close();
//		            } catch (SQLException e) {
//		                e.printStackTrace();
//		            }
//		        }
//		        ConexaoDAO.desconectar(conn);
//		    }
//
//		    return usuarios;
//		}
//
//		public void postTUsuarios(Cadastro novoUsuario) {
//		    
//			Connection conn = null;
//		    PreparedStatement stmt = null;
//		    
//			try {
//				conn = ConexaoDAO.conectar();
//				String sqlTUsuario = "INSERT INTO t_usuario (cd_usuario, t_tipo_tel_cd_tipo_tel, nr_telefone, nm_usuario, nm_social, nr_cpf) VALUES (seq_usuario.nextval, ?, ?, ?, ?, ?)";
//				stmt = conn.prepareStatement(sqlTUsuario);
//				
//			    stmt.setInt(1, novoUsuario.getTipoTelefone().intValue());
//			    stmt.setBigDecimal(2, new BigDecimal(novoUsuario.getTelefone()));
//			    stmt.setString(3, novoUsuario.getNome());
//			    stmt.setString(4, novoUsuario.getNomeSocial());
//			    stmt.setString(5, novoUsuario.getCpf());
////		        stmt.setString(5, novoUsuario.getEndereco());
////		        stmt.setInt(8, (int) novoUsuario.getTipoTelefone());
////		        stmt.executeUpdate();
//		        
//		        int rowsInserted = stmt.executeUpdate();
//
//		        if (rowsInserted > 0) {
//		            // Obtemos o valor gerado (cd_usuario) usando a sequência seq_usuario
//		            String sqlSelectCdUsuario = "SELECT seq_usuario.CURRVAL FROM DUAL";
//		            stmt = conn.prepareStatement(sqlSelectCdUsuario);
//		            ResultSet generatedKeys = stmt.executeQuery();
//
//		            if (generatedKeys.next()) {
//		                int cdUsuario = generatedKeys.getInt(1);
//
//		                // Usamos o valor gerado como t_usuario_cd_usuario para a tabela t_login
//		                String sqlTLogin = "INSERT INTO t_login (t_usuario_cd_usuario, email_login, senha_login) VALUES (?, ?, ?)";
//		                stmt = conn.prepareStatement(sqlTLogin);
//		                stmt.setInt(1, cdUsuario);
//		                stmt.setString(2, novoUsuario.getEmail()); // Supondo que você tenha um método getEmail() em Cadastro
//		                stmt.setString(3, novoUsuario.getSenha()); // Supondo que você tenha um método getSenha() em Cadastro
//		                stmt.executeUpdate();
//		            }
//		        }
//		        
//		        
//	        } catch (SQLException e) {
//	            System.err.println("Erro ao inserir cadastro: " + e.getMessage());
//	        }finally {
//				conn = null;
//			    stmt = null;
//				ConexaoDAO.desconectar(conn);
//			}
//			
//		}
//
//		public void updateUsuario(Cadastro usuario) {
//			
//			Connection conn = null;
//		    PreparedStatement stmt = null;
//		    
//			try {
//				conn = ConexaoDAO.conectar();
//				String sql = "UPDATE t_usuario SET (cd_usuario = ?, t_tipo_tel_cd_tipo_tel = ?, nr_telefone = ?, nm_usuario = ?, nm_social = ?, nr_cpf = ?) WHERE nr_cpf = ?";
//				
//				stmt = conn.prepareStatement(sql);
//				
//				System.out.println("Qual informação deseja atualizar?");
//				
//			    stmt.setInt(1, usuario.getTipoTelefone().intValue());
//			    stmt.setBigDecimal(2, new BigDecimal(usuario.getTelefone()));
//			    stmt.setString(3, usuario.getNome());
//			    stmt.setString(4, usuario.getNomeSocial());
//			    stmt.setString(6, usuario.getCpf());
////		        stmt.setString(3, novoUsuario.getEmail());
////		        stmt.setString(4, novoUsuario.getSenha());
////		        stmt.setString(5, novoUsuario.getEndereco());
////		        stmt.setInt(8, (int) novoUsuario.getTipoTelefone());
//		        stmt.executeUpdate();
//		        
//	        } catch (SQLException e) {
//	            System.err.println("Erro ao inserir cadastro: " + e.getMessage());
//	        }finally {
//				ConexaoDAO.desconectar(conn);
//			}
//		}
//		
//		public void deleteUsuario(Cadastro usuario) {
//			
//		}
		
}
