package br.edu.fatecpg.consumoApi.view;

import br.edu.fatecpg.consumoApi.model.Endereco;
import br.edu.fatecpg.consumoApi.service.BuscaEndereco;
import com.google.gson.Gson;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        BuscaEndereco bE = new BuscaEndereco();
        Gson gson = new Gson();
        String endereco = bE.obterEndereco();

        Endereco novoEndereco = gson.fromJson(endereco, Endereco.class);
        System.out.println(endereco);
        System.out.println(novoEndereco);
    }
}
