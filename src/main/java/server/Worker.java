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

/**
 * Classe Worker responsável por lidar com as requisições de jogadores no servidor.
 * Cada instância dessa classe é uma thread separada que lida com uma conexão de jogador.
 */
public class Worker extends Thread {

    private final Socket socketJogador1;


    /**
     * Porta para conexão com o jogador 2.
     */
    static final int PORTA2 = 8081;

    /**
     * Construtor da Thread Worker.
     *
     * @param socketJogador1 o Socket de conexão com o jogador 1.
     */
    public Worker(Socket socketJogador1) {
        this.socketJogador1 = socketJogador1;
    }

    /**
     * Método executado pela thread ao iniciar.
     * Responsável por receber as configurações de jogo dos jogadores e executar a lógica do jogo.
     */
    @Override
    public void run() {
        try {
            ObjectInputStream entradaJogador1 = new ObjectInputStream(socketJogador1.getInputStream());
            ObjectOutputStream saidaJogador1 = new ObjectOutputStream(socketJogador1.getOutputStream());

            // Recebe o modo de jogo escolhido pelo jogador 1
            Modo modoJogo = (Modo) entradaJogador1.readObject();

            if (modoJogo == Modo.JOGADOR_VS_CPU) {
                // Modo de jogo: Jogador vs CPU
                ConfiguracoesJogador configuracoesJogador = (ConfiguracoesJogador) entradaJogador1.readObject();
                String resultado = jogarCpu(configuracoesJogador);
                saidaJogador1.writeObject(resultado);
            } else if (modoJogo == Modo.JOGADOR_Vs_JOGADOR) {

                // Modo de jogo: Jogador vs Jogador

                ServerSocket serverSocket2 = new ServerSocket(PORTA2);
                Socket socketJogador2 = serverSocket2.accept();


                ConfiguracoesJogador configuracoesJogador1 = (ConfiguracoesJogador) entradaJogador1.readObject();

                ObjectInputStream entradaJogador2 = new ObjectInputStream(socketJogador2.getInputStream());
                ObjectOutputStream saidaJogador2 = new ObjectOutputStream(socketJogador2.getOutputStream());

                // Envia a escolha (ímpar ou par) do jogador 1 para o jogador 2
                saidaJogador2.writeObject(configuracoesJogador1.getImparOuPar());

                // Recebe as configurações de jogo do jogador 2
                ConfiguracoesJogador configuracoesJogador2 = (ConfiguracoesJogador) entradaJogador2.readObject();

                String resultado = jogarContraJogador(configuracoesJogador1, configuracoesJogador2);
                saidaJogador1.writeObject(resultado);
                saidaJogador2.writeObject(resultado);
            } else {
                // Modo de jogo inválido
                saidaJogador1.writeObject("Modo inválido");
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Executa a lógica do jogo no modo Jogador vs CPU.
     *
     * @param configuracoesJogador as configurações de jogo do jogador.
     * @return o resultado do jogo.
     */
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

    /**
     * Executa a lógica do jogo no modo Jogador vs Jogador.
     *
     * @param configuracoesJogador1 as configurações de jogo do jogador 1.
     * @param configuracoesJogador2 as configurações de jogo do jogador 2.
     * @return o resultado do jogo.
     */
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
