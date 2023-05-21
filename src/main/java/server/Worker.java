package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Worker extends Thread {

    Socket socket;

    public Worker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());

            System.out.println(entrada.readObject());



        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
