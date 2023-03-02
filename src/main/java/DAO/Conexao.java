package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao implements BancoDados {
    public static Connection getConnection() throws SQLException {
        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            return conexao;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw e;
        }
    }

}
