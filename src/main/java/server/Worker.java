package server;

import util.ConfiguracoesJogador;
import util.ImparOuPar;
import util.Modo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Worker extends Thread {

    Socket socket;
    Socket socketJogador2;

    public Worker(Socket socket,Socket socketJogador2) {
        this.socket = socket;
        this.socketJogador2 = socketJogador2;
    }

    @Override
    public void run() {

        try {

            ObjectInputStream entradaJogador1 = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream saidaJogador1 = new ObjectOutputStream(socket.getOutputStream());

            Modo modoJogo =(Modo) entradaJogador1.readObject();

            if (modoJogo == Modo.JOGADOR_VS_CPU){

                ConfiguracoesJogador configuracoesJogador = (ConfiguracoesJogador) entradaJogador1.readObject();
                String resultado = jogarCpu(configuracoesJogador);
                saidaJogador1.writeObject(resultado);

            }else if(modoJogo == Modo.JOGADOR_Vs_JOGADOR){

                ConfiguracoesJogador configuracoesJogador1 = (ConfiguracoesJogador) entradaJogador1.readObject();

                ObjectInputStream entradaJogador2 = new ObjectInputStream(socketJogador2.getInputStream());
                ObjectOutputStream saidaJogador2 = new ObjectOutputStream(socketJogador2.getOutputStream());

                saidaJogador2.writeObject(configuracoesJogador1.getImparOuPar());

                ConfiguracoesJogador configuracoesJogador2 = (ConfiguracoesJogador) entradaJogador2.readObject();

                String resultado = jogarContraJogador(configuracoesJogador1,configuracoesJogador2);
                saidaJogador1.writeObject(resultado);
                saidaJogador2.writeObject(resultado);
            }
            else {
                saidaJogador1.writeObject("Modo inválido");
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public static String jogarCpu(ConfiguracoesJogador configuracoesJogador) {

        Random random = new Random();
        int numeroCpu = random.nextInt(5);

        int soma = configuracoesJogador.getNumero() + numeroCpu;
        ImparOuPar resultado = (soma % 2 == 0) ? ImparOuPar.PAR : ImparOuPar.IMPAR;

        if (configuracoesJogador.getImparOuPar() == ImparOuPar.ESCOLHA_INVALIDA
                || configuracoesJogador.getNumero() < 0
                || configuracoesJogador.getNumero() > 5)
            return "Informações Inválidas";
        else if (configuracoesJogador.getImparOuPar() == resultado)
            return  "Seu número: " + configuracoesJogador.getNumero() + "\n" +
                    "Número da CPU: " + numeroCpu + "\n"
                    + "Você venceu!";


        return "Seu número: " + configuracoesJogador.getNumero() + "\n" +
                "Número da CPU: " + numeroCpu + "\n"+
                "A CPU venceu!";

    }

    private String jogarContraJogador(ConfiguracoesJogador configuracoesJogador1, ConfiguracoesJogador configuracoesJogador2) {

        int numeroJogador1 = configuracoesJogador1.getNumero();
        int numeroJogador2 = configuracoesJogador2.getNumero();

        int soma = numeroJogador2 + numeroJogador1;

        ImparOuPar resultado = (soma % 2 == 0) ? ImparOuPar.PAR : ImparOuPar.IMPAR;

        if (configuracoesJogador1.getImparOuPar() == ImparOuPar.ESCOLHA_INVALIDA
                || configuracoesJogador1.getNumero() < 0
                || configuracoesJogador1.getNumero() > 5)

            return "Informações Inválidas passadas pelo Jogador 1";

        else if (configuracoesJogador2.getNumero() < 0
                || configuracoesJogador2.getNumero() > 5)

            return "Informações Inválidas passadas pelo Jogador 2";

        else if (configuracoesJogador1.getImparOuPar() == resultado)
            return  "Número Jogador 1: " + configuracoesJogador1.getNumero() + "\n" +
                    "Número Jogador 2: " + configuracoesJogador2.getNumero() + "\n"
                    + "Jogador 1 venceu!";


        return "Número Jogador 1: " + configuracoesJogador1.getNumero() + "\n" +
                "Número Jogador 2: " + configuracoesJogador2.getNumero() + "\n"
                + "Jogador 2 venceu!";
    }
}
