package br.edu.fatecpg.consumoApi.view;

import br.edu.fatecpg.consumoApi.service.BuscaEndereco;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        BuscaEndereco bE = new BuscaEndereco();

        String endereco = bE.obterEndereco();

        System.out.println(endereco);
    }
}
