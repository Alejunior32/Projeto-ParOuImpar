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

public class JogadorDois {



    final static int PORTA = 8081;
    final static String HOST = "localhost";


    public static void main(String[] args) {

        try {

            Socket socket = new Socket(HOST,PORTA);
            Metodos metodos = new Metodos();

            ObjectOutputStream saidaJogador = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entradaServidor = new ObjectInputStream(socket.getInputStream());

            Scanner scannerEntrada = new Scanner(System.in);

            ImparOuPar imparOuParJogador1 = (ImparOuPar) entradaServidor.readObject();
            ImparOuPar imparOuParJogador2 = metodos.imparOuParJogador2(imparOuParJogador1);

            System.out.println("Você é o jogador 2 e ficará com " + imparOuParJogador2 + "\n");


            System.out.println("Digite um número entre 0 e 5: ");
            int numero = scannerEntrada.nextInt();

            ConfiguracoesJogador configuracoesJogador = new ConfiguracoesJogador(imparOuParJogador2,numero);
            saidaJogador.writeObject(configuracoesJogador);

            String resultado = (String) entradaServidor.readObject();
            System.out.println(resultado);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
