package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadJogador extends Thread {

    Socket jogador1;
    Socket jogador2;




    public void escolhaImparOuPar(ObjectOutputStream saidaJogador1,ObjectInputStream entradaJogador1,
                                  ObjectOutputStream saidaJogador2) throws IOException, ClassNotFoundException {

        saidaJogador2.writeObject("Você é o Jogador 2, esperando o Jogador 1 escolher ...");
        saidaJogador1.writeObject("""
                    
                    Você é o Jogador 1, por isso escolhe primeiro.
                    
                    impar ou par?
                    
                    1.Impar
                    2.Par
                    
                    """);

        if (entradaJogador1.readObject().equals(1)) {
            saidaJogador2.writeObject("Jogador 1 escolheu Impar, você ficou com Par. ");
            saidaJogador1.writeObject("Você escolheu Impar");
        }else if (entradaJogador1.readObject().equals(2)) {
            saidaJogador2.writeObject(" Jogador 1 escolheu Par, você ficou com Impar.");
            saidaJogador1.writeObject(" Você escolheu Par");
        }else{
            saidaJogador1.writeObject(" Resposta inválida. ");
            escolhaImparOuPar(saidaJogador1,entradaJogador1,saidaJogador2);
        }
    }

    @Override
    public void run() {

        try {

            ObjectInputStream entradaJogador1 = new ObjectInputStream(jogador1.getInputStream());
            ObjectOutputStream saidaJogador1 = new ObjectOutputStream(jogador1.getOutputStream());

            ObjectInputStream entradaJogador2 = new ObjectInputStream(jogador2.getInputStream());
            ObjectOutputStream saidaJogador2 = new ObjectOutputStream(jogador2.getOutputStream());

            System.out.println("chegou");
            escolhaImparOuPar(saidaJogador1,entradaJogador1,saidaJogador2);


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
