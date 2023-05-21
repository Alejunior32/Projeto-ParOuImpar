package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    static final int PORTA = 8080;
    static final String MENU_ESCOLHA = """
            Escolha a modalidades de jogo:
            
            1. Jogador Vs CPU
            2. Jogador Vs Jogador""";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket;
        serverSocket = new ServerSocket(PORTA);

        while(true){

            Socket socketJogador1 = serverSocket.accept();

            ObjectInputStream respostaCliente = new ObjectInputStream(socketJogador1.getInputStream());
            ObjectOutputStream mensagemDeSaida = new ObjectOutputStream(socketJogador1.getOutputStream());

            mensagemDeSaida.writeObject(MENU_ESCOLHA);
            String escolha = (String) respostaCliente.readObject();

            if (escolha.equals("1")) {

                mensagemDeSaida.writeObject("Modo de jogo 1 selecionado.");
                ThreadCpu threadCpu = new ThreadCpu(socketJogador1);
                threadCpu.start();

            } else if (escolha.equals("2")) {

                mensagemDeSaida.writeObject("Modo de jogo 2 selecionado.");
                mensagemDeSaida.writeObject("Esperando 2° Jogador ...");
                Socket socketJogador2 = serverSocket.accept();
                ThreadJogador threadJogador = new ThreadJogador(socketJogador1,socketJogador2);
                threadJogador.start();

            } else {
                mensagemDeSaida.writeObject("Resposta inválida.");
                socketJogador1.close();
            }


        }


    }

}
