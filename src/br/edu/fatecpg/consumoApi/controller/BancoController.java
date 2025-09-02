package br.edu.fatecpg.consumoApi.controller;
import java.sql.PreparedStatement;
import br.edu.fatecpg.consumoApi.model.Banco;


public class BancoController {
    public static boolean insertEndereco(String cep, String rua, String bairro, String cidade, String estado) {
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
        return false;
    }

    public static void mostrarEnderecos() {
        String sql = "SELECT * FROM tb_endereco";
        try (var conexao = Banco.conectar();
             var stmt = conexao.prepareStatement(sql);
             var rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("CEP: " + rs.getString("cep"));
                System.out.println("Rua: " + rs.getString("rua"));
                System.out.println("Bairro: " + rs.getString("bairro"));
                System.out.println("Cidade: " + rs.getString("cidade"));
                System.out.println("Estado: " + rs.getString("estado"));
                System.out.println("---------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
