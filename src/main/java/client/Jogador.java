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

public class Jogador{

    final static int PORTA = 8080;
    final static String HOST = "localhost";


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

            System.out.println("""
                    Escolha entre Ímpar ou Par:
                    
                    1. Ímpar
                    2. Par
                    """);

            ImparOuPar imparOuPar = metodos.escolherImparOuPar(scannerEntrada);

            System.out.println("Digite um número entre 0 e 5: ");
            int numero = scannerEntrada.nextInt();

            ConfiguracoesJogador configuracoesJogador = new ConfiguracoesJogador(modoJogo,imparOuPar,numero);
            saidaJogador.writeObject(configuracoesJogador);

            String resultado = (String) entradaServidor.readObject();
            System.out.println(resultado);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



}
