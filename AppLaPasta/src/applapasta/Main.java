package applapasta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Variáveis auxiliares
        Scanner teclado = new Scanner(System.in);   // Entrada do usuário
        int op = -1;                                // Opção escolhida no menu

        System.out.println("\t\tBem vindo ao La Pasta Calabreza! (Delivery)\n");

        // Menu principal
        do {
            // Opções
            System.out.println("1 - Cadastrar novo prato");
            System.out.println("2 - Cardápio");
            System.out.println("3 - Sair");
            System.out.print(">> ");

            // Pega o valor que o usuário digitou
            op = teclado.nextInt();

            // De acordo com a entrada do usuário, faz as ações
            switch (op) {
                // 1 - Cadastrar um novo prato
                case 1:
                    // Pra não bugar a entrada de uma string após ler um int
                    teclado.nextLine();

                    // Entrada pelo usuário
                    // Nome
                    System.out.print("Nome do Prato: ");
                    String nome = teclado.nextLine();

                    // Preço
                    System.out.print("Preço: R$");
                    float preco = teclado.nextFloat();

                    // Quantidade de pessoas servidas
                    System.out.print("Servem quantas pessoas? ");
                    int numPessoas = teclado.nextInt();

                    // Cria o prato
                    Prato p = new Prato();

                    // Atribui os valores
                    p.setNome(nome);
                    p.setPreco(preco);
                    p.setNumPessoasServidas(numPessoas);

                    // Salva no Arquivo
                    OutputStream fluxoSaida;                // Fluxo de Saida de dados
                    OutputStreamWriter geradorFluxoSaida;   // Gerador do Fluxo de Saida
                    BufferedWriter bufferSaida = null;      // Buffer da saida

                    try {
                        fluxoSaida = new FileOutputStream("la_pasta.txt", true);// Cria o arquivo "la_pasta.txt" na pasta do projeto
                        geradorFluxoSaida = new OutputStreamWriter(fluxoSaida); // Todo o fluxo de dados será armazenado neste arquivo
                        bufferSaida = new BufferedWriter(geradorFluxoSaida);    // Buffer para o gerador de fluxo

                        bufferSaida.write(p.toString());     // Escreve os dados do prato
                        bufferSaida.newLine();               // Insere uma nova linha no arquivo

                        // Tratamento de Erros
                    } catch (FileNotFoundException ex) {
                        System.err.println("Arquivo não encontrado!");
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        System.out.println("IOException");
                        ex.printStackTrace();
                    } finally {

                        // Sempre fechar o arquivo após ler/gravar !!
                        try {
                            bufferSaida.close();    // Fecha o arquivo
                        } catch (IOException ex) {
                            System.err.println("Deu ruim ao fechar o arquivo");
                            ex.printStackTrace();
                        }
                    }

                    // Se tudo deu certo, mostra as informações do prato
                    System.out.println("\nCadastrado!");
                    p.mostrarInfo();

                    break;

                // 2 - Listar pratos cadastrados
                case 2:
                    // Lê os dados do Arquivo
                    InputStream fluxoEntrada;               // Fluxo de entrada
                    InputStreamReader leitorFluxoEntrada;   // Leitor do fluxo de entrada
                    BufferedReader bufferEntrada = null;    // Buffer da entrada
                    String linha;                           // Variavel auxiliar para armazenar o conteudo da linha

                    try {
                        fluxoEntrada = new FileInputStream("la_pasta.txt");         // Abre o arquivo "la_pasta.txt"
                        leitorFluxoEntrada = new InputStreamReader(fluxoEntrada);   // Faz a leitura do arquivo
                        bufferEntrada = new BufferedReader(leitorFluxoEntrada);     // Buffer de entrada
                        linha = bufferEntrada.readLine();                           // Lê a linha e armazena na variavel auxiliar

                        System.out.println("\t Lista de Pratos Cadastrados:");

                        while (linha != null) {                 // Enquanto não chegar no final do arquivo
                            System.out.println(linha);          // Printa a linha
                            linha = bufferEntrada.readLine();   // Pula para a próxima linha
                        }

                        // Tratamento de Erros
                    } catch (Exception ex) {
                        System.out.println("Você ainda não cadastrou nenhum prato!!");
                    } finally {
                        // Sempre fechar o arquivo após ler/gravar !!
                        try {
                            bufferEntrada.close();      // Fecha o arquivo
                        } catch (Exception ex) {
                            System.out.println("");
                        }
                    }

                    break;

                // 3 - Sair
                case 3:
                    op = 3;
                    System.out.println("Bom apetite!");
                    break;
                default:
                    System.out.println("Entrada inválida!\n");
            }

        } while (op != 3);

    }

}
