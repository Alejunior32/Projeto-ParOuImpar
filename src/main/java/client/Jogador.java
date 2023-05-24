package client;

import util.ConfiguracoesJogador;
import util.ImparOuPar;
import util.Metodos;
import util.Modo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Classe que representa o cliente do jogo.
 */
public class Jogador{

    /**
     * Porta do servidor.
     */
    final static int PORTA = 8080;
    /**
     * Endereço do servidor.
     */
    final static String HOST = "localhost";

    /**
     * Método principal que inicia o cliente do jogo.
     *
     * @param args Argumentos de linha de comando.
     */
    public static void main(String[] args) {

        try {

            Socket socket = new Socket(HOST,PORTA);
            Metodos metodos = new Metodos();

            ObjectOutputStream saidaJogador = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entradaServidor = new ObjectInputStream(socket.getInputStream());

            Scanner scannerEntrada = new Scanner(System.in);

            System.out.println("""
                Escolha a modalidades de jogo:
            
                1. Jogador Vs CPU
                2. Jogador Vs Jogador
                 """);

            Modo modoJogo  = metodos.escolhaModoJogo(scannerEntrada);
            saidaJogador.writeObject(modoJogo);

            System.out.println("Você é o jogador 1" + "\n");

            System.out.println("""
                    Escolha entre Ímpar ou Par:
                    
                    1. Ímpar
                    2. Par
                    """);

            ImparOuPar imparOuPar = metodos.escolherImparOuPar(scannerEntrada);

            System.out.println("Digite um número entre 0 e 5: "+ "\n");
            int numero = scannerEntrada.nextInt();

            ConfiguracoesJogador configuracoesJogador = new ConfiguracoesJogador(imparOuPar,numero);
            saidaJogador.writeObject(configuracoesJogador);

            System.out.println("Esperando jogador 2 escolher um número...");

            String resultado = (String) entradaServidor.readObject();
            System.out.println(resultado);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



}
