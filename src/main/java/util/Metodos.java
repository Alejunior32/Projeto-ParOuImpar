package util;

import java.util.Scanner;

public class Metodos {

    public Modo escolhaModoJogo(Scanner scanner){

        String escolha = scanner.nextLine();
        if (escolha.equals("1"))
            return Modo.JOGADOR_VS_CPU;
        else if (escolha.equals("2"))
            return Modo.JOGADOR_Vs_JOGADOR;

        return Modo.ESCOLHA_INVALIDA;
    }


    public ImparOuPar escolherImparOuPar(Scanner scanner) {

        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("1"))
            return ImparOuPar.IMPAR;
        else if (resposta.equalsIgnoreCase("2"))
            return ImparOuPar.PAR;

        return ImparOuPar.ESCOLHA_INVALIDA;
    }

    public ImparOuPar imparOuParJogador2(ImparOuPar imparOuParJogador1){

        if (imparOuParJogador1 == ImparOuPar.IMPAR)
            return ImparOuPar.PAR;

        return ImparOuPar.IMPAR;

    }



    public static int parseToInt (String string) throws Exception {
        int numeroConvertido;
        try {
            numeroConvertido = Integer.parseInt(string);
        } catch (Exception exception) {
            throw new Exception();
        }
        return numeroConvertido;
    }

}
