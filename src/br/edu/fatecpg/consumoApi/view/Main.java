package br.edu.fatecpg.consumoApi.view;

import br.edu.fatecpg.consumoApi.controller.BancoController;
import br.edu.fatecpg.consumoApi.model.Endereco;
import br.edu.fatecpg.consumoApi.service.BuscaEndereco;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Digite um CEP (ou 'sair' para encerrar): ");
            String cep = scanner.nextLine();

            if (cep.equalsIgnoreCase("sair")) {
                break;
            }

            BuscaEndereco buscaEndereco = new BuscaEndereco();
            String json = buscaEndereco.obterEndereco(cep);

            Gson gson = new Gson();
            Endereco endereco = gson.fromJson(json, Endereco.class);

            if (endereco.getCep() != null) {
                System.out.println("\n--- Endereço Encontrado ---");
                System.out.println("CEP: " + endereco.getCep());
                System.out.println("Rua: " + endereco.getRua());
                System.out.println("Bairro: " + endereco.getBairro());
                System.out.println("Cidade: " + endereco.getLocalidade());
                System.out.println("Estado: " + endereco.getEstado());
                System.out.println("--------------------------\n");

                System.out.print("Deseja salvar no banco? (sim/não): ");
                String resposta = scanner.nextLine();

                if (resposta.equalsIgnoreCase("sim")) {
                    System.out.println("Salvando endereço no banco...");
                    BancoController.insertEndereco(endereco.getCep(), endereco.getRua(), endereco.getBairro(), endereco.getLocalidade(), endereco.getEstado());
                    System.out.println("Endereço salvo com sucesso!");
                } else {
                    System.out.println("Endereço não salvo.");
                }


            } else {
                System.out.println("CEP não encontrado.");
            }

            System.out.println();
        }

        scanner.close();
        System.out.println("Programa encerrado.");

    }
}
