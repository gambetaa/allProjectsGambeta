package br.com.fintech.system.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ConexaoDAO {
	
	private static final String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
	private static final String usuario = "RM99437";
	private static final String senha = "300600";
	
	public static void getAll() {
		
		List<String> tabelas = new ArrayList<String>();
		tabelas.add("t_usuario");
		tabelas.add("t_bairro");
		tabelas.add("t_cidade");
		tabelas.add("t_estado");
		tabelas.add("t_endereco");
		tabelas.add("t_dado_ficanc");
		tabelas.add("t_gasto");
		tabelas.add("t_receita");
		tabelas.add("t_lancamento");
		tabelas.add("t_login");
		tabelas.add("t_tipo_tel");
		
		System.out.println("TODAS AS ENTIDADES DO BANCO: "+tabelas);
		
		try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
           
			for (String tableName : tabelas) {
                System.out.println("Tabela: " + tableName);
                extractTableData(connection, tableName);
                System.out.println();
            }
        } catch (Exception e) {
			System.out.println("Erro no get all!" + e.getMessage());
        }	
	}
	
    private static void extractTableData(Connection connection, String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM " + tableName;
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        String value = resultSet.getString(i);
                        System.out.println(columnName + ": " + value);
                    }
                }
            }
        }
    }
	
	public static Connection conectar() {
		try {
			return DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException e) {
			System.out.println("Erro na conexão!" + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar ao banco de dados.");
		}
	}
	
	public static void desconectar(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Erro ao desconectar do banco de dados.");
			}
		}
	}
}
