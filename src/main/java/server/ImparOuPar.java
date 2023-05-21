package server;

import java.util.Random;

public class ImparOuPar {

    public static String jogar(String numero, String escolhaCliente) {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(5);

        String numeroCpu = String.valueOf(numeroAleatorio);

        int soma = Integer.parseInt(numero) + Integer.parseInt(numeroCpu);
        String resultado = (soma % 2 == 0) ? "Par" : "Ímpar";

        if (resultado.equals(escolhaCliente)) {
            return "Seu número: " + numero + "\n" +
                    "Número da CPU: " + numeroAleatorio + "\n"
                    + "Soma dos números: " + soma + " / " + resultado + "\n"
                    + "Você venceu!";
        } else {
            return "Seu número: " + numero + "\n" +
                    "Número da CPU: " + numeroAleatorio + "\n"
                    + "Soma dos números: " + soma + " / " + resultado + "\n"
                    + "A CPU venceu!";
        }
    }

}
