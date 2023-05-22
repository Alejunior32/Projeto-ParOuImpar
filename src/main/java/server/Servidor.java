package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

    static final int PORTA1 = 8080;
    static final int PORTA2 = 8081;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket1;
        serverSocket1 = new ServerSocket(PORTA1);
        ServerSocket serverSocket2 = new ServerSocket(PORTA2);


        while(true){
            Socket socketjogador1 = serverSocket1.accept();
            Socket socketJogador2 = serverSocket2.accept();
            Worker worker = new Worker(socketjogador1,socketJogador2);
            worker.start();
        }


    }

}
