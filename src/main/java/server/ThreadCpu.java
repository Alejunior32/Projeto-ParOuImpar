package server;

import java.net.Socket;

public class ThreadCpu extends Thread{

    Socket Jogador;

    public ThreadCpu(Socket jogador) {
        Jogador = jogador;
    }
}
