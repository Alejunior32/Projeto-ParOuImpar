package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static server.ImparOuPar.jogar;
import static server.Utils.parseToInt;

public class ThreadCpu extends Thread{

    private ObjectOutputStream mensagemDeSaida;
    private ObjectInputStream respostaCliente;
    private Socket socketCliente;

    public ThreadCpu(ObjectOutputStream mensagemDeSaida, ObjectInputStream respostaCliente, Socket socketCliente) {
        this.mensagemDeSaida = mensagemDeSaida;
        this.respostaCliente = respostaCliente;
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {
        String numero = null;
        String escolhaCliente = null;
        try {
            numero = escolherNumero();
            escolhaCliente = escolherImparOuPar();
            String resultado = jogar(numero, escolhaCliente);
            mensagemDeSaida.writeObject(resultado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String escolherNumero () throws Exception {
        String resposta;
        try {
            mensagemDeSaida.writeObject("Digite um número entre 0 e 5: ");
            resposta = (String) respostaCliente.readObject();
            int numero = parseToInt(resposta);
            if (numero < 0 || numero > 5) {
                mensagemDeSaida.writeObject("Número Inválido!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resposta;
    }

    private String escolherImparOuPar() {
        String resposta;
        try {
            mensagemDeSaida.writeObject(
                    "Escolha entre Ímpar ou Par:" + "\n"
                            + "1. Ímpar" + "\n"
                            + "2. Par ");
            resposta = (String) respostaCliente.readObject();
            if (resposta.equalsIgnoreCase("1")) {
                mensagemDeSaida.writeObject("Você selecionou Ímpar!");
                return "Ímpar";
            } else if (resposta.equalsIgnoreCase("2")) {
                mensagemDeSaida.writeObject("Você selecionou Par!");
                return "Par";
            } else {
                mensagemDeSaida.writeObject("Resposta Inválida");
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}