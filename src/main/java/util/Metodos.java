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