package br.edu.fatecpg.consumoApi.view;

import br.edu.fatecpg.consumoApi.controller.BancoController;
import br.edu.fatecpg.consumoApi.model.Endereco;
import br.edu.fatecpg.consumoApi.service.BuscaEndereco;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            exibirMenu();
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha pendente do nextInt()

                switch (opcao) {
                    case 1:
                        buscarNovoEndereco(scanner);
                        break;
                    case 2:
                        BancoController.mostrarEnderecos();
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
                scanner.nextLine(); // Limpa o buffer do scanner
            } catch (Exception e) {
                System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
            }

            if(running) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        }

        scanner.close();
        System.out.println("Programa encerrado.");
    }

    private static void exibirMenu() {
        System.out.println("\n===== MENU DE OPÇÕES =====");
        System.out.println("1 - Buscar Endereço por CEP");
        System.out.println("2 - Mostrar todos os endereços salvos");
        System.out.println("3 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void buscarNovoEndereco(Scanner scanner) throws IOException, InterruptedException {
        System.out.print("Digite um CEP: ");
        String cep = scanner.nextLine();

        BuscaEndereco buscaEndereco = new BuscaEndereco();
        String json = buscaEndereco.obterEndereco(cep);

        Gson gson = new Gson();
        Endereco endereco = gson.fromJson(json, Endereco.class);

        if (endereco != null && endereco.getCep() != null) {
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
                boolean sucesso = BancoController.insertEndereco(
                        endereco.getCep(),
                        endereco.getRua(),
                        endereco.getBairro(),
                        endereco.getLocalidade(),
                        endereco.getEstado()
                );
                if (sucesso) {
                    System.out.println("Endereço salvo com sucesso!");
                } else {
                    System.out.println("Falha ao salvar o endereço.");
                }
            } else {
                System.out.println("Endereço não salvo.");
            }
        } else {
            System.out.println("CEP não encontrado ou inválido.");
        }
    }
}