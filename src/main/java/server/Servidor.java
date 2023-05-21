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

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket;
        serverSocket = new ServerSocket(PORTA);

        while(true){
            Socket socket = serverSocket.accept();
            Worker worker = new Worker(socket);
            worker.start();
        }


    }

}
