package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    final static int PORTA = 8080;
    final static String HOST = "localhost";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Socket socket = new Socket(HOST,PORTA);

        ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream entradaServidor = new ObjectInputStream(socket.getInputStream());
        Scanner scannerEntrada = new Scanner(System.in);

        System.out.println(entradaServidor.readObject());

        String escolha = scannerEntrada.nextLine();
        saida.writeObject(escolha);

        System.out.println(entradaServidor.readObject());

    }

}
