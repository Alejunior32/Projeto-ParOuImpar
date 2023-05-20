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

            Socket socketCliente = serverSocket.accept();

            ObjectInputStream respostaCliente = new ObjectInputStream(socketCliente.getInputStream());
            ObjectOutputStream mensagemDeSaida = new ObjectOutputStream(socketCliente.getOutputStream());

            mensagemDeSaida.writeObject(MENU_ESCOLHA);
            String escolha = (String) respostaCliente.readObject();

            ThreadCpu threadCpu = new ThreadCpu();
            ThreadJogador threadJogador = new ThreadJogador();

            if (escolha.equals("1")) {
                mensagemDeSaida.writeObject("Modo de jogo 1 selecionado.");
                threadCpu.start();
            } else if (escolha.equals("2")) {
                mensagemDeSaida.writeObject("Modo de jogo 2 selecionado.");
                threadJogador.start();
            } else {
                mensagemDeSaida.writeObject("Resposta inválida.");
            }


        }


    }

}
