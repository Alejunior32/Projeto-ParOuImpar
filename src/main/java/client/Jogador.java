package client;

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
            saidaJogador.writeObject(modoJogo);

//            System.out.println("""
//            Escolha Impar ou Par:
//
//            1. Impar
//            2. Par
//
//            """);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
