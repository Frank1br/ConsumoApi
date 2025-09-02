package br.edu.fatecpg.consumoApi.controller;
import java.sql.PreparedStatement;
import br.edu.fatecpg.consumoApi.model.Banco;


public class BancoController {
    public static void insertEndereco(String cep, String rua, String bairro, String cidade, String estado) {
        String sql = "INSERT INTO tb_endereco (cep, rua, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = Banco.conectar().prepareStatement(sql)) {
            stmt.setString(1, cep);
            stmt.setString(2, rua);
            stmt.setString(3, bairro);
            stmt.setString(4, cidade);
            stmt.setString(5, estado);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
