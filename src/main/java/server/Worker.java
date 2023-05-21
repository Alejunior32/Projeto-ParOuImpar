package server;

import util.ConfiguracoesJogador;
import util.ImparOuPar;
import util.Modo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

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

            ConfiguracoesJogador configuracoesJogador = (ConfiguracoesJogador) entrada.readObject();

            if (configuracoesJogador.getModo() == Modo.JOGADOR_VS_CPU){
                String resultado = jogarCpu(configuracoesJogador);
                saida.writeObject(resultado);
            }else if(configuracoesJogador.getModo() == Modo.JOGADOR_Vs_JOGADOR){
                String resultado = jogarJogador(configuracoesJogador);
                saida.writeObject(resultado);
            }
            else {
                saida.writeObject("Modo inválido");
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

    private String jogarJogador(ConfiguracoesJogador configuracoesJogador) {
        return "";
    }
}
