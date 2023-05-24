package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe Servidor responsável por iniciar e gerenciar o servidor do jogo.
 */
public class Servidor {

    /**
     * Porta para conexão com o jogador 1.
     */
    static final int PORTA1 = 8080;



    /**
     * Método principal que inicia o servidor.
     *
     * @param args os argumentos de linha de comando fornecidos quando a aplicação é iniciada.
     * @throws IOException            se ocorrer um erro de I/O ao criar os sockets do servidor.
     */
    public static void main(String[] args) throws IOException{

        ServerSocket serverSocket1;
        serverSocket1 = new ServerSocket(PORTA1);


        // Aguarda as conexões dos jogadores e cria uma thread Worker para cada conexão.
        while(true){
            Socket socketjogador1 = serverSocket1.accept();
            Worker worker = new Worker(socketjogador1);
            worker.start();
        }


    }

}
