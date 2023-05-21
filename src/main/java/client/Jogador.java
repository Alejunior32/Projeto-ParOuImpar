package client;

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

            ObjectOutputStream saidaJogador = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entradaServidor = new ObjectInputStream(socket.getInputStream());
            Scanner scannerEntrada = new Scanner(System.in);

            System.out.println(entradaServidor.readObject());

            String escolha = scannerEntrada.nextLine();
            saidaJogador.writeObject(escolha);

            System.out.println(entradaServidor.readObject());
            System.out.println(entradaServidor.readObject());

            escolhaParOuImpar(entradaServidor,saidaJogador,scannerEntrada);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void escolhaParOuImpar(ObjectInputStream entradaServidor,ObjectOutputStream saidaJogador, Scanner scanner) throws IOException, ClassNotFoundException {

        String entrada = (String) entradaServidor.readObject();
        System.out.println(entrada);

        if (entrada.equals("Você é o Jogador 2, esperando o Jogador 1 escolher ..."))
            System.out.println(entradaServidor.readObject());
        else{
            saidaJogador.writeObject(scanner.nextInt());
            System.out.println(entradaServidor.readObject());
        }

    }


}
