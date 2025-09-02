package br.edu.fatecpg.consumoApi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
    private static final String url = "jdbc:postgresql://localhost:5433/db_endereco";
    private static final String user = "postgres";
    private static final String password = "12345";


    public static Connection conectar() throws SQLException {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw e;
        } catch (Exception e ) {
            throw new SQLException();
        }
    }
}